package com.mashery.pm.api.sdk.gson;

import java.util.List;

import com.mashery.pm.api.sdk.domain.Service_definition_method;

public class Service_definition_methodJSONResult extends BaseJSONResult {
	

	private Service_definition_method result;
	
	Service_definition_methodJSONResult()
	{
		
	}

	public Service_definition_method getResult() {
		return result;
	}

	public void setResult(Service_definition_method result) {
		this.result = result;
	}
	
}
