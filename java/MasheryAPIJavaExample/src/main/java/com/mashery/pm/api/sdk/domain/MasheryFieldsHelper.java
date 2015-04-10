package com.mashery.pm.api.sdk.domain;

public class MasheryFieldsHelper {

	public static final String RESPONSE_FILTER_ALL_FIELDS = "*, service_definition_method, formats, plan_filters";
	public static final String RESPONSE_FILTER_FORMAT_ALL_FIELDS = "*, response_filter";
	public static final String APPLICATION_ALL_FIELDS="*, keys, package_keys, packages, plans, member";
	public static final String DEVELOPER_CLASS_ALL_FIELDS="*, service_classes, keys";
	public static final String KEY_ALL_FIELDS="*, limits, member, application, service, developer_class";
	public static final String MEMBER_ALL_FIELDS="*, applications, keys, roles, package_keys";
	public static final String METHOD_OVERRIDE_ALL_FIELDS="*, package_key, plan_method";
	public static final String METHOD_FILTER_OVERRIDE_ALL_FIELDS="*, method_override, response_filter";
	public static final String PACKAGE_KEY_ALL_FIELDS="*, plan, package, application, member, method_overrides";
	public static final String PACKAGE_ALL_FIELDS="*, plans";
	public static final String PLAN_ENDPOINT_ALL_FIELDS="*, service_definition_endpoint";
	public static final String PLAN_FILTER_ALL_FIELDS="*, plan, plan_method, response_filter";
	public static final String PLAN_METHOD_ALL_FIELDS="*, plan, service_definition_method";
	public static final String PLAN_SERVICE_ALL_FIELDS="*, plan, service_definition";
	public static final String PLAN_ALL_FIELDS="*, limits, package, plan_services, plan_services.service_definition, plan_endpoints, endpoints.service_definition_endpoint, plan_methods, plan_methods.service_definition_method, plan_filters, plan_filters.response_filter, keys";
	public static final String ROLE_ALL_FIELDS="*";
	public static final String SERVICE_CLASS_ALL_FIELDS="*, developer_class, service";
	public static final String SERVICE_DEFINITION_ENDPOINT_ALL_FIELDS="*, service_definition, service_definition_methods";
	public static final String SERVICE_DEFINITION_METHOD_ALL_FIELDS="*, service_definition_endpoint, service_definition_responses";
	public static final String SERVICE_DEFINITION_ALL_FIELDS="*, service, service_definition_endpoints";
	public static final String SERVICE_DEFINITION_RESPONSE_ALL_FIELDS="*, service_definition_method ";
	public static final String SERVICE_ALL_FIELDS="*";
	public static final String SERVICE_IODOC_ALL_FIELDS="*, service";
	
	public static String returnAllFields(String objectType)
	{
		objectType = objectType.toLowerCase();
		if (objectType.equals("application"))
			return APPLICATION_ALL_FIELDS;
		if (objectType.equals("developer_class"))
			return DEVELOPER_CLASS_ALL_FIELDS;
		if (objectType.equals("key"))
			return KEY_ALL_FIELDS;
		if (objectType.equals("member"))
			return MEMBER_ALL_FIELDS;
		if (objectType.equals("method_override"))
			return METHOD_OVERRIDE_ALL_FIELDS;
		if (objectType.equals("package_key"))
			return PACKAGE_KEY_ALL_FIELDS;
		if (objectType.equals("package"))
			return PACKAGE_ALL_FIELDS;
		if (objectType.equals("plan_endpoint"))
			return PLAN_ENDPOINT_ALL_FIELDS;
		if (objectType.equals("plan_filter"))
			return PLAN_FILTER_ALL_FIELDS;
		if (objectType.equals("plan_method"))
			return PLAN_METHOD_ALL_FIELDS;
		if (objectType.equals("plan_service"))
			return PLAN_SERVICE_ALL_FIELDS;
		if (objectType.equals("plan"))
			return PLAN_ALL_FIELDS;
		if (objectType.equals("role"))
			return ROLE_ALL_FIELDS;
		if (objectType.equals("service_iodoc"))
			return SERVICE_IODOC_ALL_FIELDS;
		if (objectType.equals("service_class"))
			return SERVICE_CLASS_ALL_FIELDS;
		if (objectType.equals("service_definition_endpoint"))
			return SERVICE_DEFINITION_ENDPOINT_ALL_FIELDS;
		if (objectType.equals("service_definition_method"))
			return SERVICE_DEFINITION_METHOD_ALL_FIELDS;
		if (objectType.equals("service_definition"))
			return SERVICE_DEFINITION_ALL_FIELDS;
		if (objectType.equals("service"))
			return SERVICE_ALL_FIELDS;
		if (objectType.equals("service_definition_response"))
			return SERVICE_DEFINITION_RESPONSE_ALL_FIELDS;
		if (objectType.equals("method_filter_override"))
			return METHOD_FILTER_OVERRIDE_ALL_FIELDS;
		if (objectType.equals("response_filter"))
			return RESPONSE_FILTER_ALL_FIELDS;
		if (objectType.equals("response_filter_format"))
			return RESPONSE_FILTER_FORMAT_ALL_FIELDS;
		return null;
	}
}
