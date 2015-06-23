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

    if (restore_keys.confirm_ready() == False):
        return        
    
    parser = argparse.ArgumentParser()
    parser.add_argument('--nodryrun', action='store_true', default=False, help='specify to perform work, leave off command for dry run')

    args = parser.parse_args()
    nodryrun = args.nodryrun
    
    migration_data = restore_keys.get_migration_data(restore_keys.migration_environment.configuration['migration']['key_input_file'])
    if (migration_data == None):
        return

    for application in migration_data:
        if restore_keys.restore_application(application, nodryrun) == False:
            continue


if __name__ == "__main__":
        main(sys.argv[1:])        
