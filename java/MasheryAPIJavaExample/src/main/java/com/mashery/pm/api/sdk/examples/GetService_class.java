package com.mashery.pm.api.sdk.examples;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Properties;
import java.io.IOException;
import com.mashery.pm.api.sdk.domain.MasheryFieldsHelper;
import com.mashery.pm.api.sdk.domain.Service_class;
import com.mashery.pm.api.sdk.service.Service_classAPIService;

public class GetService_class {


public static void main(String[] args) {
		GetService_class getService_class = new GetService_class();
		getService_class.exec();
	}
	private void exec() {

		try {
			Service_classAPIService svc = new Service_classAPIService();
			List<Service_class> items = svc.getAllService_class(MasheryFieldsHelper.SERVICE_CLASS_ALL_FIELDS, null);
			for (Service_class item : items) {
				System.out.println(item.getObject_type());
			}
		} catch (Exception ex) { ex.printStackTrace(); }
	}
}