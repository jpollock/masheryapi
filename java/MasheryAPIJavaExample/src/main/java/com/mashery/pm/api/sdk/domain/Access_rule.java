package com.mashery.pm.api.sdk.domain;

import java.util.List;

public class Access_rule {

	private String object_type;  //maxLength:32, format: , defaultValue=, optional=yes, readonly=yes, createonly=no, queryable=no, sortable=no
	private Integer id;  //maxLength:32, format: , defaultValue=, optional=no, readonly=yes, createonly=no, queryable=no, sortable=no
	private String updated;  //maxLength:20, format: date-time, defaultValue=, optional=no, readonly=yes, createonly=no, queryable=no, sortable=no
	private String created;  //maxLength:20, format: date-time, defaultValue=, optional=no, readonly=yes, createonly=no, queryable=no, sortable=no
	private Integer pos;  //maxLength:32, format: , defaultValue=, optional=no, readonly=yes, createonly=no, queryable=no, sortable=no
	private String action;  //maxLength:20, format: date-time, defaultValue=, optional=no, readonly=no, createonly=no, queryable=no, sortable=no
	private String object;  //maxLength:20, format: date-time, defaultValue=, optional=no, readonly=no, createonly=no, queryable=no, sortable=no
	private String content;  //maxLength:32, format: date-time, defaultValue=, optional=no, readonly=no, createonly=no, queryable=no, sortable=yes
	private Boolean allow;  //maxLength:32, format: date-time, defaultValue=, optional=no, readonly=yes, createonly=no, queryable=no, sortable=no
	private Role role; 

	public Role getRole() {
		return role;
	}


	public void setRole(Role role) {
		this.role = role;
	}


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


	public Integer getPos() {
		return pos;
	}


	public void setPos(Integer pos) {
		this.pos = pos;
	}


	public String getAction() {
		return action;
	}


	public void setAction(String action) {
		this.action = action;
	}


	public String getObject() {
		return object;
	}


	public void setObject(String object) {
		this.object = object;
	}


	public String getContent() {
		return content;
	}


	public void setContent(String content) {
		this.content = content;
	}


	public Boolean getAllow() {
		return allow;
	}


	public void setAllow(Boolean allow) {
		this.allow = allow;
	}


	public Access_rule() {
	}

	
}
