import os, sys, urllib, argparse, time, requests, json, logging, random, string
from lib.base_migrator import BaseMigrator

class UpdateMemberlessApplicationlessKeys(BaseMigrator):
    def __init__(self):
        BaseMigrator.__init__(self)

    def random_password(self):
        # generate a random string for password for new member create
        length = 12
        chars = string.ascii_letters + string.digits
        random.seed = (os.urandom(1024))
        return str(random.randint(0,9)) + (''.join(random.choice(chars) for i in range(length)))

    def get_member_for_memberless_key(self, username, member_email, nodryrun):
        member = {}
        member['username'] = username
        try:
            member = self.base.fetch('members', '*, applications', 'WHERE username = \'' + username + '\'')
        except ValueError as err:
            self.logger.error('Error fetching data: %s', json.dumps(err.args))
            return None

        if (len(member) == 0):
            member = {}
            member['username'] = username
            member['passwd_new'] = self.random_password()
            member['email'] = member_email
            member['display_name'] = username
            member['area_status'] = 'active'
            if (nodryrun == True):
                try:
                    self.logger.info('Creating Member: %s', json.dumps(member))
                    member = self.base.create('member', member)
                    member = member['result']
                except ValueError as err:
                    if (err.args[0][0]['message'] == 'Invalid Object'):
                        member = self.update_object_with_required_attributes(member, err.args[0][0]['data'])
                        try:
                            member = self.base.create('member', member)
                        except ValueError as err:
                            self.logger.error(json.dumps(err.args))
                            return
                    else:
                        self.logger.error(json.dumps(err.args))
                        return
        else:
            member = member[0]

        return member

def main(argv):
    update_keys = UpdateMemberlessApplicationlessKeys()
    if (update_keys.migration_environment.valid() == False):
        print 'Migration Environment not setup properly.'
        return

    if (update_keys.confirm_ready() == False):
        return        

    # get arguments passed in from command line
    parser = argparse.ArgumentParser()
    parser.add_argument('--nodryrun', action='store_true', default=False, help='specify to perform work, leave off command for dry run')

    args = parser.parse_args()
    nodryrun = args.nodryrun

    # fetch all of the keys in the area
    try:
        keys = update_keys.base.fetch('keys', '*, member, application.*, application.member', '')
        member_schema = update_keys.base.object_describe('member')
        application_schema = update_keys.base.object_describe('application')
    except ValueError as err:
        update_keys.logger.error('Error fetching data: %s', json.dumps(err.args))
        return
    
    key_count = 1
    member_count = 1
    application_count = 0
    
    # for each key, we're looking for memberless and applicationless one
    for key in keys:
        memberless = False
        member = None # keep this around in case we need it for applicationless keys
        if (key['member'] == None and (key['application'] != None and key['application']['member'] == None)):
            # MEMBERLESS KEY
            update_keys.logger.info('Memberless key: %s', key['apikey'])
            memberless = True

            # build the username for the member
            username = update_keys.migration_environment.configuration['mashery_area']['name'] + '_' + update_keys.migration_environment.configuration['migration']['new_member_string'] + '_' + str(member_count)

            member = update_keys.get_member_for_memberless_key(username, update_keys.migration_environment.configuration['migration']['member_email'], nodryrun)
            time.sleep(5)
            if (member == None):
                return

        else:
            if ((key['member'] == None and (key['application'] != None and key['application']['member'] != None)) or (key['member'] != None and key['application'] != None and key['member']['username'] != key['application']['member']['username'])):
                member = key['application']['member']
                memberless = True
            else:
                member = key['member']    

        if (key['application'] == None):
            update_keys.logger.info('Applicationless key: %s', key['apikey'])
            application_count += 1 # keep track of apps needed to create

            application = {}
            application['name'] = 'Application for ' + member['username']
            application['member'] = member
            
            update_keys.logger.info('Creating Application: %s', json.dumps(application))
            if (nodryrun == True):
                try:
                    application = update_keys.base.create('application', application)

                except ValueError as err:
                    if (err.args[0][0]['message'] == 'Invalid Object'):
                        application = update_keys.update_object_with_required_attributes(application, err.args[0][0]['data'])
                        try:
                            application = update_keys.base.create('application', application)
                        except ValueError as err:
                            update_keys.logger.error(json.dumps(err.args))
                    else:
                        update_keys.logger.error(json.dumps(err.args))
                        return

                key['application'] = application['result']
                key['member'] = member
                key['username'] = member['username']
            
                try:
                    update_keys.logger.info('Updating key: %s', json.dumps(key))
                    update_keys.base.update('key', key)
                except ValueError as err:
                    update_keys.logger.error(json.dumps(err.args))
                    return
        elif (key['application'] != None and memberless == True): # handling memberless keys with applications
                key['member'] = member
                update_keys.logger.info('Updating key: %s', json.dumps(key))
                if (nodryrun == True):
                    try:
                        update_keys.base.update('key', key)
                    except ValueError as err:
                        update_keys.logger.error(json.dumps(err.args))
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
