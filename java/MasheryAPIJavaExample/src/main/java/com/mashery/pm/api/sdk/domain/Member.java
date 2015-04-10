package com.mashery.pm.api.sdk.domain;

import java.util.List;

public class Member {
	private String region;  //maxLength:50, format: , defaultValue=, optional=yes, readonly=no, createonly=yes, queryable=yes, sortable=no
	private String external_id;  //maxLength:255, format: , defaultValue=, optional=no, readonly=no, createonly=no, queryable=no, sortable=no
	private String phone;  //maxLength:255, format: , defaultValue=, optional=no, readonly=no, createonly=yes, queryable=no, sortable=no
	private String address1;  //maxLength:255, format: , defaultValue=, optional=no, readonly=no, createonly=yes, queryable=no, sortable=no
	private String address2;  //maxLength:255, format: , defaultValue=, optional=no, readonly=no, createonly=yes, queryable=no, sortable=no
	private String area_status;  //maxLength:16, format: , defaultValue=, optional=no, readonly=no, createonly=no, queryable=no, sortable=no
	private String registration_ipaddr;  //maxLength:15, format: , defaultValue=, optional=no, readonly=no, createonly=yes, queryable=no, sortable=no
	private String blog;  //maxLength:255, format: , defaultValue=, optional=no, readonly=no, createonly=yes, queryable=no, sortable=no
	private String im;  //maxLength:255, format: , defaultValue=, optional=no, readonly=no, createonly=yes, queryable=no, sortable=no
	private String passwd_new;  //maxLength:32, format: , defaultValue=, optional=no, readonly=no, createonly=yes, queryable=no, sortable=no
	private String country_code;  //maxLength:8, format: , defaultValue=, optional=no, readonly=no, createonly=yes, queryable=no, sortable=no
	private String uri;  //maxLength:255, format: , defaultValue=, optional=no, readonly=no, createonly=yes, queryable=no, sortable=no
	private String object_type;  //maxLength:32, format: , defaultValue=, optional=no, readonly=yes, createonly=no, queryable=no, sortable=no
	private String first_name;  //maxLength:255, format: , defaultValue=, optional=no, readonly=no, createonly=yes, queryable=no, sortable=no
	private String display_name;  //maxLength:255, format: , defaultValue=, optional=no, readonly=no, createonly=yes, queryable=no, sortable=yes
	private String username;  //maxLength:255, format: , defaultValue=, optional=no, readonly=no, createonly=yes, queryable=no, sortable=yes
	private String created;  //maxLength:20, format: date-time, defaultValue=, optional=no, readonly=yes, createonly=no, queryable=no, sortable=yes
	private String updated;  //maxLength:20, format: date-time, defaultValue=, optional=no, readonly=yes, createonly=no, queryable=no, sortable=yes
	private String email;  //maxLength:255, format: date-time, defaultValue=, optional=no, readonly=no, createonly=yes, queryable=no, sortable=yes
	private String postal_code;  //maxLength:64, format: date-time, defaultValue=, optional=no, readonly=no, createonly=yes, queryable=no, sortable=no
	private String company;  //maxLength:255, format: date-time, defaultValue=, optional=no, readonly=no, createonly=yes, queryable=no, sortable=no
	private String last_name;  //maxLength:255, format: date-time, defaultValue=, optional=no, readonly=no, createonly=yes, queryable=no, sortable=no
	private String locality;  //maxLength:255, format: date-time, defaultValue=, optional=no, readonly=no, createonly=yes, queryable=no, sortable=no
	private String imsvc;  //maxLength:64, format: date-time, defaultValue=, optional=no, readonly=no, createonly=yes, queryable=no, sortable=no
	private List<Application> applications;
	private List<Key> keys;
	private List<Role> roles;
	private List<Package_key> package_keys;

	public Member() {
	}

	public String getRegion() {
		return this.region;
}
	public void setRegion(String region) {
		this.region = region;
	}

	public String getExternal_id() {
		return this.external_id;
}
	public void setExternal_id(String external_id) {
		this.external_id = external_id;
	}

	public String getPhone() {
		return this.phone;
}
	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress1() {
		return this.address1;
}
	public void setAddress1(String address1) {
		this.address1 = address1;
	}

	public String getAddress2() {
		return this.address2;
}
	public void setAddress2(String address2) {
		this.address2 = address2;
	}

	public String getArea_status() {
		return this.area_status;
}
	public void setArea_status(String area_status) {
		this.area_status = area_status;
	}

	public String getRegistration_ipaddr() {
		return this.registration_ipaddr;
}
	public void setRegistration_ipaddr(String registration_ipaddr) {
		this.registration_ipaddr = registration_ipaddr;
	}

	public String getBlog() {
		return this.blog;
}
	public void setBlog(String blog) {
		this.blog = blog;
	}

	public String getIm() {
		return this.im;
}
	public void setIm(String im) {
		this.im = im;
	}

	public String getPasswd_new() {
		return this.passwd_new;
}
	public void setPasswd_new(String passwd_new) {
		this.passwd_new = passwd_new;
	}

	public String getCountry_code() {
		return this.country_code;
}
	public void setCountry_code(String country_code) {
		this.country_code = country_code;
	}

	public String getUri() {
		return this.uri;
}
	public void setUri(String uri) {
		this.uri = uri;
	}

	public String getObject_type() {
		return this.object_type;
}
	public void setObject_type(String object_type) {
		this.object_type = object_type;
	}

	public String getDisplay_name() {
		return this.display_name;
}
	public void setDisplay_name(String display_name) {
		this.display_name = display_name;
	}

	public String getFirst_name() {
		return this.first_name;
}
	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	public String getUsername() {
		return this.username;
}
	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return this.email;
}
	public void setEmail(String email) {
		this.email = email;
	}

	public String getUpdated() {
		return this.updated;
}
	public void setUpdated(String updated) {
		this.updated = updated;
	}

	public String getCreated() {
		return this.created;
}
	public void setCreated(String created) {
		this.created = created;
	}

	public String getCompany() {
		return this.company;
}
	public void setCompany(String company) {
		this.company = company;
	}

	public String getPostal_code() {
		return this.postal_code;
}
	public void setPostal_code(String postal_code) {
		this.postal_code = postal_code;
	}

	public String getLast_name() {
		return this.last_name;
}
	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	public String getLocality() {
		return this.locality;
}
	public void setLocality(String locality) {
		this.locality = locality;
	}

	public String getImsvc() {
		return this.imsvc;
}
	public void setImsvc(String imsvc) {
		this.imsvc = imsvc;
	}

	
	public List<Application> getApplications() {
		return applications;
	}

	public void setApplications(List<Application> applications) {
		this.applications = applications;
	}

	public List<Key> getKeys() {
		return keys;
	}

	public void setKeys(List<Key> keys) {
		this.keys = keys;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	public List<Package_key> getPackage_keys() {
		return package_keys;
	}

	public void setPackage_keys(List<Package_key> package_keys) {
		this.package_keys = package_keys;
	}
}
