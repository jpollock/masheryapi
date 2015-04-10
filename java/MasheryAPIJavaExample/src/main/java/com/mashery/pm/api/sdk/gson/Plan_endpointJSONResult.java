package com.mashery.pm.api.sdk.gson;

import java.util.List;

import com.mashery.pm.api.sdk.domain.Plan_endpoint;

public class Plan_endpointJSONResult  extends BaseJSONResult{
	

	private Plan_endpoint result;
	
	Plan_endpointJSONResult()
	{
		
	}

	public Plan_endpoint getResult() {
		return result;
	}

	public void setResult(Plan_endpoint result) {
		this.result = result;
	}
	
}
