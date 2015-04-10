package com.mashery.pm.api.sdk.gson;

public class PackageQueryJSONResultContainer extends BaseQueryJSONResultContainer {
	public PackageQueryJSONResult getResult() {
		return result;
	}

	public void setResult(PackageQueryJSONResult result) {
		this.result = result;
	}

	private PackageQueryJSONResult result;
	
	public PackageQueryJSONResultContainer()
	{
		
	}
	
}
