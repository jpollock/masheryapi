package com.mashery.pm.api.sdk.service;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import com.mashery.pm.api.sdk.domain.Plan;
import com.mashery.pm.api.sdk.gson.PlanQueryJSONResultContainer;
import com.mashery.pm.api.sdk.gson.PlanJSONResult;

public class PlanAPIService extends MasheryAPIBase {
	public PlanAPIService() throws Exception {
		super();
	}
	private HashMap<String,HashMap<String,Plan>> itemsMap;
	public void delete(int id)  throws Exception {
		String jsonString = "{\"method\":\"plan.delete\",\"id\":1,\"params\":["+id+"]}";
		String resContainer = callMasheryAPI(jsonString);
		PlanJSONResult resContainerJSON =  (PlanJSONResult) gson.fromJson(resContainer,PlanJSONResult.class);
		if (resContainerJSON.getError() != null)
		throw new Exception();


	}
	public Plan fetch(int id)  throws Exception {
		String jsonString = "{\"method\":\"plan.fetch\",\"id\":1,\"params\":["+id+"]}";
		String resContainer = callMasheryAPI(jsonString);
		PlanJSONResult resContainerJSON =  (PlanJSONResult) gson.fromJson(resContainer,PlanJSONResult.class);
		if (resContainerJSON.getError() != null)
		throw new Exception();
		return resContainerJSON.getResult();

	}
	public void validate(int id)  throws Exception {
		String jsonString = "{\"method\":\"plan.validate\",\"id\":1,\"params\":["+id+"]}";
		String resContainer = callMasheryAPI(jsonString);
		PlanJSONResult resContainerJSON =  (PlanJSONResult) gson.fromJson(resContainer,PlanJSONResult.class);
		if (resContainerJSON.getError() != null)
		throw new Exception();


	}
	public Plan update(Plan plan)  throws Exception {
		String jsonString = "{\"method\":\"plan.update\",\"id\":1,\"params\":["+gson.toJson(plan)+"]}";
		String resContainer = callMasheryAPI(jsonString);
		PlanJSONResult resContainerJSON =  (PlanJSONResult) gson.fromJson(resContainer,PlanJSONResult.class);
		if (resContainerJSON.getError() != null)
		throw new Exception();
		return resContainerJSON.getResult();

	}
	public void clone(int id)  throws Exception {
		String jsonString = "{\"method\":\"plan.clone\",\"id\":1,\"params\":["+id+"]}";
		String resContainer = callMasheryAPI(jsonString);
		PlanJSONResult resContainerJSON =  (PlanJSONResult) gson.fromJson(resContainer,PlanJSONResult.class);
		if (resContainerJSON.getError() != null)
		throw new Exception();


	}
	public Plan create(Plan plan)  throws Exception {
		String jsonString = "{\"method\":\"plan.create\",\"id\":1,\"params\":["+gson.toJson(plan).replace("parentPackage", "package")+"]}";
		String resContainer = callMasheryAPI(jsonString);
		PlanJSONResult resContainerJSON =  (PlanJSONResult) gson.fromJson(resContainer,PlanJSONResult.class);
		if (resContainerJSON.getError() != null)
		throw new Exception();
		return resContainerJSON.getResult();

	}
	public List<Plan> getAllPlans(String attributes, String whereClause) throws Exception {
		if (attributes == null || attributes.isEmpty()) {
			attributes = "*";
		}
		List<Plan> items = new ArrayList<Plan>();
		int total_pages = 1;
		for (int i = 1; i < total_pages + 1; i++) { 
			String resContainer = callMasheryAPI("object.query", "plans", attributes , whereClause, i, limit);
			PlanQueryJSONResultContainer resContainerJSON =  (PlanQueryJSONResultContainer) gson.fromJson(resContainer,PlanQueryJSONResultContainer.class);
			items.addAll(resContainerJSON.getResult().getItems());
			total_pages = resContainerJSON.getResult().getTotal_pages();
		}
		return items;
	}

}