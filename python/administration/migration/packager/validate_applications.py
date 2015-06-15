import sys, urllib, argparse, time, requests, json, logging
from lib.base_migrator import BaseMigrator

class ValidateApplications(BaseMigrator):
    def __init__(self):
        BaseMigrator.__init__(self)


def main(argv):
    validate_applications = ValidateApplications()
    if (validate_applications.migration_environment.valid() == False):
        print 'Migration Environment not setup properly.'
        return

    parser = argparse.ArgumentParser()
    parser.add_argument('--packagekeys', action='store_true', default=False, help='specify to perform work, leave off command for dry run')

    args = parser.parse_args()
    packagekeys = args.packagekeys
    
    migration_data = validate_applications.get_migration_data(validate_applications.migration_environment.configuration['migration']['key_input_file'])
    if (migration_data == None):
        return

    for application in migration_data:
        if (application['id'] != ''):
            application_data = validate_applications.base.fetch('applications', '*', 'WHERE id = ' + str(application['id']))
            if (len(application_data) == 1):
                application_data = application_data[0]
            else:
                validate_applications.logger.error('Problem fetching application for %s', )
                continue

            if (packagekeys == True):
                if (application_data['is_packaged'] == False):
                    validate_applications.logger.error('Expecting packaged application for %s', json.dumps(application))
            else:
                if (application_data['is_packaged'] == True):
                    validate_applications.logger.error('Expecting non-packaged application for %s', json.dumps(application))


        for key in application['keys']:
            if (packagekeys == True):
                package_key_data = None
                # fetch key data

                package_keys = validate_applications.base.fetch('package_keys', '*, member, application, package, plan', 'WHERE apikey = \'' + key['apikey'] + '\'')
                for data in package_keys:
                    if (data['package']['id'] == key['package_id'] and data['plan']['id'] == key['plan_id']):
                        package_key_data = data
                        break

                if (package_key_data == None):
                    validate_applications.logger.error('Package Key Not Found:: %s %s %s', key['apikey'], str(key['package_id']), str(key['plan_id']))
                    continue

                key_data_from_backup = validate_applications.get_service_key_from_backup(validate_applications.migration_environment.configuration['migration']['backup_location'], key)

                if (validate_applications.validator.validate_package_key(package_key_data, key_data_from_backup) == False):
                    validate_applications.logger.error('Mismatch:: %s %s', package_key_data['apikey'], package_key_data['application']['name'])

            else:
                key_data = validate_applications.fetch_key(key)
                key_data_from_backup = validate_applications.get_service_key_from_backup(validate_applications.migration_environment.configuration['migration']['backup_location'], key)
                if (validate_applications.validator.validate_service_key(key_data, key_data_from_backup) == False):
                    validate_applications.logger.error('Mismatch:: %s %s', key_data['apikey'], key_data['application']['name'])

if __name__ == "__main__":
        main(sys.argv[1:])        
