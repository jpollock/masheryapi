package com.mashery.pm.api.sdk.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.SimpleTimeZone;

import com.mashery.pm.api.sdk.domain.ReportingProcessingDate;



public class DateService {

	public HashMap<String, String> getReportingDatesForPastN7DayPeriods(int periods) {
		HashMap<String, String> startDatesAndEndDates = new HashMap<String, String>();
		
		Date recentDate = new Date();// = getStartOfDayAsDate();
		Date pastDate = new Date();// = get7DaysAgoAsDate();
		
		
		int i = 0;
		while (i < periods) {
			if (i == 0) {
				recentDate = getStartOfDayAsDate();
			} else {
				recentDate = pastDate;
			}
			pastDate = get7DaysAgoAsDate(pastDate.getTime());
			startDatesAndEndDates.put(formatAsISO8601String(pastDate), formatAsISO8601String(recentDate));

			i++;
		}
		return startDatesAndEndDates;
	}
	
	public HashMap<String, ReportingProcessingDate> getDatesToProcess(Date lastProcessedDate)
	{
		HashMap<String, ReportingProcessingDate> datesToProcess = new HashMap<String, ReportingProcessingDate>();

		Date mostRecentDate = getStartOfDayAsDate();
		List<Date> datesBetween = getDatesBetween(lastProcessedDate, mostRecentDate);
		Iterator<Date> dateItr = datesBetween.iterator();
		
		String d1String = "";
		String d2String = "";
		String lastProcessDateString = formatAsISO8601String(lastProcessedDate);
		String mostRecentDateString = formatAsISO8601String(mostRecentDate);
		
		while ( dateItr.hasNext())
		{
			String dateString = formatAsISO8601String(dateItr.next());
			
			// Check to see if first date is the date of last processed date.  If so, skip.
			if (lastProcessDateString.equals(dateString))
				continue;
			
			if (d1String.isEmpty())
			{
				d1String = dateString;
				
			} else {
				d2String = dateString;
			}
			
			if (!dateItr.hasNext())
			{
				d1String = dateString;
				d2String = mostRecentDateString;
			}
			if (!d1String.isEmpty() && !d2String.isEmpty())
				datesToProcess.put(d1String, fullReportingProcessingDate(d1String, d2String));
				
			
			if (!d2String.isEmpty())
				d1String = d2String;
			
			
		}
		return datesToProcess;
		
	}
	
	public HashMap<String, ReportingProcessingDate> getDatesToProcess(Date startDate, Date endDate)
	{
		HashMap<String, ReportingProcessingDate> datesToProcess = new HashMap<String, ReportingProcessingDate>();

		Date mostRecentDate = getStartOfDayAsDate();
		List<Date> datesBetween = getDatesBetween(startDate, endDate);
		Iterator<Date> dateItr = datesBetween.iterator();
		
		String d1String = "";
		String d2String = "";
		String lastProcessDateString = formatAsISO8601String(startDate);
		String mostRecentDateString = formatAsISO8601String(endDate);
		
		while ( dateItr.hasNext())
		{
			String dateString = formatAsISO8601String(dateItr.next());
			
			// Check to see if first date is the date of last processed date.  If so, skip.
			if (lastProcessDateString.equals(dateString))
				continue;
			
			if (d1String.isEmpty())
			{
				d1String = dateString;
				
			} else {
				d2String = dateString;
			}
			
			if (!dateItr.hasNext())
			{
				d1String = dateString;
				d2String = mostRecentDateString;
			}
			if (!d1String.isEmpty() && !d2String.isEmpty())
				datesToProcess.put(d1String, fullReportingProcessingDate(d1String, d2String));
				
			
			if (!d2String.isEmpty())
				d1String = d2String;
			
			
		}
		return datesToProcess;
		
	}
	 public static List<Date> getDatesBetween(Date initialDate, Date finalDate)
	 {
	     List<Date> dates = new ArrayList<Date>();
	     Calendar calendar = new GregorianCalendar();
	     calendar.setTime(initialDate);

	     while (calendar.getTime().before(finalDate))
	     {
	         Date resultado = calendar.getTime();
	         dates.add(resultado);
	         calendar.add(Calendar.DATE, 1);
	     }
	     return dates;
	 }

	
	public Date getStartOfDayAsDate() 
	{
		   Calendar dCal = Calendar.getInstance(new SimpleTimeZone(0, "GMT"));
		   dCal.setTime(new Date(System.currentTimeMillis()));
		   dCal.set(Calendar.HOUR_OF_DAY, 0);
		   dCal.set(Calendar.MINUTE, 0);
		   dCal.set(Calendar.SECOND, 0);
		   dCal.set(Calendar.MILLISECOND, 0);
		   return dCal.getTime();

	}
	
