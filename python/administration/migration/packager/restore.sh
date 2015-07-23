echo 'Restoring Service Keys'
python restore_service_keys.py --nodryrun
echo 'Validating Restored Keys'
python validate_applications.py