import sys, urllib, argparse, time, json
import masheryV2, masheryDate, keysReports

def fetchAllDevelopers(siteId, apikey, secret, fields):
  allDevelopers = []
  result = masheryV2.post(siteId, apikey, secret, '{"method":"object.query","id":1,"params":["select ' + ','.join(fields) + ' from members ITEMS 1000"]}')
  total_pages = result['result']['total_pages']
  page = 1
  while (page < total_pages):
    result = masheryV2.post(siteId, apikey, secret, '{"method":"object.query","id":1,"params":["select ' + ','.join(fields) + ' from members PAGE ' + str(page) + ' ITEMS 1000"]}')
    allDevelopers.extend(result['result']['items'])
    page = page + 1

  return allDevelopers

def main(argv):
  
  parser = argparse.ArgumentParser()
  parser.add_argument("apikey", type=str, help="Mashery V2 API Key")
  parser.add_argument("secret", type=str, help="Mashery V2 API Secret")
  parser.add_argument("siteId", type=str, help="Mashery Area/Site ID")
  parser.add_argument("outputFile", type=str, help="Output Filename")
  parser.add_argument('--fields',  nargs='+', help='List of member fields to retrieve, space separated, e.g username email')

  args = parser.parse_args()
  
  apikey = args.apikey
  secret = args.secret
  siteId = args.siteId
  outputFile = args.outputFile
  fields = args.fields

  allDevelopers = fetchAllDevelopers(siteId, apikey, secret, fields)

  f = open(outputFile,'w')
  headers = ','.join(fields) + '\n'
  f.write(headers)

  for developer in allDevelopers:
    fieldValues = ''
    unknown_field = '<UNKNOWN>'

    for field in fields:
      splitFields = field.split('.')
      try:
        if len(splitFields) == 2:
          fieldValues = fieldValues + '"' + developer[splitFields[0]][splitFields[1]] + '",'
        else:
          fieldValues = fieldValues + '"' + developer[splitFields[0]] + '",'
      except TypeError:
        fieldValues = fieldValues + '"' + unknown_field + '",'
      pass

    f.write(fieldValues + '\n')


if __name__ == "__main__":
    main(sys.argv[1:])