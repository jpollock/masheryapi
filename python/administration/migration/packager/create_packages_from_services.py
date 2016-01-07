# to run the program: python create_packages_from_services.py [-h] apikey secret env area_name input_filename
import sys, urllib, argparse, time, requests, json, random, hashlib, ssl
import logger
        
def post(env, area_name, apikey, secret, payload):
    if (env == "Production"):
        dashboard_domain_suffix = '.admin.mashery.com'
    else:
        dashboard_domain_suffix = '-dash.qasandbox.mashery.com'
        
    dashboard_domain = area_name + dashboard_domain_suffix
    apiHost = 'https://' + dashboard_domain + '/proxy'
    print apiHost

    resourceEndpoint = ''
    headers = {"Content-type": "application/json"}
    url = apiHost + resourceEndpoint + '?mashery_area=' + area_name + '&apikey=' + apikey + '&sig=' + hash(apikey, secret)
    print url
    response = requests.post(url, headers=headers, data=payload, verify=False)
    if (response.status_code == 200):
        return response.json()
    else:
        print response.json()
        raise ValueError(response.json()['error']['message']) 

def hash(apikey, secret):
    authHash = hashlib.md5();
    temp = str.encode(apikey + secret + repr(int(time.time())))
    authHash.update(temp)
    return authHash.hexdigest()

def fetch(env, area_name, apikey, secret, object_type, fields, filter_clause):
    results = []
    try:
        result = post(env, area_name, apikey, secret, '{"method":"object.query","id":1,"params":["select ' + fields + ' from ' + object_type + ' ' + filter_clause + ' ITEMS 1000"]}')
        results.extend(result['result']['items'])
        total_pages = result['result']['total_pages']
        page = 1
        while (page < total_pages):
            page = page + 1
            result = post(env, area_name, apikey, secret, '{"method":"object.query","id":1,"params":["select ' + fields + ' from ' + object_type + ' ' + filter_clause + ' PAGE ' + str(page) + ' ITEMS 1000"]}')
            results.extend(result['result']['items'])
            
    except ValueError as err:
        print err

    return results

def create(env, siteId, apikey, secret, object_type, item_data):
    result = []
    method = '{"method":"' + object_type + '.create","id":1,"params":[' + json.dumps(item_data) + ']}'
    try:
        result = post(env, siteId, apikey, secret, method)
        return result
    except ValueError as err:
        print err

def update(env, siteId, apikey, secret, object_type, item_data):
    result = []
    method = '{"method":"' + object_type + '.update","id":1,"params":[' + json.dumps(item_data) + ']}'
    try:
        result = post(env, siteId, apikey, secret, method)
        return result
    except ValueError as err:
        print err

def delete(env, siteId, apikey, secret, object_type, item_data):
    result = []
    method = '{"method":"' + object_type + '.delete","id":1,"params":[' + str(item_data['id']) + ']}'
    try:
        result = post(env, siteId, apikey, secret, method)
        return result
    except ValueError as err:
        print err


def getPackageForService(api, packages):
    for package in packages:
        if (package['name'] == api['name']):
            return package
    return None

def getEmailTemplateForService(api, email_template_sets):
    for email_template_set in email_template_sets:
        if (email_template_set['name'] == api['name']):
            return email_template_set
    return None

def uniqueNotifyAdminEmails(notifications):
    notify_admin_emails = notifications['warn_to'].split('\n')
    notify_admin_emails.extend(notifications['warn_cc'].split('\n'))
    notify_admin_emails.extend(notifications['warn_bcc'].split('\n'))
    notify_admin_emails.extend(notifications['over_to'].split('\n'))
    notify_admin_emails.extend(notifications['over_cc'].split('\n'))
    notify_admin_emails.extend(notifications['over_bcc'].split('\n'))
    notify_admin_emails.extend(notifications['qps_to'].split('\n'))
    notify_admin_emails.extend(notifications['qps_cc'].split('\n'))
    notify_admin_emails.extend(notifications['qps_bcc'].split('\n'))

    notify_admin_emails_set = set(notify_admin_emails)

    return list(notify_admin_emails_set)

