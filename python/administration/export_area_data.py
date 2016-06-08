# -*- coding: utf-8 -*-
import sys, argparse, json, os
from masheryV3 import MasheryV3

class ExportAreaData():
    masheryV3 = MasheryV3()
    token = None
    mashery_api_key = None
    mashery_api_secret = None
    mashery_area_uuid = None
    mashery_username = None
    mashery_password = None
    output_directory = None

    def authenticate(self):
        self.token = self.masheryV3.authenticate(self.mashery_api_key, self.mashery_api_secret, self.mashery_username, self.mashery_password, self.mashery_area_uuid)
        if 'error' in self.token:
            print self.token
            return

    def list(self, resource):
        apis = self.masheryV3.get(self.token, resource, 'fields=id&limit=1000')
        if 'errorCode' in apis:
            print apis
            return
        return apis

    def get(self, resource, fields):
        api = self.masheryV3.get(self.token, resource, fields)
        if 'errorCode' in api:
            print 'problem fetching...' + str(api_id) + ' ERROR:' + json.dumps(api)
            return
        return api

    def archive(self, backup_location, api):
        f = open(backup_location + str(api['id']) + '.json', 'w')
        f.write(json.dumps(api, sort_keys=True, indent=4, separators=(',', ': ')))
        f.write('\n')
        f.close()

    def make_dir(self, path):
        try: 
            os.makedirs(path)
        except OSError:
            if not os.path.isdir(path):
                raise


    def export(self, resource, fields):
        path_to_dir = self.output_directory + '/' + self.mashery_area_uuid + '/' + resource
        self.make_dir(path_to_dir)
        items = self.list('/' + resource)
        for item in items:
            item = self.get('/' + resource + '/' + item['id'], fields)
            if item != None:
                print 'exporting...' + item['id']
                self.archive(path_to_dir + '/', item)


def main(argv):
    export_area_data = ExportAreaData()
    parser = argparse.ArgumentParser()
    parser.add_argument("mashery_api_key", type=str, help="Mashery V3 API Key")
    parser.add_argument("mashery_api_secret", type=str, help="Mashery V3 API Secret")
    parser.add_argument("mashery_area_uuid", type=str, help="Mashery Area/Site UUID")
    parser.add_argument("mashery_username", type=str, help="Mashery Portal Username")
    parser.add_argument("mashery_password", type=str, help="Mashery Portal Password")
    parser.add_argument("output_directory", type=str, help="Output Directory")

    args = parser.parse_args()
  
    export_area_data.mashery_api_key = args.mashery_api_key
    export_area_data.mashery_api_secret = args.mashery_api_secret
    export_area_data.mashery_area_uuid = args.mashery_area_uuid
    export_area_data.mashery_username = args.mashery_username
    export_area_data.mashery_password = args.mashery_password
    export_area_data.output_directory = args.output_directory

    export_area_data.authenticate()

    export_area_data.export('services', 'fields=id,name,description,created,updated,securityProfile,qpsLimitOverall,rfc3986Encode,version,cache,endpoints.inboundSslRequired,endpoints.jsonpCallbackParameter,endpoints.jsonpCallbackParameterValue,endpoints.scheduledMaintenanceEvent,endpoints.forwardedHeaders,endpoints.returnedHeaders,endpoints.id,endpoints.name,endpoints.numberOfHttpRedirectsToFollow,endpoints.outboundRequestTargetPath,endpoints.outboundRequestTargetQueryParameters,endpoints.outboundTransportProtocol,endpoints.processor,endpoints.publicDomains,endpoints.requestAuthenticationType,endpoints.requestPathAlias,endpoints.requestProtocol,endpoints.oauthGrantTypes,endpoints.supportedHttpMethods,endpoints.apiMethodDetectionLocations,endpoints.apiMethodDetectionKey,endpoints.systemDomainAuthentication,endpoints.setpassword.setPassword,endpoints.systemDomains,endpoints.trafficManagerDomain,endpoints.updated,endpoints.useSystemDomainCredentials,endpoints.systemDomainCredentialKey,endpoints.systemDomainCredentialSecret,endpoints.methods,endpoints.methods.id,endpoints.methods.name,endpoints.methods.sampleXmlResponse,endpoints.methods.sampleJsonResponse,endpoints.strictSecurity,endpoints.highSecurity,endpoints.allowMissingApiKey,endpoints.hostPassthroughIncludedInBackendCallHeader,endpoints.cors,endpoints.customRequestAuthenticationAdapter,endpoints.headersToExcludeFromIncomingCall,endpoints.rateLimitHeadersEnabled,endpoints.forceGzipOfBackendCall,endpoints.gzipPassthroughSupportEnabled,endpoints.cookiesDuringHttpRedirectsEnabled,endpoints.connectionTimeoutForSystemDomainRequest,endpoints.connectionTimeoutForSystemDomainResponse,endpoints.rateLimitHeadersEnabled,endpoints.dropApiKeyFromIncomingCall,endpoints.cache,endpoints.processor,endpoints.apiKeyValueLocationKey, securityProfile.oauth')
    export_area_data.export('packages', 'fields=id,name,description,created,updated,keyLength,sharedSecretLength,notifyDeveloperPeriod,notifyDeveloperNearQuota,notifyDeveloperOverQuota,notifyDeveloperOverThrottle,notifyAdminPeriod,notifyAdminNearQuota,notifyAdminOverQuota,notifyAdminOverThrottle,notifyAdminEmails,nearQuotaThreshold,plans.id,plans.created,plans.updated,plans.name,plans.description,plans.eav,plans.selfServiceKeyProvisioningEnabled,plans.adminKeyProvisioningEnabled,plans.notes,plans.maxNumKeysAllowed,plans.numKeysBeforeReview,plans.qpsLimitCeiling,plans.qpsLimitExempt,plans.qpsLimitKeyOverrideAllowed,plans.rateLimitCeiling,plans.rateLimitExempt,plans.rateLimitKeyOverrideAllowed,plans.rateLimitPeriod,plans.responseFilterOverrideAllowed,plans.status,plans.emailTemplateSetId,plans.services,plans.services.endpoints,plans.services.endpoints.methods,plans.services.endpoints.methods.responseFilter')
    export_area_data.export('members', 'fields=*')
    export_area_data.export('applications', 'fields=*')
    export_area_data.export('packageKeys', 'fields=*, application, package, plan')
    export_area_data.export('roles', 'fields=*')
if __name__ == "__main__":
    main(sys.argv[1:])