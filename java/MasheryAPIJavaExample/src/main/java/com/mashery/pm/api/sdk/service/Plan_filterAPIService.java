package com.mashery.pm.api.sdk.service;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import com.mashery.pm.api.sdk.domain.Plan_filter;
import com.mashery.pm.api.sdk.gson.Plan_filterQueryJSONResultContainer;
import com.mashery.pm.api.sdk.gson.Plan_filterJSONResult;

public class Plan_filterAPIService extends MasheryAPIBase {
	public Plan_filterAPIService() throws Exception {
		super();
	}
	private HashMap<String,HashMap<String,Plan_filter>> itemsMap;
	public Plan_filter fetch(int id)  throws Exception {
		String jsonString = "{\"method\":\"plan_filter.fetch\",\"id\":1,\"params\":["+id+"]}";
		String resContainer = callMasheryAPI(jsonString);
		Plan_filterJSONResult resContainerJSON =  (Plan_filterJSONResult) gson.fromJson(resContainer,Plan_filterJSONResult.class);
		if (resContainerJSON.getError() != null)
		throw new Exception();
		return resContainerJSON.getResult();

	}
	public Plan_filter create(Plan_filter plan_filter)  throws Exception {
		String jsonString = "{\"method\":\"plan_filter.create\",\"id\":1,\"params\":["+gson.toJson(plan_filter)+"]}";
		String resContainer = callMasheryAPI(jsonString);
		Plan_filterJSONResult resContainerJSON =  (Plan_filterJSONResult) gson.fromJson(resContainer,Plan_filterJSONResult.class);
		if (resContainerJSON.getError() != null)
		throw new Exception();
		return resContainerJSON.getResult();

	}
	public void delete(int id)  throws Exception {
		String jsonString = "{\"method\":\"plan_filter.delete\",\"id\":1,\"params\":["+id+"]}";
		String resContainer = callMasheryAPI(jsonString);
		Plan_filterJSONResult resContainerJSON =  (Plan_filterJSONResult) gson.fromJson(resContainer,Plan_filterJSONResult.class);
		if (resContainerJSON.getError() != null)
		throw new Exception();


	}
	public void validate(int id)  throws Exception {
		String jsonString = "{\"method\":\"plan_filter.validate\",\"id\":1,\"params\":["+id+"]}";
		String resContainer = callMasheryAPI(jsonString);
		Plan_filterJSONResult resContainerJSON =  (Plan_filterJSONResult) gson.fromJson(resContainer,Plan_filterJSONResult.class);
		if (resContainerJSON.getError() != null)
		throw new Exception();


	}
	public List<Plan_filter> getAllPlan_filters(String attributes, String whereClause) throws Exception {
		if (attributes == null || attributes.isEmpty()) {
			attributes = "*";
		}
		List<Plan_filter> items = new ArrayList<Plan_filter>();
		int total_pages = 1;
		for (int i = 1; i < total_pages + 1; i++) { 
			String resContainer = callMasheryAPI("object.query", "plan_filters", attributes , whereClause, i, limit);
			Plan_filterQueryJSONResultContainer resContainerJSON =  (Plan_filterQueryJSONResultContainer) gson.fromJson(resContainer,Plan_filterQueryJSONResultContainer.class);
			items.addAll(resContainerJSON.getResult().getItems());
			total_pages = resContainerJSON.getResult().getTotal_pages();
		}
		return items;
	}

}