def buildPackagedApi(api, notifications):
    package = {}
    package['name'] = api['name']
    package['shared_secret_length'] = api['service']['shared_secret_length']
    package['key_length'] = api['service']['key_length']
    package['key_adapter'] = api['service']['key_adapter']
    package['near_quota_threshold'] = int(notifications['warn_percent'])

    package['notify_admin_emails'] = ','.join(uniqueNotifyAdminEmails(notifications))

    if notifications['warn_admin'] == '1':
        package['notify_admin_near_quota'] = True
    else:
        package['notify_admin_near_quota'] = False    

    if notifications['over_admin'] == '1':
        package['notify_admin_over_quota'] = True
    else:
        package['notify_admin_over_quota'] = False    

    if notifications['qps_admin'] == '1':
        package['notify_admin_over_throttle'] = True
    else:
        package['notify_admin_over_throttle'] = False    


    if notifications['warn_devel'] == '1':
        package['notify_developer_near_quota'] = True
    else:
        package['notify_developer_near_quota'] = False

    if notifications['over_devel'] == '1':
        package['notify_developer_over_quota'] = True
    else:
        package['notify_developer_over_quota'] = False

    if notifications['qps_devel'] == '1':
        package['notify_developer_over_throttle'] = True
    else:
        package['notify_developer_over_throttle'] = False

    notify_period = ''
    notify_period = notifications['warn_delay']

    if notifications['over_delay'] != '' and notify_period == '':
        notify_period = notifications['over_delay']

    if notifications['qps_delay'] != '' and notify_period == '':
        notify_period = notifications['qps_delay']

    package['notify_admin_period'] = notify_period
    package['notify_developer_period'] = notify_period

    package['plans'] = []

    service = api['service']
    package['plans'].append(buildPackagePlan(service))

    for service_class in service['service_classes']:
        developer_class = service_class['developer_class']
        if (not('qps_limit_override_allowed' in developer_class)):
            developer_class['qps_limit_override_allowed'] = service['qps_limit_override_allowed']
        if (not('rate_limit_override_allowed' in developer_class)):
            developer_class['rate_limit_override_allowed'] = service['rate_limit_override_allowed']
        if (not('qps_limit_ceiling' in developer_class)):
            developer_class['qps_limit_ceiling'] = service['qps_limit_ceiling']
        if (not('is_qps_limiting_enabled' in developer_class)):
            developer_class['is_qps_limiting_enabled'] = service['is_qps_limiting_enabled']
        if (not('rate_limit_ceiling' in developer_class)):
            developer_class['rate_limit_ceiling'] = service['rate_limit_ceiling']
        if (not('rate_limit_period' in developer_class)):
            developer_class['rate_limit_period'] = service['rate_limit_period']
        if (not('is_rate_limiting_enabled' in developer_class)):
            developer_class['is_rate_limiting_enabled'] = service['is_rate_limiting_enabled']
        if (not('is_dashboard_serve_allowed' in developer_class)):
            developer_class['is_dashboard_serve_allowed'] = service['is_dashboard_serve_allowed']
        if (not('is_self_serve_allowed' in developer_class)):
            developer_class['is_self_serve_allowed'] = service['is_self_serve_allowed']
        if (not('key_moderation_threshold' in developer_class)):
            developer_class['key_moderation_threshold'] = service['key_moderation_threshold']
        if (not('max_keys_per_user' in developer_class)):
            developer_class['max_keys_per_user'] = service['max_keys_per_user']

        developer_class['is_self_serve_allowed'] = False # developer classes should not be visible        
        package['plans'].append(buildPackagePlan(developer_class))        

    return package

def buildPackagePlan(api):
    plan = {}
    plan['name'] = api['name']
    plan['package_key_moderation_threshold'] = api['key_moderation_threshold']
    plan['package_key_max'] = api['max_keys_per_user']
    plan['qps_limit_package_key_override_allowed'] = api['qps_limit_override_allowed']
    if api['is_qps_limiting_enabled'] == True:
        plan['qps_limit_ceiling'] = api['qps_limit_ceiling']
    else: 
        plan['qps_limit_ceiling'] = 0
        
    plan['qps_limit_exempt'] = not api['is_qps_limiting_enabled']
    plan['rate_limit_package_key_override_allowed'] = api['rate_limit_override_allowed']
    if api['is_rate_limiting_enabled'] == True:
        plan['rate_limit_ceiling'] = api['rate_limit_ceiling']
    else:
        plan['rate_limit_ceiling'] = 0

    plan['rate_limit_period'] = api['rate_limit_period']
    plan['rate_limit_exempt'] = not api['is_rate_limiting_enabled']
    plan['is_moderated'] = api['is_dashboard_serve_allowed']
    if (api['is_self_serve_allowed'] == True):
        print ""
        print "Self Service Provisioning is enabled for " + api['name']
        print ""
    plan['is_public'] = False

    return plan

def buildPlanService(api, plan):
    plan_services = []
    plan_service = {}
    plan_service['plan'] = plan
    plan_service['service_definition'] = api
    plan_services.append(plan_service)
    return plan_services

