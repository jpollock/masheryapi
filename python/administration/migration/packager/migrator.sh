#python testDataSetup.py 4ngdrf2tg62gvgsq7z9fd2f7 xgqZPDC9 47404
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
echo 'Validating Migration'
python validate_applications.py --packagekeys
echo 'Restoring Service Keys'
python restore_service_keys.py --nodryrun
echo 'Validating Restored Keys'
python validate_applications.py