package com.mashery.pm.api.sdk.gson;

import java.util.List;

import com.mashery.pm.api.sdk.domain.Developer_class;

public class Developer_classJSONResult extends BaseJSONResult {
	

	private Developer_class result;
	
	Developer_classJSONResult()
	{
		
	}

	public Developer_class getResult() {
		return result;
	}

	public void setResult(Developer_class result) {
		this.result = result;
	}
	
}
