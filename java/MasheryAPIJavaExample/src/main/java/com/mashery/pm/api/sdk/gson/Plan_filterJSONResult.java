package com.mashery.pm.api.sdk.gson;

import java.util.List;

import com.mashery.pm.api.sdk.domain.Plan_filter;

public class Plan_filterJSONResult  extends BaseJSONResult{
	

	private Plan_filter result;
	
	Plan_filterJSONResult()
	{
		
	}

	public Plan_filter getResult() {
		return result;
	}

	public void setResult(Plan_filter result) {
		this.result = result;
	}
	
}
