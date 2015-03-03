import sys, datetime, urllib
sys.path.append( '../../masheryapi/python' )
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
  
  if (dayGap(sevenDayGap[1], endDate) < 7): 
    daysToReportOn.append([sevenDayGap[1], endDate])

  return daysToReportOn

def apiName(apis, apiId):
  for api in apis['result']['items']:
    if (api['service_key'] == apiId):
      return api['name']

def main(argv):
  
  if (len(sys.argv) < 7 or len(sys.argv) > 8):
    print 'Usage: python reportingForAppsAndKeys.py <Mashery V2 API Key> <Mashery V2 API Secret> <Mashery Area/Site ID> <Start Date> <End Date> <Output Filename> <List of APIs (optional)>'
    return

  apikey = sys.argv[1] 
  secret = sys.argv[2]
  siteId = sys.argv[3]
  startDate = sys.argv[4]
  endDate = sys.argv[5]
  outputFile = sys.argv[6]

  apis = []

  if len(sys.argv) == 8:
    apis = sys.argv[7]

  all_apis = masheryV2.post(siteId, apikey, secret, '{"method":"object.query","id":1,"params":["select * from services ITEMS 1000"]}')

  results = []

  dates = daysToReportOn(startDate, endDate)

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
  f.write('Application,API ID,API Name,Key,Start Date,End Date,Successful,Blocked,Other,Total\n')
  for result in results:
    try:

        key = masheryV2.post(siteId, apikey, secret, '{"method":"object.query","id":1,"params":["select *, application from keys where apikey = \'' + result['serviceDevKey']  + '\'"]}')

        application_name = '<UNKNOWN>'
        if (key['result']['total_items'] > 0) :
            application_name = key['result']['items'][0]['application']['name']
        f.write('"' + application_name + '",' + result['serviceKey'] + ',"' + apiName(all_apis, result['serviceKey']) + '",'+ result['serviceDevKey']  + ',' + result['startDate']  + ',' + result['endDate']  + ','+ str(result['callStatusSuccessful'])  + ',' + str(result['callStatusBlocked']) + ',' + str(result['callStatusOther']) + ',' + str(result['callStatusSuccessful'] +  result['callStatusBlocked'] + result['callStatusOther']) + '\n')
    except TypeError:
        pass

  f.close()
if __name__ == "__main__":
    main(sys.argv[1:])