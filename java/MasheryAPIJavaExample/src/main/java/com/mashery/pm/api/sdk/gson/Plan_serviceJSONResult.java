package com.mashery.pm.api.sdk.gson;

import java.util.List;

import com.mashery.pm.api.sdk.domain.Plan_service;

public class Plan_serviceJSONResult  extends BaseJSONResult{
	

	private Plan_service result;
	
	Plan_serviceJSONResult()
	{
		
	}

	public Plan_service getResult() {
		return result;
	}

	public void setResult(Plan_service result) {
		this.result = result;
	}
	
}
