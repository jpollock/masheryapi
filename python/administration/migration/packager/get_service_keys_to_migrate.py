import os, sys, urllib, argparse, time, requests, json, logging, random, string, csv
from lib.base_migrator import BaseMigrator

class GetServiceKeysToMigrate(BaseMigrator):
    def __init__(self):
        BaseMigrator.__init__(self)
    
    def update_key_with_package_data(self, key, apis, plans):
        key_to_migrate = {}
        key_to_migrate['apikey'] = key['apikey']
        key_to_migrate['service_key'] = key['service_key']

        for api in apis:
            if (api['service_key'] == key['service_key']):
                for plan in plans:
                    if (plan['package']['name'] == (api['name'] + '- created for Mashery Packager Migration')):
                        key_to_migrate['package_id'] = plan['package']['id']
                        plan_name = api['name']
                        if (key['developer_class'] != None):
                            plan_name = key['developer_class']['name']

                        if (plan['name'] == plan_name):
                            key_to_migrate['plan_id'] = plan['id']

        return key_to_migrate

def main(argv):
    get_keys_to_migrate = GetServiceKeysToMigrate()
    if (get_keys_to_migrate.migration_environment.valid() == False):
        print 'Migration Environment not setup properly.'
        return

    if (get_keys_to_migrate.ready_for_migration() == False):
        get_keys_to_migrate.logger.error('Not ready for migration')
        return    

    if (get_keys_to_migrate.confirm_ready() == False):
        return

    parser = argparse.ArgumentParser()
    parser.add_argument('--file_with_keys',  nargs='?', help='file with keys')

    args = parser.parse_args()
    file_with_keys = args.file_with_keys

    # fetch data necessary for the rest of the script
    try:
        apis = get_keys_to_migrate.base.fetch('service_definitions', '*, service, service_definition_endpoints, service.service_classes, service.service_classes.developer_class', '')
        plans = get_keys_to_migrate.base.fetch('plans', '*, package, plan_services.service_definition', '')
        applications = get_keys_to_migrate.base.fetch('applications', '*, keys, keys.developer_class', '')
    except ValueError as err:
        get_keys_to_migrate.logger.error('Error fetching data: %s', json.dumps(err.args))
        return

    applications_from_csv = {}
    if (file_with_keys != None):
        with open(file_with_keys, 'rU') as csvfile:
            reader = csv.DictReader(csvfile)
            for row in reader:
                application = {}
                if row['application_id'] in applications_from_csv:
                    application = applications_from_csv[row['application_id']]

                key = {}
                key['apikey'] = row['apikey']
                key['service_key'] = row['service_key']

                keys = []
                if ('keys' in application):
                    keys = application['keys']

                keys.append(key)

                application['keys'] = keys

                applications_from_csv[row['application_id']] = application

    applications_to_migrate = []
    for application in applications:
        if (application['is_packaged'] == True):
            continue

        if len(applications_from_csv) > 0 and str(application['id']) not in applications_from_csv:
            continue

        application_to_migrate = {}
        application_to_migrate['id'] = application['id']
        application_to_migrate['name'] = application['name']
        application_to_migrate['username'] = application['username']
        application_to_migrate['keys'] = []
        for key in application['keys']:
            key_to_migrate = get_keys_to_migrate.update_key_with_package_data(key, apis, plans)
            application_to_migrate['keys'].append(key_to_migrate)

        if (get_keys_to_migrate.validator.validate_application_to_migrate(application_to_migrate, apis, plans) == True):
            applications_to_migrate.append(application_to_migrate)
        else:
            get_keys_to_migrate.logger.error('Application not valid for migration: %s', json.dumps(application))


    f = open(get_keys_to_migrate.migration_environment.configuration['migration']['key_input_file'],'w')
    get_keys_to_migrate.logger.info('Dumping applications and keys')
    f.write(json.dumps(applications_to_migrate, indent=4, sort_keys=True))
    f.close()

if __name__ == "__main__":
        main(sys.argv[1:])        
