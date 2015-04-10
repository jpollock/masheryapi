package com.mashery.pm.api.sdk.service;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import com.mashery.pm.api.sdk.domain.Access_rule;
import com.mashery.pm.api.sdk.gson.Access_ruleQueryJSONResultContainer;
import com.mashery.pm.api.sdk.gson.Access_ruleJSONResult;

public class Access_ruleAPIService extends MasheryAPIBase {
	public Access_ruleAPIService() throws Exception {
		super();
	}
	private HashMap<String,HashMap<String,Access_rule>> itemsMap;
	public Access_rule create(Access_rule Access_rule)  throws Exception {
		String jsonString = "{\"method\":\"access_rule.create\",\"id\":1,\"params\":["+gson.toJson(Access_rule)+"]}";
		String resContainer = callMasheryAPI(jsonString);
		Access_ruleJSONResult resContainerJSON =  (Access_ruleJSONResult) gson.fromJson(resContainer,Access_ruleJSONResult.class);
		if (resContainerJSON.getError() != null)
		throw new Exception();
		return resContainerJSON.getResult();

	}
	public Access_rule fetch(int id)  throws Exception {
		String jsonString = "{\"method\":\"access_rule.fetch\",\"id\":1,\"params\":["+id+"]}";
		String resContainer = callMasheryAPI(jsonString);
		Access_ruleJSONResult resContainerJSON =  (Access_ruleJSONResult) gson.fromJson(resContainer,Access_ruleJSONResult.class);
		if (resContainerJSON.getError() != null)
		throw new Exception();
		return resContainerJSON.getResult();

	}
	public Access_rule update(Access_rule Access_rule)  throws Exception {
		String jsonString = "{\"method\":\"access_rule.update\",\"id\":1,\"params\":["+gson.toJson(Access_rule)+"]}";
		String resContainer = callMasheryAPI(jsonString);
		Access_ruleJSONResult resContainerJSON =  (Access_ruleJSONResult) gson.fromJson(resContainer,Access_ruleJSONResult.class);
		if (resContainerJSON.getError() != null)
		throw new Exception();
		return resContainerJSON.getResult();

	}
	public void delete(int id)  throws Exception {
		String jsonString = "{\"method\":\"access_rule.delete\",\"id\":1,\"params\":["+id+"]}";
		String resContainer = callMasheryAPI(jsonString);
		Access_ruleJSONResult resContainerJSON =  (Access_ruleJSONResult) gson.fromJson(resContainer,Access_ruleJSONResult.class);
		if (resContainerJSON.getError() != null)
		throw new Exception();


	}
	public void validate(int id)  throws Exception {
		String jsonString = "{\"method\":\"access_rule.validate\",\"id\":1,\"params\":["+id+"]}";
		String resContainer = callMasheryAPI(jsonString);
		Access_ruleJSONResult resContainerJSON =  (Access_ruleJSONResult) gson.fromJson(resContainer,Access_ruleJSONResult.class);
		if (resContainerJSON.getError() != null)
		throw new Exception();


	}
	public List<Access_rule> getAllAccess_rules(String attributes, String whereClause) throws Exception {
		if (attributes == null || attributes.isEmpty()) {
			attributes = "*";
		}
		List<Access_rule> items = new ArrayList<Access_rule>();
		int total_pages = 1;
		for (int i = 1; i < total_pages + 1; i++) { 
			String resContainer = callMasheryAPI("object.query", "access_rules", attributes , whereClause, i, limit);
			Access_ruleQueryJSONResultContainer resContainerJSON =  (Access_ruleQueryJSONResultContainer) gson.fromJson(resContainer,Access_ruleQueryJSONResultContainer.class);
			items.addAll(resContainerJSON.getResult().getItems());
			total_pages = resContainerJSON.getResult().getTotal_pages();
		}
		return items;
	}

}