import json

class Validator:

    def __init__(self, logger):
        self.logger = logger

    def get_api_for_key(self, apis, key_data):
        for api in apis:
            if (api['service_key'] == key_data['service_key']):
                return api

        return None

    def get_package_plan_for_key(self, packages, key):
        for plan in packages:
            if (plan['id'] == key['plan_id'] and plan['package']['id'] == key['package_id']):
                return plan

        return None

    def plan_contains_service(self, package_data, service_of_key):
        for plan_service in package_data['plan_services']:
            if (plan_service['service_definition']['service_key'] == service_of_key):
                return True

        return False

    def validate_application_to_migrate(self, application, apis, packages):
        # tests:
        #   1) make sure that the app's keys, if same key string, all point to same package and plan
        #   2) make sure that the app's keys are not pointing to a plan that doesn't contain the service
        '''apikeys = {}
        for key in application['keys']:
            if (key['apikey'] not in apikeys):
                apikeys[key['apikey']] = key
            else:
                if (key['package_id'] != apikeys[key['apikey']]['package_id'] or key['plan_id'] != apikeys[key['apikey']]['plan_id']):
                    return False
        '''
        if (application == None):
            return False

        for key in application['keys']:
            if ('package_id' not in key or 'plan_id' not in key or 'service_key' not in key):
                self.logger.warn('Application %s has keys assigned to a service without a match for a package plan, key=%s', str(application['id']), json.dumps(key))                
                return False

            # fetch api data
            api_data = self.get_api_for_key(apis, key)
            if (api_data == None):
                self.logger.error('Problem fetching api: %s', json.dumps(key))
                return False

            # fetch package data
            plan_data = self.get_package_plan_for_key(packages, key)
            if (plan_data == None):
                self.logger.error('Problem fetching package plan: %s', json.dumps(key))
                return False

            if (plan_data['package']['name'] != (api_data['name'] + '- created for Mashery Packager Migration')):
                self.logger.error('Mismatch on package and api name: %s %s', plan_data['package']['name'], api_data['name'])
                return False

            if (self.plan_contains_service(plan_data, key['service_key']) == False):
                self.logger.error('Package %s Plan %s does not contain service: %s', plan_data['package']['name'], plan_data['name'], key['service_key'])
                return False

        return True

    def validate_package_key(self, package_key_data, key_data_from_backup):
        valid = True
        for val in package_key_data:
            if (val == 'object_type' or val == 'uuid' or val == 'id' or val == 'created' or val == 'updated' or val == 'member' or val == 'application' or val == 'package' or val == 'plan' or val == 'service'):
                continue

            
            n_val = package_key_data[val]
            if val in key_data_from_backup:
                o_val = key_data_from_backup[val]
                if (val == 'limits'):
                    for n_limit in n_val:
                        n_period = n_limit['period']
                        n_ceiling = n_limit['ceiling']
                        for o_limit in o_val:
                            o_period = o_limit['period']
                            if n_period == o_period:
                                o_ceiling = o_limit['ceiling']
                                if n_ceiling != o_ceiling:
                                    self.logger.error('Package Key Mismatch : %s : %s %s', val, o_val, n_val)
                                    valid = False

                else:
                    if (n_val != o_val):
                        #print val + ' ' + str(o_val) + ' ' + str(n_val)
                        self.logger.error('Package Key Mismatch : %s : %s %s', val, o_val, n_val)
                        valid = False
            else:
                valid = False

        return valid


    def validate_service_key(self, key_data, key_data_from_backup):
        for val in key_data:
            if (val == 'uuid' or val == 'id' or val == 'created' or val == 'updated' or val == 'member' or val == 'application' or val == 'service' or val == 'developer_class'):
                continue

            n_val = key_data[val]
            if val in key_data_from_backup:
                o_val = key_data_from_backup[val]
                if (n_val != o_val):
                    self.logger.error('Key Mismatch : %s : %s %s', val, o_val, n_val)                
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

        # Removing this check; only necessary for consolidation of keys feature
        #if (area_validation_data['applications_with_multiple_same_apikey_strings']['same_key_string_different_statuses'] > 0):
        #    return False

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
                self.logger.info('same_key_string_different_developer_classes : %s', json.dumps(application))
                return 1

        return 0

    def same_key_string_different_statuses(self, application):
        if (self.application_with_multiple_same_apikey_strings(application) == 1):
            status_set = []
            for key in application['keys']:
                if (key['status'] not in status_set):
                    status_set.append(key['status'])

            if (len(status_set) > 1 and len(application['keys']) > 1):
                self.logger.info('same_key_string_different_statuses : %s', json.dumps(application))
                return 1

        return 0
