package com.mashery.pm.api.sdk.gson;


import com.mashery.pm.api.sdk.domain.Service_definition_response;

public class Service_definition_responseJSONResult extends BaseJSONResult {
	

	private Service_definition_response result;
	
	Service_definition_responseJSONResult()
	{
		
	}

	public Service_definition_response getResult() {
		return result;
	}

	public void setResult(Service_definition_response result) {
		this.result = result;
	}


}