	public String getStartOfDayAsString() 
	{
		   Calendar dCal = Calendar.getInstance(new SimpleTimeZone(0, "GMT"));
		   dCal.setTime(new Date(System.currentTimeMillis()));
		   dCal.set(Calendar.HOUR_OF_DAY, 0);
		   dCal.set(Calendar.MINUTE, 0);
		   dCal.set(Calendar.SECOND, 0);
		   dCal.set(Calendar.MILLISECOND, 0);
		   SimpleDateFormat format =  new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		   format.setCalendar(dCal);
		   String returnString = format.format(dCal.getTime());
		   
		   returnString = returnString.replaceFirst(" ", "T");
		   returnString = returnString + "Z";
		   return returnString;

	}	

	public static String formatAsISO8601String(Date dateToFormat)
	{
		   Calendar dCal = Calendar.getInstance(new SimpleTimeZone(0, "GMT"));
		   dCal.setTime(dateToFormat);
		   dCal.set(Calendar.HOUR_OF_DAY, 0);
		   dCal.set(Calendar.MINUTE, 0);
		   dCal.set(Calendar.SECOND, 0);
		   dCal.set(Calendar.MILLISECOND, 0);
		   SimpleDateFormat format =  new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		   format.setCalendar(dCal);
		   String returnString = format.format(dCal.getTime());
		   
		   returnString = returnString.replaceFirst(" ", "T");
		   returnString = returnString + "Z";
		   return returnString;
		
	}
	public static String formatAsString(Date dateToFormat)
	{
		   Calendar dCal = Calendar.getInstance(new SimpleTimeZone(0, "GMT"));
		   dCal.setTime(dateToFormat);
		   dCal.set(Calendar.HOUR_OF_DAY, 0);
		   dCal.set(Calendar.MINUTE, 0);
		   dCal.set(Calendar.SECOND, 0);
		   dCal.set(Calendar.MILLISECOND, 0);
		   SimpleDateFormat format =  new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		   format.setCalendar(dCal);
		   String returnString = format.format(dCal.getTime());
		   
		   
		   return returnString;
		
	}
	
	public static Date getDateFromString(String dateToFormat)
	{
		
		   SimpleDateFormat format =  new SimpleDateFormat("yyyy-MM-dd");
		   try {
			   return format.parse(dateToFormat);
		   } catch (Exception ex)
		   {
			   ex.printStackTrace();
		   }

		   return null;
		
	}
	public static ReportingProcessingDate fullReportingProcessingDate(Date startDate, Date endDate)
	{
		String startDateStr = formatAsISO8601String(startDate);
		ReportingProcessingDate reportingProcessingDate = new ReportingProcessingDate(startDateStr, null);
		updateProcessingDate(reportingProcessingDate, startDateStr);
	
		
		return reportingProcessingDate;
	}
	
	public static ReportingProcessingDate fullReportingProcessingDate(String startDate, String endDate)
	{
		ReportingProcessingDate reportingProcessingDate = new ReportingProcessingDate(startDate, endDate);
		updateProcessingDate(reportingProcessingDate, startDate);
	
		
		return reportingProcessingDate;
	}
	public static ReportingProcessingDate fullReportingProcessingDate(Date date)
	{
		String startDateStr = formatAsISO8601String(date);
		ReportingProcessingDate reportingProcessingDate = new ReportingProcessingDate(startDateStr, null);
		updateProcessingDate(reportingProcessingDate, startDateStr);
	
		
		return reportingProcessingDate;
	}
	
