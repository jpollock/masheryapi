import requests, json, hashlib, time

apiHost = 'http://api.mashery.com'

def get(siteId, apikey, secret, resource, params):
    resourceEndpoint = '/v2/rest'
    url = apiHost + resourceEndpoint + '/' + siteId + resource + '?apikey=' + apikey + '&sig=' + hash(apikey, secret) + params
    response = requests.get(url)
    if (response.status_code == 200):
        return response.json()
    else:
        return None

def post(siteId, apikey, secret, payload):
    resourceEndpoint = '/v2/json-rpc'
    headers = {"Content-type": "application/json"}
    url = apiHost + resourceEndpoint + '/' + siteId + '?apikey=' + apikey + '&sig=' + hash(apikey, secret)
    response = requests.post(url, headers=headers, data=payload)
    return response.json()

def hash(apikey, secret):
    authHash = hashlib.md5();
    temp = str.encode(apikey + secret + repr(int(time.time())))
    authHash.update(temp)
    return authHash.hexdigest()