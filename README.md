# masheryapi

## masheryreporting

Scripts that interact with the Mashery Reporting API V2. Please see [here](http://support.mashery.com/docs/read/mashery_api/20_reporting) for more information about this API.

### Python Scripts

Requires checkout of https://github.com/jpollock/masheryapi in the same parent directory as this repository.

#### reportingForAppsAndKeys

usage: reportingForAppsAndKeys.py [-h] [--apis APIS [APIS ...]]
                                  [--keys keys [keys ...]]
                                  [--fields FIELDS [FIELDS ...]]
                                  apikey secret siteId startDate endDate
                                  outputFile

positional arguments:
  apikey                Mashery V2 API Key
  secret                Mashery V2 API Secret
  siteId                Mashery Area/Site ID
  startDate             Start Date
  endDate               End Date
  outputFile            Output Filename

optional arguments:
  -h, --help            show this help message and exit
  --apis APIS [APIS ...]
                        List of APIs by name, space separated
  --keys keys [keys ...]
                        List of keys to include in results, space separated
  --fields FIELDS [FIELDS ...]
                        List of key/app fields to retrieve, space separated


Output: CSV of API key performance data.