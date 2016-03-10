# -*- coding: utf-8 -*-
import sys, argparse, json
from masheryV3 import MasheryV3

class ExportApiDefinitions():
    masheryV3 = MasheryV3()
    token = None

    def authenticate(self, mashery_api_key, mashery_api_secret, mashery_username, mashery_password, mashery_area_uuid):
        self.token = self.masheryV3.authenticate(mashery_api_key, mashery_api_secret, mashery_username, mashery_password, mashery_area_uuid)
        if 'error' in self.token:
            print self.token
            return

    def get_apis(self):
        apis = self.masheryV3.get(self.token, '/services', 'fields=id')
        if 'errorCode' in apis:
            print apis
            return
        return apis

    def get_api(self, api_id):
        api = self.masheryV3.get(self.token, '/services/' + api_id, 'fields=id,name,description,securityProfile,qpsLimitOverall,rfc3986Encode,version,cache,endpoints.inboundSslRequired,endpoints.jsonpCallbackParameter,endpoints.jsonpCallbackParameterValue,endpoints.scheduledMaintenanceEvent,endpoints.forwardedHeaders,endpoints.returnedHeaders,endpoints.id,endpoints.name,endpoints.numberOfHttpRedirectsToFollow,endpoints.outboundRequestTargetPath,endpoints.outboundRequestTargetQueryParameters,endpoints.outboundTransportProtocol,endpoints.processor,endpoints.publicDomains,endpoints.requestAuthenticationType,endpoints.requestPathAlias,endpoints.requestProtocol,endpoints.oauthGrantTypes,endpoints.supportedHttpMethods,endpoints.apiMethodDetectionLocations,endpoints.apiMethodDetectionKey,endpoints.systemDomainAuthentication,endpoints.setpassword.setPassword,endpoints.systemDomains,endpoints.trafficManagerDomain,endpoints.updated,endpoints.useSystemDomainCredentials,endpoints.systemDomainCredentialKey,endpoints.systemDomainCredentialSecret,endpoints.methods,endpoints.methods.id,endpoints.methods.name,endpoints.methods.sampleXmlResponse,endpoints.methods.sampleJsonResponse,endpoints.strictSecurity,endpoints.highSecurity,endpoints.allowMissingApiKey,endpoints.hostPassthroughIncludedInBackendCallHeader,endpoints.cors,endpoints.customRequestAuthenticationAdapter,endpoints.headersToExcludeFromIncomingCall,endpoints.rateLimitHeadersEnabled,endpoints.forceGzipOfBackendCall,endpoints.gzipPassthroughSupportEnabled,endpoints.cookiesDuringHttpRedirectsEnabled,endpoints.connectionTimeoutForSystemDomainRequest,endpoints.connectionTimeoutForSystemDomainResponse,endpoints.rateLimitHeadersEnabled,endpoints.dropApiKeyFromIncomingCall,endpoints.cache,endpoints.processor,securityProfile.oauth')
        if 'errorCode' in api:
            print api
            return
        return api

    def archive(self, backup_location, api):
        f = open(backup_location + str(api['id']) + '.json', 'w')
        f.write(json.dumps(api, sort_keys=True, indent=4, separators=(',', ': ')))
        f.write('\n')
        f.close()


def main(argv):
    export_api_definitions = ExportApiDefinitions()
    parser = argparse.ArgumentParser()
    parser.add_argument("mashery_api_key", type=str, help="Mashery V3 API Key")
    parser.add_argument("mashery_api_secret", type=str, help="Mashery V3 API Secret")
    parser.add_argument("mashery_area_uuid", type=str, help="Mashery Area/Site UUID")
    parser.add_argument("mashery_username", type=str, help="Mashery Portal Username")
    parser.add_argument("mashery_password", type=str, help="Mashery Portal Password")
    parser.add_argument("output_directory", type=str, help="Output Directory")

    args = parser.parse_args()
  
    mashery_api_key = args.mashery_api_key
    mashery_api_secret = args.mashery_api_secret
    mashery_area_uuid = args.mashery_area_uuid
    mashery_username = args.mashery_username
    mashery_password = args.mashery_password
    output_directory = args.output_directory

    apis = []

    export_api_definitions.authenticate(mashery_api_key, mashery_api_secret, mashery_username, mashery_password, mashery_area_uuid)    

    apis = export_api_definitions.get_apis()
    for api in apis:
        api = export_api_definitions.get_api(api['id'])
        if api != None:
            export_api_definitions.archive(output_directory, api)
    
if __name__ == "__main__":
    main(sys.argv[1:])