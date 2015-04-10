package com.mashery.pm.api.sdk.gson;

public class ApplicationQueryJSONResultContainer extends BaseQueryJSONResultContainer {
	public ApplicationQueryJSONResult getResult() {
		return result;
	}

	public void setResult(ApplicationQueryJSONResult result) {
		this.result = result;
	}

	private ApplicationQueryJSONResult result;
	
	public ApplicationQueryJSONResultContainer()
	{
		
	}
	
}
