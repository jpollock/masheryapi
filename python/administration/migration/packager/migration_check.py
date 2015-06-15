import sys, urllib, argparse, time, requests, json, logging
from lib.base_migrator import BaseMigrator

class MigrationCheck(BaseMigrator):
    def __init__(self):
        BaseMigrator.__init__(self)

def main(argv):
    migration_check = MigrationCheck()
    if (migration_check.migration_environment.valid() == False):
        print 'Migration Environment not setup properly.'
        return    


    parser = argparse.ArgumentParser()
    parser.add_argument('--preflight', nargs='?', default=True)

    args = parser.parse_args()
    preflight = args.preflight
    
    keys = []

    if (preflight == True):
        print migration_check.ready_for_migration()

if __name__ == "__main__":
        main(sys.argv[1:])        
