# -*- coding: utf-8 -*-
import os, sys, urllib, argparse, time, codecs
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
  parser.add_argument('--keys',  nargs='+', help='List of keys to include in results, space separated')
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

  # get list of APIs
  all_apis = masheryV2.post(siteId, apikey, secret, '{"method":"object.query","id":1,"params":["select * from services ITEMS 1000"]}')

  keys = args.keys

  if args.keys == None:
    keys = []

  results = []

  dates = masheryDate.daysToReportOn(startDate, endDate)
  
  for api in all_apis['result']['items']:
    try:
      if api['name'] in apis or len(apis) == 0:

        print 'Processing...' + api['name']
        for date in dates:
          urlParams = '&start_date=' + urllib.quote_plus(date[0]) + '&end_date=' + urllib.quote_plus(date[1]) + '&format=json&limit=1000'
          results.extend(masheryV2.get(siteId, apikey, secret, '/reports/calls/developer_activity/service/' + api['service_key'], urlParams))

    except ValueError:
        pass

  UTF8Writer = codecs.getwriter('utf8')
  f = UTF8Writer(open(outputFile,'w'))
  customFields = u'application.name'
  if (args.additionalfields != None):
    customFields = ','.join(args.additionalfields)

  headers = customFields + ',service.id,service.name,key.apikey,startDate,endDate,successful,blocked,other,total\n'

  f.write(headers)
  for result in results:
    if result['serviceDevKey'] in keys or len(keys) == 0:
      key_results = []
      for date in dates:
        urlParams = '&start_date=' + urllib.quote_plus(date[0]) + '&end_date=' + urllib.quote_plus(date[1]) + '&format=json&limit=1000'
        results_for_key = masheryV2.get(siteId, apikey, secret, '/reports/calls/status/service/' + result['serviceKey'] + '/developer/' + result['serviceDevKey'], urlParams)
        if results_for_key != None:
          key_results.extend(results_for_key)

      time.sleep(1) # adding slight delay so as to decrease chances of hitting mashery api qps limits
      key = masheryV2.post(siteId, apikey, secret, '{"method":"object.query","id":1,"params":["select ' + customFields + ' from keys where apikey = \'' + result['serviceDevKey']  + '\'"]}')
      
      unknown_field = '<UNKNOWN>'

      if (key['result']['total_items'] > 0) :
        customFieldValues = ''
        for field in customFields.split(','):
          splitFields = field.split('.')
          
          try:
            if len(splitFields) == 2:
              customFieldValues = customFieldValues + '"' + key['result']['items'][0][splitFields[0]][splitFields[1]] + '",'
            else:
              customFieldValues = customFieldValues + '"' + key['result']['items'][0][splitFields[0]] + '",'
          except TypeError:
            customFieldValues = customFieldValues + '"' + unknown_field + '",'
            pass

        for key_result in key_results:
          outputString = customFieldValues + key_result['serviceKey'] + ',"' + apiName(all_apis, key_result['serviceKey']) + '",'+ key_result['serviceDevKey']  + ',' + key_result['startDate']  + ',' + key_result['endDate']  + ','+ str(key_result['callStatusSuccessful'])  + ',' + str(key_result['callStatusBlocked']) + ',' + str(key_result['callStatusOther']) + ',' + str(key_result['callStatusSuccessful'] +  key_result['callStatusBlocked'] + key_result['callStatusOther'])
          f.write(outputString + '\n')

  f.close()
if __name__ == "__main__":
    main(sys.argv[1:])