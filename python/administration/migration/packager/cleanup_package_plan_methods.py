# -*- coding: utf-8 -*-
import sys, argparse, json
from masheryV3 import MasheryV3

class CleanUpPackagePlanMethods():
    masheryV3 = MasheryV3()
    token = None

    def authenticate(self, mashery_api_key, mashery_api_secret, mashery_username, mashery_password, mashery_area_uuid):
        self.token = self.masheryV3.authenticate(mashery_api_key, mashery_api_secret, mashery_username, mashery_password, mashery_area_uuid)
        if 'error' in self.token:
            print self.token
            return

    def get_packages(self):
        packages = self.masheryV3.get(self.token, '/packages', 'fields=id,name')
        if 'errorCode' in packages:
            return None
        return packages

    def get_package(self, package_id):
        package = self.masheryV3.get(self.token, '/packages/' + package_id, 'fields=id,created,updated,name,description,notifyDeveloperPeriod,notifyDeveloperNearQuota,notifyDeveloperOverQuota,notifyDeveloperOverThrottle,notifyAdminPeriod,notifyAdminNearQuota,notifyAdminOverQuota,notifyAdminOverThrottle,notifyAdminEmails,nearQuotaThreshold,eav,keyAdapter,keyLength,sharedSecretLength,plans.id,plans.created,plans.updated,plans.name,plans.description,plans.eav,plans.selfServiceKeyProvisioningEnabled,plans.adminKeyProvisioningEnabled,plans.notes,plans.maxNumKeysAllowed,plans.numKeysBeforeReview,plans.qpsLimitCeiling,plans.qpsLimitExempt,plans.qpsLimitKeyOverrideAllowed,plans.rateLimitCeiling,plans.rateLimitExempt,plans.rateLimitKeyOverrideAllowed,plans.rateLimitPeriod,plans.responseFilterOverrideAllowed,plans.status,plans.emailTemplateSetId,plans.services,plans.services.endpoints,plans.services.endpoints.methods,plans.services.endpoints.methods.responseFilter,plans.roles')
        if 'errorCode' in package:
            return None
        return package

    def update_plan_method(self, package_id, plan_id, service_id, endpoint_id, method_id, plan_method):
        updated_plan_method = self.masheryV3.put(self.token, '/packages/' + package_id + '/plans/' + plan_id + '/services/' + service_id + '/endpoints/'+ endpoint_id +'/methods/' + method_id, 'fields=id', json.dumps(plan_method))
        if 'errorCode' in updated_plan_method:
            return None
        return updated_plan_method

def main(argv):
    cleanup_package_plan_methods = CleanUpPackagePlanMethods()
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

    packages = []

    cleanup_package_plan_methods.authenticate(mashery_api_key, mashery_api_secret, mashery_username, mashery_password, mashery_area_uuid)    

    packages = cleanup_package_plan_methods.get_packages()

    for package in packages:
        package = cleanup_package_plan_methods.get_package(package['id'])
        for plan in package['plans']:
            for plan_service in plan['services']:
                for plan_endpoint in plan_service['endpoints']:
                    for plan_method in plan_endpoint['methods']:
                        if plan_method['rateLimitPeriod'] == None:
                            print 'Processing Package ' + package['name'] + ' Plan ' + plan['name']
                            plan_method['rateLimitPeriod'] = plan['rateLimitPeriod']
                            print cleanup_package_plan_methods.update_plan_method(package['id'], plan['id'], plan_service['id'], plan_endpoint['id'], plan_method['id'], plan_method)

if __name__ == "__main__":
    main(sys.argv[1:])