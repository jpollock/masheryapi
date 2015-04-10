package com.mashery.pm.api.sdk.domain;

import java.util.List;

public class Service_definition_endpoint extends BaseMasheryObject {
	private String name;  //maxLength:255, format: date-time, defaultValue=, optional=no, readonly=no, createonly=no, queryable=no, sortable=no
	private String epkey;  //maxLength:24, format: date-time, defaultValue=, optional=no, readonly=no, createonly=no, queryable=no, sortable=no
	private Service_definition service_definition;
	private List<Service_definition_method> service_definition_methods;

	public Service_definition_endpoint() {
	}


	public String getName() {
		return this.name;
}
	public void setName(String name) {
		this.name = name;
	}

	public String getEpkey() {
		return this.epkey;
}
	public void setEpkey(String epkey) {
		this.epkey = epkey;
	}

	public Service_definition getService_definition() {
		return service_definition;
	}

	public void setService_definition(Service_definition service_definition) {
		this.service_definition = service_definition;
	}

	public List<Service_definition_method> getService_definition_methods() {
		return service_definition_methods;
	}

	public void setService_definition_methods(List<Service_definition_method> service_definition_methods) {
		this.service_definition_methods = service_definition_methods;
	}

}
