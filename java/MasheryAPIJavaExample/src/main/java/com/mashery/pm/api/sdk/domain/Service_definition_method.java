package com.mashery.pm.api.sdk.domain;

import java.util.List;

public class Service_definition_method extends BaseMasheryObject {
	private String name;  //maxLength:255, format: date-time, defaultValue=, optional=no, readonly=no, createonly=no, queryable=no, sortable=no
	private Service_definition_endpoint service_definition_endpoint;
	private List<Service_definition_response> service_definition_responses;
	
	public Service_definition_method() {
	}

	public String getName() {
		return this.name;
}
	public void setName(String name) {
		this.name = name;
	}

	public Service_definition_endpoint getService_definition_endpoint() {
		return service_definition_endpoint;
	}

	public void setService_definition_endpoint(
			Service_definition_endpoint service_definition_endpoint) {
		this.service_definition_endpoint = service_definition_endpoint;
	}

	public List<Service_definition_response> getService_definition_responses() {
		return service_definition_responses;
	}

	public void setService_definition_responses(List<Service_definition_response> service_definition_responses) {
		this.service_definition_responses = service_definition_responses;
	}


}
