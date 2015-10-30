import os, sys, urllib, argparse, time, requests, json, logging, random, string
from lib.base_migrator import BaseMigrator

class CleanupApplicationsWithSamePackageKeyStrings(BaseMigrator):
    def __init__(self):
        BaseMigrator.__init__(self)

    def archive(self, backup_location, key_data):
        f = open(backup_location + str(key_data['apikey']) + '_' + str(key_data['package']['id']) + '.json', 'w')
        f.write(json.dumps(key_data))
        f.write('\n')
        f.close()

    def application_with_multiple_same_apikey_strings(self, application):
        key_set = []
        for key in application['package_keys']:
            if (key['apikey'] not in key_set):
                key_set.append(key['apikey'])

        if (len(key_set) == 1 and len(application['package_keys']) > 1):
            return 1

        return 0

def main(argv):
    migrate_applications = CleanupApplicationsWithSamePackageKeyStrings()

    parser = argparse.ArgumentParser()
    parser.add_argument('--nodryrun', action='store_true', default=False, help='specify to perform work, leave off command for dry run')

    args = parser.parse_args()
    nodryrun = args.nodryrun

    if (migrate_applications.confirm_ready() == False):
        return        

    # fetch all of the applications that are not packaged in the area
    try:
        applications = migrate_applications.base.fetch('applications', '*, member, package_keys, package_keys.package.id, package_keys.plan.id', '')
    except ValueError as err:
        migrate_applications.logger.error('Error fetching data: %s', json.dumps(err.args))
        return
    
    application_count = 0
    application_success_count = 0
    key_count = 0

    for application in applications:
        if migrate_applications.application_with_multiple_same_apikey_strings(application) > 0:
            application_count += 1
            try:
                migrate_applications.logger.info('Updating application: %s', json.dumps(application))
                keys = application['package_keys']
                for key in keys:
                    key_count += 1

                    if nodryrun == True:
                        # archive package key
                        migrate_applications.archive(migrate_applications.migration_environment.configuration['migration']['backup_location'], key)
                        
                        # create app
                        application.pop('package_keys', None)
                        new_application = migrate_applications.base.create('application', application)

                        # delete and re-create package key
                        migrate_applications.base.delete('package_key', key)
                        key['application'] = {}
                        key['application']['id'] = new_application['result']['id']
                        new_package_key = migrate_applications.base.create('package_key', key)
                        if new_package_key['result']['status'] != key['status']:
                            new_package_key['result']['status'] = key['status']
                            migrate_applications.base.update('package_key', new_package_key['result'])

                if nodryrun == True:
                    migrate_applications.base.delete('application', application)
                    
                application_success_count += 1
            except ValueError as err:
                migrate_applications.logger.error(json.dumps(err.args))
                print err
                return

    print 'Total Applications: ' + str(len(applications))
    print 'Applications to be updated: ' + str(application_count)
    print 'Keys to be updated: ' + str(key_count)
    print 'Applications updated: ' + str(application_success_count)

if __name__ == "__main__":
        main(sys.argv[1:])        
