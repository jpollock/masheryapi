package com.mashery.pm.api.sdk.gson;

import java.util.List;

import com.mashery.pm.api.sdk.domain.Method_override;

public class Method_overrideJSONResult  extends BaseJSONResult{
	

	private Method_override result;
	
	Method_overrideJSONResult()
	{
		
	}

	public Method_override getResult() {
		return result;
	}

	public void setResult(Method_override result) {
		this.result = result;
	}
	
}
