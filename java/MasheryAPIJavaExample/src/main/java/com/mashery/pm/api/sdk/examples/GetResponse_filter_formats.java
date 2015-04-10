package com.mashery.pm.api.sdk.examples;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Properties;
import java.io.IOException;
import com.mashery.pm.api.sdk.domain.MasheryFieldsHelper;
import com.mashery.pm.api.sdk.domain.Response_filter_format;
import com.mashery.pm.api.sdk.service.Response_filter_formatAPIService;

public class GetResponse_filter_formats {


public static void main(String[] args) {
		GetResponse_filter_formats getResponse_filter_formats = new GetResponse_filter_formats();
		getResponse_filter_formats.exec();
	}
	private void exec() {
		try {
			Response_filter_formatAPIService svc = new Response_filter_formatAPIService();

			List<Response_filter_format> items = svc.getAllResponse_filter_formats(MasheryFieldsHelper.RESPONSE_FILTER_FORMAT_ALL_FIELDS, null);
			for (Response_filter_format item : items) {
				System.out.println(item.getObject_type());
			}
		} catch (Exception ex) { ex.printStackTrace(); }
	}
}