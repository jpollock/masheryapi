package com.mashery.pm.api.sdk.examples;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Properties;
import java.io.IOException;
import com.mashery.pm.api.sdk.domain.MasheryFieldsHelper;
import com.mashery.pm.api.sdk.domain.Plan_service;
import com.mashery.pm.api.sdk.service.Plan_serviceAPIService;

public class GetPlan_services {


public static void main(String[] args) {
		GetPlan_services getPlan_services = new GetPlan_services();
		getPlan_services.exec();
	}
	private void exec() {

		try {
			Plan_serviceAPIService svc = new Plan_serviceAPIService();
			List<Plan_service> items = svc.getAllPlan_services(MasheryFieldsHelper.PLAN_SERVICE_ALL_FIELDS, null);
			for (Plan_service item : items) {
				System.out.println(item.getObject_type());
			}
		} catch (Exception ex) { ex.printStackTrace(); }
	}
}