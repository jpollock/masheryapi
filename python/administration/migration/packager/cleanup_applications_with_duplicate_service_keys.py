import os, sys, urllib, argparse, time, requests, json, logging, random, string
from lib.base_migrator import BaseMigrator

class CleanupApplicationsWithDuplicateServiceKeys(BaseMigrator):
    def __init__(self):
        BaseMigrator.__init__(self)

    def archive(self, backup_location, key_data):
        f = open(backup_location + str(key_data['apikey']) + '_' + str(key_data['service']['service_key']) + '.json', 'w')
        f.write(json.dumps(key_data))
        f.write('\n')
        f.close()

    def application_with_multiple_same_service_keys(self, application):
        key_set = []
        for key in application['keys']:
            if (key['service_key'] not in key_set):
                key_set.append(key['service_key'])
        if len(key_set) < len(application['keys']):
            return 1

        return 0

    def has_multiple_service_keys(self, keys, service_key):
        key_ct = 0
        for key in keys:
            if (key['service_key'] == service_key):
                key_ct += 1

        if key_ct > 1:
            return 1

        return 0        

def main(argv):
    migrate_applications = CleanupApplicationsWithDuplicateServiceKeys()

    parser = argparse.ArgumentParser()
    parser.add_argument('--nodryrun', action='store_true', default=False, help='specify to perform work, leave off command for dry run')

    args = parser.parse_args()
    nodryrun = args.nodryrun

    if (migrate_applications.confirm_ready() == False):
        return        

    # fetch all of the applications that are not packaged in the area
    try:
        applications = migrate_applications.base.fetch('applications', '*, member, keys, keys.service', 'WHERE is_packaged = false')
    except ValueError as err:
        migrate_applications.logger.error('Error fetching data: %s', json.dumps(err.args))
        return
    
    application_count = 0
    application_success_count = 0
    key_count = 0

    for application in applications:
        if migrate_applications.application_with_multiple_same_service_keys(application) > 0:
            application_count += 1
            try:
                migrate_applications.logger.info('Updating application: %s', json.dumps(application))
                keys = application['keys']
                for key in keys:
                    
                    if migrate_applications.has_multiple_service_keys(keys, key['service_key']) == 1:
                        key_count += 1
                    else:
                        continue

                    if nodryrun == True:
                        # archive package key
                        migrate_applications.archive(migrate_applications.migration_environment.configuration['migration']['backup_location'], key)
                        
                        # create app
                        application.pop('keys', None)
                        new_application = migrate_applications.base.create('application', application)

                        # delete and re-create package key
                        migrate_applications.base.delete('key', key)
                        key['application'] = {}
                        key['application']['id'] = new_application['result']['id']
                        key['member'] = application['member']
                        new_key = migrate_applications.base.create('key', key)
                        if new_key['result']['status'] != key['status']:
                            new_key['result']['status'] = key['status']
                            migrate_applications.base.update('key', new_key['result'])

                        keys.remove(key)

            except ValueError as err:
                migrate_applications.logger.error(json.dumps(err.args))
                print err
                return

    print 'Total Applications: ' + str(len(applications))
    print 'Applications to be updated: ' + str(application_count)
    print 'Keys to be updated: ' + str(key_count)

if __name__ == "__main__":
        main(sys.argv[1:])        
