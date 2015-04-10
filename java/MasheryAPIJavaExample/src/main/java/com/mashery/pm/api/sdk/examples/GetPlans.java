package com.mashery.pm.api.sdk.examples;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Properties;
import java.io.IOException;
import com.mashery.pm.api.sdk.domain.MasheryFieldsHelper;
import com.mashery.pm.api.sdk.domain.Plan;
import com.mashery.pm.api.sdk.service.PlanAPIService;

public class GetPlans {


public static void main(String[] args) {
		GetPlans getPlans = new GetPlans();
		getPlans.exec();
	}
	private void exec() {


		try {
			PlanAPIService svc = new PlanAPIService();
			List<Plan> items = svc.getAllPlans(MasheryFieldsHelper.PLAN_ALL_FIELDS, null);
			for (Plan item : items) {
				System.out.println(item.getObject_type());
			}
		} catch (Exception ex) { ex.printStackTrace(); }
	}
}