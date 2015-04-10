package com.mashery.pm.api.sdk.gson;

import java.util.List;

import com.mashery.pm.api.sdk.domain.Service;

public class ServiceJSONResult extends BaseJSONResult {
	

	private Service result;
	
	
	ServiceJSONResult()
	{
		
	}

	public Service getResult() {
		return result;
	}

	public void setResult(Service result) {
		this.result = result;
	}

	
	
}
