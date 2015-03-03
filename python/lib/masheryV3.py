import requests, json
from requests.auth import HTTPBasicAuth

apiHost = 'https://api.mashery.com'
tokenEndpoint = '/v3/token'
resourceEndpoint = '/v3/rest'

def authenticate(apikey, secret, username, password, areaUuid):
    payload = {'grant_type': 'password', 'username' : username, 'password'  : password, 'scope' : areaUuid} 
    response = requests.post(apiHost + tokenEndpoint, auth=HTTPBasicAuth(apikey, secret), data=payload)
    return response.json()['access_token']


def get(token, resource, params):
    headers = {"Content-type": "application/json", "Authorization": 'Bearer ' + token}
    response = requests.get(apiHost + resourceEndpoint + resource + '?' + params, headers=headers)
    return response.json()

def put(token, resource, payload):
    headers = {"Content-type": "application/json", "Authorization": 'Bearer ' + token}
    response = requests.post(apiHost + resourceEndpoint + resource , headers=headers, data=json.dumps(payload))
    return response.json()

def post(token, resource, payload):
    headers = {"Content-type": "application/json", "Authorization": 'Bearer ' + token}
    response = requests.post(apiHost + resourceEndpoint + resource , headers=headers, data=json.dumps(payload))
    return response.json()

def delete(token, resource):
    headers = {"Content-type": "application/json", "Authorization": 'Bearer ' + token}
    response = requests.delete(apiHost + resourceEndpoint + resource , headers=headers)
    return response.status_code