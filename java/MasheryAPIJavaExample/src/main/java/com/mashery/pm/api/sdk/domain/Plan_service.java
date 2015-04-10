package com.mashery.pm.api.sdk.domain;

import java.util.List;

public class Plan_service {
	//private String allFields = "*, plan, service_definition";
	private String object_type;  //maxLength:32, format: , defaultValue=, optional=yes, readonly=yes, createonly=no, queryable=no, sortable=no
	private Integer id;  //maxLength:32, format: , defaultValue=, optional=no, readonly=yes, createonly=no, queryable=no, sortable=no
	private Plan plan;
	private Service_definition service_definition;


	public Plan_service() {
	}

	public Integer getId() {
		return this.id;
}
	public void setId(Integer id) {
		this.id = id;
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

	public Service_definition getService_definition() {
		return service_definition;
	}

	public void setService_definition(Service_definition service_definition) {
		this.service_definition = service_definition;
	}
}
