import datetime

def dayGap(startDate, endDate):
  dt1 = datetime.datetime.strptime(startDate, "%Y-%m-%dT%H:%M:%SZ")
  dt2 = datetime.datetime.strptime(endDate, "%Y-%m-%dT%H:%M:%SZ")
  return (dt2 - dt1).days  

def sevenDays(startDate, endDate):
  dt1 = datetime.datetime.strptime(startDate, "%Y-%m-%dT%H:%M:%SZ")
  if dayGap(startDate, endDate) > 7:
    dt1 = datetime.datetime.strptime(startDate, "%Y-%m-%dT%H:%M:%SZ")
    dt1_1 = dt1 + datetime.timedelta(days=7)
    endDate = dt1_1.strftime("%Y-%m-%dT%H:%M:%SZ")

  return [startDate, endDate]

def daysToReportOn(startDate, endDate):
  daysToReportOn = []
  
  sevenDayGap = sevenDays(startDate, endDate)
  daysToReportOn.append(sevenDayGap)
  dayGap(sevenDayGap[1], endDate)

  while dayGap(sevenDayGap[1], endDate) > 7:
    sevenDayGap = sevenDays(sevenDayGap[1], endDate)
    daysToReportOn.append(sevenDayGap)
  
  if (dayGap(sevenDayGap[1], endDate) < 7 and sevenDayGap[1] != endDate): 
    daysToReportOn.append([sevenDayGap[1], endDate])

  return daysToReportOn