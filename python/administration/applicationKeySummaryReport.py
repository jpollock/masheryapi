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

  allApplications = masheryV2.fetch('applications', 'member.username, member.email, name, keys, package_keys', '')

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