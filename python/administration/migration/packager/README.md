# Requirements
* Python 2.6+
* requests Python package (https://pypi.python.org/pypi/requests)

# Setup
* Checkout repo
    * git clone git@github.com:jpollock/masheryapi.git
    * git checkout packager_migration
* PYTHONPATH=$PYTHONPATH:\<path to checked out repo>/masheryapi/python/lib/api:\<path to checked out repo>/masheryapi/python/lib/services
* Make a directory for key backup and note location
    * key_backup in same dir as checkout
    * log in same dir as checkout
    * test_data in same dir as checkout
* Make a environment json

````
 {
    "mashery_api": {
        "protocol": "http",
        "hostname": "api.example.com",
        "apikey": "<insert your key>",
        "secret": "<insert your secret>"
 
    },
    "mashery_area": {
        "id": 47404,
        "name": "Test3"

    },
    "migration": {
        "member_email": "jpollock@mashery.com",
        "new_member_string": "memberless",
        "backup_location": "/Users/jppolloc/code/GitHubRepositories/masheryapi/python/administration/migration/packager/key_backup/",
        "log_location": "/Users/jppolloc/code/GitHubRepositories/masheryapi/python/administration/migration/packager/log/",
        "key_input_file": "/Users/jppolloc/code/GitHubRepositories/masheryapi/python/administration/migration/packager/test_data/keys_to_migrate.json"
    }


}
````
    

# Usage

cd to <path to checked out repo>/masheryapi/python/administration/migration/packager

1. python prepareServiceKeysForMigration.py

    usage: prepareServiceKeysForMigration.py [-h]
                                         --nodryrun

       --nodryrun leave off if you want to not create but see what would be created. add if you want to create the data
    
    Example:
    python prepareServiceKeysForMigration.py --nodryrun

2. python getServiceKeysToMigrate.py

    usage: getServiceKeysToMigrate.py [-h]
        
    Example:
    
     python getServiceKeysToMigrate.py 
     
3. python migrateServiceKeyToPackageKey.py

    usage: migrateServiceKeyToPackageKey.py [-h] [--nodryrun] 
    
    Example: 

	python migrateServiceKeyToPackageKey.py  --nodryrun
	
	leave off nodryun if you want a dry run and don't migrate
	
	
# Updated Flow

1. python update_memberless_applicationless_keys.py --nodryrun
2. python get_service_keys_to_migrate.py
3. python archive_service_keys.py
4. python validate_applications.py
5. python migrate_service_key_to_package_key.py --nodryrun
6. python validate_applications.py --packagekeys
7. python restore_service_keys.py --nodryrun
8. python validate_applications.py

See migrate.sh and restore.sh
