package com.mashery.pm.api.sdk.gson;

import java.util.List;

import com.mashery.pm.api.sdk.domain.Access_rule;

public class Access_ruleJSONResult extends BaseJSONResult {
	

	private Access_rule result;
	
	Access_ruleJSONResult()
	{
		
	}

	public Access_rule getResult() {
		return result;
	}

	public void setResult(Access_rule result) {
		this.result = result;
	}
	
}
