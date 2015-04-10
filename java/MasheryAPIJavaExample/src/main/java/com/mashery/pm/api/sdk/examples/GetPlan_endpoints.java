package com.mashery.pm.api.sdk.examples;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Properties;
import java.io.IOException;
import com.mashery.pm.api.sdk.domain.MasheryFieldsHelper;
import com.mashery.pm.api.sdk.domain.Plan_endpoint;
import com.mashery.pm.api.sdk.service.Plan_endpointAPIService;

public class GetPlan_endpoints {


public static void main(String[] args) {
		GetPlan_endpoints getPlan_endpoints = new GetPlan_endpoints();
		getPlan_endpoints.exec();
	}
	private void exec() {

		try {
			Plan_endpointAPIService svc = new Plan_endpointAPIService();
			List<Plan_endpoint> items = svc.getAllPlan_endpoints(MasheryFieldsHelper.PLAN_ENDPOINT_ALL_FIELDS, null);
			for (Plan_endpoint item : items) {
				System.out.println(item.getObject_type());
			}
		} catch (Exception ex) { ex.printStackTrace(); }
	}
}