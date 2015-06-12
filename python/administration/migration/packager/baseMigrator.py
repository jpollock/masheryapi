import base, logger, json

def validatePackageKey(package_data, key_data_from_backup, loggerMigrator):
    for val in package_data:
        if (val == 'created' or val == 'updated' or val == 'member' or val == 'application' or val == 'package' or val == 'plan' or val == 'service'):
            continue

        n_val = package_data[val]
        if val in key_data_from_backup:
            o_val = key_data_from_backup[val]
            if (n_val != o_val):
                print val + ' ' + str(o_val) + ' ' + str(n_val)
                loggerMigrator.error('Package Key Mismatch : %s %s', o_val, n_val)
                return False
        else:
            return False

    return True


def validateServiceKey(key_data, key_data_from_backup, loggerMigrator):
    for val in key_data:
        if (val == 'created' or val == 'updated' or val == 'member' or val == 'application'):
            continue

        n_val = key_data[val]
        if val in key_data_from_backup:
            o_val = key_data_from_backup[val]
            if (n_val != o_val):
                loggerMigrator.error('Key Mismatch : %s %s', o_val, n_val)                
                return False
        else:
            return False

    return True


def getServiceKeyFromBackup(backup_location, key): 
    f = open(backup_location +    str(key['apikey']) + '_' + str(key['service_key']) + '.json', 'r')
    file_contents = f.read()
    key_data = json.loads(file_contents)
    f.close()

    if (key_data == None):
        print 'Key archive file not found.'
        return

    return key_data

def fetchKey(site_id, apikey, secret, key, loggerMigrator):
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
