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

    def update_endpoint(self, api_id, endpoint_id, endpoint_domains, public_domains, system_domains):
        for public_domain in public_domains:
            new_public_domain = {}
            new_public_domain[u'address'] = unicode(public_domain)
            endpoint_domains['publicDomains'].append(new_public_domain)

        for system_domain in system_domains:
            new_system_domain = {}
            new_system_domain[u'address'] = unicode(system_domain)        
            endpoint_domains['systemDomains'].append(new_system_domain)

        updated_endpoint_domains = self.masheryV3.put(self.token, '/services/' + api_id + '/endpoints/'+endpoint_id, 'fields=publicDomains,systemDomains', endpoint_domains)
        if 'errorCode' in updated_endpoint_domains:
            print updated_endpoint_domains
            return

        return updated_endpoint_domains

def main(argv):
    update_endpoint_domains = UpdateEndpointDomains()
    parser = argparse.ArgumentParser()
    parser.add_argument("mashery_api_key", type=str, help="Mashery V3 API Key")
    parser.add_argument("mashery_api_secret", type=str, help="Mashery V3 API Secret")
    parser.add_argument("mashery_area_uuid", type=str, help="Mashery Area/Site UUID")
    parser.add_argument("mashery_username", type=str, help="Mashery Portal Username")
    parser.add_argument("mashery_password", type=str, help="Mashery Portal Password")
    parser.add_argument('--apis',  nargs='+', help='List of APIs, ids space delimited, e.g. asd24sfsaasf 2224124afsf 2q3242432')    
    parser.add_argument('--endpoints',  nargs='+', help='List of Endpoints to update, in form API ID:Endpoint ID, space delimited, e.g. 1213131313:12121212 23232323:232323232323')
    parser.add_argument('--public_domains',  nargs='+', help='List of public domains to add')
    parser.add_argument('--system_domains',  nargs='+', help='List of system domains to add')

    args = parser.parse_args()
  
    mashery_api_key = args.mashery_api_key
    mashery_api_secret = args.mashery_api_secret
    mashery_area_uuid = args.mashery_area_uuid
    mashery_username = args.mashery_username
    mashery_password = args.mashery_password

    apis = []
    if args.apis != None:
        apis = args.apis
    
    endpoints = []
    if args.endpoints != None:
        endpoints = args.endpoints
    
    public_domains = []
    if args.public_domains != None:
        public_domains = args.public_domains
    
    system_domains = []
    if args.system_domains != None:
        system_domains = args.system_domains

    update_endpoint_domains.authenticate(mashery_api_key, mashery_api_secret, mashery_username, mashery_password, mashery_area_uuid)    

    for api_id in apis:
        api_endpoints = update_endpoint_domains.get_endpoints(api_id)
        for endpoint in api_endpoints:  
            endpoint_id = endpoint['id']
            print 'EXISTING ENDPOINT DOMAINS==' + json.dumps(endpoint)
            proceed = raw_input('Proceed? (y/n)')
            if proceed == 'y' or proceed == 'yes':
                updated_endpoint_domains = update_endpoint_domains.update_endpoint(api_id, endpoint_id, endpoint, public_domains, system_domains)
                if updated_endpoint_domains == None:
                    print 'Error'
                else:
                    print 'NEW ENDPOINT DOMAINS==' + json.dumps(updated_endpoint_domains)

    for endpoint in endpoints:
        ep_data = endpoint.split(':')
        api_id = ep_data[0]
        endpoint_id = ep_data[1]
        endpoint_domains = update_endpoint_domains.get_endpoint_domains(api_id, endpoint_id)
        print 'EXISTING ENDPOINT DOMAINS==' + json.dumps(endpoint_domains)
        proceed = raw_input('Proceed? (y/n)')
        if proceed == 'y' or proceed == 'yes':
            updated_endpoint_domains = update_endpoint_domains.update_endpoint(api_id, endpoint_id, endpoint_domains, public_domains, system_domains)
            if updated_endpoint_domains == None:
                print 'Error'
            else:
                print 'NEW ENDPOINT DOMAINS==' + json.dumps(updated_endpoint_domains)
    
if __name__ == "__main__":
    main(sys.argv[1:])