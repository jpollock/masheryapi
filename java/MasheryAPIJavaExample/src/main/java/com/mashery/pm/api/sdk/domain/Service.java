package com.mashery.pm.api.sdk.domain;

import java.util.List;

public class Service {
	private Integer id;  //maxLength:32, format: , defaultValue=, optional=no, readonly=yes, createonly=no, queryable=no, sortable=no
	private Boolean is_client;
	private Boolean is_active;
	private Boolean is_using_shared_secret;  //maxLength:, format: , defaultValue=, optional=yes, readonly=yes, createonly=no, queryable=no, sortable=no
	private String revision_label;  //maxLength:255, format: , defaultValue=, optional=no, readonly=no, createonly=no, queryable=no, sortable=no
	private String cache_config;  //maxLength:255, format: , defaultValue=, optional=no, readonly=no, createonly=no, queryable=no, sortable=no
	private Integer qps_limit_ceiling;  //maxLength:255, format: , defaultValue=, optional=no, readonly=yes, createonly=no, queryable=no, sortable=no
	private String editor_username;  //maxLength:255, format: , defaultValue=, optional=no, readonly=no, createonly=no, queryable=no, sortable=no
	private Integer pos;  //maxLength:255, format: , defaultValue=, optional=no, readonly=no, createonly=no, queryable=no, sortable=no
	private String object_type;  //maxLength:32, format: , defaultValue=, optional=no, readonly=yes, createonly=no, queryable=no, sortable=no
	private Boolean is_self_serve_allowed;  //maxLength:32, format: , defaultValue=, optional=no, readonly=yes, createonly=no, queryable=no, sortable=no
	private Integer revision_number;  //maxLength:32, format: , defaultValue=, optional=no, readonly=no, createonly=no, queryable=no, sortable=no
	private String service_key;  //maxLength:255, format: , defaultValue=, optional=no, readonly=no, createonly=no, queryable=no, sortable=no
	private Integer shared_secret_length;  //maxLength:255, format: , defaultValue=, optional=no, readonly=yes, createonly=no, queryable=no, sortable=no
	private String updated;  //maxLength:20, format: date-time, defaultValue=, optional=no, readonly=yes, createonly=no, queryable=no, sortable=no
	private String created;  //maxLength:20, format: date-time, defaultValue=, optional=no, readonly=yes, createonly=no, queryable=no, sortable=no
	private String name;  //maxLength:255, format: date-time, defaultValue=, optional=no, readonly=no, createonly=no, queryable=no, sortable=no
	private Integer key_length;  //maxLength:255, format: date-time, defaultValue=, optional=no, readonly=yes, createonly=no, queryable=no, sortable=no
	private String config;  //maxLength:255, format: date-time, defaultValue=, optional=no, readonly=no, createonly=no, queryable=no, sortable=no
	private List<Limit> limits;  //maxLength:255, format: date-time, defaultValue=, optional=no, readonly=yes, createonly=no, queryable=no, sortable=no
	private String config_comment;  //maxLength:255, format: date-time, defaultValue=, optional=no, readonly=no, createonly=no, queryable=no, sortable=no
	private Boolean is_dashboard_serve_allowed;  //maxLength:255, format: date-time, defaultValue=, optional=no, readonly=yes, createonly=no, queryable=no, sortable=no
	private String rate_limit_period;  //maxLength:32, format: date-time, defaultValue=, optional=no, readonly=yes, createonly=no, queryable=no, sortable=no
	private Boolean is_rate_limiting_enabled;  //maxLength:32, format: date-time, defaultValue=, optional=no, readonly=yes, createonly=no, queryable=no, sortable=no
	private Integer rate_limit_ceiling;  //maxLength:32, format: date-time, defaultValue=, optional=no, readonly=yes, createonly=no, queryable=no, sortable=no
	private Boolean qps_limit_override_allowed;  //maxLength:32, format: date-time, defaultValue=, optional=no, readonly=yes, createonly=no, queryable=no, sortable=no
	private Boolean rate_limit_override_allowed;  //maxLength:32, format: date-time, defaultValue=, optional=no, readonly=yes, createonly=no, queryable=no, sortable=no
	private Boolean is_qps_limiting_enabled;  //maxLength:32, format: date-time, defaultValue=, optional=no, readonly=yes, createonly=no, queryable=no, sortable=no
	public Boolean getIs_client() {
		return is_client;
	}

