import sys, urllib, argparse, time, requests, json, logging
import logger
from lib.base_migrator import BaseMigrator
from lib.migration_environment import MigrationEnvironment

from base import Base

def archive(backup_location, key_data):
    f = open(backup_location + str(key_data['apikey']) + '_' + str(key_data['service_key']) + '.json', 'w')
    f.write(json.dumps(key_data))
    f.write('\n')
    f.close()

def noAppNoMember(keys):
    for key in keys:
        if (key['member'] == None or key['application'] == None):
            return True
    return False

def getPackagePlanForKey(packages, key):
    for plan in packages:
      if (plan['id'] == key['plan_id'] and plan['package']['id'] == key['package_id']):
        return plan

    return None

def getApiForKey(apis, key_data):
    for api in apis:
      if (api['service_key'] == key_data['service_key']):
        return api

    return None

def fetchKey(key):
    key_data = None
    # fetch key data
    try:
        key_data = base.fetch('keys', '*, member, application, service, developer_class', 'WHERE apikey = \'' + key['apikey'] + '\' AND service_key = \'' + key['service_key'] + '\'')
    except ValueError as err:
        base_migrator.logger.error('Problem fetching key: %s', json.dumps(err.args))
        return False

    if (len(key_data) == 1):
        key_data = key_data[0]
    else:
        base_migrator.logger.error('Problem fetching key for %s', json.dumps(application))
    
    return key_data

def fetchApplication(application):
    application_data = None
    if (application['id'] != ''):
        try:
            # get the application from the database
            application_data = base.fetch('applications', '*, keys.*, keys.member, keys.service, keys.developer_class', 'WHERE id = ' + str(application['id']))
        except ValueError as err:
            base_migrator.logger.error('Problem fetching application: %s', json.dumps(err.args))

        if (len(application_data) == 1):
            application_data = application_data[0]
        else:
            base_migrator.logger.error('Problem fetching application for %s', json.dumps(application))

        if (len(application['keys']) != len(application_data['keys'])):
            base_migrator.logger.error('Application data missing keys %s', json.dumps(application_data))

    return application_data

def getPackageKey(application_data, key_data, package_data):
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

def planContainsService(package_data, service_of_key):
    for plan_service in package_data['plan_services']:
        if (plan_service['service_definition']['service_key'] == service_of_key):
            return True

    return False

def applicationReadyToBeMigrated(application, application_data, apis, packages):
    if (application_data == None):
        return False

    if (application_data['is_packaged'] == True):
        return False

    key_set = []
    package_set = []
    plan_set = []
    for key in application['keys']:
        if (key['apikey'] not in key_set):
            key_set.append(key['apikey'])
        if (key['package_id'] not in package_set):
            package_set.append(key['package_id'])
        if (key['plan_id'] not in plan_set):
            plan_set.append(key['plan_id'])

    if (len(key_set) == 1 and len(package_set) != 1 and len(plan_set) != 1):
        base_migrator.logger.info('Application %s has keys of same value and multiple package plans', str(application_data['id']))
        return False

    for key_data in application_data['keys']:
        # fetch api data
        api_data = getApiForKey(apis, key_data)
        if (api_data == None):
            base_migrator.logger.error('Problem fetching api: %s', json.dumps(key_data))
            return False

        # fetch package data
        package_data = getPackagePlanForKey(packages, key)
        if (package_data == None):
            base_migrator.logger.error('Problem fetching package: %s', json.dumps(key_data))
            return False

        if (keyReadyToBeMigrated(application_data, key_data, api_data, package_data) == False):
            return False

    return True

def keyReadyToBeMigrated(application_data, key_data, api_data, package_data):
    result = {}
    result['Apikey'] = key_data['apikey']
    result['Memberless Key'] = False
    result['Applicationless Key'] = False
    result['Service Not In Plan'] = False

    ready = True
        
    if (base_migrator.application_should_be_consolidated(application_data, base_migrator.logger) == False):
        if (package_data['package']['name'] != api_data['name']):
            ready = False
            result['Service Name Does Not Match Package Name'] = True

        if (key_data['developer_class'] != None and package_data['name'] != key_data['developer_class']['name']):
            ready = False
            result['Developer Class Name Does Not Match Plan Name'] = True

    if (planContainsService(package_data, key_data['service_key']) == False):
        ready = False
        result['Service Not In Plan'] = True

    result['ready'] = ready

    return result;

