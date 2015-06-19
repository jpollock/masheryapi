import sys, urllib, argparse, time, json
sys.path.append( '../lib/' )
import masheryV2, masheryDate, keysReports

def apiName(apis, apiId):
  for api in apis['result']['items']:
    if (api['service_key'] == apiId):
      return api['name']

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
    print method
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
    
    for item in list:
      if ('apikey' in item):
        f.write(item['apikey'])
      elif ('serviceDevKey' in item): 
        f.write(item['serviceDevKey'])
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

  args = parser.parse_args()
  
  apikey = args.apikey
  secret = args.secret
  siteId = args.siteId
  startDate = args.startDate
  endDate = args.endDate
  keyCreateDate = args.keyCreateDate


  if masheryDate.dayGap(startDate, endDate) < 1:
    print 'ERROR: endDate must be at least 1 day past startDate'
    return
  
  allKeys = fetchAllKeys(siteId, apikey, secret)

  writeToFile('keys.csv', allKeys)

  allActiveKeys = fetchAllActiveKeys(siteId, apikey, secret, startDate, endDate)

  writeToFile('active_keys.csv', allActiveKeys)

  inactiveKeys = []
  for key in allKeys:
    if (masheryDate.dayGap(key['created'], keyCreateDate) > 0):
      if hasActivity(key['apikey'], allActiveKeys) == False and key['apikey'] != 'noapikey':
        inactiveKeys.append(key)
  
  writeToFile('inactive_keys.csv', inactiveKeys)
if __name__ == "__main__":
    main(sys.argv[1:])