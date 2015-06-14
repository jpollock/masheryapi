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

# Using this repo
Some of the people visiting this git repo might not be familiar with git. Below is 

1. Make a new directory to story this git repo.
```
machine:~ user$ mkdir somedirectoryforgit
```
2. Change to that directory.
```
machine:~ user$ cd somedirectoryforgit/
```
3. Clone this git repo, using ssh or https
SSH:
```
machine:somedirectoryforgit user$ git clone git@github.com:jpollock/masheryapi.git
```
HTTPS:
```
machine:somedirectoryforgit user$ git clone https://github.com/jpollock/masheryapi.git
```
If your ssh is not setup properly, to speak to github, then https route might be the way to go.
4. What cloning looks like:
```
machine:somedirectoryforgit user$ git clone
git@github.com:jpollock/masheryapi.git
Cloning into 'masheryapi'...
remote: Counting objects: 483, done.
remote: Compressing objects: 100% (104/104), done.
remote: Total 483 (delta 58), reused 0 (delta 0), pack-reused 369
Receiving objects: 100% (483/483), 118.05 KiB | 0 bytes/s, done.
Resolving deltas: 100% (252/252), done.
Checking connectivity... done.
```
5. To run one of the reporting scripts:
```
machine:somedirectoryforgit user$ ls
masheryapi
machine:somedirectoryforgit user$ cd masheryapi
machine:masheryapi user$ ls
README.md   java        python
machine:masheryapi user$ cd python
machine:python user$ ls
administration  lib     reporting
machine:python user$ cd reporting/
machine:reporting user$ ls
reportingForAppsAndKeys.py      reportingForAppsAndKeysByDay.py     reportingForMethodsAndAppsAndKeys.py
machine:reporting user$ python reportingForAppsAndKeys.py
usage: reportingForAppsAndKeys.py [-h] [--apis APIS [APIS ...]]
                                  [--keys KEYS [KEYS ...]]
                                  [--additionalfields ADDITIONALFIELDS [ADDITIONALFIELDS ...]]
                                  apikey secret siteId startDate endDate
                                  outputFile
reportingForAppsAndKeys.py: error: too few arguments
```