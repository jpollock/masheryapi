package com.mashery.pm.api.sdk.domain;

import java.util.List;

public class Plan {
	//private String allFields = "*, limits, package, services, endpoints, methods, filters, keys";
	private List<Limit> limits;  //maxLength:, format: , defaultValue=, optional=yes, readonly=yes, createonly=no, queryable=no, sortable=no
	private Boolean rate_limit_key_override_allowed;  //maxLength:, format: , defaultValue=, optional=no, readonly=no, createonly=no, queryable=no, sortable=no
	private String status;  //maxLength:16, format: , defaultValue=, optional=no, readonly=no, createonly=no, queryable=no, sortable=no
	private String rate_limit_period;  //maxLength:8, format: , defaultValue=, optional=no, readonly=no, createonly=no, queryable=no, sortable=no
	private Integer package_key_moderation_threshold;  //maxLength:8, format: , defaultValue=, optional=no, readonly=no, createonly=no, queryable=no, sortable=no
	private Integer qps_limit_ceiling;  //maxLength:8, format: , defaultValue=, optional=no, readonly=no, createonly=no, queryable=no, sortable=no
	private Boolean qps_limit_package_key_override_allowed;  //maxLength:8, format: , defaultValue=, optional=no, readonly=no, createonly=no, queryable=no, sortable=no
	private Boolean rate_limit_package_key_override_allowed;  //maxLength:8, format: , defaultValue=, optional=no, readonly=no, createonly=no, queryable=no, sortable=no
	private Boolean qps_limit_exempt;  //maxLength:8, format: , defaultValue=, optional=no, readonly=no, createonly=no, queryable=no, sortable=no
	private String object_type;  //maxLength:32, format: , defaultValue=, optional=no, readonly=yes, createonly=no, queryable=no, sortable=no
	private Integer id;  //maxLength:32, format: , defaultValue=, optional=no, readonly=yes, createonly=no, queryable=no, sortable=no
	private Integer package_key_max;  //maxLength:32, format: , defaultValue=, optional=no, readonly=no, createonly=no, queryable=no, sortable=no
	private Boolean is_public;  //maxLength:32, format: , defaultValue=, optional=no, readonly=no, createonly=no, queryable=no, sortable=no
	private String updated;  //maxLength:20, format: date-time, defaultValue=, optional=no, readonly=yes, createonly=no, queryable=no, sortable=no
	private String created;  //maxLength:20, format: date-time, defaultValue=, optional=no, readonly=yes, createonly=no, queryable=no, sortable=no
	private Integer rate_limit_ceiling;  //maxLength:20, format: date-time, defaultValue=, optional=no, readonly=no, createonly=no, queryable=no, sortable=no
	private Integer priority;  //maxLength:20, format: date-time, defaultValue=, optional=no, readonly=no, createonly=no, queryable=no, sortable=no
	private String description;  //maxLength:20, format: date-time, defaultValue=, optional=no, readonly=no, createonly=no, queryable=no, sortable=no
	private String name;  //maxLength:255, format: date-time, defaultValue=, optional=no, readonly=no, createonly=no, queryable=no, sortable=no
	private Boolean rate_limit_exempt;  //maxLength:255, format: date-time, defaultValue=, optional=no, readonly=no, createonly=no, queryable=no, sortable=no
	private Boolean response_filter_override_allowed;  //maxLength:255, format: date-time, defaultValue=, optional=no, readonly=no, createonly=no, queryable=no, sortable=no
	private Boolean is_moderated;  //maxLength:255, format: date-time, defaultValue=, optional=no, readonly=no, createonly=no, queryable=no, sortable=no
	private Boolean qps_limit_key_override_allowed;  //maxLength:255, format: date-time, defaultValue=, optional=no, readonly=no, createonly=no, queryable=no, sortable=no
	private String notes;  //maxLength:255, format: date-time, defaultValue=, optional=no, readonly=no, createonly=no, queryable=no, sortable=no
	private Email_template_set email_template_set;
	private Package parentPackage;
	private List<Plan_service> plan_services;
	private List<Plan_endpoint> plan_endpoints;
	private List<Plan_method> plan_methods;
	private List<Plan_filter> plan_filters;
	private List<Package_key> keys;

	public Plan() {
	}

	public List<Limit> getLimits() {
		return this.limits;
}
	public void setLimits(List<Limit> limits) {
		this.limits = limits;
	}

	public Boolean getRate_limit_key_override_allowed() {
		return this.rate_limit_key_override_allowed;
}
	public void setRate_limit_key_override_allowed(Boolean rate_limit_key_override_allowed) {
		this.rate_limit_key_override_allowed = rate_limit_key_override_allowed;
	}

