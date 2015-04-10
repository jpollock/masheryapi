package com.mashery.pm.api.sdk.domain;

import java.util.List;

public class Plan_endpoint {
	//private String allFields = "*, service_definition_endpoint";
	private String object_type;  //maxLength:32, format: , defaultValue=, optional=yes, readonly=yes, createonly=no, queryable=no, sortable=no
	private Boolean allow_undefined_methods;  //maxLength:32, format: , defaultValue=, optional=no, readonly=no, createonly=no, queryable=no, sortable=no
	private Integer id;  //maxLength:32, format: , defaultValue=, optional=no, readonly=yes, createonly=no, queryable=no, sortable=no
	private Plan plan;
	private Service_definition_endpoint service_definition_endpoint;

	public Plan_endpoint() {
	}

	public Integer getId() {
		return this.id;
}
	public void setId(Integer id) {
		this.id = id;
	}

	public Boolean getAllow_undefined_methods() {
		return this.allow_undefined_methods;
}
	public void setAllow_undefined_methods(Boolean allow_undefined_methods) {
		this.allow_undefined_methods = allow_undefined_methods;
	}

	public String getObject_type() {
		return this.object_type;
}
	public void setObject_type(String object_type) {
		this.object_type = object_type;
	}

	public Plan getPlan() {
		return plan;
	}

	public void setPlan(Plan plan) {
		this.plan = plan;
	}

	public Service_definition_endpoint getService_definition_endpoint() {
		return service_definition_endpoint;
	}

	public void setService_definition_endpoint(
			Service_definition_endpoint service_definition_endpoint) {
		this.service_definition_endpoint = service_definition_endpoint;
	}
}
