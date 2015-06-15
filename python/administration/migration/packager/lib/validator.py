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


    def validate_area_for_migration(self, area, applications, keys):
        area_validation_data = {}
        area_validation_data['packager_enabled'] = area['config']['enable_packaging']
        area_validation_data['service_keys_enabled'] = area['config']['show_service_keys']
        area_validation_data['control_center_enabled'] = area['config']['enable_calypso']

        area_validation_data['keys_with_no_member_or_application'] = self.keys_with_no_member_or_application(keys)
        area_validation_data['applications_with_multiple_same_apikey_strings'] = self.applications_with_multiple_same_apikey_strings(applications)

        area_validation_data['ready_for_migration'] = self.determine_readiness(area_validation_data)

        return area_validation_data

    def determine_readiness(self, area_validation_data):
        if (area_validation_data['control_center_enabled'] == True):
            return False

        if (area_validation_data['packager_enabled'] == False):
            return False

        if (area_validation_data['packager_enabled'] == True and area_validation_data['service_keys_enabled'] == False):
            return False

        if (area_validation_data['keys_with_no_member_or_application'] == True):
            return False

        if (area_validation_data['applications_with_multiple_same_apikey_strings']['same_key_string_different_developer_classes'] > 0):
            return False

        if (area_validation_data['applications_with_multiple_same_apikey_strings']['same_key_string_different_statuses'] > 0):
            return False

        return True

    def keys_with_no_member_or_application(self, keys):
        for key in keys:
            if (key['member'] == None or key['application'] == None):
                return True
        return False


    def applications_with_multiple_same_apikey_strings(self, applications):
        result = {}
        cnt_count_applications_with_multiple_same_apikey_strings = 0
        cnt_count_same_key_string_different_developer_classes = 0
        cnt_same_key_string_different_statuses = 0

        for application in applications:
            if application == 0: # application less - no point in checking
                continue

            application = applications[application]

            cnt_count_applications_with_multiple_same_apikey_strings += self.application_with_multiple_same_apikey_strings(application)

            cnt_count_same_key_string_different_developer_classes += self.same_key_string_different_developer_classes(application)

            cnt_same_key_string_different_statuses += self.same_key_string_different_statuses(application)

        result['same_key_string'] = cnt_count_applications_with_multiple_same_apikey_strings
        result['same_key_string_different_developer_classes'] = cnt_count_same_key_string_different_developer_classes
        result['same_key_string_different_statuses'] = cnt_same_key_string_different_statuses

        return result

    def application_with_multiple_same_apikey_strings(self, application):
        key_set = []
        for key in application['keys']:
            if (key['apikey'] not in key_set):
                key_set.append(key['apikey'])

        if (len(key_set) == 1 and len(application['keys']) > 1):
            return 1

        return 0

    def same_key_string_different_developer_classes(self, application):
        if (self.application_with_multiple_same_apikey_strings(application) == 1):
            developer_class_set = []
            for key in application['keys']:
                if (key['developer_class'] != None):
                    if (key['developer_class']['id'] not in developer_class_set):
                        developer_class_set.append(key['developer_class']['id'])
            
            if (len(developer_class_set) > 1 and len(application['keys']) > 1):
                return 1

        return 0

    def same_key_string_different_statuses(self, application):
        if (self.application_with_multiple_same_apikey_strings(application) == 1):
            status_set = []
            for key in application['keys']:
                if (key['status'] not in status_set):
                    status_set.append(key['status'])

            if (len(status_set) > 1 and len(application['keys']) > 1):
                return 1

        return 0
