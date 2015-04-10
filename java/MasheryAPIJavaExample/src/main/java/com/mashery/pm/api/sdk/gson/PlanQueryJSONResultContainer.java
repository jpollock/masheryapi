package com.mashery.pm.api.sdk.gson;

public class PlanQueryJSONResultContainer extends BaseQueryJSONResultContainer {
	public PlanQueryJSONResult getResult() {
		return result;
	}

	public void setResult(PlanQueryJSONResult result) {
		this.result = result;
	}

	private PlanQueryJSONResult result;
	
	public PlanQueryJSONResultContainer()
	{
		
	}
	
}
