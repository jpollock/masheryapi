package com.mashery.pm.api.sdk.domain;

import java.util.List;

public class Package {
	//private String allFields = "*, plans";
	private Boolean notify_developer_near_quota;  //maxLength:, format: , defaultValue=, optional=yes, readonly=no, createonly=no, queryable=yes, sortable=no
	private Integer near_quota_threshold;  //maxLength:, format: , defaultValue=, optional=no, readonly=no, createonly=no, queryable=no, sortable=no
	private Boolean notify_admin_over_quota;  //maxLength:, format: , defaultValue=, optional=no, readonly=no, createonly=no, queryable=no, sortable=no
	private Boolean is_using_shared_secret;  //maxLength:, format: , defaultValue=, optional=no, readonly=yes, createonly=no, queryable=no, sortable=no
	private Boolean notify_developer_over_quota;  //maxLength:, format: , defaultValue=, optional=no, readonly=no, createonly=no, queryable=no, sortable=no
	private String notify_admin_period;  //maxLength:8, format: , defaultValue=, optional=no, readonly=no, createonly=no, queryable=no, sortable=no
	private String key_adapter;  //maxLength:256, format: , defaultValue=, optional=no, readonly=no, createonly=no, queryable=no, sortable=no
	private String notify_developer_period;  //maxLength:8, format: , defaultValue=, optional=no, readonly=no, createonly=no, queryable=no, sortable=no
	private Boolean notify_admin_over_throttle;  //maxLength:8, format: , defaultValue=, optional=no, readonly=no, createonly=no, queryable=no, sortable=no
	private String object_type;  //maxLength:32, format: , defaultValue=, optional=no, readonly=yes, createonly=no, queryable=no, sortable=no
	private Integer id;  //maxLength:32, format: , defaultValue=, optional=no, readonly=yes, createonly=no, queryable=no, sortable=no
	private Boolean notify_admin_near_quota;  //maxLength:32, format: , defaultValue=, optional=no, readonly=no, createonly=no, queryable=no, sortable=no
	private String notify_admin_emails;  //maxLength:32, format: , defaultValue=, optional=no, readonly=no, createonly=no, queryable=no, sortable=no
	private Integer shared_secret_length;  //maxLength:32, format: , defaultValue=, optional=no, readonly=no, createonly=no, queryable=no, sortable=no
	private String updated;  //maxLength:20, format: date-time, defaultValue=, optional=no, readonly=yes, createonly=no, queryable=no, sortable=no
	private String created;  //maxLength:20, format: date-time, defaultValue=, optional=no, readonly=yes, createonly=no, queryable=no, sortable=no
	private String description;  //maxLength:20, format: date-time, defaultValue=, optional=no, readonly=no, createonly=no, queryable=no, sortable=no
	private Boolean notify_developer_over_throttle;  //maxLength:20, format: date-time, defaultValue=, optional=no, readonly=no, createonly=no, queryable=no, sortable=no
	private Integer key_length;  //maxLength:20, format: date-time, defaultValue=, optional=no, readonly=no, createonly=no, queryable=no, sortable=no
	private String name;  //maxLength:255, format: date-time, defaultValue=, optional=no, readonly=no, createonly=no, queryable=no, sortable=no
	private List<Plan> plans;
	

	public Package() {
	}

	public Boolean getNotify_developer_near_quota() {
		return this.notify_developer_near_quota;
}
	public void setNotify_developer_near_quota(Boolean notify_developer_near_quota) {
		this.notify_developer_near_quota = notify_developer_near_quota;
	}

	public Integer getNear_quota_threshold() {
		return this.near_quota_threshold;
}
	public void setNear_quota_threshold(Integer near_quota_threshold) {
		this.near_quota_threshold = near_quota_threshold;
	}

	public Boolean getNotify_admin_over_quota() {
		return this.notify_admin_over_quota;
}
	public void setNotify_admin_over_quota(Boolean notify_admin_over_quota) {
		this.notify_admin_over_quota = notify_admin_over_quota;
	}

	public Boolean getIs_using_shared_secret() {
		return this.is_using_shared_secret;
}
	public void setIs_using_shared_secret(Boolean is_using_shared_secret) {
		this.is_using_shared_secret = is_using_shared_secret;
	}

	public Boolean getNotify_developer_over_quota() {
		return this.notify_developer_over_quota;
}
	public void setNotify_developer_over_quota(Boolean notify_developer_over_quota) {
		this.notify_developer_over_quota = notify_developer_over_quota;
	}

	public String getNotify_admin_period() {
		return this.notify_admin_period;
}
	public void setNotify_admin_period(String notify_admin_period) {
		this.notify_admin_period = notify_admin_period;
	}

	public String getKey_adapter() {
		return this.key_adapter;
}
	public void setKey_adapter(String key_adapter) {
		this.key_adapter = key_adapter;
	}

	public String getNotify_developer_period() {
		return this.notify_developer_period;
}
	public void setNotify_developer_period(String notify_developer_period) {
		this.notify_developer_period = notify_developer_period;
	}

	public Boolean getNotify_admin_over_throttle() {
		return this.notify_admin_over_throttle;
}
	public void setNotify_admin_over_throttle(Boolean notify_admin_over_throttle) {
		this.notify_admin_over_throttle = notify_admin_over_throttle;
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

	public Boolean getNotify_admin_near_quota() {
		return this.notify_admin_near_quota;
}
	public void setNotify_admin_near_quota(Boolean notify_admin_near_quota) {
		this.notify_admin_near_quota = notify_admin_near_quota;
	}

	public String getNotify_admin_emails() {
		return this.notify_admin_emails;
}
	public void setNotify_admin_emails(String notify_admin_emails) {
		this.notify_admin_emails = notify_admin_emails;
	}

	public Integer getShared_secret_length() {
		return this.shared_secret_length;
}
	public void setShared_secret_length(Integer shared_secret_length) {
		this.shared_secret_length = shared_secret_length;
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

	public Boolean getNotify_developer_over_throttle() {
		return this.notify_developer_over_throttle;
}
	public void setNotify_developer_over_throttle(Boolean notify_developer_over_throttle) {
		this.notify_developer_over_throttle = notify_developer_over_throttle;
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

	public Integer getKey_length() {
		return this.key_length;
}
	public void setKey_length(Integer key_length) {
		this.key_length = key_length;
	}

	public List<Plan> getPlans() {
		return plans;
	}

	public void setPlans(List<Plan> plans) {
		this.plans = plans;
	}
}
