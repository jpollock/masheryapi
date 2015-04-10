package com.mashery.pm.api.sdk.gson;

import java.util.List;

import com.mashery.pm.api.sdk.domain.Email_template;

public class Email_templateJSONResult extends BaseJSONResult {
	

	private Email_template result;
	
	Email_templateJSONResult()
	{
		
	}

	public Email_template getResult() {
		return result;
	}

	public void setResult(Email_template result) {
		this.result = result;
	}
	
}
