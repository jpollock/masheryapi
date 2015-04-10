package com.mashery.pm.api.sdk.examples;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Properties;
import java.io.IOException;
import com.mashery.pm.api.sdk.domain.MasheryFieldsHelper;
import com.mashery.pm.api.sdk.domain.Developer_class;
import com.mashery.pm.api.sdk.service.Developer_classAPIService;

public class GetDeveloper_class {


public static void main(String[] args) {
		GetDeveloper_class getDeveloper_class = new GetDeveloper_class();
		getDeveloper_class.exec();
	}
	private void exec() {

		try {
			Developer_classAPIService svc = new Developer_classAPIService();
			List<Developer_class> items = svc.getAllDeveloper_class(MasheryFieldsHelper.DEVELOPER_CLASS_ALL_FIELDS, null);
			for (Developer_class item : items) {
				System.out.println(item.getObject_type());
			}
		} catch (Exception ex) { ex.printStackTrace(); }
	}
}