import sys, urllib, argparse, time, requests, json, logging
from lib.base_migrator import BaseMigrator

class MigrateServiceKeysToPackageKeys(BaseMigrator):

    def __init__(self):
        BaseMigrator.__init__(self)

    def get_package_plan_for_key(self, packages, key):
        for plan in packages:
            if (plan['id'] == key['plan_id'] and plan['package']['id'] == key['package_id']):
                return plan

        return None

    def get_api_for_key(self, apis, key_data):
        for api in apis:
            if (api['service_key'] == key_data['service_key']):
                return api

        return None

    def get_package_key(self, application_data, key_data, package_data):
        package = {}
        package['id'] = package_data['package']['id']

        plan = {}
        plan['id'] = package_data['id']

        application = {}
        application['id'] = application_data['id']

        key_data['package'] = package
        key_data['plan'] = plan
        key_data['application'] = application

        return key_data

    def plan_contains_service(self, package_data, service_of_key):
        for plan_service in package_data['plan_services']:
            if (plan_service['service_definition']['service_key'] == service_of_key):
                return True

        return False

    def application_ready_to_be_migrated(self, application, application_data, apis, packages):
        if (application_data == None):
            return False

        if (application_data['is_packaged'] == True):
            return False

        for key in application['keys']:
            if ('package_id' not in key or 'plan_id' not in key):
                self.logger.warn('Application %s has keys without package plan', str(application_data['id']))
                return False

        for key_data in application_data['keys']:
            # fetch api data
            api_data = self.get_api_for_key(apis, key_data)
            if (api_data == None):
                self.logger.error('Problem fetching api: %s', json.dumps(key_data))
                return False

            # fetch package data
            package_data = self.get_package_plan_for_key(packages, key)
            if (package_data == None):
                self.logger.error('Problem fetching package: %s', json.dumps(key_data))
                return False

            if (self.key_ready_to_be_migrated(application_data, key_data, api_data, package_data) == False):
                return False

        return True

    def key_ready_to_be_migrated(self, application_data, key_data, api_data, plan_data):
        result = {}
        result['Apikey'] = key_data['apikey']
        result['Memberless Key'] = False
        result['Applicationless Key'] = False
        result['Service Not In Plan'] = False

        ready = True
            
        if (plan_data['package']['name'] != api_data['name']):
            ready = False
            result['Service Name Does Not Match Package Name'] = True

        if (key_data['developer_class'] != None and plan_data['name'] != key_data['developer_class']['name']):
            ready = False
            result['Developer Class Name Does Not Match Plan Name'] = True

        if (self.plan_contains_service(plan_data, key_data['service_key']) == False):
            ready = False
            result['Service Not In Plan'] = True

        result['ready'] = ready

        return result;

    def get_package_keys_to_create(self, application, application_data, apis, packages):
        package_keys_to_create = []

        for key in application['keys']:

            # fetch key data
            for key_data in application_data['keys']:
                if (key_data['apikey'] == key['apikey'] and key_data['service_key'] == key['service_key']):
                    break

            # fetch api data
            api_data = self.get_api_for_key(apis, key_data)
            if (api_data == None):
                self.logger.error('Problem fetching api: %s', json.dumps(key_data))
                return False

            # fetch package data
            package_data = self.get_package_plan_for_key(packages, key)
            if (package_data == None):
                self.logger.error('Problem fetching package: %s', json.dumps(key_data))
                return False

            package_key = self.get_package_key(application_data, key_data, package_data)
            package_keys_to_create.append(package_key)

        return package_keys_to_create

