package com.mashery.pm.api.sdk.domain;

public class Limit {
	private String period;
	private String source;
	private String ceiling;

	Limit()
	{
		
	}

	public String getPeriod() {
		return period;
	}

	public void setPeriod(String period) {
		this.period = period;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getCeiling() {
		return ceiling;
	}

	public void setCeiling(String ceiling) {
		this.ceiling = ceiling;
	}

	
}
