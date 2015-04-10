package com.mashery.pm.api.sdk.gson;

public class MemberQueryJSONResultContainer extends BaseQueryJSONResultContainer {
	public MemberQueryJSONResult getResult() {
		return result;
	}

	public void setResult(MemberQueryJSONResult result) {
		this.result = result;
	}

	private MemberQueryJSONResult result;
	
	public MemberQueryJSONResultContainer()
	{
		
	}
	
}
