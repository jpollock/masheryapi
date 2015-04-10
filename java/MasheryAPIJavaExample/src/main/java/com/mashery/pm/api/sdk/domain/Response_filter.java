package com.mashery.pm.api.sdk.domain;

import java.util.List;

public class Response_filter {

	private String object_type;  //maxLength:32, format: , defaultValue=, optional=yes, readonly=yes, createonly=no, queryable=no, sortable=no
	private Integer id;  //maxLength:32, format: , defaultValue=, optional=no, readonly=yes, createonly=no, queryable=no, sortable=no
	private String updated;  //maxLength:20, format: date-time, defaultValue=, optional=yes, readonly=yes, createonly=no, queryable=no, sortable=no
	private String created;  //maxLength:20, format: date-time, defaultValue=, optional=yes, readonly=yes, createonly=no, queryable=no, sortable=no
	private String name;  //maxLength:255, format: date-time, defaultValue=, optional=no, readonly=no, createonly=no, queryable=no, sortable=no
	private String notes;  //maxLength:255, format: date-time, defaultValue=, optional=yes, readonly=no, createonly=no, queryable=no, sortable=no
	private List<Response_filter_format> formats;
	private Service_definition_method service_definition_method;
	private List<Plan_filter> plan_filters;
	
	


	public List<Response_filter_format> getFormats() {
		return formats;
	}

	public void setFormats(List<Response_filter_format> formats) {
		this.formats = formats;
	}

	public Response_filter() {
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

	public String getNotes() {
		return this.notes;
}
	public void setNotes(String notes) {
		this.notes = notes;
	}

	public Service_definition_method getService_definition_method() {
		return service_definition_method;
	}

	public void setService_definition_method(Service_definition_method service_definition_method) {
		this.service_definition_method = service_definition_method;
	}

	public List<Plan_filter> getPlan_filters() {
		return plan_filters;
	}

	public void setPlan_filters(List<Plan_filter> plan_filters) {
		this.plan_filters = plan_filters;
	}
}
