package com.mashery.pm.api.sdk.examples;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Properties;
import java.io.IOException;
import com.mashery.pm.api.sdk.domain.MasheryFieldsHelper;
import com.mashery.pm.api.sdk.domain.Plan_method;
import com.mashery.pm.api.sdk.service.Plan_methodAPIService;

public class GetPlan_methods {


public static void main(String[] args) {
		GetPlan_methods getPlan_methods = new GetPlan_methods();
		getPlan_methods.exec();
	}
	private void exec() {

		try {
			Plan_methodAPIService svc = new Plan_methodAPIService();
			List<Plan_method> items = svc.getAllPlan_methods(MasheryFieldsHelper.PLAN_METHOD_ALL_FIELDS, null);
			for (Plan_method item : items) {
				System.out.println(item.getObject_type());
			}
		} catch (Exception ex) { ex.printStackTrace(); }
	}
}