package com.mashery.pm.api.sdk.domain;

import java.util.List;

public class Plan_filter {
	//private String allFields = "*, plan, plan_method, response_filter";
	private String object_type;  //maxLength:32, format: , defaultValue=, optional=yes, readonly=yes, createonly=no, queryable=no, sortable=no
	private Integer id;  //maxLength:32, format: , defaultValue=, optional=no, readonly=yes, createonly=no, queryable=no, sortable=no
	private Plan plan;
	private Plan_method plan_method;
	

	public Plan_filter() {
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

	public Plan_method getPlan_method() {
		return plan_method;
	}

	public void setPlan_method(Plan_method plan_method) {
		this.plan_method = plan_method;
	}
}
