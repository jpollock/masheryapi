import sys, urllib, argparse, time, requests, json, logging
import base, logger
import baseMigrator

def fetchKey(site_id, apikey, secret, key):
    key_data = None
    # fetch key data
    try:
        key_data = base.fetch(site_id, apikey, secret, 'keys', '*, member, application, service, developer_class', 'WHERE apikey = \'' + key['apikey'] + '\' AND service_key = \'' + key['service_key'] + '\'')
    except ValueError as err:
        loggerMigrator.error('Problem fetching key: %s', json.dumps(err.args))
        return False

    if (len(key_data) == 1):
        key_data = key_data[0]
    else:
        loggerMigrator.error('Problem fetching key for %s', json.dumps(application))
    
    return key_data

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
    loggerMigrator =    logger.setup('validateKeys', 'myapp.log')
    
    parser = argparse.ArgumentParser()
    parser.add_argument("apikey", type=str, help="Mashery V2 API Key")
    parser.add_argument("secret", type=str, help="Mashery V2 API Secret")
    parser.add_argument("site_id", type=str, help="Mashery Area/Site ID")
    parser.add_argument("backup_location", type=str, help="Fully qualified path for backup of key data")
    parser.add_argument('migration_map_location',    type=str, help="Fully qualified path for backup of key data")
    parser.add_argument('--packagekeys', action='store_true', default=False, help='specify to perform work, leave off command for dry run')

    args = parser.parse_args()

    apikey = args.apikey
    secret = args.secret
    site_id = args.site_id
    backup_location = args.backup_location
    migration_map_location = args.migration_map_location
    packagekeys = args.packagekeys
    
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
            application_data = base.fetch(site_id, apikey, secret, 'applications', '*', 'WHERE id = ' + str(application['id']))
            if (len(application_data) == 1):
                application_data = application_data[0]
            else:
                loggerMigrator.error('Problem fetching application for %s', )
                continue

            if (packagekeys == True):
                if (application_data['is_packaged'] == False):
                    loggerMigrator.error('Expecting packaged application for %s', json.dumps(application))
            else:
                if (application_data['is_packaged'] == True):
                    loggerMigrator.error('Expecting non-packaged application for %s', json.dumps(application))


        for key in application['keys']:
            if (packagekeys == True):
                package_key_data = None
                # fetch key data
                package_keys = base.fetch(site_id, apikey, secret, 'package_keys', '*, member, application, package, plan', 'WHERE apikey = \'' + key['apikey'] + '\'')
                for data in package_keys:
                    if (data['package']['id'] != key['package_id'] and data['plan']['id'] != key['plan_id']):
                        package_key_data = data
                        break
                key_data_from_backup = baseMigrator.getServiceKeyFromBackup(backup_location, key)
                if (baseMigrator.validatePackageKey(package_key_data, key_data_from_backup, loggerMigrator) == False):
                    loggerMigrator.error('Mismatch:: %s %s', package_key_data['apikey'], package_key_data['application']['name'])

            else:
                key_data = fetchKey(site_id, apikey, secret, key)
                key_data_from_backup = baseMigrator.getServiceKeyFromBackup(backup_location, key)
                if (baseMigrator.validateServiceKey(key_data, key_data_from_backup, loggerMigrator) == False):
                    loggerMigrator.error('Mismatch:: %s %s', key_data['apikey'], key_data['application']['name'])

if __name__ == "__main__":
        main(sys.argv[1:])        
