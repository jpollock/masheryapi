# masheryapi

## masheryreporting

Scripts that interact with the Mashery Reporting API V2. Please see [here](http://support.mashery.com/docs/read/mashery_api/20_reporting) for more information about this API.

### Python Scripts

Requires checkout of https://github.com/jpollock/masheryapi in the same parent directory as this repository.

#### reportingForAppsAndKeys

Usage: python reportingForAppsAndKeys.py &lt;Mashery V2 API Key> <Mashery V2 API Secret> &lt;Mashery Area/Site ID> &lt;Start Date> &lt;End Date> &lt;Output Filename> &lt;List of APIs (optional)>

Output: CSV of API key performance data.