def main(argv):
    migrate_keys = MigrateServiceKeysToPackageKeys()
    if (migrate_keys.migration_environment.valid() == False):
        print 'Migration Environment not setup properly.'
        return

    if (migrate_keys.ready_for_migration() == False):
        migrate_keys.logger.error('Not ready for migration')
        return

    if (migrate_keys.confirm_ready() == False):
        return

    # get arguments
    parser = argparse.ArgumentParser()
    parser.add_argument('--nodryrun', action='store_true', default=False, help='specify to perform work, leave off command for dry run')
    args = parser.parse_args()
    nodryrun = args.nodryrun

    print nodryrun

    # get the input file, containing a json representation
    # of apps to archive
    migration_data = migrate_keys.get_migration_data(migrate_keys.migration_environment.configuration['migration']['key_input_file'])
    if (migration_data == None):
        return

    # get apis and packages
    apis = []
    packages = []
    try:
        apis = migrate_keys.base.fetch('services', '*', '')
        packages = migrate_keys.base.fetch('plans', '*, package, plan_services.service_definition', '')
    except ValueError as err:
        migrate_keys.logger.error('Error fetching data: %s', json.dumps(err.args))
        return

    # process each application to see if it is ready to go
    for application in migration_data:
        migrate_keys.logger.info('Processing application: %s', str(application['id']))
        ready = True

        application_data = migrate_keys.fetch_application(application)

        if (migrate_keys.application_ready_to_be_migrated(application, application_data, apis, packages) == False):
            migrate_keys.logger.warn('Application not ready for migration: %s', json.dumps(application_data))
            continue

        keys_to_delete = application['keys'] # list of keys to delete
        migrate_keys.logger.info('Service Keys to delete: %s', json.dumps(keys_to_delete))
        package_keys_to_create =  migrate_keys.get_package_keys_to_create(application, application_data, apis, packages) # list of package keys to create
        migrate_keys.logger.info('Package Keys to create: %s', json.dumps(package_keys_to_create))

        if (nodryrun == True):
            try:
                migrate_keys.base.delete('key', keys_to_delete) 
            except ValueError as err:
                migrate_keys.logger.error('Problem deleting keys: %s %s', str(application_data['id']), json.dumps(err.args))
                if migrate_keys.restore_application(application, nodryrun) == False:
                    migrate_keys.logger.error('Failed rollback')
                else:
                    migrate_keys.logger.error('Successful rollback')
                return 

            application_data['is_packaged'] = True
            try:
                migrate_keys.base.update('application', application_data)
            except ValueError as err:
                migrate_keys.logger.error('Problem updating application: %s %s', str(application_data['id']), json.dumps(err.args))
                if (err.args[0][0]['message'] == 'Invalid Object'):
                    application_data = migrate_keys.update_object_with_required_attributes(application_data, err.args[0][0]['data'])
                    try:
                        migrate_keys.base.update('application', application_data)
                    except ValueError as err:
                        if migrate_keys.restore_application(application, nodryrun) == False:
                            migrate_keys.logger.error('Failed rollback')
                        else:
                            migrate_keys.logger.error('Successful rollback')
                        return 
                else:
                    if migrate_keys.restore_application(application, nodryrun) == False:
                        migrate_keys.logger.error('Failed rollback')
                    else:
                        migrate_keys.logger.error('Successful rollback')
                    return 


            try:                  
                created_package_keys = migrate_keys.base.create('package_key', package_keys_to_create)
                for created_package_key in created_package_keys['result']:
                    status_not_right = False
                    for key in application['keys']:
                        # make sure the data is really restored, including "status" - that often gets 
                        # screwed up due to deleted keys counting against limits
                        backup_key = migrate_keys.get_service_key_from_backup(migrate_keys.migration_environment.configuration['migration']['backup_location'], key)
                        if (created_package_key['status'] != backup_key['status']):
                            status_not_right = True
                            created_package_key['status'] = backup_key['status']

                    if (status_not_right == True):
                        migrate_keys.base.update('package_key', created_package_key)

            except ValueError as err:
                migrate_keys.logger.error('Problem creating package key: %s', json.dumps(err.args))
                if migrate_keys.restore_application(application, nodryrun) == False:
                    migrate_keys.logger.error('Failed rollback')
                else:
                    migrate_keys.logger.error('Successful rollback')
                return 

if __name__ == "__main__":
        main(sys.argv[1:])        