def buildPlanEndpoints(api, plan):
    plan_endpoints = []
    for endpoint in api['service_definition_endpoints']:
        plan_endpoint = {}
        plan_endpoint['plan'] = plan
        plan_endpoint['service_definition_endpoint'] = endpoint
        plan_endpoint['allow_undefined_methods'] = True
        plan_endpoints.append(plan_endpoint)
    return plan_endpoints

def fetch_service_configs(file):
    services = {}
    f = open(file, 'r')
    for line in f:
        service_json = json.loads(line)
        services[service_json['spkey']] = service_json
    return services

def getEmailTemplate(api_config):
    email_template_set = {}
    email_template_set['name'] = api_config['moniker']
    email_template_set['type'] = 'package_notification_template'
    email_template_set['email_templates'] = []

    email_template = {}
    email_template['type'] = 'over_throttle'
    email_template['body'] = api_config['notifications']['qps_body']
    email_template['from'] = api_config['notifications']['qps_from']
    email_template['subject'] = api_config['notifications']['qps_subj']
    email_template_set['email_templates'].append(email_template)

    email_template = {}
    email_template['type'] = 'over_quota'
    email_template['body'] = api_config['notifications']['over_body']
    email_template['from'] = api_config['notifications']['over_from']
    email_template['subject'] = api_config['notifications']['over_subj']
    email_template_set['email_templates'].append(email_template)

    email_template = {}
    email_template['type'] = 'near_quota'
    email_template['body'] = api_config['notifications']['warn_body']
    email_template['from'] = api_config['notifications']['warn_from']
    email_template['subject'] = api_config['notifications']['warn_subj']
    email_template_set['email_templates'].append(email_template)

    return email_template_set


def main(argv):
    requests.packages.urllib3.disable_warnings()
    global loggerMigrator
    loggerMigrator =    logger.setup('migrator', 'myapp.log')

    parser = argparse.ArgumentParser()
    parser.add_argument("apikey", type=str, help="Mashery V2 API Key")
    parser.add_argument("secret", type=str, help="Mashery V2 API Secret")
    parser.add_argument("env", type=str, help="Is this a production area or a sandbox area")
    parser.add_argument("area_name", type=str, help="Mashery Area Name")
    parser.add_argument("config_file", type=str, help="Full path to config file containing json blobs")
    

    args = parser.parse_args()

    apikey = args.apikey
    secret = args.secret
    env = args.env
    area_name = args.area_name
    config_file = args.config_file

    print 'APIKey: ' + apikey
    print 'Secret: ' + secret
    print 'Production? ' + env 
    print "Area: " + area_name

    apis = fetch(env, area_name, apikey, secret, 'service_definitions', '*, service, service_definition_endpoints, service.service_classes, service.service_classes.developer_class', '')
    api_configs = fetch_service_configs(config_file)
    packages = fetch(env, area_name, apikey, secret, 'packages', '*, plans', '')
    email_template_sets = fetch(env, area_name, apikey, secret, 'email_template_sets', '*', '')

    for api in apis:
        print api['service_key']
        api_config = []
        if api['service_key'] in api_configs:
            api_config = api_configs[api['service_key']]
        else:
            return

        package = getPackageForService(api, packages)
        if (package != None):
            print 'Deleting Package...' + package['name']
            delete(env, area_name, apikey, secret, 'package', package)

        email_template_set = getEmailTemplateForService(api, email_template_sets)
        if (email_template_set != None):
            print 'Deleting Email Template Set..' + email_template_set['name']
            delete(env, area_name, apikey, secret, 'email_template_set', email_template_set)

        email_template_set = getEmailTemplate(api_config)
        created_email_template_set = create(env, area_name, apikey, secret, 'email_template_set', email_template_set)
        package = buildPackagedApi(api, api_config['notifications'])
        print 'Creating Package...' + package['name']

        created_package = create(env, area_name, apikey, secret, 'package', package)
        created_plans = fetch(env, area_name, apikey, secret, 'plans', '*', 'REQUIRE RELATED package WITH id = ' + str(created_package['result']['id']))

        for plan in created_plans:
            if created_email_template_set != None:
                email_template_set_t = {}
                email_template_set_t['id'] = created_email_template_set['result']['id']
                plan['email_template_set'] = email_template_set_t

            update(env, area_name, apikey, secret, 'plan', plan)
            plan_service = buildPlanService(api, plan)
            created_plan_service = create(env, area_name, apikey, secret, 'plan_service', plan_service)

            plan_endpoints = buildPlanEndpoints(api, plan)
            created_plan_endpoints = create(env, area_name, apikey, secret, 'plan_endpoint', plan_endpoints)

    return

if __name__ == "__main__":
    main(sys.argv[1:])        
