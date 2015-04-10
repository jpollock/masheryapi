package com.mashery.pm.api.sdk.gson;

import java.util.List;

import com.mashery.pm.api.sdk.domain.Application;

public class ApplicationJSONResult extends BaseJSONResult {
	

	private Application result;
	
	ApplicationJSONResult()
	{
		
	}

	public Application getResult() {
		return result;
	}

	public void setResult(Application result) {
		this.result = result;
	}
	
}
