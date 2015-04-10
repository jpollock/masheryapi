package com.mashery.pm.api.sdk.domain;

import java.util.List;

public class Service_definition {
	//private String allFields = "*, service, endpoints";
	private String object_type;  //maxLength:32, format: , defaultValue=, optional=yes, readonly=yes, createonly=no, queryable=no, sortable=no
	private Integer id;  //maxLength:32, format: , defaultValue=, optional=no, readonly=yes, createonly=no, queryable=no, sortable=no
	private String service_key;  //maxLength:255, format: , defaultValue=, optional=no, readonly=yes, createonly=no, queryable=no, sortable=no
	private String updated;  //maxLength:20, format: date-time, defaultValue=, optional=no, readonly=yes, createonly=no, queryable=no, sortable=no
	private String created;  //maxLength:20, format: date-time, defaultValue=, optional=no, readonly=yes, createonly=no, queryable=no, sortable=no
	private String name;  //maxLength:255, format: date-time, defaultValue=, optional=no, readonly=no, createonly=no, queryable=no, sortable=no
	private Service service;
	private List<Service_definition_endpoint> service_definition_endpoints;

	public Service_definition() {
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

	public String getService_key() {
		return this.service_key;
}
	public void setService_key(String service_key) {
		this.service_key = service_key;
	}

	public String getCreated() {
		return this.created;
}
	public void setCreated(String created) {
		this.created = created;
	}

	public String getUpdated() {
		return this.updated;
}
	public void setUpdated(String updated) {
		this.updated = updated;
	}

	public String getName() {
		return this.name;
}
	public void setName(String name) {
		this.name = name;
	}

	public Service getService() {
		return service;
	}

	public void setService(Service service) {
		this.service = service;
	}

	public List<Service_definition_endpoint> getService_definition_endpoints() {
		return service_definition_endpoints;
	}

	public void setService_definition_endpoints(List<Service_definition_endpoint> service_definition_endpoints) {
		this.service_definition_endpoints = service_definition_endpoints;
	}

	
}