	public void setIs_client(Boolean is_client) {
		this.is_client = is_client;
	}

	public Boolean getIs_active() {
		return is_active;
	}

	public void setIs_active(Boolean is_active) {
		this.is_active = is_active;
	}


	public Service() {
	}

	public Boolean getIs_using_shared_secret() {
		return this.is_using_shared_secret;
}
	public void setIs_using_shared_secret(Boolean is_using_shared_secret) {
		this.is_using_shared_secret = is_using_shared_secret;
	}

	public String getRevision_label() {
		return this.revision_label;
}
	public void setRevision_label(String revision_label) {
		this.revision_label = revision_label;
	}

	public String getCache_config() {
		return this.cache_config;
}
	public void setCache_config(String cache_config) {
		this.cache_config = cache_config;
	}

	public Integer getQps_limit_ceiling() {
		return this.qps_limit_ceiling;
}
	public void setQps_limit_ceiling(Integer qps_limit_ceiling) {
		this.qps_limit_ceiling = qps_limit_ceiling;
	}

	public String getEditor_username() {
		return this.editor_username;
}
	public void setEditor_username(String editor_username) {
		this.editor_username = editor_username;
	}

	public Integer getPos() {
		return this.pos;
}
	public void setPos(Integer pos) {
		this.pos = pos;
	}

	public String getObject_type() {
		return this.object_type;
}
	public void setObject_type(String object_type) {
		this.object_type = object_type;
	}

	public Boolean getIs_self_serve_allowed() {
		return this.is_self_serve_allowed;
}
	public void setIs_self_serve_allowed(Boolean is_self_serve_allowed) {
		this.is_self_serve_allowed = is_self_serve_allowed;
	}

	public String getService_key() {
		return this.service_key;
}
	public void setService_key(String service_key) {
		this.service_key = service_key;
	}

	public Integer getRevision_number() {
		return this.revision_number;
}
	public void setRevision_number(Integer revision_number) {
		this.revision_number = revision_number;
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

	public String getConfig() {
		return this.config;
}
	public void setConfig(String config) {
		this.config = config;
	}

	public List<Limit> getLimits() {
		return this.limits;
}
	public void setLimits(List<Limit> limits) {
		this.limits = limits;
	}

	public String getConfig_comment() {
		return this.config_comment;
}
	public void setConfig_comment(String config_comment) {
		this.config_comment = config_comment;
	}

	public Boolean getIs_dashboard_serve_allowed() {
		return this.is_dashboard_serve_allowed;
}
	public void setIs_dashboard_serve_allowed(Boolean is_dashboard_serve_allowed) {
		this.is_dashboard_serve_allowed = is_dashboard_serve_allowed;
	}

	public String getRate_limit_period() {
		return this.rate_limit_period;
}
	public void setRate_limit_period(String rate_limit_period) {
		this.rate_limit_period = rate_limit_period;
	}


	public Boolean getIs_rate_limiting_enabled() {
		return this.is_rate_limiting_enabled;
}
	public void setIs_rate_limiting_enabled(Boolean is_rate_limiting_enabled) {
		this.is_rate_limiting_enabled = is_rate_limiting_enabled;
	}

	public Integer getRate_limit_ceiling() {
		return this.rate_limit_ceiling;
}
	public void setRate_limit_ceiling(Integer rate_limit_ceiling) {
		this.rate_limit_ceiling = rate_limit_ceiling;
	}

	public Boolean getQps_limit_override_allowed() {
		return this.qps_limit_override_allowed;
}
	public void setQps_limit_override_allowed(Boolean qps_limit_override_allowed) {
		this.qps_limit_override_allowed = qps_limit_override_allowed;
	}

	public Boolean getRate_limit_override_allowed() {
		return this.rate_limit_override_allowed;
}
	public void setRate_limit_override_allowed(Boolean rate_limit_override_allowed) {
		this.rate_limit_override_allowed = rate_limit_override_allowed;
	}

	public Boolean getIs_qps_limiting_enabled() {
		return this.is_qps_limiting_enabled;
}
	public void setIs_qps_limiting_enabled(Boolean is_qps_limiting_enabled) {
		this.is_qps_limiting_enabled = is_qps_limiting_enabled;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
}
