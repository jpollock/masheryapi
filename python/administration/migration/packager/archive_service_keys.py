import sys, urllib, argparse, time, requests, json, logging
import logger
from lib.base_migrator import BaseMigrator
from lib.migration_environment import MigrationEnvironment
from lib.validator import Validator
from base import Base

class ArchiveKeys(BaseMigrator):
    def __init__(self):
        BaseMigrator.__init__(self)

    def archive(self, backup_location, key_data):
        f = open(backup_location + str(key_data['apikey']) + '_' + str(key_data['service_key']) + '.json', 'w')
        f.write(json.dumps(key_data))
        f.write('\n')
        f.close()


def main(argv):
    archive_keys = ArchiveKeys()
    if (archive_keys.migration_environment.valid() == False):
        print 'Migration Environment not setup properly.'
        return

    if (archive_keys.ready_for_migration() == False):
        archive_keys.logger.error('Not ready for migration')
        return

    # get the input file, containing a json representation
    # of apps to archive
    migration_data = archive_keys.get_migration_data(archive_keys.migration_environment.configuration['migration']['key_input_file'])
    if (migration_data == None):
        return

    # process each application to see if it is ready to go
    for application in migration_data:
        ready = True

        application_data = archive_keys.fetch_application(application)

        if (application_data == None):
            archive_keys.logger.error('Problem retrieving application.')
            continue

        if (application_data['is_packaged'] == True):
            archive_keys.logger.error('Application is packaged.')
            continue

        for key in application['keys']:

            # fetch key data
            key_data = archive_keys.fetch_key(key)
            if (key_data == None):
                archive_keys.logger.error('Problem retrieving key.')
                return

            archive_keys.archive(archive_keys.migration_environment.configuration['migration']['backup_location'], key_data)

if __name__ == "__main__":
        main(sys.argv[1:])        
