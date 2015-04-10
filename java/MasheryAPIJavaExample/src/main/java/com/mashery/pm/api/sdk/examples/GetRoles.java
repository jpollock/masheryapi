package com.mashery.pm.api.sdk.examples;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Properties;
import java.io.IOException;
import com.mashery.pm.api.sdk.domain.MasheryFieldsHelper;
import com.mashery.pm.api.sdk.domain.Role;
import com.mashery.pm.api.sdk.service.RoleAPIService;

public class GetRoles {


public static void main(String[] args) {
		GetRoles getRoles = new GetRoles();
		getRoles.exec();
	}
	private void exec() {

		try {
			RoleAPIService svc = new RoleAPIService();
			List<Role> items = svc.getAllRoles(MasheryFieldsHelper.ROLE_ALL_FIELDS, null);
			for (Role item : items) {
				System.out.println(item.getObject_type());
			}
		} catch (Exception ex) { ex.printStackTrace(); }
	}
}