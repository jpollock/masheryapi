# -*- coding: utf-8 -*-
import sys, urllib, argparse, time, json
from base import Base

def main(argv):
  
  parser = argparse.ArgumentParser()
  parser.add_argument("apikey", type=str, help="Mashery V2 API Key")
  parser.add_argument("secret", type=str, help="Mashery V2 API Secret")
  parser.add_argument("siteId", type=str, help="Mashery Area/Site ID")
  parser.add_argument("outputFile", type=str, help="Output Filename")
  parser.add_argument('--fields',  nargs='+', help='List of key fields to retrieve, space separated, e.g username email')

  args = parser.parse_args()
  
  apikey = args.apikey
  secret = args.secret
  siteId = args.siteId
  outputFile = args.outputFile
  fields = args.fields

  masheryV2 = Base('https', 'api.mashery.com', siteId, apikey, secret)

  allKeys = masheryV2.fetch('keys', ','.join(fields), '')

  f = open(outputFile,'w')
  headers = ','.join(fields) + '\n'
  f.write(headers)

  for key in allKeys:
    fieldValues = ''
    unknown_field = '<UNKNOWN>'
    for field in fields:
      splitFields = field.split('.')
      try:
        if field == 'limits':
          t_fieldValues = ''
          t_fieldValues = t_fieldValues + str(key[field][0]['ceiling']) + ' calls per ' + key[field][0]['period'] + ' and '
          t_fieldValues = t_fieldValues + str(key[field][1]['ceiling']) + ' calls per ' + key[field][1]['period']

          fieldValues = fieldValues + '"' + t_fieldValues + '",'
        else:
          if len(splitFields) == 2:
            value = key[splitFields[0]][splitFields[1]]
            fieldValues = fieldValues + '"' + unicode(value) + '",'
          else:
            fieldValues = fieldValues + '"' + unicode(key[splitFields[0]]) + '",'
      except TypeError:
        fieldValues = fieldValues + '"' + unknown_field + '",'
        pass
    fieldValues = fieldValues + '\n'
    f.write(fieldValues.encode('utf8'))


if __name__ == "__main__":
    main(sys.argv[1:])