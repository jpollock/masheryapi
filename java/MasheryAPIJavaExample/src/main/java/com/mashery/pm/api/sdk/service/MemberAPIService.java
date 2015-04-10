package com.mashery.pm.api.sdk.service;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import com.mashery.pm.api.sdk.domain.Member;
import com.mashery.pm.api.sdk.domain.Role;
import com.mashery.pm.api.sdk.gson.MemberQueryJSONResultContainer;
import com.mashery.pm.api.sdk.gson.MemberJSONResult;

public class MemberAPIService extends MasheryAPIBase {
	public MemberAPIService() throws Exception {
		super();
	}
	private HashMap<String,HashMap<String,Member>> itemsMap;
	public Member update(Member member)  throws Exception {
		String jsonString = "{\"method\":\"member.update\",\"id\":1,\"params\":["+gson.toJson(member)+"]}";
		String resContainer = callMasheryAPI(jsonString);
		MemberJSONResult resContainerJSON =  (MemberJSONResult) gson.fromJson(resContainer,MemberJSONResult.class);
		if (resContainerJSON.getError() != null)
		throw new Exception();
		return resContainerJSON.getResult();

	}
	public void addRole(Member member, Role role)  throws Exception {
		String jsonString = "{\"method\":\"member.addRole\",\"id\":1,\"params\":["+gson.toJson(member)+", "+gson.toJson(role)+"]}";
		String resContainer = callMasheryAPI(jsonString);
		MemberJSONResult resContainerJSON =  (MemberJSONResult) gson.fromJson(resContainer,MemberJSONResult.class);
		if (resContainerJSON.getError() != null)
		throw new Exception();


	}
	public void removeRole(Member member, Role role)  throws Exception {
		String jsonString = "{\"method\":\"member.removeRole\",\"id\":1,\"params\":["+gson.toJson(member)+", "+gson.toJson(role)+"]}";
		String resContainer = callMasheryAPI(jsonString);
		MemberJSONResult resContainerJSON =  (MemberJSONResult) gson.fromJson(resContainer,MemberJSONResult.class);
		if (resContainerJSON.getError() != null)
		throw new Exception();


	}
	public void delete(int id)  throws Exception {
		String jsonString = "{\"method\":\"member.delete\",\"id\":1,\"params\":["+id+"]}";
		String resContainer = callMasheryAPI(jsonString);
		MemberJSONResult resContainerJSON =  (MemberJSONResult) gson.fromJson(resContainer,MemberJSONResult.class);
		if (resContainerJSON.getError() != null)
		throw new Exception();


	}
	public Member fetch(int id)  throws Exception {
		String jsonString = "{\"method\":\"member.fetch\",\"id\":1,\"params\":["+id+"]}";
		String resContainer = callMasheryAPI(jsonString);
		MemberJSONResult resContainerJSON =  (MemberJSONResult) gson.fromJson(resContainer,MemberJSONResult.class);
		if (resContainerJSON.getError() != null)
		throw new Exception();
		return resContainerJSON.getResult();

	}
	public Member create(Member member)  throws Exception {
		String jsonString = "{\"method\":\"member.create\",\"id\":1,\"params\":["+gson.toJson(member)+"]}";
		String resContainer = callMasheryAPI(jsonString);
		MemberJSONResult resContainerJSON =  (MemberJSONResult) gson.fromJson(resContainer,MemberJSONResult.class);
		if (resContainerJSON.getError() != null)
		throw new Exception();
		return resContainerJSON.getResult();

	}
	public void validate(int id)  throws Exception {
		String jsonString = "{\"method\":\"member.validate\",\"id\":1,\"params\":["+id+"]}";
		String resContainer = callMasheryAPI(jsonString);
		MemberJSONResult resContainerJSON =  (MemberJSONResult) gson.fromJson(resContainer,MemberJSONResult.class);
		if (resContainerJSON.getError() != null)
		throw new Exception();


	}
	public List<Member> getAllMembers(String attributes, String whereClause) throws Exception {
		if (attributes == null || attributes.isEmpty()) {
			attributes = "*";
		}
		List<Member> items = new ArrayList<Member>();
		int total_pages = 1;
		for (int i = 1; i < total_pages + 1; i++) { 
			String resContainer = callMasheryAPI("object.query", "members", attributes , whereClause, i, limit);
			MemberQueryJSONResultContainer resContainerJSON =  (MemberQueryJSONResultContainer) gson.fromJson(resContainer,MemberQueryJSONResultContainer.class);
			items.addAll(resContainerJSON.getResult().getItems());
			total_pages = resContainerJSON.getResult().getTotal_pages();
		}
		return items;
	}

}