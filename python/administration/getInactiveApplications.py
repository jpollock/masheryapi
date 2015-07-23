import sys, urllib, argparse, time, json
import masheryV2, masheryDate, keysReports

def apiName(apis, apiId):
  for api in apis['result']['items']:
    if (api['service_key'] == apiId):
      return api['name']

def transform_keys_map_to_applications_map(keys):
    applications = {}

    for key in keys:
        app_id = 0
        if (key['application'] != None):
            app_id = key['application']['id']

        application = {}
        application['id'] = key['application']['id']
        application['name'] = key['application']['name']
        application['is_packaged'] = key['application']['is_packaged']
        application['keys'] = []
        
        if app_id in applications:
            application = applications[app_id]

        new_key = {}
        new_key['apikey'] = key['apikey']
        new_key['service_key'] = key['service_key']
        new_key['created'] = key['created']
        new_key['member'] = {}
        new_key['member']['email'] = key['member']['email']
        application['keys'].append(new_key)

        applications[app_id] = application

    return applications

def processKey(siteId, apikey, secret, key, delete, archive):
  result = ''

  if archive != None:
    f = open(archive,'a')
    f.write(json.dumps(key))
    f.write('\n')
    f.close()

  if delete == True:
    method = '{"method":"key.delete","id":1,"params":[' + str(key['id']) + ']}'
    result = masheryV2.post(siteId, apikey, secret, method)
  else:
    method = '{"method":"key.update","id":1,"params":[{"id": ' + str(key['id']) + ', "status": "disabled"}]}'
    result = masheryV2.post(siteId, apikey, secret, method)

  if result == None:
    return 'Error'
  else:
    return 'Success'

def fetchAllKeys(siteId, apikey, secret):
  allKeys = []
  result = masheryV2.post(siteId, apikey, secret, '{"method":"object.query","id":1,"params":["select * from keys ITEMS 1000"]}')
  total_pages = result['result']['total_pages']
  page = 1
  while (page < total_pages):
    result = masheryV2.post(siteId, apikey, secret, '{"method":"object.query","id":1,"params":["select *, application, member, service from keys PAGE ' + str(page) + ' ITEMS 1000"]}')
    allKeys.extend(result['result']['items'])
    page = page + 1

  return allKeys

def fetchAllActiveKeys(siteId, apikey, secret, startDate, endDate):
  allActiveKeys = []

  # get list of APIs
  all_apis = masheryV2.post(siteId, apikey, secret, '{"method":"object.query","id":1,"params":["select * from services ITEMS 1000"]}')

  for api in all_apis['result']['items']:
    print 'Processing...' + api['name']
    allActiveKeys.extend(keysReports.activityByService(siteId, apikey, secret, startDate, endDate, api['service_key']))

  return allActiveKeys

def hasActivity(apikey, allActiveKeys):
  for activeKey in allActiveKeys:
    if (activeKey['serviceDevKey'] == apikey):
      return True

  return False

def writeToFile(fileName, list):
    f = open(fileName,'w')
    f.write('apikey,service_key,application_id,email')
    f.write('\n')
    for application in list:
        for key in application['keys']:
          f.write(key['apikey'] + ',' + key['service_key'] + ',' + str(application['id']) + ',' + key['member']['email'])
          f.write('\n')
    f.close()


def main(argv):
  
  parser = argparse.ArgumentParser()
  parser.add_argument("apikey", type=str, help="Mashery V2 API Key")
  parser.add_argument("secret", type=str, help="Mashery V2 API Secret")
  parser.add_argument("siteId", type=str, help="Mashery Area/Site ID")
  parser.add_argument("startDate", type=str, help="Start Date of reporting data")
  parser.add_argument("endDate", type=str, help="End Date of reporting data")
  parser.add_argument("keyCreateDate", type=str, help="Key Create Date, i.e. keys created before this date will be considered for disablement/deletion")
  parser.add_argument("outputFile", type=str, help="Output Filename")

  args = parser.parse_args()
  
  apikey = args.apikey
  secret = args.secret
  siteId = args.siteId
  startDate = args.startDate
  endDate = args.endDate
  keyCreateDate = args.keyCreateDate
  outputFile = args.outputFile


  if masheryDate.dayGap(startDate, endDate) < 1:
    print 'ERROR: endDate must be at least 1 day past startDate'
    return
  
  all_keys = fetchAllKeys(siteId, apikey, secret)

  all_applications = transform_keys_map_to_applications_map(all_keys)

  all_active_keys = fetchAllActiveKeys(siteId, apikey, secret, startDate, endDate)

  inactive_applications = []
  for application in all_applications:

      active = False
      application = all_applications[application]
      if (application['is_packaged'] == True):
        continue

      for key in application['keys']:
        if (masheryDate.dayGap(key['created'], keyCreateDate) > 0):
          if hasActivity(key['apikey'], all_active_keys) == True and key['apikey'] != 'noapikey':
            active = True

      if active == False:
        inactive_applications.append(application)
  
  writeToFile(outputFile, inactive_applications)

if __name__ == "__main__":
    main(sys.argv[1:])