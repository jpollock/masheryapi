package com.mashery.pm.api.sdk.domain;

import java.util.List;

public class Developer_class {
	
	private String object_type;  //maxLength:32, format: , defaultValue=, optional=yes, readonly=yes, createonly=no, queryable=no, sortable=no
	private Integer id;  //maxLength:32, format: , defaultValue=, optional=no, readonly=yes, createonly=no, queryable=no, sortable=no
	private String updated;  //maxLength:20, format: date-time, defaultValue=, optional=no, readonly=yes, createonly=no, queryable=no, sortable=no
	private String created;  //maxLength:20, format: date-time, defaultValue=, optional=no, readonly=yes, createonly=no, queryable=no, sortable=no
	private String description;  //maxLength:20, format: date-time, defaultValue=, optional=no, readonly=no, createonly=no, queryable=no, sortable=no
	private Boolean qps_limit_override_allowed;  //maxLength:20, format: date-time, defaultValue=, optional=no, readonly=no, createonly=no, queryable=no, sortable=no
	private String name;  //maxLength:32, format: date-time, defaultValue=, optional=no, readonly=no, createonly=no, queryable=no, sortable=yes
	private Boolean block_calls;  //maxLength:32, format: date-time, defaultValue=, optional=no, readonly=no, createonly=no, queryable=no, sortable=no
	private Boolean rate_limit_override_allowed;  //maxLength:32, format: date-time, defaultValue=, optional=no, readonly=no, createonly=no, queryable=no, sortable=no
	private List<Service_class> service_classes;
	private List<Key> keys;

	public Developer_class() {
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

	public Boolean getQps_limit_override_allowed() {
		return this.qps_limit_override_allowed;
}
	public void setQps_limit_override_allowed(Boolean qps_limit_override_allowed) {
		this.qps_limit_override_allowed = qps_limit_override_allowed;
	}

	public Boolean getBlock_calls() {
		return this.block_calls;
}
	public void setBlock_calls(Boolean block_calls) {
		this.block_calls = block_calls;
	}

	public Boolean getRate_limit_override_allowed() {
		return this.rate_limit_override_allowed;
}
	public void setRate_limit_override_allowed(Boolean rate_limit_override_allowed) {
		this.rate_limit_override_allowed = rate_limit_override_allowed;
	}

	public List<Service_class> getService_classes() {
		return service_classes;
	}

	public void setService_classes(List<Service_class> service_classes) {
		this.service_classes = service_classes;
	}

	public List<Key> getKeys() {
		return keys;
	}

	public void setKeys(List<Key> keys) {
		this.keys = keys;
	}
}
