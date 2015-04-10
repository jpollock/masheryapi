package com.mashery.pm.api.sdk.gson;

import java.util.List;

import com.mashery.pm.api.sdk.domain.Key;

public class KeyJSONResult extends BaseJSONResult {
	

	private Key result;
	
	KeyJSONResult()
	{
		
	}

	public Key getResult() {
		return result;
	}

	public void setResult(Key result) {
		this.result = result;
	}
	
}
