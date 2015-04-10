package com.mashery.pm.api.sdk.domain;

public class ReportingData {
	String key;
	String service_guid;
	long totalCalls;
	long daysSinceLastCall;
	int weeksSinceLastCall;
	
	public int getWeeksSinceLastCall() {
		return weeksSinceLastCall;
	}
	public void setWeeksSinceLastCall(int weeksSinceLastCall) {
		this.weeksSinceLastCall = weeksSinceLastCall;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getService_guid() {
		return service_guid;
	}
	public void setService_guid(String service_guid) {
		this.service_guid = service_guid;
	}
	public long getTotalCalls() {
		return totalCalls;
	}
	public void setTotalCalls(long totalCalls) {
		this.totalCalls = totalCalls;
	}
	public long getDaysSinceLastCall() {
		return daysSinceLastCall;
	}
	public void setDaysSinceLastCall(long daysSinceLastCall) {
		this.daysSinceLastCall = daysSinceLastCall;
	}
	
	
}
