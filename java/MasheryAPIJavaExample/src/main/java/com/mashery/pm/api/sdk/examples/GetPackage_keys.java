package com.mashery.pm.api.sdk.examples;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Properties;
import java.io.IOException;
import com.mashery.pm.api.sdk.domain.MasheryFieldsHelper;
import com.mashery.pm.api.sdk.domain.Package_key;
import com.mashery.pm.api.sdk.service.Package_keyAPIService;

public class GetPackage_keys {


public static void main(String[] args) {
		GetPackage_keys getPackage_keys = new GetPackage_keys();
		getPackage_keys.exec();
	}
	private void exec() {

		try {
			Package_keyAPIService svc = new Package_keyAPIService();
			List<Package_key> items = svc.getAllPackage_keys(MasheryFieldsHelper.PACKAGE_KEY_ALL_FIELDS, null);
			for (Package_key item : items) {
				System.out.println(item.getObject_type());
			}
		} catch (Exception ex) { ex.printStackTrace(); }
	}
}