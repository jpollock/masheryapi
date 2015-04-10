package com.mashery.pm.api.sdk.examples;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Properties;
import java.io.IOException;
import com.mashery.pm.api.sdk.domain.MasheryFieldsHelper;
import com.mashery.pm.api.sdk.domain.Method_override;
import com.mashery.pm.api.sdk.service.Method_overrideAPIService;

public class GetMethod_overrides {


public static void main(String[] args) {
		GetMethod_overrides getMethod_overrides = new GetMethod_overrides();
		getMethod_overrides.exec();
	}
	private void exec() {
		try {
			Method_overrideAPIService svc = new Method_overrideAPIService();
			List<Method_override> items = svc.getAllMethod_overrides(MasheryFieldsHelper.METHOD_OVERRIDE_ALL_FIELDS, null);
			for (Method_override item : items) {
				System.out.println(item.getObject_type());
			}
		} catch (Exception ex) { ex.printStackTrace(); }
	}
}