	public static ReportingProcessingDate fullReportingProcessingDate(String date)
	{
		ReportingProcessingDate reportingProcessingDate = new ReportingProcessingDate(date);
		updateProcessingDate(reportingProcessingDate, date);
	
		
		return reportingProcessingDate;
	}
	public static Date getDateFromFullDateString(String dateString) {
		try {
			dateString = dateString.replace("Z", "");
			dateString = dateString.replace("T", " ");
			String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
		    
			SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
		    Date dt = sdf.parse(dateString);
		    return dt;
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}
	private static void updateProcessingDate(ReportingProcessingDate reportingProcessingDate, String dateString)
	{
		try {
			dateString = dateString.replace("Z", "");
			dateString = dateString.replace("T", " ");
			String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
		    
			SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
		    Date dt = sdf.parse(dateString);
		        
		    Calendar c = Calendar.getInstance();
		    c.setTime(dt);
			
		    //Set month of year, e.g 05
		    String mOy =  Integer.toString(c.get(c.MONTH) + 1);
		    if (mOy.length() == 1)
		    	mOy = "0" + mOy;		    
		    reportingProcessingDate.setMonthOfYear(mOy);
		    
		    // Set week of year, e.g. 22
		    String wOy =  Integer.toString(c.get(c.WEEK_OF_YEAR));
		    if (wOy.length() == 1)
		    	wOy = "0" + wOy;
		    reportingProcessingDate.setWeekOfYear(wOy);
		    
		    
		    // Set quarter of year
		    String qOy = getQuarterOfYearFromMonth(mOy);
		    reportingProcessingDate.setQuarterOfYear(qOy);
		    
		    
		    String year = Integer.toString(c.get(c.YEAR));
		    
		    reportingProcessingDate.setYear(year);
		    
		    String dayOfWeek = Integer.toString(c.get(c.DAY_OF_WEEK));
		    reportingProcessingDate.setDayOfWeek(dayOfWeek);
		    reportingProcessingDate.setHourOfDay(Integer.toString(c.get(c.HOUR_OF_DAY)));
		    reportingProcessingDate.setDayOfMonth(Integer.toString(c.get(c.DAY_OF_MONTH)));
		    
	    
		} catch (Exception ex)
		{
			ex.printStackTrace();
		}
	}
	
	public static String getQuarterOfYearFromMonth(String mOy)
	{
		String qOy = "";
	
		if (mOy.equals("01") || mOy.equals("02") || mOy.equals("03"))
		{
			qOy = "01";
		} else if (mOy.equals("04") || mOy.equals("05") || mOy.equals("06"))
		{
			qOy = "02";
		} else  if (mOy.equals("07") || mOy.equals("08") || mOy.equals("09"))
		{
			qOy = "03";
		} else if (mOy.equals("10") || mOy.equals("11") || mOy.equals("12"))
		{
			qOy = "04";
		}
		
		
		return qOy;
	}
	public static String getLastMonthFromQuarter(String qOy)
	{
		String mOy = "";
	
		if (qOy.equals("01"))
		{
			mOy = "03";
		} else if (qOy.equals("02"))
		{
			mOy = "06";
		} else  if (qOy.equals("03"))
		{
			mOy = "09";
		} else if (qOy.equals("04") )
		{
			mOy = "12";
		}
		
		
		return mOy;
	}
	public static String getPriorQuarter(String quarterOfYear)
	{
		int quarterInt = Integer.parseInt(quarterOfYear.substring(4));
		int yearInt = Integer.parseInt(quarterOfYear.substring(0,4));
		quarterInt--;
		if (quarterInt == 0) {
			quarterInt = 4;
			yearInt--;
		}
		
		return Integer.toString(yearInt) + "0" + Integer.toString(quarterInt);
	}
	public static String getLastDayInQuarter(String quarterOfYear)
	{
		int month = Integer.parseInt(getLastMonthFromQuarter(quarterOfYear.substring(4)));
		int year = Integer.parseInt(quarterOfYear.substring(0,4));
		
		return formatAsString(getLastDateOfMonth(year, month));
	}
	
	public static Date getLastDateOfMonth(int year, int month) {
	    Calendar calendar = new GregorianCalendar(year, month, Calendar.DAY_OF_MONTH);
	    calendar.set(Calendar.DAY_OF_MONTH, 1);
	    return calendar.getTime();
	}

	
	public Date get7DaysAgoAsDate()
	{
		return get7DaysAgoAsDate(System.currentTimeMillis());

	}
	public Date get7DaysAgoAsDate(long timeInMilliseconds)
	{
		 int MILLIS_IN_DAY = 1000 * 60 * 60 * 24 * 7;
		 Calendar dCal = Calendar.getInstance(new SimpleTimeZone(0, "GMT"));
		 dCal.setTime(new Date(timeInMilliseconds - MILLIS_IN_DAY));
		 dCal.set(Calendar.HOUR_OF_DAY, 0);
		 dCal.set(Calendar.MINUTE, 0);
		 dCal.set(Calendar.SECOND, 0);
		 dCal.set(Calendar.MILLISECOND, 0);
		   
		 return dCal.getTime();

	}
	
	public String get7DaysAgoAsString()
	{
		 Calendar dCal = Calendar.getInstance(new SimpleTimeZone(0, "GMT"));
		 dCal.setTime(get7DaysAgoAsDate());
		 
		 SimpleDateFormat format =  new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		 format.setCalendar(dCal);
		 String returnString = format.format(dCal.getTime());
		   
		   returnString = returnString.replaceFirst(" ", "T");
		   returnString = returnString + "Z";
		   
		   return returnString;

	}
	
}
	
