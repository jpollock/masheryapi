package com.mashery.pm.api.sdk.examples;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Properties;
import java.io.IOException;
import com.mashery.pm.api.sdk.domain.MasheryFieldsHelper;
import com.mashery.pm.api.sdk.domain.Plan_filter;
import com.mashery.pm.api.sdk.service.Plan_filterAPIService;

public class GetPlan_filters {


public static void main(String[] args) {
		GetPlan_filters getPlan_filters = new GetPlan_filters();
		getPlan_filters.exec();
	}
	private void exec() {
		try {
			Plan_filterAPIService svc = new Plan_filterAPIService();
			List<Plan_filter> items = svc.getAllPlan_filters(MasheryFieldsHelper.PLAN_FILTER_ALL_FIELDS, null);
			for (Plan_filter item : items) {
				System.out.println(item.getObject_type());
			}
		} catch (Exception ex) { ex.printStackTrace(); }
	}
}