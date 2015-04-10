package com.mashery.pm.api.sdk.gson;

import java.util.List;

import com.mashery.pm.api.sdk.domain.Service_definition;

public class Service_definitionJSONResult extends BaseJSONResult {
	

	private Service_definition result;
	
	Service_definitionJSONResult()
	{
		
	}

	public Service_definition getResult() {
		return result;
	}

	public void setResult(Service_definition result) {
		this.result = result;
	}
	
}
