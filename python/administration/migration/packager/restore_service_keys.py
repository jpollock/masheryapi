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
    
    parser = argparse.ArgumentParser()
    parser.add_argument('--nodryrun', action='store_true', default=False, help='specify to perform work, leave off command for dry run')
    parser.add_argument('--applications',  nargs='+', help='List of application ids to restore, space separated')

    args = parser.parse_args()
    nodryrun = args.nodryrun
    applications = args.applications

    if (restore_keys.migration_environment.valid() == False):
        print 'Migration Environment not setup properly.'
        return

    if (restore_keys.confirm_ready() == False):
        return        
    
    migration_data = []

    if applications == None:
        migration_data = restore_keys.get_migration_data(restore_keys.migration_environment.configuration['migration']['key_input_file'])
    else:
        for application in applications:
            application_data = restore_keys.base.fetch('applications', 'id, keys.apikey, keys.service_key, package_keys.apikey, package_keys.package.id, package_keys.plan.id', 'WHERE id = ' + str(application))
            if (len(application_data) == 1):
                application_object = {}
                application_object['id'] = application_data[0]['id']
                keys = []
                for package_key in application_data[0]['package_keys']:
                    new_key = {}
                    new_key['apikey'] = package_key['apikey']
                    new_key['package_id'] = package_key['package']['id']
                    new_key['plan_id'] = package_key['plan']['id']
                    keys.append(new_key)
                
                for key in  application_data[0]['keys']:
                    for new_key in keys:
                        if (new_key['apikey'] == key['apikey']):
                            new_key['service_key'] = key['service_key']
                    

                application_object['keys'] = keys
                migration_data.append(application_object)
            else:
                restore_keys.base.logger.error('Problem fetching application for %s', json.dumps(application))

    if (migration_data == None or len(migration_data) == 0):
        return

    for application in migration_data:
        if restore_keys.restore_application(application, nodryrun) == False:
            continue


if __name__ == "__main__":
        main(sys.argv[1:])        
