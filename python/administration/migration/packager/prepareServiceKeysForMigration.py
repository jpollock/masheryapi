import os, sys, urllib, argparse, time, requests, json, logging, random, string
import base

def randomPassword():
  length = 13
  chars = string.ascii_letters + string.digits + '!@#$%^&*()'
  random.seed = (os.urandom(1024))
  return ''.join(random.choice(chars) for i in range(length))

def memberExists(username):
  member = base.fetch(site_id, apikey, secret, 'members', '*, applications', 'WHERE username = \'' + username + '\'')
  if (len(member) == 1 and len(member[0]['applications']) < 100):
    return member
  else:
    return None

def getMemberForKey():
  member = {}
  count = 1
  while (len(member) == 0):
    username = 'Memberlesskey' + str(count)
  '''count = count + 1
  username = 'Memberlesskey' + str(count)
  member['username'] = username
  username = 'pmtester1'
  member = base.fetch(site_id, apikey, secret, 'members', '*, applications', 'WHERE username = \'' + username + '\'')
  if (len(member) == 0 or (len(member) == 1 and len(member[0]['applications']) > 99)):
    member['passwd_new'] = randomPassword()
    member['email'] = member_email
    member['display_name'] = username
    member['area_status'] = 'active'
    '''
  return member


def main(argv):
    # set up logging to file - see previous section for more details
  logging.basicConfig(level=logging.INFO,
                      format='%(asctime)s %(name)-12s %(levelname)-8s %(message)s',
                      datefmt='%m-%d %H:%M',
                      filename='myapp.log',
                      filemode='w')
  # define a Handler which writes INFO messages or higher to the sys.stderr
  console = logging.StreamHandler()
  console.setLevel(logging.INFO)
  # set a format which is simpler for console use
  formatter = logging.Formatter('%(name)-12s: %(levelname)-8s %(message)s')
  # tell the handler to use this format
  console.setFormatter(formatter)
  # add the handler to the root logger
  logging.getLogger('').addHandler(console)
  logging.getLogger('requests').setLevel(logging.ERROR)

  global loggerMigrator
  loggerMigrator = logging.getLogger('migrator')

  parser = argparse.ArgumentParser()
  parser.add_argument("apikey", type=str, help="Mashery V2 API Key")
  parser.add_argument("secret", type=str, help="Mashery V2 API Secret")
  parser.add_argument("site_id", type=str, help="Mashery Area/Site ID")
  parser.add_argument("member_email", type=str, help="Member email")

  args = parser.parse_args()

  apikey = args.apikey
  secret = args.secret
  site_id = args.site_id
  member_email = args.member_email

  keys = base.fetch(site_id, apikey, secret, 'keys', '*, member, application', '')
  
  key_count = 1
  member_count = 1
  memberless = False
  for key in keys:
    member = {} # keep this around in case we need it for applicationless keys
    print key['apikey'] + ' ' + str(key_count) + ' ' + str(member_count)
    if (key['member'] == None):
      loggerMigrator.info('Memberless key: %s', key['apikey'])
      memberless = True
      if (key_count < 101):
        username = 'Memberlesskey' + str(member_count)
        member['username'] = username
        member = base.fetch(site_id, apikey, secret, 'members', '*, applications', 'WHERE username = \'' + username + '\'')
        if (len(member) == 0):
          member = {}
          member['username'] = username
          member['passwd_new'] = randomPassword()
          member['email'] = member_email
          member['display_name'] = username
          member['area_status'] = 'active'
          member = base.create(site_id, apikey, secret, 'member', member)
          member = member['result']
        else:
          member = member[0]
    else:
      member = key['member']
    print member
    if (key['application'] == None):
      loggerMigrator.info('Applicationless key: %s', key['apikey'])
      application = {}
      application['name'] = 'Application for ' + member['username']
      application['member'] = member

      application = base.create(site_id, apikey, secret, 'application', application)

      key['application'] = application['result']
      if (memberless == True):
        key['member'] = member
      
      base.update(site_id, apikey, secret, 'key', key)

    key_count = key_count + 1
    if (key_count == 100):
      key_count = 1
      member_count = member_count + 1

if __name__ == "__main__":
    main(sys.argv[1:])    
