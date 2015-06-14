import sys, urllib, argparse, time, requests, json, random
from lib.migration_environment import MigrationEnvironment
from base import Base

def main(argv):
    migration_environment = MigrationEnvironment()
    if (migration_environment.valid() == False):
        print 'Migration Environment not setup properly.'
        return

    mashery_api_config = migration_environment.configuration
    global base
    base = Base(mashery_api_config['mashery_api']['protocol'], mashery_api_config['mashery_api']['hostname'], mashery_api_config['mashery_area']['id'], mashery_api_config['mashery_api']['apikey'], mashery_api_config['mashery_api']['secret'])

    apis = base.fetch('service_definitions', '*, service, service_definition_endpoints, service.service_classes, service.service_classes.developer_class', '')
    packages = base.fetch('packages', '*, plans', '')


    applications = base.fetch('applications', '*', '')
    for application in applications:
        base.delete('application', application)

    keys = base.fetch('keys', '*', '')
    for key in keys:
        base.delete('key', key)

    f = open('test_data/applicationTestData.json', 'r')
    file_contents = f.read()
    keys_to_create = json.loads(file_contents)
    f.close()

    members = base.fetch('members', '*', '')

    for key_data in keys_to_create:
        count = 0
        while (count < 15):
            application_to_create = {}
            member = members[random.randint(0,len(members)-1)]

            if (key_data['applicationless'] == False):
                application_to_create['name'] = key_data['apikey']
                application_to_create['member'] = member
                created_application = base.create('application', application_to_create)

            rnd_dev = random.randint(0,2)
            for api in apis:
                key_to_create = {}
                key_to_create['apikey'] = key_data['apikey'] + str(count)
                key_to_create['secret'] = key_data['secret']
                key_to_create['service'] = api

                developer_class = api['service']['service_classes'][rnd_dev]['developer_class']

                if (key_data['memberless'] == False):
                        key_to_create['member'] = member
                if (key_data['applicationless'] == False):
                        key_to_create['application'] = created_application['result']
                if (key_data['developerclass'] == True):
                        key_to_create['developer_class'] = developer_class

                if (key_data['customlimits'] == True):
                    key_to_create['rate_limit_exempt'] = True
                    key_to_create['qps_limit_ceiling'] = 10
                    key_to_create['qps_limit_exempt'] = False

                key_to_create['eav1'] = str(time.clock())
                key_to_create['eav2'] = str(time.clock())
                created_key = base.create('key', key_to_create)
            count = count + 1


if __name__ == "__main__":
    main(sys.argv[1:])        
