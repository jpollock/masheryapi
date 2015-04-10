package com.mashery.pm.api.sdk.service;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import com.mashery.pm.api.sdk.domain.Plan_endpoint;
import com.mashery.pm.api.sdk.gson.Plan_endpointQueryJSONResultContainer;
import com.mashery.pm.api.sdk.gson.Plan_endpointJSONResult;

public class Plan_endpointAPIService extends MasheryAPIBase {
	public Plan_endpointAPIService() throws Exception {
		super();
	}
	private HashMap<String,HashMap<String,Plan_endpoint>> itemsMap;
	public void delete(int id)  throws Exception {
		String jsonString = "{\"method\":\"plan_endpoint.delete\",\"id\":1,\"params\":["+id+"]}";
		String resContainer = callMasheryAPI(jsonString);
		Plan_endpointJSONResult resContainerJSON =  (Plan_endpointJSONResult) gson.fromJson(resContainer,Plan_endpointJSONResult.class);
		if (resContainerJSON.getError() != null)
		throw new Exception();


	}
	public Plan_endpoint fetch(int id)  throws Exception {
		String jsonString = "{\"method\":\"plan_endpoint.fetch\",\"id\":1,\"params\":["+id+"]}";
		String resContainer = callMasheryAPI(jsonString);
		Plan_endpointJSONResult resContainerJSON =  (Plan_endpointJSONResult) gson.fromJson(resContainer,Plan_endpointJSONResult.class);
		if (resContainerJSON.getError() != null)
		throw new Exception();
		return resContainerJSON.getResult();

	}
	public Plan_endpoint update(Plan_endpoint plan_endpoint)  throws Exception {
		String jsonString = "{\"method\":\"plan_endpoint.update\",\"id\":1,\"params\":["+gson.toJson(plan_endpoint)+"]}";
		String resContainer = callMasheryAPI(jsonString);
		Plan_endpointJSONResult resContainerJSON =  (Plan_endpointJSONResult) gson.fromJson(resContainer,Plan_endpointJSONResult.class);
		if (resContainerJSON.getError() != null)
		throw new Exception();
		return resContainerJSON.getResult();

	}
	public void validate(int id)  throws Exception {
		String jsonString = "{\"method\":\"plan_endpoint.validate\",\"id\":1,\"params\":["+id+"]}";
		String resContainer = callMasheryAPI(jsonString);
		Plan_endpointJSONResult resContainerJSON =  (Plan_endpointJSONResult) gson.fromJson(resContainer,Plan_endpointJSONResult.class);
		if (resContainerJSON.getError() != null)
		throw new Exception();


	}
	public Plan_endpoint create(Plan_endpoint plan_endpoint)  throws Exception {
		String jsonString = "{\"method\":\"plan_endpoint.create\",\"id\":1,\"params\":["+gson.toJson(plan_endpoint)+"]}";
		String resContainer = callMasheryAPI(jsonString);
		Plan_endpointJSONResult resContainerJSON =  (Plan_endpointJSONResult) gson.fromJson(resContainer,Plan_endpointJSONResult.class);
		if (resContainerJSON.getError() != null)
		throw new Exception();
		return resContainerJSON.getResult();

	}
	public List<Plan_endpoint> getAllPlan_endpoints(String attributes, String whereClause) throws Exception {
		if (attributes == null || attributes.isEmpty()) {
			attributes = "*";
		}
		List<Plan_endpoint> items = new ArrayList<Plan_endpoint>();
		int total_pages = 1;
		for (int i = 1; i < total_pages + 1; i++) { 
			String resContainer = callMasheryAPI("object.query", "plan_endpoints", attributes , whereClause, i, limit);
			Plan_endpointQueryJSONResultContainer resContainerJSON =  (Plan_endpointQueryJSONResultContainer) gson.fromJson(resContainer,Plan_endpointQueryJSONResultContainer.class);
			items.addAll(resContainerJSON.getResult().getItems());
			total_pages = resContainerJSON.getResult().getTotal_pages();
		}
		return items;
	}

}