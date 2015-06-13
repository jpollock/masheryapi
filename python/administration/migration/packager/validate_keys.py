import sys, urllib, argparse, time, requests, json, logging
import logger
from lib.base_migrator import BaseMigrator
from lib.migration_environment import MigrationEnvironment
from base import Base
from lib.validator import Validator

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


def main(argv):
    migration_environment = MigrationEnvironment()
    if (migration_environment.valid() == False):
        print 'Migration Environment not setup properly.'
        return

    mashery_api_config = migration_environment.configuration
    global base
    base = Base(mashery_api_config['mashery_api']['protocol'], mashery_api_config['mashery_api']['hostname'], mashery_api_config['mashery_area']['id'], mashery_api_config['mashery_api']['apikey'], mashery_api_config['mashery_api']['secret'])

    logger_migrator = logger.setup('validateKeys', 'log/package_migrator.log')

    global base_migrator
    base_migrator = BaseMigrator(logger_migrator)
    
    validator = Validator(logger_migrator)

    parser = argparse.ArgumentParser()
    parser.add_argument('--packagekeys', action='store_true', default=False, help='specify to perform work, leave off command for dry run')

    args = parser.parse_args()
    packagekeys = args.packagekeys
    
    migration_data = base_migrator.get_migration_data(migration_environment.configuration['migration']['key_input_file'])
    if (migration_data == None):
        return

    for application in migration_data:
        if (application['id'] != ''):
            application_data = base.fetch('applications', '*', 'WHERE id = ' + str(application['id']))
            if (len(application_data) == 1):
                application_data = application_data[0]
            else:
                base_migrator.logger.error('Problem fetching application for %s', )
                continue

            if (packagekeys == True):
                if (application_data['is_packaged'] == False):
                    base_migrator.logger.error('Expecting packaged application for %s', json.dumps(application))
            else:
                if (application_data['is_packaged'] == True):
                    base_migrator.logger.error('Expecting non-packaged application for %s', json.dumps(application))


        for key in application['keys']:
            if (packagekeys == True):
                package_key_data = None
                # fetch key data

                package_keys = base.fetch('package_keys', '*, member, application, package, plan', 'WHERE apikey = \'' + key['apikey'] + '\'')
                for data in package_keys:
                    if (data['package']['id'] == key['package_id'] and data['plan']['id'] == key['plan_id']):
                        package_key_data = data
                        break

                if (package_key_data == None):
                    base_migrator.logger.error('Package Key Not Found:: %s %s %s', key['apikey'], str(key['package_id']), str(key['plan_id']))
                    continue

                key_data_from_backup = base_migrator.get_service_key_from_backup(migration_environment.configuration['migration']['backup_location'], key)

                if (validator.validate_package_key(package_key_data, key_data_from_backup) == False):
                    base_migrator.logger.error('Mismatch:: %s %s', package_key_data['apikey'], package_key_data['application']['name'])

            else:
                key_data = fetch_key(key)
                key_data_from_backup = base_migrator.get_service_key_from_backup(migration_environment.configuration['migration']['backup_location'], key)
                if (validator.validate_service_key(key_data, key_data_from_backup) == False):
                    base_migrator.logger.error('Mismatch:: %s %s', key_data['apikey'], key_data['application']['name'])

if __name__ == "__main__":
        main(sys.argv[1:])        
