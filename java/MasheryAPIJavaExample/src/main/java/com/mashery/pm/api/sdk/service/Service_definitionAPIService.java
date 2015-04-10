package com.mashery.pm.api.sdk.service;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import com.mashery.pm.api.sdk.domain.Service_definition;
import com.mashery.pm.api.sdk.gson.Service_definitionQueryJSONResultContainer;
import com.mashery.pm.api.sdk.gson.Service_definitionJSONResult;

public class Service_definitionAPIService extends MasheryAPIBase {
	public Service_definitionAPIService() throws Exception {
		super();
	}
	private HashMap<String,HashMap<String,Service_definition>> itemsMap;
	public Service_definition create(Service_definition service_definition)  throws Exception {
		String jsonString = "{\"method\":\"service_definition.create\",\"id\":1,\"params\":["+gson.toJson(service_definition)+"]}";
		String resContainer = callMasheryAPI(jsonString);
		Service_definitionJSONResult resContainerJSON =  (Service_definitionJSONResult) gson.fromJson(resContainer,Service_definitionJSONResult.class);
		if (resContainerJSON.getError() != null)
		throw new Exception();
		return resContainerJSON.getResult();

	}	
	public Service_definition fetch(int id)  throws Exception {
		String jsonString = "{\"method\":\"service_definition.fetch\",\"id\":1,\"params\":["+id+"]}";
		String resContainer = callMasheryAPI(jsonString);
		Service_definitionJSONResult resContainerJSON =  (Service_definitionJSONResult) gson.fromJson(resContainer,Service_definitionJSONResult.class);
		if (resContainerJSON.getError() != null)
		throw new Exception();
		return resContainerJSON.getResult();

	}
	public List<Service_definition> getAllService_definitions(String attributes, String whereClause) throws Exception {
		if (attributes == null || attributes.isEmpty()) {
			attributes = "*";
		}
		List<Service_definition> items = new ArrayList<Service_definition>();
		int total_pages = 1;
		for (int i = 1; i < total_pages + 1; i++) { 
			String resContainer = callMasheryAPI("object.query", "service_definitions", attributes , whereClause, i, limit);
			Service_definitionQueryJSONResultContainer resContainerJSON =  (Service_definitionQueryJSONResultContainer) gson.fromJson(resContainer,Service_definitionQueryJSONResultContainer.class);
			items.addAll(resContainerJSON.getResult().getItems());
			total_pages = resContainerJSON.getResult().getTotal_pages();
		}
		return items;
	}

}