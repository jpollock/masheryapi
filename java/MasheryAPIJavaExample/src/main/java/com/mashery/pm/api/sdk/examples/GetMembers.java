package com.mashery.pm.api.sdk.examples;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Properties;
import java.io.IOException;
import com.mashery.pm.api.sdk.domain.MasheryFieldsHelper;
import com.mashery.pm.api.sdk.domain.Member;
import com.mashery.pm.api.sdk.service.MemberAPIService;

public class GetMembers {


public static void main(String[] args) {
		GetMembers getMembers = new GetMembers();
		getMembers.exec();
	}
	private void exec() {

		try {
			MemberAPIService svc = new MemberAPIService();
			List<Member> items = svc.getAllMembers(MasheryFieldsHelper.MEMBER_ALL_FIELDS, null);
			for (Member item : items) {
				System.out.println(item.getObject_type());
			}
		} catch (Exception ex) { ex.printStackTrace(); }
	}
}