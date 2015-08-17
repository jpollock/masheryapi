import logger, json, logging
from migration_environment import MigrationEnvironment
from validator import Validator
from base import Base

class BaseMigrator:
    def __init__(self,):
        self.migration_environment = MigrationEnvironment()
        mashery_api_config = self.migration_environment.configuration

        # Logging: had to move to this class since i don't understand python enough;
        # for some reason i couldn't get python to emit the logs to different files;
        # should be re-factored at some point
        
        # turn off info from http requests library
        logging.getLogger('requests').setLevel(logging.ERROR)
        # set a format which is simpler for console use
        formatter1 = logging.Formatter('%(name)-12s: %(levelname)-8s %(message)s')
        # define a Handler which writes INFO messages or higher to the sys.stderr
        streamHandler = logging.StreamHandler()
        streamHandler.setLevel(logging.ERROR)
        streamHandler.setFormatter(formatter1)
        logging.getLogger('').addHandler(streamHandler)  

        formatter2 = logging.Formatter('%(asctime)s %(name)-12s %(levelname)-8s %(message)s')
        l1 = logging.getLogger(self.__class__.__name__)
        fileHandler1 = logging.FileHandler(mashery_api_config['migration']['log_location']  + 'package_migrator.log', mode='a')
        fileHandler1.setLevel(logging.INFO)
        fileHandler1.setFormatter(formatter2)
        l1.addHandler(fileHandler1)
        l1.setLevel(logging.INFO)        
        l1 = logging.getLogger(self.__class__.__name__)

        l2 = logging.getLogger(self.__class__.__name__ + 'Transactions')
        fileHandler2 = logging.FileHandler(mashery_api_config['migration']['log_location']  + 'package_migrator_transactions.log', mode='a')
        fileHandler2.setLevel(logging.INFO)
        fileHandler2.setFormatter(formatter2)
        l2.addHandler(fileHandler2)
        l2.setLevel(logging.INFO)
        l2 = logging.getLogger(self.__class__.__name__ + 'Transactions')

        self.base = Base(mashery_api_config['mashery_api']['protocol'], mashery_api_config['mashery_api']['hostname'], mashery_api_config['mashery_area']['id'], mashery_api_config['mashery_api']['apikey'], mashery_api_config['mashery_api']['secret'], l2)

        self.validator = Validator(l1)

        self.logger = l1

    def update_object_with_required_attributes(self, object_to_update, missing_properties):
        for missing_property in missing_properties:
            if missing_property['field'] == 'uri':
                object_to_update[missing_property['field']] = 'http://www.example.com'
            else:
                object_to_update[missing_property['field']] = 'default'
        
        return object_to_update
    
    def confirm_ready(self):
        print '================================================'
        print 'Area ID= ' + str(self.migration_environment.configuration['mashery_area']['id'])
        print 'Area Name= ' + self.migration_environment.configuration['mashery_area']['name']
        print '================================================'
        proceed = raw_input('Proceed? (y/n)')
        if proceed == 'y':
            return True

        return False

    def ready_for_migration(self):
        # fetch area config
        try:
            area = self.base.area_fetch()
        except ValueError as err:
            self.logger.error('Error fetching data: %s', json.dumps(err.args))
            return

        # fetch keys in area, to see if there are any existing
        # memberless or applicationless ones
        try:
            keys = self.base.fetch('keys', '*, member, application, service, developer_class', '')
        except ValueError as err:
            self.logger.error('Error fetching data: %s', json.dumps(err.args))
            return

        applications = self.transform_keys_map_to_applications_map(keys)

        area_validation_data = self.validator.validate_area_for_migration(area, applications, keys)

        self.logger.info('ready_for_migration: %s', json.dumps(area_validation_data))

        return area_validation_data['ready_for_migration']

    def transform_keys_map_to_applications_map(self, keys):
        applications = {}

        for key in keys:
            app_id = 0
            if (key['application'] != None):
                app_id = key['application']['id']

            application = {}
            application['keys'] = []
            
            if app_id in applications:
                application = applications[app_id]

            application['keys'].append(key)

            applications[app_id] = application

        return applications

    def get_migration_data(self, migration_file):
        try:
            f = open(migration_file, 'r')
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

    def restore_application(self, application, nodryrun):
        
        if (application['id'] != ''):
            application_data = self.base.fetch('applications', '*, package_keys', 'WHERE id = ' + str(application['id']))
            if (len(application_data) == 1):
                application_data = application_data[0]
            else:
                self.logger.error('Problem fetching application for %s', json.dumps(application))
                return False

        package_keys_to_delete = []
        keys_to_restore = []
        package_key_data = None
        for key in application['keys']:
            if ('package_id' not in key or 'plan_id' not in key):
                self.logger.warn('Application %s has keys without package plan', str(application_data['id']))
                continue

            # fetch key data
            package_keys = self.base.fetch('package_keys', '*, member, application, package, plan', 'WHERE apikey = \'' + key['apikey'] + '\'')
            for data in package_keys:
                if (data['package']['id'] == key['package_id'] and data['plan']['id'] == key['plan_id']):
                    package_key_data = data
                    break

            if (package_key_data != None):
                package_keys_to_delete.append(package_key_data)
            
            key_data = self.get_service_key_from_backup(self.migration_environment.configuration['migration']['backup_location'], key)
            if (key_data == None):
                key_data = self.fetch_key(key)        
                if key_data == None:                    
                    self.logger.error('Problem fetching service key for %s', json.dumps(application))
                    return False
            keys_to_restore.append(key_data)

        # before enabling app for packager we must delete and archive all keys
        if (nodryrun == True):
            try:
                t_del = []
                for k in package_keys_to_delete:
                    t_del.append(k['id'])

                if (len(t_del) > 0):
                    self.base.delete('package_key', t_del) 
            except ValueError as err:
                self.logger.error('Problem deleting package keys: %s', json.dumps(err.args)) 
                return False

            application_data['is_packaged'] = False
            try:
                self.base.update('application', application_data)
            except ValueError as err:
                if (err.args[0][0]['message'] == 'Invalid Object'):
                    application_data = self.update_object_with_required_attributes(application_data, err.args[0][0]['data'])
                    try:
                        application = self.base.update('application', application_data)
                    except ValueError as err:
                        self.logger.error(json.dumps(err.args))
                        return False
                else:
                    self.logger.error(json.dumps(err.args))
                    return False

            for key in keys_to_restore:
                try:
                    restored_key = self.base.create('key', key)
                    # make sure the data is really restored, including "status" - that often gets 
                    # screwed up due to deleted keys counting against limits
                    backup_key = self.get_service_key_from_backup(self.migration_environment.configuration['migration']['backup_location'], key)
                    if (backup_key == None):
                        backup_key = self.fetch_key(key)

                    if (self.validator.validate_service_key(restored_key, backup_key) == False):
                        self.base.update('key', backup_key)
                except ValueError as err:
                    self.logger.error('Problem creating keys: %s', json.dumps(err.args))
                    return False

        return True



    def get_service_key_from_backup(self, backup_location, key): 
        key_data = None
        try:
            f = open(backup_location + str(key['apikey']) + '_' + str(key['service_key']) + '.json', 'r')
            file_contents = f.read()
            key_data = json.loads(file_contents)
            f.close()
        except IOError as err:
            self.logger.error('Problem retrieving backup key: %s', json.dumps(err.args))
        
        if (key_data == None or len(key_data) == 0):
            self.logger.error('Problem retrieving backup key: %s', json.dumps(err.args))
            return None

        return key_data

    def fetch_key(self, key):
        key_data = None
        # fetch key data
        try:
            key_data = self.base.fetch('keys', '*, member, application, service, developer_class', 'WHERE apikey = \'' + key['apikey'] + '\' AND service_key = \'' + key['service_key'] + '\'')
        except ValueError as err:
            self.logger.error('Problem fetching key: %s', json.dumps(err.args))
            return False

        if (len(key_data) == 1):
            key_data = key_data[0]
        else:
            self.logger.error('Problem fetching key for %s', json.dumps(key))
        
        return key_data


    def fetch_application(self, application):
        application_data = None
        if (application['id'] != ''):
            try:
                # get the application from the database
                application_data = self.base.fetch('applications', '*, keys.*, keys.member, keys.service, keys.developer_class', 'WHERE id = ' + str(application['id']))
            except ValueError as err:
                self.logger.error('Problem fetching application: %s', json.dumps(err.args))

            if (len(application_data) == 1):
                application_data = application_data[0]
            else:
                self.logger.error('Problem fetching application for %s', json.dumps(application))

            if (len(application['keys']) != len(application_data['keys'])):
                self.logger.error('Application data missing keys: json file: %s  api return: %s', json.dumps(application), json.dumps(application_data))

        return application_data
