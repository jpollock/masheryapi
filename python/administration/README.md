# Administration Scripts

## Setup

1. Checkout project
2. Set environment variable: PYTHONPATH=$PYTHONPATH:<path to checked out repo>/masheryapi/python/lib/api:<path to checked out repo>/masheryapi/python/lib/services

## Script Description

### Export Developers

Usage:
usage: exportDevelopers.py [-h] [--fields FIELDS [FIELDS ...]]
   apikey secret siteId outputFile
                           
Example:
python exportDevelopers.py <Mashery V2 API Key> <Mashery V2 API Secret> <Area/Site Id> developers.csv --fields email username 

### Export Developer IDs with Counts of Applications and Keys

usage: developerApplicationAndKeySummaryReport.py [-h]
                                                  apikey secret siteId
                                                  outputFile
Example:
python exportDevelopers.py <Mashery V2 API Key> <Mashery V2 API Secret> <Area/Site Id> developer_summary.csv

### Add Public/System Domains

Usage:

usage: update_endpoint_domains.py [-h] [--apis APIS [APIS ...]]
                                  [--endpoints ENDPOINTS [ENDPOINTS ...]]
                                  [--public_domains PUBLIC_DOMAINS [PUBLIC_DOMAINS ...]]
                                  [--system_domains SYSTEM_DOMAINS [SYSTEM_DOMAINS ...]]
                                  mashery_api_key mashery_api_secret
                                  mashery_area_uuid mashery_username
                                  mashery_password

Example:

python update_endpoint_domains.py \<Mashery V3 API Key> \<Mashery V3 API Secret> \<Area/Site Uuid> \<Mashery Portal Username> \<Mashery Portal Password> --apis asd42asdasdasdwadsasd asdasdasdad323ddsd --public_domains api1.company.com api2.company.com

This would add the two public domains to each of the endpoints contained in the two APIs. 

python update_endpoint_domains.py \<Mashery V3 API Key> \<Mashery V3 API Secret> \<Area/Site Uuid> \<Mashery Portal Username> \<Mashery Portal Password> --endpoints asd42asdasdasdwadsasd:as323asd3444444 asdasdasdad323ddsd:zzzppppzsdddsd --public_domains api1.company.com api2.company.com

This would add the two public domains to each of the endpoints passed in, i.e. only add the two public domains to specific endpoints and not all contained within a given API. 