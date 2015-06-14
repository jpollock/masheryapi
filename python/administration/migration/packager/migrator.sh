#python testDataSetup.py 4ngdrf2tg62gvgsq7z9fd2f7 xgqZPDC9 47404
python prepare_service_keys_for_migration.py --nodryrun
python get_service_keys_to_migrate.py
python archive_service_keys.py
python validate_keys.py
python migrate_service_key_to_package_key.py --nodryrun
python validate_keys.py --packagekeys
python restore_service_keys.py --nodryrun
python validate_keys.py