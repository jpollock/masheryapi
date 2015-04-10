package com.mashery.pm.api.sdk.domain;

import java.util.List;

public class Response_filter_format {

	private String object_type;  //maxLength:32, format: , defaultValue=, optional=yes, readonly=yes, createonly=no, queryable=no, sortable=no
	private Integer id;  //maxLength:32, format: , defaultValue=, optional=no, readonly=yes, createonly=no, queryable=no, sortable=no
	private String updated;  //maxLength:20, format: date-time, defaultValue=, optional=yes, readonly=yes, createonly=no, queryable=no, sortable=no
	private String created;  //maxLength:20, format: date-time, defaultValue=, optional=yes, readonly=yes, createonly=no, queryable=no, sortable=no
	private String filter_fields;  //maxLength:20, format: date-time, defaultValue=, optional=yes, readonly=no, createonly=no, queryable=no, sortable=no
	private String format;  //maxLength:10, format: date-time, defaultValue=, optional=no, readonly=no, createonly=no, queryable=no, sortable=no
	private Response_filter response_filter;

	public Response_filter getResponse_filter() {
		return response_filter;
	}

	public void setResponse_filter(Response_filter response_filter) {
		this.response_filter = response_filter;
	}

	public Response_filter_format() {
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

	public String getFilter_fields() {
		return this.filter_fields;
}
	public void setFilter_fields(String filter_fields) {
		this.filter_fields = filter_fields;
	}

	public String getFormat() {
		return this.format;
}
	public void setFormat(String format) {
		this.format = format;
	}
}
