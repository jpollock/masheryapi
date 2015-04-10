package com.mashery.pm.api.sdk.examples;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Properties;
import java.io.IOException;
import com.mashery.pm.api.sdk.domain.MasheryFieldsHelper;
import com.mashery.pm.api.sdk.domain.Package;
import com.mashery.pm.api.sdk.service.PackageAPIService;

public class GetPackages {


public static void main(String[] args) {
		GetPackages getPackages = new GetPackages();
		getPackages.exec();
	}
	private void exec() {

		try {
			PackageAPIService svc = new PackageAPIService();
			List<Package> items = svc.getAllPackages(MasheryFieldsHelper.PACKAGE_ALL_FIELDS, null);
			for (Package item : items) {
				System.out.println(item.getObject_type());
			}
		} catch (Exception ex) { ex.printStackTrace(); }
	}
}