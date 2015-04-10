package com.mashery.pm.api.sdk.gson;

public class RoleQueryJSONResultContainer extends BaseQueryJSONResultContainer {
	public RoleQueryJSONResult getResult() {
		return result;
	}

	public void setResult(RoleQueryJSONResult result) {
		this.result = result;
	}

	private RoleQueryJSONResult result;
	
	public RoleQueryJSONResultContainer()
	{
		
	}
	
}
