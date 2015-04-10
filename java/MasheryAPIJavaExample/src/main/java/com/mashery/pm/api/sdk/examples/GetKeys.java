package com.mashery.pm.api.sdk.examples;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Properties;
import java.io.IOException;
import com.mashery.pm.api.sdk.domain.MasheryFieldsHelper;
import com.mashery.pm.api.sdk.domain.Key;
import com.mashery.pm.api.sdk.service.KeyAPIService;

public class GetKeys {


public static void main(String[] args) {
		GetKeys getKeys = new GetKeys();
		getKeys.exec();
	}
	private void exec() {
		try {
			KeyAPIService svc = new KeyAPIService();

			List<Key> items = svc.getAllKeys(MasheryFieldsHelper.KEY_ALL_FIELDS, null);
			for (Key item : items) {
				System.out.println(item.getApikey());
			}
		} catch (Exception ex) { ex.printStackTrace(); }
	}
}