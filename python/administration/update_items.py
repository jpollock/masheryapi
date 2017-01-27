# -*- coding: utf-8 -*-
import sys, argparse, json
from masheryV3 import MasheryV3

class UpdateItems():
    masheryV3 = MasheryV3()
    token = None

    def authenticate(self, mashery_api_key, mashery_api_secret, mashery_username, mashery_password, mashery_area_uuid):
        self.token = self.masheryV3.authenticate(mashery_api_key, mashery_api_secret, mashery_username, mashery_password, mashery_area_uuid)
        if 'error' in self.token:
            print self.token
            return

    def get_items(self, resource, params):
        items = self.masheryV3.get_all(self.token, resource, params)
        if 'errorCode' in items:
            return None

        return items


    def update(self,api_resource, resource_params, property_to_update, new_property_value):
        item = {}
        item[property_to_update] = new_property_value
        
        updated_item = self.masheryV3.put(self.token, api_resource, resource_params, item)

        return updated_item

def main(argv):
    update_items = UpdateItems()
    parser = argparse.ArgumentParser()
    parser.add_argument("mashery_api_key", type=str, help="Mashery V3 API Key")
    parser.add_argument("mashery_api_secret", type=str, help="Mashery V3 API Secret")
    parser.add_argument("mashery_area_uuid", type=str, help="Mashery Area/Site UUID")
    parser.add_argument("mashery_username", type=str, help="Mashery Portal Username")
    parser.add_argument("mashery_password", type=str, help="Mashery Portal Password")
    parser.add_argument("api_resource", type=str, help="Resource Path to update, e.g. /members or /members/312312412414")
    parser.add_argument("property_to_update", type=str, help="Item property to update")
    parser.add_argument("new_property_value", type=str, help="New Item property")

    args = parser.parse_args()
  
    mashery_api_key = args.mashery_api_key
    mashery_api_secret = args.mashery_api_secret
    mashery_area_uuid = args.mashery_area_uuid
    mashery_username = args.mashery_username
    mashery_password = args.mashery_password
    api_resource = args.api_resource
    property_to_update = args.property_to_update
    new_property_value = args.new_property_value

    resource_params = 'fields=id,' + property_to_update

    update_items.authenticate(mashery_api_key, mashery_api_secret, mashery_username, mashery_password, mashery_area_uuid)    

    items_to_update = update_items.get_items(api_resource, resource_params)

    for item in items_to_update:
        print update_items.update(api_resource + "/" + item['id'], resource_params, property_to_update, new_property_value)
    
if __name__ == "__main__":
    main(sys.argv[1:])