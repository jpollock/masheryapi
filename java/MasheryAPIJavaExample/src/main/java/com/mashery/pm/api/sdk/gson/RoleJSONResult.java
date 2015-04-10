package com.mashery.pm.api.sdk.gson;

import java.util.List;

import com.mashery.pm.api.sdk.domain.Role;

public class RoleJSONResult extends BaseJSONResult {
	

	private Role result;
	
	RoleJSONResult()
	{
		
	}

	public Role getResult() {
		return result;
	}

	public void setResult(Role result) {
		this.result = result;
	}
	
}
