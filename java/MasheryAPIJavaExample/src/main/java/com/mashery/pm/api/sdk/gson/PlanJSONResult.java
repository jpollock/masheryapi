package com.mashery.pm.api.sdk.gson;

import java.util.List;

import com.mashery.pm.api.sdk.domain.Plan;

public class PlanJSONResult  extends BaseJSONResult{
	

	private Plan result;
	
	PlanJSONResult()
	{
		
	}

	public Plan getResult() {
		return result;
	}

	public void setResult(Plan result) {
		this.result = result;
	}
	
}
