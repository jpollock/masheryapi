import sys, urllib, argparse, time, requests, json, logging
import base, logger

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
    global loggerMigrator
    loggerMigrator =    logger.setup('migrator', 'myapp.log')

    parser = argparse.ArgumentParser()
    parser.add_argument("apikey", type=str, help="Mashery V2 API Key")
    parser.add_argument("secret", type=str, help="Mashery V2 API Secret")
    parser.add_argument("site_id", type=str, help="Mashery Area/Site ID")
    parser.add_argument("backup_location", type=str, help="Fully qualified path for backup of key data")
    parser.add_argument('migration_map_location',    type=str, help="Fully qualified path for backup of key data")
    parser.add_argument('--nodryrun', action='store_true', default=False, help='specify to perform work, leave off command for dry run')

    args = parser.parse_args()

    apikey = args.apikey
    secret = args.secret
    site_id = args.site_id
    backup_location = args.backup_location
    migration_map_location = args.migration_map_location
    nodryrun = args.nodryrun
    
    try:
        f = open(migration_map_location, 'r')
        file_contents = f.read()
        migration_data = json.loads(file_contents)
        f.close()
    except IOError:
        loggerMigrator.error('Failed opening migration map file.')
        return
    except ValueError:
        loggerMigrator.error('Invalid JSON in migration map file.')
        return

    for application in migration_data:
        if (application['id'] != ''):
            application_data = base.fetch(site_id, apikey, secret, 'applications', '*, package_keys', 'WHERE id = ' + str(application['id']))
            if (len(application_data) == 1):
                application_data = application_data[0]
            else:
                loggerMigrator.error('Problem fetching application for %s', json.dumps(application))
                continue

        package_keys_to_delete = []
        keys_to_restore = []
        package_key_data = None
        for key in application['keys']:
            # fetch key data
            package_keys = base.fetch(site_id, apikey, secret, 'package_keys', '*, member, application, package, plan', 'WHERE apikey = \'' + key['apikey'] + '\'')
            for data in package_keys:
                if (data['package']['id'] == key['package_id'] and data['plan']['id'] == key['plan_id']):
                    package_key_data = data
                    break

            if (package_key_data != None):
                package_keys_to_delete.append(package_key_data)
            else:
                loggerMigrator.error('Problem fetching packager key for %s', json.dumps(application))

            key_data = getServiceKey(backup_location, key)
            keys_to_restore.append(key_data)
            #loggerMigrator.info('Migration readiness for %s ::: %s', json.dumps(key_data), json.dumps(ready_data))

        # before enabling app for packager we must delete and archive all keys
        if (nodryrun == True):
            for package_key in package_keys_to_delete:
                base.delete(site_id, apikey, secret, 'package_key', package_key) 

            application_data['is_packaged'] = False
            base.update(site_id, apikey, secret, 'application', application_data)

            for key in keys_to_restore:
                base.create(site_id, apikey, secret, 'key', key)

if __name__ == "__main__":
        main(sys.argv[1:])        
