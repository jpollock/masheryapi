package com.mashery.pm.api.sdk.gson;

import java.util.List;

import com.mashery.pm.api.sdk.domain.Service_class;

public class Service_classJSONResult extends BaseJSONResult {
	

	private Service_class result;
	
	Service_classJSONResult()
	{
		
	}

	public Service_class getResult() {
		return result;
	}

	public void setResult(Service_class result) {
		this.result = result;
	}
	
}
