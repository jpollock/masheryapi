package com.mashery.pm.api.sdk.domain;

import java.util.List;

public class Role {

	private String object_type;  //maxLength:32, format: , defaultValue=, optional=yes, readonly=yes, createonly=no, queryable=no, sortable=no
	private Boolean is_predefined;  //maxLength:32, format: , defaultValue=, optional=no, readonly=yes, createonly=no, queryable=no, sortable=no
	private Integer id;  //maxLength:32, format: , defaultValue=, optional=no, readonly=yes, createonly=no, queryable=no, sortable=no
	private String updated;  //maxLength:20, format: date-time, defaultValue=, optional=no, readonly=yes, createonly=no, queryable=no, sortable=no
	private String created;  //maxLength:20, format: date-time, defaultValue=, optional=no, readonly=yes, createonly=no, queryable=no, sortable=no
	private String description;  //maxLength:20, format: date-time, defaultValue=, optional=no, readonly=no, createonly=no, queryable=no, sortable=no
	private String name;  //maxLength:32, format: date-time, defaultValue=, optional=no, readonly=no, createonly=no, queryable=no, sortable=yes
	private Boolean is_assignable;  //maxLength:32, format: date-time, defaultValue=, optional=no, readonly=yes, createonly=no, queryable=no, sortable=no


	public Role() {
	}

	public Integer getId() {
		return this.id;
}
	public void setId(Integer id) {
		this.id = id;
	}

	public Boolean getIs_predefined() {
		return this.is_predefined;
}
	public void setIs_predefined(Boolean is_predefined) {
		this.is_predefined = is_predefined;
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

	public String getDescription() {
		return this.description;
}
	public void setDescription(String description) {
		this.description = description;
	}

	public String getName() {
		return this.name;
}
	public void setName(String name) {
		this.name = name;
	}

	public Boolean getIs_assignable() {
		return this.is_assignable;
}
	public void setIs_assignable(Boolean is_assignable) {
		this.is_assignable = is_assignable;
	}
}
