package com.mashery.pm.api.sdk.domain;

import java.util.List;

public class Key {
	//private String allFields = "*, limits, member, application, service, developer_class";
	private List<Limit> limits;  //maxLength:, format: , defaultValue=, optional=yes, readonly=yes, createonly=no, queryable=no, sortable=no
	private String status;  //maxLength:16, format: , defaultValue=, optional=no, readonly=no, createonly=no, queryable=no, sortable=no
	private Integer group_id;  //maxLength:16, format: , defaultValue=, optional=no, readonly=no, createonly=no, queryable=no, sortable=no
	private Integer qps_limit_ceiling;  //maxLength:16, format: , defaultValue=, optional=no, readonly=no, createonly=no, queryable=no, sortable=no
	private Boolean qps_limit_exempt;  //maxLength:16, format: , defaultValue=, optional=no, readonly=no, createonly=no, queryable=no, sortable=no
	private String apikey;  //maxLength:255, format: , defaultValue=, optional=no, readonly=no, createonly=no, queryable=no, sortable=no
	private String object_type;  //maxLength:32, format: , defaultValue=, optional=no, readonly=yes, createonly=no, queryable=no, sortable=no
	private Integer id;  //maxLength:32, format: , defaultValue=, optional=no, readonly=yes, createonly=no, queryable=no, sortable=no
	private String username;  //maxLength:255, format: , defaultValue=, optional=no, readonly=yes, createonly=no, queryable=no, sortable=no
	private String service_key;  //maxLength:255, format: , defaultValue=, optional=no, readonly=yes, createonly=no, queryable=no, sortable=no
	private String created;  //maxLength:20, format: date-time, defaultValue=, optional=no, readonly=yes, createonly=no, queryable=no, sortable=yes
	private String updated;  //maxLength:20, format: date-time, defaultValue=, optional=no, readonly=yes, createonly=no, queryable=no, sortable=no
	private Integer rate_limit_ceiling;  //maxLength:20, format: date-time, defaultValue=, optional=no, readonly=no, createonly=no, queryable=no, sortable=no
	private Boolean rate_limit_exempt;  //maxLength:20, format: date-time, defaultValue=, optional=no, readonly=no, createonly=no, queryable=no, sortable=no
	private String secret;  //maxLength:255, format: date-time, defaultValue=, optional=no, readonly=no, createonly=no, queryable=no, sortable=no
	private String required_referer;  //maxLength:255, format: date-time, defaultValue=, optional=no, readonly=no, createonly=no, queryable=no, sortable=no
	private Member member;
	private Application application;
	private Service service;
	private Developer_class developer_class;
	

	public Key() {
	}

	public List<Limit> getLimits() {
		return this.limits;
}
	public void setLimits(List<Limit> limits) {
		this.limits = limits;
	}

	public String getStatus() {
		return this.status;
}
	public void setStatus(String status) {
		this.status = status;
	}

	public Integer getGroup_id() {
		return this.group_id;
}
	public void setGroup_id(Integer group_id) {
		this.group_id = group_id;
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

	public String getApikey() {
		return this.apikey;
}
	public void setApikey(String apikey) {
		this.apikey = apikey;
	}

	public String getObject_type() {
		return this.object_type;
}
	public void setObject_type(String object_type) {
		this.object_type = object_type;
	}

	public Integer getId() {
		return this.id;
}
	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsername() {
		return this.username;
}
	public void setUsername(String username) {
		this.username = username;
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

	public String getSecret() {
		return this.secret;
}
	public void setSecret(String secret) {
		this.secret = secret;
	}

	public String getRequired_referer() {
		return this.required_referer;
}
	public void setRequired_referer(String required_referer) {
		this.required_referer = required_referer;
	}

	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

	public Application getApplication() {
		return application;
	}

	public void setApplication(Application application) {
		this.application = application;
	}

	public Service getService() {
		return service;
	}

	public void setService(Service service) {
		this.service = service;
	}

	public Developer_class getDeveloper_class() {
		return developer_class;
	}

	public void setDeveloper_class(Developer_class developer_class) {
		this.developer_class = developer_class;
	}

}
