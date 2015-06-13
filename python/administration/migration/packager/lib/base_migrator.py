import base, logger, json

class BaseMigrator:
    def __init__(self, logger):
        self.logger = logger

    def get_migration_data(self, file):
        try:
            f = open(file, 'r')
            file_contents = f.read()
            migration_data = json.loads(file_contents)
            f.close()
            return migration_data
        except IOError:
            self.logger.error('Failed opening migration map file.')
            return None
        except ValueError:
            self.logger.error('Invalid JSON in migration map file.')
            return None

    def application_should_be_consolidated(self, application):
        key_set = []
        package_set = []
        plan_set = []
        for key in application['keys']:
            if (key['apikey'] not in key_set):
                key_set.append(key['apikey'])

        if (len(key_set) == 1 and len(application['keys']) > 1):
            self.logger.info('Application %s has keys of same value', str(application['id']))
            return True

        return False


    def get_service_key_from_backup(self, backup_location, key): 
        f = open(backup_location +    str(key['apikey']) + '_' + str(key['service_key']) + '.json', 'r')
        file_contents = f.read()
        key_data = json.loads(file_contents)
        f.close()

        if (key_data == None):
            print 'Key archive file not found.'
            return

        return key_data

    def fetch_key(self, site_id, apikey, secret, key):
        key_data = None
        # fetch key data
        try:
            key_data = base.fetch(site_id, apikey, secret, 'keys', '*, member, application, service, developer_class', 'WHERE apikey = \'' + key['apikey'] + '\' AND service_key = \'' + key['service_key'] + '\'')
        except ValueError as err:
            self.logger.error('Problem fetching key: %s', json.dumps(err.args))
            return False

        if (len(key_data) == 1):
            key_data = key_data[0]
        else:
            self.logger.error('Problem fetching key for %s', json.dumps(application))
        
        return key_data
