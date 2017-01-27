import requests, json
from requests.auth import HTTPBasicAuth

class MasheryV3:

    def __init__(self):
        self.protocol = 'https'
        self.api_host = 'api.mashery.com'
        self.token_endpoint = '/v3/token'
        self.resource_endpoint = '/v3/rest'

    def authenticate(self, apikey, secret, username, password, areaUuid):
        payload = {'grant_type': 'password', 'username' : username, 'password'  : password, 'scope' : areaUuid} 
        response = requests.post(self.protocol + '://' + self.api_host + self.token_endpoint, auth=HTTPBasicAuth(apikey, secret), data=payload)
        if response.status_code == 200:
            return response.json()['access_token']
        else:
            return response.json()

    def get_all(self, token, resource, params):
        all_items = []

        headers = {"Content-type": "application/json", "Authorization": 'Bearer ' + token}
        response = requests.get(self.protocol + '://' + self.api_host + self.resource_endpoint + resource + '?' + params, headers=headers)
        total_count = response.headers['X-Total-count']

        offset = 0
        while len(all_items) < int(total_count):
            response = requests.get(self.protocol + '://' + self.api_host + self.resource_endpoint + resource + '?' + params + '&limit=1000&offset=' + str(offset), headers=headers)
            items = response.json()
            all_items.extend(items)
            offset = len(items) + 1
            
        return all_items

    def get(self, token, resource, params):
        headers = {"Content-type": "application/json", "Authorization": 'Bearer ' + token}
        response = requests.get(self.protocol + '://' + self.api_host + self.resource_endpoint + resource + '?' + params, headers=headers)
        return response.json()

    def put(self, token, resource, params, payload):
        headers = {"Content-type": "application/json", "Authorization": 'Bearer ' + token}
        response = requests.put(self.protocol + '://' + self.api_host + self.resource_endpoint + resource + '?' + params, headers=headers, data=json.dumps(payload))
        return response.json()

    def post(self, token, resource, params, payload):
        headers = {"Content-type": "application/json", "Authorization": 'Bearer ' + token}
        response = requests.post(self.protocol + '://' + self.api_host + self.resource_endpoint + resource + '?' + params, headers=headers, data=json.dumps(payload))
        return response.json()

    def delete(self, token, resource):
        headers = {"Content-type": "application/json", "Authorization": 'Bearer ' + token}
        response = requests.delete(self.protocol + '://' + self.api_host + self.resource_endpoint + resource , headers=headers)
        return response.status_code