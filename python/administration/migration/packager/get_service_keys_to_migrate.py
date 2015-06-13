import os, sys, urllib, argparse, time, requests, json, logging, random, string
import logger
from lib.base_migrator import BaseMigrator
from lib.migration_environment import MigrationEnvironment
from lib.validator import Validator
from base import Base

def noAppNoMember(keys):
    for key in keys:
        if (key['member'] == None or key['application'] == None):
            return True
    return False
    
def updateKeyWithPackageData(key, apis, plans, consolidate):
    key_to_migrate = {}
    key_to_migrate['apikey'] = key['apikey']
    key_to_migrate['service_key'] = key['service_key']

    if (consolidate == True):
        foundAllServicesPlan = False
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
    migration_environment = MigrationEnvironment()
    if (migration_environment.valid() == False):
        print 'Migration Environment not setup properly.'
        return

    mashery_api_config = migration_environment.configuration
    base = Base(mashery_api_config['mashery_api']['protocol'], mashery_api_config['mashery_api']['hostname'], mashery_api_config['mashery_area']['id'], mashery_api_config['mashery_api']['apikey'], mashery_api_config['mashery_api']['secret'])

    logger_migrator = logger.setup('getServiceKeysToMigrate', 'log/package_migrator.log')

    global base_migrator
    base_migrator = BaseMigrator(logger_migrator)

    validator = Validator(logger_migrator)

    # fetch data necessary for the rest of the script
    try:
        apis = base.fetch('service_definitions', '*, service, service_definition_endpoints, service.service_classes, service.service_classes.developer_class', '')
        plans = base.fetch('plans', '*, package, plan_services.service_definition', '')
        applications = base.fetch('applications', '*, keys, keys.developer_class', '')
        keys = base.fetch('keys', '*, member, application', '')
    except ValueError as err:
        base_migrator.logger.error('Error fetching data: %s', json.dumps(err.args))
        return

    # check to see if there are any applicationless and/or memberless keys
    # if there are, stop script execution
    if (noAppNoMember(keys) == True):
        base_migrator.logger.error('Applicationless and memberless keys still exist.')
        return 

    applications_to_migrate = []
    for application in applications:
        if (application['is_packaged'] == True):
            continue
    
        consolidate = base_migrator.application_should_be_consolidated(application)
        
        application_to_migrate = {}
        application_to_migrate['id'] = application['id']
        application_to_migrate['name'] = application['name']
        application_to_migrate['username'] = application['username']
        application_to_migrate['keys'] = []
        for key in application['keys']:
            key_to_migrate = updateKeyWithPackageData(key, apis, plans, consolidate)
            application_to_migrate['keys'].append(key_to_migrate)

        if (validator.validate_application_to_migrate(application_to_migrate) == True):
            applications_to_migrate.append(application_to_migrate)
        else:
            base_migrator.logger.error('Problem with %s', json.dumps(application))


    f = open(migration_environment.configuration['migration']['key_input_file'],'w')
    base_migrator.logger.info('Dumping applications and keys')
    f.write(json.dumps(applications_to_migrate, indent=4, sort_keys=True))
    f.close()

if __name__ == "__main__":
        main(sys.argv[1:])        
