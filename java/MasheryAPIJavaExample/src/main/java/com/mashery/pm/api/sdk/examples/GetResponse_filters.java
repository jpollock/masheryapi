package com.mashery.pm.api.sdk.examples;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Properties;
import java.io.IOException;
import com.mashery.pm.api.sdk.domain.MasheryFieldsHelper;
import com.mashery.pm.api.sdk.domain.Response_filter;
import com.mashery.pm.api.sdk.service.Response_filterAPIService;

public class GetResponse_filters {


public static void main(String[] args) {
		GetResponse_filters getResponse_filters = new GetResponse_filters();
		getResponse_filters.exec();
	}
	private void exec() {

		try {
			Response_filterAPIService svc = new Response_filterAPIService();
			List<Response_filter> items = svc.getAllResponse_filters(MasheryFieldsHelper.RESPONSE_FILTER_ALL_FIELDS, null);
			for (Response_filter item : items) {
				System.out.println(item.getObject_type());
			}
		} catch (Exception ex) { ex.printStackTrace(); }
	}
}