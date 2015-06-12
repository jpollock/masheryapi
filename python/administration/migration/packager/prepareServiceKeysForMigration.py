import os, sys, urllib, argparse, time, requests, json, logging, random, string
import base, logger

def randomPassword():
    length = 13
    chars = string.ascii_letters + string.digits
    random.seed = (os.urandom(1024))
    return ''.join(random.choice(chars) for i in range(length))

def main(argv):
    global loggerMigrator
    loggerMigrator =    logger.setup('prepareServiceKeysForMigration', 'myapp.log')

    parser = argparse.ArgumentParser()
    parser.add_argument("apikey", type=str, help="Mashery V2 API Key")
    parser.add_argument("secret", type=str, help="Mashery V2 API Secret")
    parser.add_argument("site_id", type=str, help="Mashery Area/Site ID")
    parser.add_argument("area_name", type=str, help="Mashery Area/Site Name")
    parser.add_argument("member_email", type=str, help="Member email")
    parser.add_argument('--nodryrun', action='store_true', default=False, help='specify to perform work, leave off command for dry run')

    args = parser.parse_args()

    apikey = args.apikey
    secret = args.secret
    site_id = args.site_id
    area_name = args.area_name
    member_email = args.member_email
    nodryrun = args.nodryrun

    keys = base.fetch(site_id, apikey, secret, 'keys', '*, member, application', '')
    
    key_count = 1
    member_count = 1
    memberless = False
    for key in keys:
        member = {} # keep this around in case we need it for applicationless keys
        if (key['member'] == None):
            loggerMigrator.info('Memberless key: %s', key['apikey'])
            if (nodryrun == True):
                memberless = True
                if (key_count < 101):
                    username = area_name + '_memberlesskey' + str(member_count)
                    member['username'] = username
                    member = base.fetch(site_id, apikey, secret, 'members', '*, applications', 'WHERE username = \'' + username + '\'')
                    if (len(member) == 0):
                        member = {}
                        member['username'] = username
                        member['passwd_new'] = randomPassword()
                        member['email'] = member_email
                        member['display_name'] = username
                        member['area_status'] = 'active'
                        try:
                            loggerMigrator.info('Creating Member: %s', json.dumps(member))
                            member = base.create(site_id, apikey, secret, 'member', member)
                            member = member['result']
                        except ValueError as err:
                            loggerMigrator.error(json.dumps(err.args))
                            return
                    else:
                        member = member[0]
        else:
            member = key['member']

        if (key['application'] == None):
            loggerMigrator.info('Applicationless key: %s', key['apikey'])
            if (nodryrun == True):
                application = {}
                application['name'] = 'Application for ' + member['username']
                application['member'] = member

                try:
                    loggerMigrator.info('Creating Application: %s', json.dumps(application))
                    application = base.create(site_id, apikey, secret, 'application', application)
                except ValueError as err:
                    loggerMigrator.error(json.dumps(err.args))
                    return

                key['application'] = application['result']
                if (memberless == True):
                    key['member'] = member
            
                try:
                    loggerMigrator.info('Updating key: %s', json.dumps(key))
                    base.update(site_id, apikey, secret, 'key', key)
                except ValueError as err:
                    loggerMigrator.error(json.dumps(err.args))
                    return

        key_count = key_count + 1
        if (key_count == 100):
            key_count = 1
            member_count = member_count + 1

if __name__ == "__main__":
        main(sys.argv[1:])        
