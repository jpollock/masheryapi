package com.mashery.pm.api.sdk.domain;

import java.util.List;

public class Service_definition_response extends BaseMasheryObject {
	private String definition;  //maxLength:32, format: , defaultValue=, optional=yes, readonly=no, createonly=no, queryable=no, sortable=no
	private String format;  //maxLength:10, format: date-time, defaultValue=, optional=no, readonly=no, createonly=no, queryable=no, sortable=no
	private Service_definition_method service_definition_method;


	public Service_definition_response() {
	}

	public String getDefinition() {
		return this.definition;
}
	public void setDefinition(String definition) {
		this.definition = definition;
	}

	public String getFormat() {
		return this.format;
}
	public void setFormat(String format) {
		this.format = format;
	}

	public Service_definition_method getService_definition_method() {
		return service_definition_method;
	}

	public void setService_definition_method(Service_definition_method service_definition_method) {
		this.service_definition_method = service_definition_method;
	}
}
