#http://demo3.api.mashery.com/apicommons/businesses/?api_key=sxx53fntq4g3uu8s74pyydjj
import sys, urllib, argparse, time, requests, json, random, hashlib, ssl

def post(area_name, apikey, secret, payload):
    apiHost = 'https://'+area_name+'.admin.mashery.com/proxy'
    resourceEndpoint = ''
    headers = {"Content-type": "application/json"}
    url = apiHost + resourceEndpoint + '?mashery_area=' + area_name + '&apikey=' + apikey + '&sig=' + hash(apikey, secret)
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

def fetch(area_name, apikey, secret, object_type, fields, filter_clause):
  results = []
  try:
    result = post(area_name, apikey, secret, '{"method":"object.query","id":1,"params":["select ' + fields + ' from ' + object_type + ' ' + filter_clause + ' ITEMS 1000"]}')
    results.extend(result['result']['items'])
    total_pages = result['result']['total_pages']
    page = 1
    while (page < total_pages):
      page = page + 1
      result = post(area_name, apikey, secret, '{"method":"object.query","id":1,"params":["select ' + fields + ' from ' + object_type + ' ' + filter_clause + ' PAGE ' + str(page) + ' ITEMS 1000"]}')
      results.extend(result['result']['items'])
      
  except ValueError as err:
    print err

  return results

def create(siteId, apikey, secret, object_type, item_data):
  result = []
  method = '{"method":"' + object_type + '.create","id":1,"params":[' + json.dumps(item_data) + ']}'
  try:
    result = post(siteId, apikey, secret, method)
    return result
  except ValueError as err:
    print err

def update(siteId, apikey, secret, object_type, item_data):
  result = []
  method = '{"method":"' + object_type + '.update","id":1,"params":[' + json.dumps(item_data) + ']}'
  try:
    result = post(siteId, apikey, secret, method)
    return result
  except ValueError as err:
    print err

def delete(siteId, apikey, secret, object_type, item_data):
  result = []
  method = '{"method":"' + object_type + '.delete","id":1,"params":[' + str(item_data['id']) + ']}'
  try:
    result = post(siteId, apikey, secret, method)
    return result
  except ValueError as err:
    print err


def getPackageForService(api, packages):
  for package in packages:
    if (package['name'] == api['name']):
      return package
  return None

def buildPackagedApi(api):
  package = {}
  package['name'] = api['name']
  package['shared_secret_length'] = api['service']['shared_secret_length']
  package['key_length'] = api['service']['key_length']
  #package['key_adapter'] = api['service']['key_adapter']
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

    developer_class['is_self_serve_allowed'] = False # developer classes should not be visible
    package['plans'].append(buildPackagePlan(developer_class))    

  return package

def buildPackagePlan(api):
  print api['name']
  plan = {}
  plan['name'] = api['name']
  plan['package_key_moderation_threshold'] = 100
  plan['package_key_max'] = 100
  plan['qps_limit_package_key_override_allowed'] = api['qps_limit_override_allowed']
  plan['qps_limit_ceiling'] = api['qps_limit_ceiling']
  plan['qps_limit_exempt'] = not api['is_qps_limiting_enabled']
  plan['rate_limit_package_key_override_allowed'] = api['rate_limit_override_allowed']
  plan['rate_limit_ceiling'] = api['rate_limit_ceiling']
  plan['rate_limit_period'] = api['rate_limit_period']
  plan['rate_limit_exempt'] = not api['is_rate_limiting_enabled']
  plan['is_moderated'] = api['is_dashboard_serve_allowed']
  plan['is_public'] = api['is_self_serve_allowed']

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


def main(argv):
  parser = argparse.ArgumentParser()
  parser.add_argument("apikey", type=str, help="Mashery V2 API Key")
  parser.add_argument("secret", type=str, help="Mashery V2 API Secret")
  parser.add_argument("area_name", type=str, help="Mashery Area Name")

  args = parser.parse_args()

  apikey = args.apikey
  secret = args.secret
  area_name = args.area_name

  apis = fetch(area_name, apikey, secret, 'service_definitions', '*, service, service_definition_endpoints, service.service_classes, service.service_classes.developer_class', '')
  packages = fetch(area_name, apikey, secret, 'packages', '*, plans', '')

  for api in apis:
    package = getPackageForService(api, packages)
    if (package != None):
      print 'Deleting...' + package['name']
      delete(area_name, apikey, secret, 'package', package)

    package = buildPackagedApi(api)
    print 'Creating Package...' + package['name']
    print package
    created_package = create(area_name, apikey, secret, 'package', package)
    created_plans = fetch(area_name, apikey, secret, 'plans', '*', 'REQUIRE RELATED package WITH id = ' + str(created_package['result']['id']))

    for plan in created_plans:
      plan_service = buildPlanService(api, plan)
      created_plan_service = create(area_name, apikey, secret, 'plan_service', plan_service)

      plan_endpoints = buildPlanEndpoints(api, plan)
      created_plan_endpoints = create(area_name, apikey, secret, 'plan_endpoint', plan_endpoints)


if __name__ == "__main__":
  main(sys.argv[1:])    
