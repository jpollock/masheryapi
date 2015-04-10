package com.mashery.pm.api.sdk.examples;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Properties;
import java.io.IOException;
import com.mashery.pm.api.sdk.domain.MasheryFieldsHelper;
import com.mashery.pm.api.sdk.domain.Service_definition_response;
import com.mashery.pm.api.sdk.service.Service_definition_responseAPIService;

public class GetService_definition_responses {


public static void main(String[] args) {
		GetService_definition_responses getService_definition_responses = new GetService_definition_responses();
		getService_definition_responses.exec();
	}
	private void exec() {
		try {
			Service_definition_responseAPIService svc = new Service_definition_responseAPIService();

			List<Service_definition_response> items = svc.getAllService_definition_responses(MasheryFieldsHelper.SERVICE_DEFINITION_RESPONSE_ALL_FIELDS, null);
			for (Service_definition_response item : items) {
				System.out.println(item.getObject_type());
			}
		} catch (Exception ex) { ex.printStackTrace(); }
	}
}