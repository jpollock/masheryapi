import sys, urllib, argparse, time, requests, json, logging
import logger
from lib.base_migrator import BaseMigrator
from lib.migration_environment import MigrationEnvironment
from base import Base


def getServiceKey(backup_location, key): 
    f = open(backup_location +    str(key['apikey']) + '_' + str(key['service_key']) + '.json', 'r')
    file_contents = f.read()
    key_data = json.loads(file_contents)
    f.close()

    if (key_data == None):
        print 'Key archive file not found.'
        return

    return key_data

def main(argv):
    migration_environment = MigrationEnvironment()
    if (migration_environment.valid() == False):
        print 'Migration Environment not setup properly.'
        return

    mashery_api_config = migration_environment.configuration
    base = Base(mashery_api_config['mashery_api']['protocol'], mashery_api_config['mashery_api']['hostname'], mashery_api_config['mashery_area']['id'], mashery_api_config['mashery_api']['apikey'], mashery_api_config['mashery_api']['secret'])

    logger_migrator = logger.setup('restoreKeys', 'log/package_migrator.log')

    base_migrator = BaseMigrator(logger_migrator)

    parser = argparse.ArgumentParser()
    parser.add_argument('--nodryrun', action='store_true', default=False, help='specify to perform work, leave off command for dry run')

    args = parser.parse_args()
    nodryrun = args.nodryrun
    
    migration_data = base_migrator.get_migration_data(migration_environment.configuration['migration']['key_input_file'])
    if (migration_data == None):
        return


    for application in migration_data:
        if (application['id'] != ''):
            application_data = base.fetch('applications', '*, package_keys', 'WHERE id = ' + str(application['id']))
            if (len(application_data) == 1):
                application_data = application_data[0]
            else:
                base_migrator.logger.error('Problem fetching application for %s', json.dumps(application))
                continue

        package_keys_to_delete = []
        keys_to_restore = []
        package_key_data = None
        for key in application['keys']:
            # fetch key data
            package_keys = base.fetch('package_keys', '*, member, application, package, plan', 'WHERE apikey = \'' + key['apikey'] + '\'')
            for data in package_keys:
                if (data['package']['id'] == key['package_id'] and data['plan']['id'] == key['plan_id']):
                    package_key_data = data
                    break

            if (package_key_data != None):
                package_keys_to_delete.append(package_key_data)
            else:
                base_migrator.logger.error('Problem fetching packager key for %s', json.dumps(application))

            key_data = getServiceKey(migration_environment.configuration['migration']['backup_location'], key)
            keys_to_restore.append(key_data)
            #loggerMigrator.info('Migration readiness for %s ::: %s', json.dumps(key_data), json.dumps(ready_data))

        # before enabling app for packager we must delete and archive all keys
        if (nodryrun == True):
            try:
                t_del = []
                for k in package_keys_to_delete:
                    t_del.append(k['id'])

                if (len(t_del) > 0):
                  base.delete('package_key', t_del) 
            except ValueError as err:
                base_migrator.logger.error('Problem deleting keys: %s', json.dumps(err.args)) 

            application_data['is_packaged'] = False
            try:
                base.update('application', application_data)
            except ValueError as err:
                base_migrator.logger.error('Problem deleting keys: %s', json.dumps(err.args))

            for key in keys_to_restore:
                try:
                    base.create('key', key)
                except ValueError as err:
                    base_migrator.logger.error('Problem creating keys: %s', json.dumps(err.args))


if __name__ == "__main__":
        main(sys.argv[1:])        
