package com.mashery.pm.api.sdk.gson;

import java.util.List;

import com.mashery.pm.api.sdk.domain.Service_iodoc;

public class Service_iodocJSONResult extends BaseJSONResult {
	

	private Service_iodoc result;
	
	
	Service_iodocJSONResult()
	{
		
	}

	public Service_iodoc getResult() {
		return result;
	}

	public void setResult(Service_iodoc result) {
		this.result = result;
	}

	
	
}
