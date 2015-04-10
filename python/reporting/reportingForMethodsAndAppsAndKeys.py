import sys, urllib, argparse
sys.path.append( '../lib/' )
import masheryV2, masheryDate

def apiName(apis, apiId):
  for api in apis['result']['items']:
    if (api['service_key'] == apiId):
      return api['name']

def main(argv):
  
  parser = argparse.ArgumentParser()
  parser.add_argument("apikey", type=str, help="Mashery V2 API Key")
  parser.add_argument("secret", type=str, help="Mashery V2 API Secret")
  parser.add_argument("siteId", type=str, help="Mashery Area/Site ID")
  parser.add_argument("startDate", type=str, help="Start Date")
  parser.add_argument("endDate", type=str, help="End Date")
  parser.add_argument("outputFile", type=str, help="Output Filename")
  parser.add_argument('--apis',  nargs='+', help='List of APIs by name, space separated')
  parser.add_argument('--additionalfields',  nargs='+', help='List of key/app fields to retrieve, space separated')
  args = parser.parse_args()
  
  apikey = args.apikey
  secret = args.secret
  siteId = args.siteId
  startDate = args.startDate
  endDate = args.endDate
  outputFile = args.outputFile

  if masheryDate.dayGap(startDate, endDate) < 1:
    print 'ERROR: endDate must be at least 1 day past startDate'
    return

  apis = args.apis

  if args.apis == None:
    apis = []

  all_apis = masheryV2.post(siteId, apikey, secret, '{"method":"object.query","id":1,"params":["select * from services ITEMS 1000"]}')

  results = []

  dates = masheryDate.daysToReportOn(startDate, endDate)
  
  for api in all_apis['result']['items']:
    try:
      if api['name'] in apis or len(apis) == 0:

        print 'Processing...' + api['name']
        for date in dates:
          urlParams = '&start_date=' + urllib.quote_plus(date[0]) + '&end_date=' + urllib.quote_plus(date[1]) + '&format=json&limit=1000'
          apiReportingResults = masheryV2.get(siteId, apikey, secret, '/reports/calls/developer_activity/service/' + api['service_key'], urlParams)
          for apiReportingResult in apiReportingResults:
            if (apiReportingResult['serviceDevKey'] != 'unknown'):
              developerReportingResult = masheryV2.get(siteId, apikey, secret, '/reports/calls/methods/service/' + api['service_key'] +  '/developer/' + apiReportingResult['serviceDevKey'], urlParams)
              if (developerReportingResult != None):
                results.extend(developerReportingResult)

    except ValueError:
        pass

  f = open(outputFile,'w')
  f.write('Application,API ID,API Name,API Method,Key,Start Date,End Date,Calls\n')
  for result in results:
    try:
        key = masheryV2.post(siteId, apikey, secret, '{"method":"object.query","id":1,"params":["select *, application from keys where apikey = \'' + result['serviceDevKey']  + '\'"]}')

        application_name = '<UNKNOWN>'
        if (key['result']['total_items'] > 0) :
            application_name = key['result']['items'][0]['application']['name']
        f.write('"' + application_name + '",' + result['serviceKey'] + ',"' + apiName(all_apis, result['serviceKey']) + '","'+  result['apiMethod']+ '",'+ result['serviceDevKey']  + ',' + result['startDate']  + ',' + result['endDate']  + ','+ str(result['methodCount']) + '\n')
    except TypeError:
        pass

  f.close()
if __name__ == "__main__":
    main(sys.argv[1:])