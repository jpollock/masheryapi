import json

class MigrationEnvironment:

    def __init__(self):
        try:
            f = open('migration.environment.json', 'r')
            file_contents = f.read()
            self.configuration = json.loads(file_contents)
            f.close()
        except IOError:            
            return
        except ValueError:
            return

    def valid(self):

        if 'mashery_api' not in self.configuration:
            return False
        if 'protocol' not in self.configuration['mashery_api']:
            return False
        if 'hostname' not in self.configuration['mashery_api']:
            return False
        if 'apikey' not in self.configuration['mashery_api']:
            return False
        if 'secret' not in self.configuration['mashery_api']:
            return False
        
        if 'mashery_area' not in self.configuration:
            return False
        if 'id' not in self.configuration['mashery_area']:
            return False
        if 'name' not in self.configuration['mashery_area']:
            return False

        if 'migration' not in self.configuration:
            return False
        if 'backup_location' not in self.configuration['migration']:
            return False
        if 'log_location' not in self.configuration['migration']:
            return False
        if 'key_input_file' not in self.configuration['migration']:
            return False

        return True
