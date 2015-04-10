package com.mashery.pm.api.sdk.gson;

import java.util.List;

import com.mashery.pm.api.sdk.domain.Response_filter;

public class Response_filterJSONResult  extends BaseJSONResult{
	

	private Response_filter result;
	
	Response_filterJSONResult()
	{
		
	}

	public Response_filter getResult() {
		return result;
	}

	public void setResult(Response_filter result) {
		this.result = result;
	}
	
}
