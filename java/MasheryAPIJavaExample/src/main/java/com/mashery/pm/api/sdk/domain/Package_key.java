package com.mashery.pm.api.sdk.domain;

import java.util.List;

public class Package_key {
	//private String allFields = "*, plan, package, application, member, method_overrides";
	private String object_type;  //maxLength:32, format: , defaultValue=, optional=yes, readonly=yes, createonly=no, queryable=no, sortable=no
	private List<Limit> limits;  //maxLength:32, format: , defaultValue=, optional=no, readonly=yes, createonly=no, queryable=no, sortable=no
	private Integer id;  //maxLength:32, format: , defaultValue=, optional=no, readonly=yes, createonly=no, queryable=no, sortable=no
	private String updated;  //maxLength:20, format: date-time, defaultValue=, optional=no, readonly=yes, createonly=no, queryable=no, sortable=no
	private String created;  //maxLength:20, format: date-time, defaultValue=, optional=no, readonly=yes, createonly=no, queryable=no, sortable=yes
	private String status;  //maxLength:16, format: date-time, defaultValue=, optional=no, readonly=no, createonly=no, queryable=no, sortable=no
	private Integer rate_limit_ceiling;  //maxLength:16, format: date-time, defaultValue=, optional=no, readonly=no, createonly=no, queryable=no, sortable=no
	private Boolean rate_limit_exempt;  //maxLength:16, format: date-time, defaultValue=, optional=no, readonly=no, createonly=no, queryable=no, sortable=no
	private String secret;  //maxLength:255, format: date-time, defaultValue=, optional=no, readonly=no, createonly=no, queryable=no, sortable=no
	private Integer qps_limit_ceiling;  //maxLength:255, format: date-time, defaultValue=, optional=no, readonly=no, createonly=no, queryable=no, sortable=no
	private Boolean qps_limit_exempt;  //maxLength:255, format: date-time, defaultValue=, optional=no, readonly=no, createonly=no, queryable=no, sortable=no
	private String apikey;  //maxLength:255, format: date-time, defaultValue=, optional=no, readonly=no, createonly=no, queryable=no, sortable=no
	private List<Key> keys;
	private Plan plan;
	private Package parentPackage;
	private Application application;
	private Member member; 
	private List<Method_override> method_overrides;

	public Package_key() {
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

	public Integer getRate_limit_ceiling() {
		return this.rate_limit_ceiling;
}
	public void setRate_limit_ceiling(Integer rate_limit_ceiling) {
		this.rate_limit_ceiling = rate_limit_ceiling;
	}

	public String getStatus() {
		return this.status;
}
	public void setStatus(String status) {
		this.status = status;
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

	public String getSecret() {
		return this.secret;
}
	public void setSecret(String secret) {
		this.secret = secret;
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

	public List<Key> getKeys() {
		return keys;
	}

	public void setKeys(List<Key> keys) {
		this.keys = keys;
	}

	public Plan getPlan() {
		return plan;
	}

	public void setPlan(Plan plan) {
		this.plan = plan;
	}

	public Package getPackage() {
		return parentPackage;
	}


	public void setPackage(Package parentPackage) {
		this.parentPackage = parentPackage;
	}
	public Application getApplication() {
		return application;
	}

	public void setApplication(Application application) {
		this.application = application;
	}

	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

	public List<Method_override> getMethod_overrides() {
		return method_overrides;
	}

	public void setMethod_overrides(List<Method_override> method_overrides) {
		this.method_overrides = method_overrides;
	}

	
}
