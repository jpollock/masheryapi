package com.mashery.pm.api.sdk.domain;

import java.util.List;

public class Method_override {
	//private String allFields = "*, package_key, plan_method";
	private String object_type;  //maxLength:32, format: , defaultValue=, optional=yes, readonly=yes, createonly=no, queryable=no, sortable=no
	private Integer id;  //maxLength:32, format: , defaultValue=, optional=no, readonly=yes, createonly=no, queryable=no, sortable=no
	private String updated;  //maxLength:20, format: date-time, defaultValue=, optional=no, readonly=yes, createonly=no, queryable=no, sortable=no
	private String created;  //maxLength:20, format: date-time, defaultValue=, optional=no, readonly=yes, createonly=no, queryable=no, sortable=no
	private Integer rate_limit_ceiling;  //maxLength:20, format: date-time, defaultValue=, optional=no, readonly=no, createonly=no, queryable=no, sortable=no
	private Boolean rate_limit_exempt;  //maxLength:20, format: date-time, defaultValue=, optional=no, readonly=no, createonly=no, queryable=no, sortable=no
	private Integer qps_limit_ceiling;  //maxLength:20, format: date-time, defaultValue=, optional=no, readonly=no, createonly=no, queryable=no, sortable=no
	private Boolean response_filter_exempt;  //maxLength:20, format: date-time, defaultValue=, optional=no, readonly=no, createonly=no, queryable=no, sortable=no
	private Boolean qps_limit_exempt;  //maxLength:20, format: date-time, defaultValue=, optional=no, readonly=no, createonly=no, queryable=no, sortable=no
	private Package_key package_key;
	private Plan_method plan_method;

	public Method_override() {
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

	public Integer getRate_limit_ceiling() {
		return this.rate_limit_ceiling;
}
	public void setRate_limit_ceiling(Integer rate_limit_ceiling) {
		this.rate_limit_ceiling = rate_limit_ceiling;
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

	public Boolean getRate_limit_exempt() {
		return this.rate_limit_exempt;
}
	public void setRate_limit_exempt(Boolean rate_limit_exempt) {
		this.rate_limit_exempt = rate_limit_exempt;
	}

	public Integer getQps_limit_ceiling() {
		return this.qps_limit_ceiling;
}
	public void setQps_limit_ceiling(Integer qps_limit_ceiling) {
		this.qps_limit_ceiling = qps_limit_ceiling;
	}

	public Boolean getQps_limit_exempt() {
		return this.qps_limit_exempt;
}
	public void setQps_limit_exempt(Boolean qps_limit_exempt) {
		this.qps_limit_exempt = qps_limit_exempt;
	}

	public Boolean getResponse_filter_exempt() {
		return this.response_filter_exempt;
}
	public void setResponse_filter_exempt(Boolean response_filter_exempt) {
		this.response_filter_exempt = response_filter_exempt;
	}

	public Package_key getPackage_key() {
		return package_key;
	}

	public void setPackage_key(Package_key package_key) {
		this.package_key = package_key;
	}

	public Plan_method getPlan_method() {
		return plan_method;
	}

	public void setPlan_method(Plan_method plan_method) {
		this.plan_method = plan_method;
	}

}
