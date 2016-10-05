echo 'Preparing Service Keys For Migration'
python update_memberless_applicationless_keys.py --nodryrun
echo 'Getting Service Keys To Migrate'
python get_service_keys_to_migrate.py
echo 'Archiving Service Keys Before Migration'
python archive_service_keys.py
echo 'Validating Archived Copies'
python validate_applications.py
echo 'Migrating'
python migrate_service_keys_to_package_keys.py --nodryrun
echo 'Validating Packaged Keys'
python validate_applications.py --packagekeys

