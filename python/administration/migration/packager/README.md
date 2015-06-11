# Setup
* Checkout repo
    * git clone git@github.com:jpollock/masheryapi.git
    * git checkout packager_migration
* PYTHONPATH=$PYTHONPATH:\<path to checked out repo>/masheryapi/python/lib/api:\<path to checked out repo>/masheryapi/python/lib/services
* Make a directory for key backup and note location
* 

# Usage

cd to <path to checked out repo>/masheryapi/python/administration/migration/packager

1. python prepareServiceKeysForMigration.py

    usage: prepareServiceKeysForMigration.py [-h]
                                         apikey secret site\_id member_email

    member_email is the email address to use for members created for memberless keys
    
    Example:
    python prepareServiceKeysForMigration.py mymasherykey mymasherysecret 99999 jpollock@mashery.com

2. python getServiceKeysToMigrate.py

    usage: getServiceKeysToMigrate.py [-h] apikey secret site_id output
    
    Example:
    
     python getServiceKeysToMigrate.py mymasherykey mymasherysecret 99999 /users/jppolloc/somefile.json
     
3. python migrateServiceKeyToPackageKey.py

    usage: migrateServiceKeyToPackageKey.py [-h] [--nodryrun] apikey secret site\_id backup\_location migration\_map_location
    
    Example: 

	python migrateServiceKeyToPackageKey.py mymasherykey mymasherysecret 99999 /somepath/key_backup/ /somepath/keysToMigrate.json --nodryrun
	
	leave off nodryun if you want a dry run and don't migrate
	