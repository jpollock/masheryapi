package com.mashery.pm.api.sdk.gson;

public class KeyQueryJSONResultContainer extends BaseQueryJSONResultContainer {
	public KeyQueryJSONResult getResult() {
		return result;
	}

	public void setResult(KeyQueryJSONResult result) {
		this.result = result;
	}

	private KeyQueryJSONResult result;
	
	public KeyQueryJSONResultContainer()
	{
		
	}
	
}
