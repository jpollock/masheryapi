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

def less_keys_still_exist():
    # fetch keys in area, to see if there are any existing
    # memberless or applicationless ones
    try:
        keys = base.fetch('keys', '*, member, application', '')
    except ValueError as err:
        base_migrator.logger.error('Error fetching data: %s', json.dumps(err.args))
        return

    for key in keys:
        if (key['member'] == None or key['application'] == None):
            return True
    return False

def fetch_key(key):
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

def fetch_application(application):
    application_data = None
    if (application['id'] != ''):
        try:
            # get the application from the database
            application_data = base.fetch('applications', '*, keys', 'WHERE id = ' + str(application['id']))
        except ValueError as err:
            base_migrator.logger.error('Problem fetching application: %s', json.dumps(err.args))

        if (len(application_data) == 1):
            application_data = application_data[0]
        else:
            base_migrator.logger.error('Problem fetching application for %s', json.dumps(application))

        if (len(application['keys']) != len(application_data['keys'])):
            base_migrator.logger.error('Application data missing keys %s', json.dumps(application))

    return application_data


def main(argv):

    migration_environment = MigrationEnvironment()
    if (migration_environment.valid() == False):
        print 'Migration Environment not setup properly.'
        return

    mashery_api_config = migration_environment.configuration
    global base
    base = Base(mashery_api_config['mashery_api']['protocol'], mashery_api_config['mashery_api']['hostname'], mashery_api_config['mashery_area']['id'], mashery_api_config['mashery_api']['apikey'], mashery_api_config['mashery_api']['secret'])

    # setup logging
    logger_migrator = logger.setup('archiveServiceKeys', 'log/package_migrator.log')

    global base_migrator
    base_migrator = BaseMigrator(logger_migrator)

    if (less_keys_still_exist() == True):
        base_migrator.logger.error('Applicationless and memberless keys still exist.')
        return 

    # get the input file, containing a json representation
    # of apps to archive
    migration_data = base_migrator.get_migration_data(migration_environment.configuration['migration']['key_input_file'])
    if (migration_data == None):
        return

    # process each application to see if it is ready to go
    for application in migration_data:
        ready = True

        application_data = fetch_application(application)

        if (application_data == None):
            base_migrator.logger.error('Problem retrieving application.')
            continue

        if (application_data['is_packaged'] == True):
            base_migrator.logger.error('Application is packaged.')
            continue

        for key in application['keys']:

            # fetch key data
            key_data = fetch_key(key)
            if (key_data == None):
                base_migrator.logger.error('Problem retrieving key.')
                return

            archive(migration_environment.configuration['migration']['backup_location'], key_data)

if __name__ == "__main__":
        main(sys.argv[1:])        