def getPackageKeysToCreate(application, application_data, apis, packages):
    package_keys_to_create = []

    for key in application['keys']:

        # fetch key data
        for key_data in application_data['keys']:
            if (key_data['apikey'] == key['apikey'] and key_data['service_key'] == key['service_key']):
                break

        # fetch api data
        api_data = getApiForKey(apis, key_data)
        if (api_data == None):
            base_migrator.logger.error('Problem fetching api: %s', json.dumps(key_data))
            return False

        # fetch package data
        package_data = getPackagePlanForKey(packages, key)
        if (package_data == None):
            base_migrator.logger.error('Problem fetching package: %s', json.dumps(key_data))
            return False

        package_key = getPackageKey(application_data, key_data, package_data)
        package_keys_to_create.append(package_key)

    if (base_migrator.application_should_be_consolidated(application_data, base_migrator.logger) == True):
        single_package_keys_to_create = []
        single_package_keys_to_create.append(package_keys_to_create[0])
        package_keys_to_create = single_package_keys_to_create

    return package_keys_to_create

def main(argv):
    migration_environment = MigrationEnvironment()
    if (migration_environment.valid() == False):
        print 'Migration Environment not setup properly.'
        return

    mashery_api_config = migration_environment.configuration
    global base
    base = Base(mashery_api_config['mashery_api']['protocol'], mashery_api_config['mashery_api']['hostname'], mashery_api_config['mashery_area']['id'], mashery_api_config['mashery_api']['apikey'], mashery_api_config['mashery_api']['secret'])

    # setup logging
    logger_migrator = logger.setup('migrateServiceKeyToPackageKey', 'log/package_migrator.log')

    global base_migrator
    base_migrator = BaseMigrator(logger_migrator)
    

    # get arguments
    parser = argparse.ArgumentParser()
    parser.add_argument('--nodryrun', action='store_true', default=False, help='specify to perform work, leave off command for dry run')
    args = parser.parse_args()
    nodryrun = args.nodryrun

    # fetch keys in area, to see if there are any existing
    # memberless or applicationless ones
    try:
        keys = base.fetch('keys', '*, member, application', '')
    except ValueError as err:
        base_migrator.logger.error('Error fetching data: %s', json.dumps(err.args))
        return

    if (noAppNoMember(keys) == True):
        base_migrator.logger.error('Applicationless and memberless keys still exist.')
        return 

    # get the input file, containing a json representation
    # of apps to migrate
    try:
        f = open(migration_environment.configuration['migration']['key_input_file'], 'r')
        file_contents = f.read()
        migration_data = json.loads(file_contents)
        f.close()
    except IOError:
        base_migrator.logger.error('Failed opening migration map file.')
        return
    except ValueError:
        base_migrator.logger.error('Invalid JSON in migration map file.')
        return

    # get apis and packages
    apis = []
    packages = []
    try:
        apis = base.fetch('services', '*', '')
        packages = base.fetch('plans', '*, package, plan_services.service_definition', '')
    except ValueError as err:
        base_migrator.logger.error('Error fetching data: %s', json.dumps(err.args))
        return

    # process each application to see if it is ready to go
    for application in migration_data:
        ready = True

        application_data = fetchApplication(application)

        if (applicationReadyToBeMigrated(application, application_data, apis, packages) == False):
          return False

        keys_to_delete = application['keys'] # list of keys to delete
        package_keys_to_create =  getPackageKeysToCreate(application, application_data, apis, packages) # list of package keys to create
        
        print str(len(keys_to_delete)) + ' ' + str(len(package_keys_to_create))

        if (nodryrun == True):
            try:
                base.delete('key', keys_to_delete) 
            except ValueError as err:
                base_migrator.logger.error('Problem deleting keys: %s', json.dumps(err.args))
                return 

            application_data['is_packaged'] = True
            try:
                base.update('application', application_data)
            except ValueError as err:
                base_migrator.logger.error('Problem updating application: %s', json.dumps(err.args))
                return 

            try:                  
                base.create('package_key', package_keys_to_create)
            except ValueError as err:
                base_migrator.logger.error('Problem creating package key: %s', json.dumps(err.args))
                return 

if __name__ == "__main__":
        main(sys.argv[1:])        
