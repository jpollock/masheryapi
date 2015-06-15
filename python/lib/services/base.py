import json
from masheryV2 import MasheryV2

class Base:

    def __init__(self, protocol, api_host, site_id, apikey, secret):
        self.masheryV2 = MasheryV2(protocol, api_host)
        self.site_id = site_id
        self.apikey = apikey
        self.secret = secret


    def area_fetch(self):
      try:
        result = self.masheryV2.post(self.site_id, self.apikey, self.secret, '{"method":"area.fetch","id":1,"params":[]}')
        return result['result']

      except ValueError as err:
        raise ValueError(err.args)

      return None


    def fetch(self, object_type, fields, filter_clause):
      results = []
      try:
        result = self.masheryV2.post(self.site_id, self.apikey, self.secret, '{"method":"object.query","id":1,"params":["select ' + fields + ' from ' + object_type + ' ' + filter_clause + ' ITEMS 1000"]}')
        results.extend(result['result']['items'])
        total_pages = result['result']['total_pages']
        page = 1
        while (page < total_pages):
          page = page + 1
          result = self.masheryV2.post(self.site_id, self.apikey, self.secret, '{"method":"object.query","id":1,"params":["select ' + fields + ' from ' + object_type + ' ' + filter_clause + ' PAGE ' + str(page) + ' ITEMS 1000"]}')
          results.extend(result['result']['items'])
          
      except ValueError as err:
        raise ValueError(err.args)

      return results

    def create(self, object_type, item_data):
      result = []
      method = '{"method":"' + object_type + '.create","id":1,"params":[' + json.dumps(item_data) + ']}'
      try:
        result = self.masheryV2.post(self.site_id, self.apikey, self.secret, method)
        return result
      except ValueError as err:
        raise ValueError(err.args)

    def update(self, object_type, item_data):
      result = []
      method = '{"method":"' + object_type + '.update","id":1,"params":[' + json.dumps(item_data) + ']}'
      try:
        result = self.masheryV2.post(self.site_id, self.apikey, self.secret, method)
        return result
      except ValueError as err:
        raise ValueError(err.args)

    def delete(self, object_type, item_data):
      result = []
      method = '{"method":"' + object_type + '.delete","id":1,"params":[' + json.dumps(item_data) + ']}'
      try:
        result = self.masheryV2.post(self.site_id, self.apikey, self.secret, method)
        return result
      except ValueError as err:
        raise ValueError(err.args)