import os, sys, urllib, argparse, time, requests, json, logging, random, string
from lib.base_migrator import BaseMigrator

class MigrateApplicationsWithoutKeys(BaseMigrator):
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
    migrate_applications = MigrateApplicationsWithoutKeys()

    if (migrate_applications.confirm_ready() == False):
        return        

    # fetch all of the applications that are not packaged in the area
    try:
        applications = migrate_applications.base.fetch('applications', '*, keys', 'WHERE is_packaged = \'false\'')
    except ValueError as err:
        migrate_applications.logger.error('Error fetching data: %s', json.dumps(err.args))
        return
    
    application_count = 0
    application_success_count = 0

    # for each key, we're looking for memberless and applicationless one
    for application in applications:
        if len(application['keys']) == 0:
            application_count += 1
            try:
                migrate_applications.logger.info('Updating application: %s', json.dumps(application))
                application['is_packaged'] = True
                migrate_applications.base.update('application', application)
                application_success_count += 1
            except ValueError as err:
                if (err.args[0][0]['message'] == 'Invalid Object'):
                    application = migrate_applications.update_object_with_required_attributes(application, err.args[0][0]['data'])
                    try:
                        application = migrate_applications.base.update('application', application)
                        application_success_count += 1
                    except ValueError as err:
                        migrate_applications.logger.error(json.dumps(err.args))
                        return
                else:
                    migrate_applications.logger.error(json.dumps(err.args))
                    return

    print 'Applications to be migrated: ' + str(application_count)
    print 'Applications migrated: ' + str(application_success_count)

if __name__ == "__main__":
        main(sys.argv[1:])        
