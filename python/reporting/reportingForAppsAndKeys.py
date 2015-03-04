import sys, datetime, urllib, argparse
sys.path.append( '../lib/' )
import masheryV2

def dayGap(startDate, endDate):
  dt1 = datetime.datetime.strptime(startDate, "%Y-%m-%dT%H:%M:%SZ")
  dt2 = datetime.datetime.strptime(endDate, "%Y-%m-%dT%H:%M:%SZ")
  return (dt2 - dt1).days  

def sevenDays(startDate, endDate):
  dt1 = datetime.datetime.strptime(startDate, "%Y-%m-%dT%H:%M:%SZ")
  if dayGap(startDate, endDate) > 7:
    dt1 = datetime.datetime.strptime(startDate, "%Y-%m-%dT%H:%M:%SZ")
    dt1_1 = dt1 + datetime.timedelta(days=7)
    endDate = dt1_1.strftime("%Y-%m-%dT%H:%M:%SZ")

  return [startDate, endDate]

def daysToReportOn(startDate, endDate):
  daysToReportOn = []
  
  sevenDayGap = sevenDays(startDate, endDate)
  daysToReportOn.append(sevenDayGap)
  dayGap(sevenDayGap[1], endDate)

  while dayGap(sevenDayGap[1], endDate) > 7:
    sevenDayGap = sevenDays(sevenDayGap[1], endDate)
    daysToReportOn.append(sevenDayGap)
  
  if (dayGap(sevenDayGap[1], endDate) < 7 and sevenDayGap[1] != endDate): 
    daysToReportOn.append([sevenDayGap[1], endDate])

  return daysToReportOn

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
  parser.add_argument('--fields',  nargs='+', help='List of key/app fields to retrieve, space separated')
  args = parser.parse_args()
  
  apikey = args.apikey
  secret = args.secret
  siteId = args.siteId
  startDate = args.startDate
  endDate = args.endDate
  outputFile = args.outputFile

  if dayGap(startDate, endDate) < 1:
    print 'ERROR: endDate must be at least 1 day past startDate'
    return

  apis = args.apis

  if args.apis == None:
    apis = []

  # get list of APIs
  all_apis = masheryV2.post(siteId, apikey, secret, '{"method":"object.query","id":1,"params":["select * from services ITEMS 1000"]}')

  results = []

  dates = daysToReportOn(startDate, endDate)
  print dates
  for api in all_apis['result']['items']:
    try:
        if (len(apis) > 0):
          str(apis.index(api['name'])) + ' ' + api['name']

        print 'Processing...' + api['name']
        for date in dates:
          urlParams = '&start_date=' + urllib.quote_plus(date[0]) + '&end_date=' + urllib.quote_plus(date[1]) + '&format=json&limit=1000'
          results.extend(masheryV2.get(siteId, apikey, secret, '/reports/calls/developer_activity/service/' + api['service_key'], urlParams))

    except ValueError:
        pass

  f = open(outputFile,'w')
  customFields = 'application.name'
  if (args.fields != None):
    customFields = ','.join(args.fields)

  headers = customFields + ',service.name,service.id,key.apikey,startDate,endDate,successful,blocked,other,total\n'

  f.write(headers)
  for result in results:
    try:
        key = masheryV2.post(siteId, apikey, secret, '{"method":"object.query","id":1,"params":["select ' + customFields + ' from keys where apikey = \'' + result['serviceDevKey']  + '\'"]}')

        application_name = '<UNKNOWN>'
        if (key['result']['total_items'] > 0) :            
            if (args.fields != None):
              customFieldValues = ''
              for field in args.fields:
                splitFields = field.split('.')
                if len(splitFields) == 2:
                  customFieldValues = customFieldValues + '"' + key['result']['items'][0][splitFields[0]][splitFields[1]] + '",'
                else:
                  customFieldValues = customFieldValues + '"' + key['result']['items'][0][splitFields[0]] + '",'

        f.write(customFieldValues + result['serviceKey'] + ',"' + apiName(all_apis, result['serviceKey']) + '",'+ result['serviceDevKey']  + ',' + result['startDate']  + ',' + result['endDate']  + ','+ str(result['callStatusSuccessful'])  + ',' + str(result['callStatusBlocked']) + ',' + str(result['callStatusOther']) + ',' + str(result['callStatusSuccessful'] +  result['callStatusBlocked'] + result['callStatusOther']) + '\n')
    except TypeError:
        pass

  f.close()
if __name__ == "__main__":
    main(sys.argv[1:])