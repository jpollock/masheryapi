package com.mashery.pm.api.sdk.gson;

import com.mashery.pm.api.sdk.domain.Error;

public class BaseJSONResult {
	private Error error;
	public Error getError() {
		return error;
	}

	public void setError(Error error) {
		this.error = error;
	}
}
