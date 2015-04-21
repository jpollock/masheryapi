import masheryV2, masheryDate, urllib

def activityByService(siteId, apikey, secret, startDate, endDate, serviceId):

    dates = masheryDate.daysToReportOn(startDate, endDate)

    results = []

    urlParams = '&start_date={startDate}&end_date={endDate}&format=json&limit=1000'.format(startDate= startDate, endDate= endDate)

    for date in dates:
        urlParams = '&start_date=' + urllib.quote_plus(date[0]) + '&end_date=' + urllib.quote_plus(date[1]) + '&format=json&limit=1000'
        try:
            results.extend(masheryV2.get(siteId, apikey, secret, '/reports/calls/developer_activity/service/{serviceId}'.format(serviceId= serviceId), urlParams))

        except TypeError:
            pass

    return results