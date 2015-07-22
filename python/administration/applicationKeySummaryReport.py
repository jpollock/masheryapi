import sys, urllib, argparse, time, json
sys.path.append( '../lib/' )
import masheryV2, masheryDate, keysReports

def fetchAllApplications(siteId, apikey, secret):
  allApplications = []
  result = masheryV2.post(siteId, apikey, secret, '{"method":"object.query","id":1,"params":["select member.username, member.email, name, keys, package_keys from applications ITEMS 10"]}')
  total_pages = result['result']['total_pages']
  page = 1
  while (page < total_pages + 1):
    result = masheryV2.post(siteId, apikey, secret, '{"method":"object.query","id":1,"params":["select member.username, member.email, name, keys, package_keys from applications PAGE ' + str(page) + ' ITEMS 10"]}')
    allApplications.extend(result['result']['items'])
    page = page + 1

  return allApplications

def main(argv):
  
  parser = argparse.ArgumentParser()
  parser.add_argument("apikey", type=str, help="Mashery V2 API Key")
  parser.add_argument("secret", type=str, help="Mashery V2 API Secret")
  parser.add_argument("siteId", type=str, help="Mashery Area/Site ID")
  parser.add_argument("outputFile", type=str, help="Output Filename")

  args = parser.parse_args()
  
  apikey = args.apikey
  secret = args.secret
  siteId = args.siteId
  outputFile = args.outputFile

  allApplications = fetchAllApplications(siteId, apikey, secret)

  f = open(outputFile,'w')
  headers = 'username, email, application_name, num_keys, num_package_keys\n'
  f.write(headers)
  for application in allApplications:
    fieldValues = ''
    fieldValues = fieldValues + '"' + application['member']['username'] + '",'
    fieldValues = fieldValues + '"' + application['member']['email'] + '",'
    fieldValues = fieldValues + '"' + application['name'] + '",'
    fieldValues = fieldValues + str(len(application['keys'])) + ','
    fieldValues = fieldValues + str(len(application['package_keys']))
    f.write(fieldValues + '\n')


if __name__ == "__main__":
    main(sys.argv[1:])