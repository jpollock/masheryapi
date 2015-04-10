package com.mashery.pm.api.sdk.gson;

import java.util.List;

import com.mashery.pm.api.sdk.domain.Member;

public class MemberJSONResult extends BaseJSONResult {
	

	private Member result;
	
	MemberJSONResult()
	{
		
	}

	public Member getResult() {
		return result;
	}

	public void setResult(Member result) {
		this.result = result;
	}
	
}
