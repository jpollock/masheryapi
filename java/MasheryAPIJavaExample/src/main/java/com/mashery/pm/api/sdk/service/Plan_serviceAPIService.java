package com.mashery.pm.api.sdk.service;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import com.mashery.pm.api.sdk.domain.Plan_service;
import com.mashery.pm.api.sdk.gson.Plan_serviceQueryJSONResultContainer;
import com.mashery.pm.api.sdk.gson.Plan_serviceJSONResult;

public class Plan_serviceAPIService extends MasheryAPIBase {
	public Plan_serviceAPIService() throws Exception {
		super();
	}
	private HashMap<String,HashMap<String,Plan_service>> itemsMap;
	public Plan_service create(Plan_service plan_service)  throws Exception {
		
		String jsonString = "{\"method\":\"plan_service.create\",\"id\":1,\"params\":["+gson.toJson(plan_service)+"]}";
		String resContainer = callMasheryAPI(jsonString);
		Plan_serviceJSONResult resContainerJSON =  (Plan_serviceJSONResult) gson.fromJson(resContainer,Plan_serviceJSONResult.class);
		if (resContainerJSON.getError() != null)
		throw new Exception();
		return resContainerJSON.getResult();

	}
	public void delete(int id)  throws Exception {
		String jsonString = "{\"method\":\"plan_service.delete\",\"id\":1,\"params\":["+id+"]}";
		String resContainer = callMasheryAPI(jsonString);
		Plan_serviceJSONResult resContainerJSON =  (Plan_serviceJSONResult) gson.fromJson(resContainer,Plan_serviceJSONResult.class);
		if (resContainerJSON.getError() != null)
		throw new Exception();


	}
	public Plan_service fetch(int id)  throws Exception {
		String jsonString = "{\"method\":\"plan_service.fetch\",\"id\":1,\"params\":["+id+"]}";
		String resContainer = callMasheryAPI(jsonString);
		Plan_serviceJSONResult resContainerJSON =  (Plan_serviceJSONResult) gson.fromJson(resContainer,Plan_serviceJSONResult.class);
		if (resContainerJSON.getError() != null)
		throw new Exception();
		return resContainerJSON.getResult();

	}
	public void validate(int id)  throws Exception {
		String jsonString = "{\"method\":\"plan_service.validate\",\"id\":1,\"params\":["+id+"]}";
		String resContainer = callMasheryAPI(jsonString);
		Plan_serviceJSONResult resContainerJSON =  (Plan_serviceJSONResult) gson.fromJson(resContainer,Plan_serviceJSONResult.class);
		if (resContainerJSON.getError() != null)
		throw new Exception();


	}
	public List<Plan_service> getAllPlan_services(String attributes, String whereClause) throws Exception {
		if (attributes == null || attributes.isEmpty()) {
			attributes = "*";
		}
		List<Plan_service> items = new ArrayList<Plan_service>();
		int total_pages = 1;
		for (int i = 1; i < total_pages + 1; i++) { 
			String resContainer = callMasheryAPI("object.query", "plan_services", attributes , whereClause, i, limit);
			Plan_serviceQueryJSONResultContainer resContainerJSON =  (Plan_serviceQueryJSONResultContainer) gson.fromJson(resContainer,Plan_serviceQueryJSONResultContainer.class);
			items.addAll(resContainerJSON.getResult().getItems());
			total_pages = resContainerJSON.getResult().getTotal_pages();
		}
		return items;
	}

}