package com.mashery.pm.api.sdk.gson;

import java.util.List;

import com.mashery.pm.api.sdk.domain.Plan_method;

public class Plan_methodJSONResult  extends BaseJSONResult{
	

	private Plan_method result;
	
	Plan_methodJSONResult()
	{
		
	}

	public Plan_method getResult() {
		return result;
	}

	public void setResult(Plan_method result) {
		this.result = result;
	}
	
}