	public String getStatus() {
		return this.status;
}
	public void setStatus(String status) {
		this.status = status;
	}

	public String getRate_limit_period() {
		return this.rate_limit_period;
}
	public void setRate_limit_period(String rate_limit_period) {
		this.rate_limit_period = rate_limit_period;
	}

	public Integer getPackage_key_moderation_threshold() {
		return this.package_key_moderation_threshold;
}
	public void setPackage_key_moderation_threshold(Integer package_key_moderation_threshold) {
		this.package_key_moderation_threshold = package_key_moderation_threshold;
	}

	public Integer getQps_limit_ceiling() {
		return this.qps_limit_ceiling;
}
	public void setQps_limit_ceiling(Integer qps_limit_ceiling) {
		this.qps_limit_ceiling = qps_limit_ceiling;
	}

	public Boolean getQps_limit_package_key_override_allowed() {
		return this.qps_limit_package_key_override_allowed;
}
	public void setQps_limit_package_key_override_allowed(Boolean qps_limit_package_key_override_allowed) {
		this.qps_limit_package_key_override_allowed = qps_limit_package_key_override_allowed;
	}

	public Boolean getRate_limit_package_key_override_allowed() {
		return this.rate_limit_package_key_override_allowed;
}
	public void setRate_limit_package_key_override_allowed(Boolean rate_limit_package_key_override_allowed) {
		this.rate_limit_package_key_override_allowed = rate_limit_package_key_override_allowed;
	}

	public Boolean getQps_limit_exempt() {
		return this.qps_limit_exempt;
}
	public void setQps_limit_exempt(Boolean qps_limit_exempt) {
		this.qps_limit_exempt = qps_limit_exempt;
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

	public Integer getPackage_key_max() {
		return this.package_key_max;
}
	public void setPackage_key_max(Integer package_key_max) {
		this.package_key_max = package_key_max;
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

	public Boolean getIs_public() {
		return this.is_public;
}
	public void setIs_public(Boolean is_public) {
		this.is_public = is_public;
	}

	public String getDescription() {
		return this.description;
}
	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getPriority() {
		return this.priority;
}
	public void setPriority(Integer priority) {
		this.priority = priority;
	}

	public Boolean getRate_limit_exempt() {
		return this.rate_limit_exempt;
}
	public void setRate_limit_exempt(Boolean rate_limit_exempt) {
		this.rate_limit_exempt = rate_limit_exempt;
	}

	public String getName() {
		return this.name;
}
	public void setName(String name) {
		this.name = name;
	}

	public Boolean getResponse_filter_override_allowed() {
		return this.response_filter_override_allowed;
}
	public void setResponse_filter_override_allowed(Boolean response_filter_override_allowed) {
		this.response_filter_override_allowed = response_filter_override_allowed;
	}

	public Boolean getQps_limit_key_override_allowed() {
		return this.qps_limit_key_override_allowed;
}
	public void setQps_limit_key_override_allowed(Boolean qps_limit_key_override_allowed) {
		this.qps_limit_key_override_allowed = qps_limit_key_override_allowed;
	}

	public Boolean getIs_moderated() {
		return this.is_moderated;
}
	public void setIs_moderated(Boolean is_moderated) {
		this.is_moderated = is_moderated;
	}

	public String getNotes() {
		return this.notes;
}
	public void setNotes(String notes) {
		this.notes = notes;
	}

	public Package getPackage() {
		return parentPackage;
	}

	public void setPackage(Package parentPackage) {
		this.parentPackage = parentPackage;
	}

	public List<Plan_service> getPlan_services() {
		return plan_services;
	}

	public void setPlan_services(List<Plan_service> plan_services) {
		this.plan_services = plan_services;
	}

	public List<Plan_endpoint> getPlan_endpoints() {
		return plan_endpoints;
	}

	public void setEndpoints(List<Plan_endpoint> plan_endpoints) {
		this.plan_endpoints = plan_endpoints;
	}

	public List<Plan_method> getPlan_methods() {
		return plan_methods;
	}

	public void setPlan_methods(List<Plan_method> plan_methods) {
		this.plan_methods = plan_methods;
	}

	public List<Plan_filter> getPlan_filters() {
		return plan_filters;
	}

	public void setPlan_filters(List<Plan_filter> plan_filters) {
		this.plan_filters = plan_filters;
	}

	public List<Package_key> getKeys() {
		return keys;
	}

	public void setKeys(List<Package_key> keys) {
		this.keys = keys;
	}

	public Email_template_set getEmail_template_set() {
		return email_template_set;
	}

	public void setEmail_template_set(Email_template_set email_template_set) {
		this.email_template_set = email_template_set;
	}
}
