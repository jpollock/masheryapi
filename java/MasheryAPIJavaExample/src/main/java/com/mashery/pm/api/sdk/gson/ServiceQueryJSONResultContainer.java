package com.mashery.pm.api.sdk.gson;

public class ServiceQueryJSONResultContainer extends BaseQueryJSONResultContainer {
	public ServiceQueryJSONResult getResult() {
		return result;
	}

	public void setResult(ServiceQueryJSONResult result) {
		this.result = result;
	}

	private ServiceQueryJSONResult result;
	
	public ServiceQueryJSONResultContainer()
	{
		
	}
	
}
