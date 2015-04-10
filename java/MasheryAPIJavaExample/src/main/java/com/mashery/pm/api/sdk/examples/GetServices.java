package com.mashery.pm.api.sdk.examples;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Properties;
import java.io.IOException;
import com.mashery.pm.api.sdk.domain.MasheryFieldsHelper;
import com.mashery.pm.api.sdk.domain.Service;
import com.mashery.pm.api.sdk.service.ServiceAPIService;

public class GetServices {


public static void main(String[] args) {
		GetServices getServices = new GetServices();
		getServices.exec();
	}
	private void exec() {

		try {
			ServiceAPIService svc = new ServiceAPIService();
			List<Service> items = svc.list(MasheryFieldsHelper.SERVICE_ALL_FIELDS, null);
			for (Service item : items) {
				System.out.println(item.getObject_type());
			}
		} catch (Exception ex) { ex.printStackTrace(); }
	}
}