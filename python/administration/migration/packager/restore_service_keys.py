import sys, urllib, argparse, time, requests, json, logging
import logger
from lib.base_migrator import BaseMigrator
from lib.migration_environment import MigrationEnvironment
from lib.validator import Validator
from base import Base

class RestoreKeys(BaseMigrator):
    def __init__(self):
        BaseMigrator.__init__(self)


def main(argv):
    restore_keys = RestoreKeys()
    if (restore_keys.migration_environment.valid() == False):
        print 'Migration Environment not setup properly.'
        return
    
    parser = argparse.ArgumentParser()
    parser.add_argument('--nodryrun', action='store_true', default=False, help='specify to perform work, leave off command for dry run')

    args = parser.parse_args()
    nodryrun = args.nodryrun
    
    migration_data = restore_keys.get_migration_data(restore_keys.migration_environment.configuration['migration']['key_input_file'])
    if (migration_data == None):
        return

    for application in migration_data:
        if (application['id'] != ''):
            application_data = restore_keys.base.fetch('applications', '*, package_keys', 'WHERE id = ' + str(application['id']))
            if (len(application_data) == 1):
                application_data = application_data[0]
            else:
                restore_keys.logger.error('Problem fetching application for %s', json.dumps(application))
                continue

        package_keys_to_delete = []
        keys_to_restore = []
        package_key_data = None
        for key in application['keys']:
            # fetch key data
            package_keys = restore_keys.base.fetch('package_keys', '*, member, application, package, plan', 'WHERE apikey = \'' + key['apikey'] + '\'')
            for data in package_keys:
                if (data['package']['id'] == key['package_id'] and data['plan']['id'] == key['plan_id']):
                    package_key_data = data
                    break

            if (package_key_data != None):
                package_keys_to_delete.append(package_key_data)
            else:
                restore_keys.logger.error('Problem fetching packager key for %s', json.dumps(application))

            key_data = restore_keys.get_service_key_from_backup(restore_keys.migration_environment.configuration['migration']['backup_location'], key)
            keys_to_restore.append(key_data)

        # before enabling app for packager we must delete and archive all keys
        if (nodryrun == True):
            try:
                t_del = []
                for k in package_keys_to_delete:
                    t_del.append(k['id'])

                if (len(t_del) > 0):
                  restore_keys.base.delete('package_key', t_del) 
            except ValueError as err:
                restore_keys.logger.error('Problem deleting keys: %s', json.dumps(err.args)) 

            application_data['is_packaged'] = False
            try:
                restore_keys.base.update('application', application_data)
            except ValueError as err:
                restore_keys.logger.error('Problem deleting keys: %s', json.dumps(err.args))

            for key in keys_to_restore:
                try:
                    restored_key = restore_keys.base.create('key', key)
                    # make sure the data is really restored, including "status" - that often gets 
                    # screwed up due to deleted keys counting against limits
                    backup_key = restore_keys.get_service_key_from_backup(restore_keys.migration_environment.configuration['migration']['backup_location'], key)
                    if (restore_keys.validator.validate_service_key(restored_key, backup_key) == False):
                        restore_keys.base.update('key', backup_key)
                except ValueError as err:
                    restore_keys.logger.error('Problem creating keys: %s', json.dumps(err.args))


if __name__ == "__main__":
        main(sys.argv[1:])        
