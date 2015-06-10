import masheryV2, json


def fetch(site_id, apikey, secret, object_type, fields, filter_clause):
  results = []
  try:
    result = masheryV2.post(site_id, apikey, secret, '{"method":"object.query","id":1,"params":["select ' + fields + ' from ' + object_type + ' ' + filter_clause + ' ITEMS 1000"]}')
    results.extend(result['result']['items'])
    total_pages = result['result']['total_pages']
    page = 1
    while (page < total_pages):
      page = page + 1
      result = masheryV2.post(site_id, apikey, secret, '{"method":"object.query","id":1,"params":["select ' + fields + ' from ' + object_type + ' ' + filter_clause + ' PAGE ' + str(page) + ' ITEMS 1000"]}')
      results.extend(result['result']['items'])
      
  except ValueError as err:
    print err

  return results

def create(siteId, apikey, secret, object_type, item_data):
  result = []
  method = '{"method":"' + object_type + '.create","id":1,"params":[' + json.dumps(item_data) + ']}'
  try:
    result = masheryV2.post(siteId, apikey, secret, method)
    return result
  except ValueError as err:
    print err

def update(siteId, apikey, secret, object_type, item_data):
  result = []
  method = '{"method":"' + object_type + '.update","id":1,"params":[' + json.dumps(item_data) + ']}'
  try:
    result = masheryV2.post(siteId, apikey, secret, method)
    return result
  except ValueError as err:
    print err

def delete(siteId, apikey, secret, object_type, item_data):
  result = []
  method = '{"method":"' + object_type + '.delete","id":1,"params":[' + str(item_data['id']) + ']}'
  try:
    result = masheryV2.post(siteId, apikey, secret, method)
    return result
  except ValueError as err:
    print err