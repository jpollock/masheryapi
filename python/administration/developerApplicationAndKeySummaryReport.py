import sys, urllib, argparse, time, json
import masheryV2, masheryDate, keysReports

def fetchAllDevelopers(siteId, apikey, secret):
  allDevelopers = []
  result = masheryV2.post(siteId, apikey, secret, '{"method":"object.query","id":1,"params":["select username, email, applications, keys, package_keys from members ITEMS 10"]}')
  total_pages = result['result']['total_pages']
  page = 1
  while (page < total_pages + 1):
    result = masheryV2.post(siteId, apikey, secret, '{"method":"object.query","id":1,"params":["select username, email, applications, keys, package_keys from members PAGE ' + str(page) + ' ITEMS 10"]}')
    allDevelopers.extend(result['result']['items'])
    page = page + 1

  return allDevelopers

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

  allDevelopers = fetchAllDevelopers(siteId, apikey, secret)

  f = open(outputFile,'w')
  headers = 'username, email, num_applications, num_keys, num_package_keys\n'
  f.write(headers)
  for developer in allDevelopers:
    fieldValues = ''
    fieldValues = fieldValues + '"' + developer['username'] + '",'
    fieldValues = fieldValues + '"' + developer['email'] + '",'
    fieldValues = fieldValues + str(len(developer['applications'])) + ','
    fieldValues = fieldValues + str(len(developer['keys'])) + ','
    fieldValues = fieldValues + str(len(developer['package_keys']))
    f.write(fieldValues + '\n')


if __name__ == "__main__":
    main(sys.argv[1:])