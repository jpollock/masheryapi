package com.mashery.pm.api.sdk.gson;

import java.util.List;

import com.mashery.pm.api.sdk.domain.Email_template_set;

public class Email_template_setJSONResult extends BaseJSONResult {
	

	private Email_template_set result;
	
	Email_template_setJSONResult()
	{
		
	}

	public Email_template_set getResult() {
		return result;
	}

	public void setResult(Email_template_set result) {
		this.result = result;
	}
	
}
