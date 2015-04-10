package com.mashery.pm.api.sdk.service;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import com.mashery.pm.api.sdk.domain.Role;
import com.mashery.pm.api.sdk.gson.RoleQueryJSONResultContainer;
import com.mashery.pm.api.sdk.gson.RoleJSONResult;

public class RoleAPIService extends MasheryAPIBase {
	public RoleAPIService() throws Exception {
		super();
	}
	private HashMap<String,HashMap<String,Role>> itemsMap;
	public void delete(int id)  throws Exception {
		String jsonString = "{\"method\":\"role.delete\",\"id\":1,\"params\":["+id+"]}";
		String resContainer = callMasheryAPI(jsonString);
		RoleJSONResult resContainerJSON =  (RoleJSONResult) gson.fromJson(resContainer,RoleJSONResult.class);
		if (resContainerJSON.getError() != null)
		throw new Exception();


	}
	public Role update(Role role)  throws Exception {
		String jsonString = "{\"method\":\"role.update\",\"id\":1,\"params\":["+gson.toJson(role)+"]}";
		String resContainer = callMasheryAPI(jsonString);
		RoleJSONResult resContainerJSON =  (RoleJSONResult) gson.fromJson(resContainer,RoleJSONResult.class);
		if (resContainerJSON.getError() != null)
		throw new Exception();
		return resContainerJSON.getResult();

	}
	public void validate(int id)  throws Exception {
		String jsonString = "{\"method\":\"role.validate\",\"id\":1,\"params\":["+id+"]}";
		String resContainer = callMasheryAPI(jsonString);
		RoleJSONResult resContainerJSON =  (RoleJSONResult) gson.fromJson(resContainer,RoleJSONResult.class);
		if (resContainerJSON.getError() != null)
		throw new Exception();


	}
	public Role create(Role role)  throws Exception {
		String jsonString = "{\"method\":\"role.create\",\"id\":1,\"params\":["+gson.toJson(role)+"]}";
		String resContainer = callMasheryAPI(jsonString);
		RoleJSONResult resContainerJSON =  (RoleJSONResult) gson.fromJson(resContainer,RoleJSONResult.class);
		if (resContainerJSON.getError() != null)
		throw new Exception();
		return resContainerJSON.getResult();

	}
	public Role fetch(int id)  throws Exception {
		String jsonString = "{\"method\":\"role.fetch\",\"id\":1,\"params\":["+id+"]}";
		String resContainer = callMasheryAPI(jsonString);
		RoleJSONResult resContainerJSON =  (RoleJSONResult) gson.fromJson(resContainer,RoleJSONResult.class);
		if (resContainerJSON.getError() != null)
		throw new Exception();
		return resContainerJSON.getResult();

	}
	public List<Role> getAllRoles(String attributes, String whereClause) throws Exception {
		if (attributes == null || attributes.isEmpty()) {
			attributes = "*";
		}
		List<Role> items = new ArrayList<Role>();
		int total_pages = 1;
		for (int i = 1; i < total_pages + 1; i++) { 
			String resContainer = callMasheryAPI("object.query", "roles", attributes , whereClause, i, limit);
			RoleQueryJSONResultContainer resContainerJSON =  (RoleQueryJSONResultContainer) gson.fromJson(resContainer,RoleQueryJSONResultContainer.class);
			items.addAll(resContainerJSON.getResult().getItems());
			total_pages = resContainerJSON.getResult().getTotal_pages();
		}
		return items;
	}

}