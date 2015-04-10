package com.mashery.pm.api.sdk.examples;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Properties;
import java.io.IOException;
import com.mashery.pm.api.sdk.domain.MasheryFieldsHelper;
import com.mashery.pm.api.sdk.domain.Service_definition_method;
import com.mashery.pm.api.sdk.service.Service_definition_methodAPIService;

public class GetService_definition_methods {


public static void main(String[] args) {
		GetService_definition_methods getService_definition_methods = new GetService_definition_methods();
		getService_definition_methods.exec();
	}
	private void exec() {
		try {
			Service_definition_methodAPIService svc = new Service_definition_methodAPIService();

			List<Service_definition_method> items = svc.getAllService_definition_methods(MasheryFieldsHelper.SERVICE_DEFINITION_METHOD_ALL_FIELDS, null);
			for (Service_definition_method item : items) {
				System.out.println(item.getObject_type());
			}
		} catch (Exception ex) { ex.printStackTrace(); }
	}
}