package com.mashery.pm.api.sdk.gson;

import java.util.List;

import com.mashery.pm.api.sdk.domain.Service_definition_endpoint;

public class Service_definition_endpointJSONResult extends BaseJSONResult {
	

	private Service_definition_endpoint result;
	
	Service_definition_endpointJSONResult()
	{
		
	}

	public Service_definition_endpoint getResult() {
		return result;
	}

	public void setResult(Service_definition_endpoint result) {
		this.result = result;
	}
	
}
