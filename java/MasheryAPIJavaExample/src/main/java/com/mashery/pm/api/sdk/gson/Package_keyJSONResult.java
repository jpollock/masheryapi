package com.mashery.pm.api.sdk.gson;

import java.util.List;

import com.mashery.pm.api.sdk.domain.Package_key;

public class Package_keyJSONResult extends BaseJSONResult {
	

	private Package_key result;
	
	Package_keyJSONResult()
	{
		
	}

	public Package_key getResult() {
		return result;
	}

	public void setResult(Package_key result) {
		this.result = result;
	}
	
}
