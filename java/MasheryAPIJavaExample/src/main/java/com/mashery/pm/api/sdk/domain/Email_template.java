package com.mashery.pm.api.sdk.domain;

public class Email_template {
	private String object_type;  //maxLength:32, format: , defaultValue=, optional=yes, readonly=yes, createonly=no, queryable=no, sortable=no
	private Integer id;  //maxLength:32, format: , defaultValue=, optional=no, readonly=yes, createonly=no, queryable=no, sortable=no
	private String updated;  //maxLength:20, format: date-time, defaultValue=, optional=no, readonly=yes, createonly=no, queryable=no, sortable=no
	private String created;  //maxLength:20, format: date-time, defaultValue=, optional=no, readonly=yes, createonly=no, queryable=no, sortable=no
	private String from;  //maxLength:20, format: date-time, defaultValue=, optional=no, readonly=no, createonly=no, queryable=no, sortable=no
	private String subject;  //maxLength:20, format: date-time, defaultValue=, optional=no, readonly=no, createonly=no, queryable=no, sortable=no
	private String body;  //maxLength:20, format: date-time, defaultValue=, optional=no, readonly=no, createonly=no, queryable=no, sortable=no
	private String type;  //maxLength:20, format: date-time, defaultValue=, optional=no, readonly=no, createonly=no, queryable=no, sortable=no
	public String getObject_type() {
		return object_type;
	}
	public void setObject_type(String object_type) {
		this.object_type = object_type;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUpdated() {
		return updated;
	}
	public void setUpdated(String updated) {
		this.updated = updated;
	}
	public String getCreated() {
		return created;
	}
	public void setCreated(String created) {
		this.created = created;
	}
	public String getFrom() {
		return from;
	}
	public void setFrom(String from) {
		this.from = from;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
}
