package com.mashery.pm.api.sdk.examples;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Properties;
import java.io.IOException;
import com.mashery.pm.api.sdk.domain.MasheryFieldsHelper;
import com.mashery.pm.api.sdk.domain.Service_definition;
import com.mashery.pm.api.sdk.service.Service_definitionAPIService;

public class GetService_definitions {


public static void main(String[] args) {
		GetService_definitions getService_definitions = new GetService_definitions();
		getService_definitions.exec();
	}
	private void exec() {

		try {
			Service_definitionAPIService svc = new Service_definitionAPIService();
			List<Service_definition> items = svc.getAllService_definitions(MasheryFieldsHelper.SERVICE_DEFINITION_ALL_FIELDS, null);
			for (Service_definition item : items) {
				System.out.println(item.getObject_type());
			}
		} catch (Exception ex) { ex.printStackTrace(); }
	}
}