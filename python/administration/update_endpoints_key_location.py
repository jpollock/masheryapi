# -*- coding: utf-8 -*-
import sys, argparse, json
from masheryV3 import MasheryV3

class UpdateEndpointDomains():
    masheryV3 = MasheryV3()
    token = None

    def authenticate(self, mashery_api_key, mashery_api_secret, mashery_username, mashery_password, mashery_area_uuid):
        self.token = self.masheryV3.authenticate(mashery_api_key, mashery_api_secret, mashery_username, mashery_password, mashery_area_uuid)
        if 'error' in self.token:
            print self.token
            return

    def get_apis(self):
        apis = self.masheryV3.get(self.token, '/services', 'limit=1000&fields=id')
        if 'errorCode' in apis:
            print apis
            return
        return apis

    def get_api(self, api_id):
        api = self.masheryV3.get(self.token, '/services/' + api_id, 'fields=id,name,description,securityProfile,qpsLimitOverall,rfc3986Encode,version,cache,endpoints.inboundSslRequired,endpoints.jsonpCallbackParameter,endpoints.jsonpCallbackParameterValue,endpoints.scheduledMaintenanceEvent,endpoints.forwardedHeaders,endpoints.returnedHeaders,endpoints.id,endpoints.name,endpoints.numberOfHttpRedirectsToFollow,endpoints.outboundRequestTargetPath,endpoints.outboundRequestTargetQueryParameters,endpoints.outboundTransportProtocol,endpoints.processor,endpoints.publicDomains,endpoints.requestAuthenticationType,endpoints.requestPathAlias,endpoints.requestProtocol,endpoints.oauthGrantTypes,endpoints.supportedHttpMethods,endpoints.apiMethodDetectionLocations,endpoints.apiMethodDetectionKey,endpoints.systemDomainAuthentication,endpoints.setpassword.setPassword,endpoints.systemDomains,endpoints.trafficManagerDomain,endpoints.updated,endpoints.useSystemDomainCredentials,endpoints.systemDomainCredentialKey,endpoints.systemDomainCredentialSecret,endpoints.methods,endpoints.methods.id,endpoints.methods.name,endpoints.methods.sampleXmlResponse,endpoints.methods.sampleJsonResponse,endpoints.strictSecurity,endpoints.highSecurity,endpoints.allowMissingApiKey,endpoints.hostPassthroughIncludedInBackendCallHeader,endpoints.cors,endpoints.customRequestAuthenticationAdapter,endpoints.headersToExcludeFromIncomingCall,endpoints.rateLimitHeadersEnabled,endpoints.forceGzipOfBackendCall,endpoints.gzipPassthroughSupportEnabled,endpoints.cookiesDuringHttpRedirectsEnabled,endpoints.connectionTimeoutForSystemDomainRequest,endpoints.connectionTimeoutForSystemDomainResponse,endpoints.rateLimitHeadersEnabled,endpoints.dropApiKeyFromIncomingCall,endpoints.cache,endpoints.processor,endpoints.apiKeyValueLocations,securityProfile.oauth')
        if 'errorCode' in api:
            print 'problem fetching...' + str(api_id) + ' ERROR:' + json.dumps(api)
            return
        return api


    def get_endpoints(self, api_id):
        endpoints = self.masheryV3.get(self.token, '/services/' + api_id + '/endpoints', 'fields=id,publicDomains,systemDomains')
        if 'errorCode' in endpoints:
            print endpoints
            return
        return endpoints


    def get_endpoint_domains(self, api_id, endpoint_id):
        endpoint_domains = self.masheryV3.get(self.token, '/services/' + api_id + '/endpoints/'+endpoint_id, 'fields=publicDomains,systemDomains')
        if 'errorCode' in endpoint_domains:
            print endpoint_domains
            return
        return endpoint_domains

    def update_endpoint(self, api_id, endpoint_id, apiKeyValueLocations):
        updated_endpoint = {}
        updated_endpoint['apiKeyValueLocations'] = []
        updated_endpoint['apiKeyValueLocations'] = apiKeyValueLocations
        print updated_endpoint

        updated_endpoint = self.masheryV3.put(self.token, '/services/' + api_id + '/endpoints/'+endpoint_id, 'fields=id,apiKeyValueLocations', updated_endpoint)
        if 'errorCode' in updated_endpoint:
            print updated_endpoint
            return

        return updated_endpoint

def main(argv):
    this = UpdateEndpointDomains()
    parser = argparse.ArgumentParser()
    parser.add_argument("mashery_api_key", type=str, help="Mashery V3 API Key")
    parser.add_argument("mashery_api_secret", type=str, help="Mashery V3 API Secret")
    parser.add_argument("mashery_area_uuid", type=str, help="Mashery Area/Site UUID")
    parser.add_argument("mashery_username", type=str, help="Mashery Portal Username")
    parser.add_argument("mashery_password", type=str, help="Mashery Portal Password")

    args = parser.parse_args()
  
    mashery_api_key = args.mashery_api_key
    mashery_api_secret = args.mashery_api_secret
    mashery_area_uuid = args.mashery_area_uuid
    mashery_username = args.mashery_username
    mashery_password = args.mashery_password


    this.authenticate(mashery_api_key, mashery_api_secret, mashery_username, mashery_password, mashery_area_uuid)    

    apis = this.get_apis()

    for api in apis:
        api = this.get_api(api['id'])
        for endpoint in api['endpoints']:
            if endpoint['requestProtocol'] == 'soap':
                if endpoint['apiKeyValueLocations'] != None and 'request-body' in endpoint['apiKeyValueLocations']:
                    endpoint['apiKeyValueLocations'].remove('request-body')
                    updated_endpoint = this.update_endpoint(api['id'], endpoint['id'], endpoint['apiKeyValueLocations'])

if __name__ == "__main__":
    main(sys.argv[1:])