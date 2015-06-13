import os, sys, urllib, argparse, time, requests, json, logging, random, string
import logger
from lib.base_migrator import BaseMigrator
from lib.migration_environment import MigrationEnvironment
from base import Base

def randomPassword():
    # generate a random string for password for new member create
    length = 13
    chars = string.ascii_letters + string.digits
    random.seed = (os.urandom(1024))
    return ''.join(random.choice(chars) for i in range(length))

def getMemberForMemberlessKey(username, member_email, nodryrun):
    member = {}
    member['username'] = username
    try:
        member = base.fetch('members', '*, applications', 'WHERE username = \'' + username + '\'')
    except ValueError as err:
        base_migrator.logger.error('Error fetching data: %s', json.dumps(err.args))
        return None

    if (len(member) == 0):
        member = {}
        member['username'] = username
        member['passwd_new'] = randomPassword()
        member['email'] = member_email
        member['display_name'] = username
        member['area_status'] = 'active'
        if (nodryrun == True):
            try:
                base_migrator.logger.info('Creating Member: %s', json.dumps(member))
                member = base.create('member', member)
                member = member['result']
            except ValueError as err:
                base_migrator.logger.error(json.dumps(err.args))
                return None
    else:
        member = member[0]

    return member

def main(argv):
    migration_environment = MigrationEnvironment()
    if (migration_environment.valid() == False):
        print 'Migration Environment not setup properly.'
        return

    mashery_api_config = migration_environment.configuration
    global base
    base = Base(mashery_api_config['mashery_api']['protocol'], mashery_api_config['mashery_api']['hostname'], mashery_api_config['mashery_area']['id'], mashery_api_config['mashery_api']['apikey'], mashery_api_config['mashery_api']['secret'])

    logger_migrator = logger.setup('prepareServiceKeysForMigration', 'log/package_migrator.log')

    global base_migrator
    base_migrator = BaseMigrator(logger_migrator)
    

    # get arguments passed in from command line
    parser = argparse.ArgumentParser()
    parser.add_argument('--nodryrun', action='store_true', default=False, help='specify to perform work, leave off command for dry run')

    args = parser.parse_args()
    nodryrun = args.nodryrun

    # fetch all of the keys in the area
    try:
        keys = base.fetch('keys', '*, member, application', '')
    except ValueError as err:
        base_migrator.logger.error('Error fetching data: %s', json.dumps(err.args))
        return
    
    key_count = 1
    member_count = 1
    application_count = 0
    memberless = False

    # for each key, we're looking for memberless and applicationless one
    for key in keys:
        member = None # keep this around in case we need it for applicationless keys
        if (key['member'] == None):
            # MEMBERLESS KEY
            base_migrator.logger.info('Memberless key: %s', key['apikey'])
            memberless = True

            # build the username for the member
            username = migration_environment.configuration['mashery_area']['name'] + '_' + migration_environment.configuration['migration']['new_member_string'] + '_' + str(member_count)

            member = getMemberForMemberlessKey(username, migration_environment.configuration['migration']['member_email'], nodryrun)
            if (member == None):
                return

        else:
            member = key['member']

        if (key['application'] == None):
            base_migrator.logger.info('Applicationless key: %s', key['apikey'])
            application_count += 1 # keep track of apps needed to create
            if (nodryrun == True):

                application = {}
                application['name'] = 'Application for ' + member['username']
                application['member'] = member

                try:
                    base_migrator.logger.info('Creating Application: %s', json.dumps(application))
                    application = base.create('application', application)

                except ValueError as err:
                    base_migrator.logger.error(json.dumps(err.args))
                    return

                key['application'] = application['result']
                key['member'] = member
            
                try:
                    base_migrator.logger.info('Updating key: %s', json.dumps(key))
                    base.update('key', key)
                except ValueError as err:
                    base_migrator.logger.error(json.dumps(err.args))
                    return

        # increment counts. key_count is being used to tack how many keys are being added to a member
        key_count = key_count + 1
        if (key_count == 100):
            key_count += 1
            member_count += 1

    print 'Run Type:' + ('Creation' if nodryrun else 'Dry Run')
    print 'Members created:' + str(member_count)
    print 'Applications created:' + str(application_count)

if __name__ == "__main__":
        main(sys.argv[1:])        
