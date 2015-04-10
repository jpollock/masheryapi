package com.mashery.pm.api.sdk.service;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import com.mashery.pm.api.sdk.domain.Plan_method;
import com.mashery.pm.api.sdk.gson.Plan_methodQueryJSONResultContainer;
import com.mashery.pm.api.sdk.gson.Plan_methodJSONResult;

public class Plan_methodAPIService extends MasheryAPIBase {
	public Plan_methodAPIService() throws Exception {
		super();
	}
	private HashMap<String,HashMap<String,Plan_method>> itemsMap;
	public Plan_method update(Plan_method plan_method)  throws Exception {
		String jsonString = "{\"method\":\"plan_method.update\",\"id\":1,\"params\":["+gson.toJson(plan_method)+"]}";
		String resContainer = callMasheryAPI(jsonString);
		Plan_methodJSONResult resContainerJSON =  (Plan_methodJSONResult) gson.fromJson(resContainer,Plan_methodJSONResult.class);
		if (resContainerJSON.getError() != null)
		throw new Exception();
		return resContainerJSON.getResult();

	}
	public Plan_method create(Plan_method plan_method)  throws Exception {
		String jsonString = "{\"method\":\"plan_method.create\",\"id\":1,\"params\":["+gson.toJson(plan_method)+"]}";
		String resContainer = callMasheryAPI(jsonString);
		Plan_methodJSONResult resContainerJSON =  (Plan_methodJSONResult) gson.fromJson(resContainer,Plan_methodJSONResult.class);
		if (resContainerJSON.getError() != null)
		throw new Exception();
		return resContainerJSON.getResult();

	}
	public void validate(int id)  throws Exception {
		String jsonString = "{\"method\":\"plan_method.validate\",\"id\":1,\"params\":["+id+"]}";
		String resContainer = callMasheryAPI(jsonString);
		Plan_methodJSONResult resContainerJSON =  (Plan_methodJSONResult) gson.fromJson(resContainer,Plan_methodJSONResult.class);
		if (resContainerJSON.getError() != null)
		throw new Exception();


	}
	public void delete(int id)  throws Exception {
		String jsonString = "{\"method\":\"plan_method.delete\",\"id\":1,\"params\":["+id+"]}";
		String resContainer = callMasheryAPI(jsonString);
		Plan_methodJSONResult resContainerJSON =  (Plan_methodJSONResult) gson.fromJson(resContainer,Plan_methodJSONResult.class);
		if (resContainerJSON.getError() != null)
		throw new Exception();


	}
	public Plan_method fetch(int id)  throws Exception {
		String jsonString = "{\"method\":\"plan_method.fetch\",\"id\":1,\"params\":["+id+"]}";
		String resContainer = callMasheryAPI(jsonString);
		Plan_methodJSONResult resContainerJSON =  (Plan_methodJSONResult) gson.fromJson(resContainer,Plan_methodJSONResult.class);
		if (resContainerJSON.getError() != null)
		throw new Exception();
		return resContainerJSON.getResult();

	}
	public List<Plan_method> getAllPlan_methods(String attributes, String whereClause) throws Exception {
		if (attributes == null || attributes.isEmpty()) {
			attributes = "*";
		}
		List<Plan_method> items = new ArrayList<Plan_method>();
		int total_pages = 1;
		for (int i = 1; i < total_pages + 1; i++) { 
			String resContainer = callMasheryAPI("object.query", "plan_methods", attributes , whereClause, i, limit);
			Plan_methodQueryJSONResultContainer resContainerJSON =  (Plan_methodQueryJSONResultContainer) gson.fromJson(resContainer,Plan_methodQueryJSONResultContainer.class);
			items.addAll(resContainerJSON.getResult().getItems());
			total_pages = resContainerJSON.getResult().getTotal_pages();
		}
		return items;
	}

}