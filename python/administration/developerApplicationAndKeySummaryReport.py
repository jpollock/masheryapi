import sys, urllib, argparse, time, json
from base import Base

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

  masheryV2 = Base('https', 'api.mashery.com', siteId, apikey, secret)

  allDevelopers = masheryV2.fetch('members', 'username, email, applications, keys, package_keys', '')

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