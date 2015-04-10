package com.mashery.pm.api.sdk.examples;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Properties;
import java.io.IOException;
import com.mashery.pm.api.sdk.domain.MasheryFieldsHelper;
import com.mashery.pm.api.sdk.domain.Application;
import com.mashery.pm.api.sdk.service.ApplicationAPIService;

public class GetApplications {


public static void main(String[] args) {
		GetApplications getApplications = new GetApplications();
		getApplications.exec();
	}
	private void exec() {

		try {
			ApplicationAPIService svc = new ApplicationAPIService();
			List<Application> items = svc.getAllApplications(MasheryFieldsHelper.APPLICATION_ALL_FIELDS, null);
			for (Application item : items) {
				System.out.println(item.getObject_type());
			}
		} catch (Exception ex) { ex.printStackTrace(); }
	}
}