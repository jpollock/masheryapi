# Administration Scripts

## Export Developers

Usage:
usage: exportDevelopers.py [-h] [--fields FIELDS [FIELDS ...]]
   apikey secret siteId outputFile
                           
Example:
python exportDevelopers.py <Mashery V2 API Key> <Mashery V2 API Secret> <Area/Site Id> developers.csv --fields email username 

## Export Developer IDs with Counts of Applications and Keys

usage: developerApplicationAndKeySummaryReport.py [-h]
                                                  apikey secret siteId
                                                  outputFile
Example:
python exportDevelopers.py <Mashery V2 API Key> <Mashery V2 API Secret> <Area/Site Id> developer_summary.csv