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
  result = masheryV2.post(siteId, apikey, secret, '{"method":"object.query","id":1,"params":["select * from keys ITEMS 1"]}')
  total_items = result['result']['total_items']
  processed_items = 0
  while (processed_items < total_items):
    result = masheryV2.post(siteId, apikey, secret, '{"method":"object.query","id":1,"params":["select *, application, member, service from keys ITEMS 1000"]}')
    allKeys.extend(result['result']['items'])
    processed_items = result['result']['total_items']

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

def main(argv):
  
  parser = argparse.ArgumentParser()
  parser.add_argument("apikey", type=str, help="Mashery V2 API Key")
  parser.add_argument("secret", type=str, help="Mashery V2 API Secret")
  parser.add_argument("siteId", type=str, help="Mashery Area/Site ID")
  parser.add_argument("startDate", type=str, help="Start Date of reporting data")
  parser.add_argument("endDate", type=str, help="End Date of reporting data")
  parser.add_argument("keyCreateDate", type=str, help="Key Create Date, i.e. keys created before this date will be considered for disablement/deletion")
  parser.add_argument('--delete', action='store_true', default=False, help='specify to delete, leave off command to disable')
  parser.add_argument('--nodryrun', action='store_true', default=False, help='specify to perform work, leave off command for dry run')
  parser.add_argument('--archive', type=str, help="Name of file to use for archiving deleted fileds")

  args = parser.parse_args()
  
  apikey = args.apikey
  secret = args.secret
  siteId = args.siteId
  startDate = args.startDate
  endDate = args.endDate
  keyCreateDate = args.keyCreateDate
  delete = args.delete
  dryrun = args.nodryrun
  archive = args.archive


  if masheryDate.dayGap(startDate, endDate) < 1:
    print 'ERROR: endDate must be at least 1 day past startDate'
    return
  
  allKeys = fetchAllKeys(siteId, apikey, secret)

  allActiveKeys = fetchAllActiveKeys(siteId, apikey, secret, startDate, endDate)

  for key in allKeys:
    if (masheryDate.dayGap(key['created'], keyCreateDate) > 0):
      if hasActivity(key['apikey'], allActiveKeys) == False and key['apikey'] != 'noapikey':
        actionType = 'disable'
        if delete == True:
          actionType = 'delete'

        if dryrun == False:
          print 'Need to {actionType} {apikey}, created on {created} for user: {username} ({email})'.format(actionType= actionType, apikey= key['apikey'], created= key['created'], username= key['username'], email= key['member']['email'] if key['member'] != None else '' )
        else:          
          if key['application'] == None or key['application']['is_packaged'] == False:
            print '{actionType}: {apikey}, created on {created} for user: {username} ({email})'.format(actionType= actionType, apikey= key['apikey'], created= key['created'], username= key['username'], email= key['member']['email'] if key['member'] != None else '' )
            print processKey(siteId, apikey, secret, key, delete, archive)


if __name__ == "__main__":
    main(sys.argv[1:])