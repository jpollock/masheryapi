package com.mashery.pm.api.sdk.domain;

import java.util.List;

public class Service_class {
	//private String allFields = "*, developer_class, service";
	private String object_type;  //maxLength:32, format: , defaultValue=, optional=yes, readonly=yes, createonly=no, queryable=no, sortable=no
	private List<Limit> limits;  //maxLength:32, format: , defaultValue=, optional=no, readonly=yes, createonly=no, queryable=no, sortable=no
	private Integer id;  //maxLength:32, format: , defaultValue=, optional=no, readonly=yes, createonly=no, queryable=no, sortable=no
	private Integer rate_limit_ceiling;  //maxLength:32, format: , defaultValue=, optional=no, readonly=no, createonly=no, queryable=no, sortable=no
	private String updated;  //maxLength:20, format: date-time, defaultValue=, optional=no, readonly=yes, createonly=no, queryable=no, sortable=no
	private String created;  //maxLength:20, format: date-time, defaultValue=, optional=no, readonly=yes, createonly=no, queryable=no, sortable=no
	private Boolean rate_limit_exempt;  //maxLength:20, format: date-time, defaultValue=, optional=no, readonly=no, createonly=no, queryable=no, sortable=no
	private Integer qps_limit_ceiling;  //maxLength:20, format: date-time, defaultValue=, optional=no, readonly=no, createonly=no, queryable=no, sortable=no
	private Boolean qps_limit_exempt;  //maxLength:20, format: date-time, defaultValue=, optional=no, readonly=no, createonly=no, queryable=no, sortable=no
	private Developer_class developer_class;
	private Service service;

	public Service_class() {
	}

	public Integer getId() {
		return this.id;
}
	public void setId(Integer id) {
		this.id = id;
	}

	public List<Limit> getLimits() {
		return this.limits;
}
	public void setLimits(List<Limit> limits) {
		this.limits = limits;
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

	public Integer getRate_limit_ceiling() {
		return this.rate_limit_ceiling;
}
	public void setRate_limit_ceiling(Integer rate_limit_ceiling) {
		this.rate_limit_ceiling = rate_limit_ceiling;
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

	public Developer_class getDeveloper_class() {
		return developer_class;
	}

	public void setDeveloper_class(Developer_class developer_class) {
		this.developer_class = developer_class;
	}

	public Service getService() {
		return service;
	}

	public void setService(Service service) {
		this.service = service;
	}

}
