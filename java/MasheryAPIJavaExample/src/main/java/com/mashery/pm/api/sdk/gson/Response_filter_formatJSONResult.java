package com.mashery.pm.api.sdk.gson;

import java.util.List;

import com.mashery.pm.api.sdk.domain.Response_filter_format;

public class Response_filter_formatJSONResult  extends BaseJSONResult{
	

	private Response_filter_format result;
	
	Response_filter_formatJSONResult()
	{
		
	}

	public Response_filter_format getResult() {
		return result;
	}

	public void setResult(Response_filter_format result) {
		this.result = result;
	}
	
}
