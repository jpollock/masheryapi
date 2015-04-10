package com.mashery.pm.api.sdk.gson;

import java.util.List;
import com.mashery.pm.api.sdk.domain.Package;

public class PackageJSONResult extends BaseJSONResult {
	

	private Package result;
	
	PackageJSONResult()
	{
		
	}

	public Package getResult() {
		return result;
	}

	public void setResult(Package result) {
		this.result = result;
	}
	
}
