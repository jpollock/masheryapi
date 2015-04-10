package com.mashery.pm.api.sdk.domain;

public class ReportingProcessingDate {
	String startDateStr;
	String monthOfYear;
	String weekOfYear;
	String year;
	String quarterOfYear;
	String week;
	String quarter;
	String dayOfWeek;
	String dayOfMonth;
	public String getDayOfMonth() {
		return dayOfMonth;
	}
	public void setDayOfMonth(String dayOfMonth) {
		this.dayOfMonth = dayOfMonth;
	}

	public String getHourOfDay() {
		return hourOfDay;
	}
	public void setHourOfDay(String hourOfDay) {
		this.hourOfDay = hourOfDay;
	}

	String hourOfDay;
	
	
	public String getWeek() {
		return week;
	}
	public void setWeek(String week) {
		this.week = week;
	}
	public String getQuarter() {
		return quarter;
	}
	public void setQuarter(String quarter) {
		this.quarter = quarter;
	}
	public String getDayOfWeek() {
		return dayOfWeek;
	}
	public void setDayOfWeek(String dayOfWeek) {
		this.dayOfWeek = dayOfWeek;
	}
	public String getPriorQuarter() {
		int quarterInt = Integer.parseInt(quarterOfYear);
		int yearInt = Integer.parseInt(year);
		quarterInt--;
		if (quarterInt == 0) {
			quarterInt = 4;
			yearInt--;
		}
		
		return  Integer.toString(yearInt) + "0" + Integer.toString(quarterInt);
		
	}
	public String getPriorQuarterPriorYear() {
		int quarterInt = Integer.parseInt(quarterOfYear);
		int yearInt = Integer.parseInt(year);
		yearInt--;
		quarterInt--;
		if (quarterInt == 0) {
			quarterInt = 4;
			yearInt--;
		}
		
		return Integer.toString(yearInt)+ "0" + Integer.toString(quarterInt) ;
		
	}
	public String getPriorYear() {
		int yearInt = Integer.parseInt(year);
		yearInt--;
		
		return Integer.toString(yearInt);
		
	}
	public String getQuarterOfYear() {
		return quarterOfYear;
	}

	public void setQuarterOfYear(String quarterOfYear) {
		this.quarterOfYear = quarterOfYear;
	}

	public String getMonthOfYear() {
		return monthOfYear;
	}

	public void setMonthOfYear(String monthOfYear) {
		this.monthOfYear = monthOfYear;
	}

	public String getWeekOfYear() {
		return weekOfYear;
	}

	public void setWeekOfYear(String weekOfYear) {
		this.weekOfYear = weekOfYear;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getStartDateStr() {
		return startDateStr;
	}

	public void setStartDateStr(String startDateStr) {
		this.startDateStr = startDateStr;
	}

	public String getEndDateStr() {
		return endDateStr;
	}

	public void setEndDateStr(String endDateStr) {
		this.endDateStr = endDateStr;
	}

	String endDateStr;
	
	public ReportingProcessingDate(String startDateStr, String endDateStr)
	{
		this.startDateStr = startDateStr;
		this.endDateStr = endDateStr;
	}
	public ReportingProcessingDate(String startDateStr)
	{
		this.startDateStr = startDateStr;
	
	}

}
