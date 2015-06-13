class Validator:

    def __init__(self, logger):
        self.logger = logger


    def validate_application_to_migrate(self, application):
        # tests:
        #   1) make sure that the app's keys, if same key string, all point to same package and plan
        apikeys = {}
        for key in application['keys']:
            if (key['apikey'] not in apikeys):
                apikeys[key['apikey']] = key
            else:
                if (key['package_id'] != apikeys[key['apikey']]['package_id'] or key['plan_id'] != apikeys[key['apikey']]['plan_id']):
                    return False

        return True

    def validate_package_key(self, package_key_data, key_data_from_backup):
        for val in package_key_data:
            if (val == 'created' or val == 'updated' or val == 'member' or val == 'application' or val == 'package' or val == 'plan' or val == 'service'):
                continue

            n_val = package_key_data[val]
            if val in key_data_from_backup:
                o_val = key_data_from_backup[val]
                if (n_val != o_val):
                    #print val + ' ' + str(o_val) + ' ' + str(n_val)
                    self.logger.error('Package Key Mismatch : %s %s', o_val, n_val)
                    return False
            else:
                return False

        return True


    def validate_service_key(self, key_data, key_data_from_backup):
        for val in key_data:
            if (val == 'created' or val == 'updated' or val == 'member' or val == 'application'):
                continue

            n_val = key_data[val]
            if val in key_data_from_backup:
                o_val = key_data_from_backup[val]
                if (n_val != o_val):
                    self.logger.error('Key Mismatch : %s %s', o_val, n_val)                
                    return False
            else:
                return False

        return True
