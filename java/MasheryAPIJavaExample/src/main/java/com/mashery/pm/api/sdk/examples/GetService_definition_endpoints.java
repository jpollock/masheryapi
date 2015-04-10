package com.mashery.pm.api.sdk.examples;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Properties;
import java.io.IOException;
import com.mashery.pm.api.sdk.domain.MasheryFieldsHelper;
import com.mashery.pm.api.sdk.domain.Service_definition_endpoint;
import com.mashery.pm.api.sdk.service.Service_definition_endpointAPIService;

public class GetService_definition_endpoints {


public static void main(String[] args) {
		GetService_definition_endpoints getService_definition_endpoints = new GetService_definition_endpoints();
		getService_definition_endpoints.exec();
	}
	private void exec() {

		try {
			Service_definition_endpointAPIService svc = new Service_definition_endpointAPIService();
			List<Service_definition_endpoint> items = svc.getAllService_definition_endpoints(MasheryFieldsHelper.SERVICE_DEFINITION_ENDPOINT_ALL_FIELDS, null);
			for (Service_definition_endpoint item : items) {
				System.out.println(item.getObject_type());
			}
		} catch (Exception ex) { ex.printStackTrace(); }
	}
}