import os, sys, urllib, argparse, time, requests, json, logging, random, string
from lib.base_migrator import BaseMigrator

class GetServiceKeysToMigrate(BaseMigrator):
    def __init__(self):
        BaseMigrator.__init__(self)

    
    def update_key_with_package_data(self, key, apis, plans, consolidate):
        key_to_migrate = {}
        key_to_migrate['apikey'] = key['apikey']
        key_to_migrate['service_key'] = key['service_key']

        if (consolidate == True):
            for plan in plans:
                matchCt = 0
                for plan_service in plan['plan_services']:
                    for api in apis:
                      if (api['service_key'] == plan_service['service_definition']['service_key']):
                        if (key['developer_class'] == None or (key['developer_class'] != None and plan['name'] == key['developer_class']['name'])):
                            matchCt += 1

                if (matchCt == len(plan['plan_services'])):
                    key_to_migrate['package_id'] = plan['package']['id']
                    key_to_migrate['plan_id'] = plan['id']

        else:
            for api in apis:
                if (api['service_key'] == key['service_key']):
                    for plan in plans:
                        if (plan['package']['name'] == api['name']):
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
        archive_keys.logger.error('Not ready for migration')
        return    

    # fetch data necessary for the rest of the script
    try:
        apis = get_keys_to_migrate.base.fetch('service_definitions', '*, service, service_definition_endpoints, service.service_classes, service.service_classes.developer_class', '')
        plans = get_keys_to_migrate.base.fetch('plans', '*, package, plan_services.service_definition', '')
        #applications = get_keys_to_migrate.base.fetch('applications', '*, keys, keys.developer_class', 'where name = \'key_with_app_with_member\'')
        applications = get_keys_to_migrate.base.fetch('applications', '*, keys, keys.developer_class', '')
        #keys = get_keys_to_migrate.base.fetch('keys', '*, member, application', '')
    except ValueError as err:
        base_migrator.logger.error('Error fetching data: %s', json.dumps(err.args))
        return

    applications_to_migrate = []
    for application in applications:
        if (application['is_packaged'] == True):
            continue
    
        consolidate = get_keys_to_migrate.application_should_be_consolidated(application)
        
        application_to_migrate = {}
        application_to_migrate['id'] = application['id']
        application_to_migrate['name'] = application['name']
        application_to_migrate['username'] = application['username']
        application_to_migrate['keys'] = []
        for key in application['keys']:
            key_to_migrate = get_keys_to_migrate.update_key_with_package_data(key, apis, plans, consolidate)
            application_to_migrate['keys'].append(key_to_migrate)

        if (get_keys_to_migrate.validator.validate_application_to_migrate(application_to_migrate) == True):
            applications_to_migrate.append(application_to_migrate)
        else:
            get_keys_to_migrate.logger.error('Problem with %s', json.dumps(application))


    f = open(get_keys_to_migrate.migration_environment.configuration['migration']['key_input_file'],'w')
    get_keys_to_migrate.logger.info('Dumping applications and keys')
    f.write(json.dumps(applications_to_migrate, indent=4, sort_keys=True))
    f.close()

if __name__ == "__main__":
        main(sys.argv[1:])        
