package com.mashery.pm.api.sdk.service;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import com.mashery.pm.api.sdk.domain.Service_definition_method;
import com.mashery.pm.api.sdk.gson.Service_definition_methodQueryJSONResultContainer;
import com.mashery.pm.api.sdk.gson.Service_definition_methodJSONResult;

public class Service_definition_methodAPIService extends MasheryAPIBase {
	public Service_definition_methodAPIService() throws Exception {
		super();
	}
	private HashMap<String,HashMap<String,Service_definition_method>> itemsMap;
	public Service_definition_method update(Service_definition_method service_definition_method)  throws Exception {
		String jsonString = "{\"method\":\"service_definition_method.update\",\"id\":1,\"params\":["+gson.toJson(service_definition_method)+"]}";
		String resContainer = callMasheryAPI(jsonString);
		Service_definition_methodJSONResult resContainerJSON =  (Service_definition_methodJSONResult) gson.fromJson(resContainer,Service_definition_methodJSONResult.class);
		if (resContainerJSON.getError() != null)
		throw new Exception();
		return resContainerJSON.getResult();

	}
	public Service_definition_method create(Service_definition_method service_definition_method)  throws Exception {
		String jsonString = "{\"method\":\"service_definition_method.create\",\"id\":1,\"params\":["+gson.toJson(service_definition_method)+"]}";
		String resContainer = callMasheryAPI(jsonString);
		Service_definition_methodJSONResult resContainerJSON =  (Service_definition_methodJSONResult) gson.fromJson(resContainer,Service_definition_methodJSONResult.class);
		if (resContainerJSON.getError() != null)
		throw new Exception();
		return resContainerJSON.getResult();

	}
	public void delete(int id)  throws Exception {
		String jsonString = "{\"method\":\"service_definition_method.delete\",\"id\":1,\"params\":["+id+"]}";
		String resContainer = callMasheryAPI(jsonString);
		Service_definition_methodJSONResult resContainerJSON =  (Service_definition_methodJSONResult) gson.fromJson(resContainer,Service_definition_methodJSONResult.class);
		if (resContainerJSON.getError() != null)
		throw new Exception();


	}
	public Service_definition_method fetch(int id)  throws Exception {
		String jsonString = "{\"method\":\"service_definition_method.fetch\",\"id\":1,\"params\":["+id+"]}";
		String resContainer = callMasheryAPI(jsonString);
		Service_definition_methodJSONResult resContainerJSON =  (Service_definition_methodJSONResult) gson.fromJson(resContainer,Service_definition_methodJSONResult.class);
		if (resContainerJSON.getError() != null)
		throw new Exception();
		return resContainerJSON.getResult();

	}
	public void validate(int id)  throws Exception {
		String jsonString = "{\"method\":\"service_definition_method.validate\",\"id\":1,\"params\":["+id+"]}";
		String resContainer = callMasheryAPI(jsonString);
		Service_definition_methodJSONResult resContainerJSON =  (Service_definition_methodJSONResult) gson.fromJson(resContainer,Service_definition_methodJSONResult.class);
		if (resContainerJSON.getError() != null)
		throw new Exception();


	}
	public List<Service_definition_method> getAllService_definition_methods(String attributes, String whereClause) throws Exception {
		if (attributes == null || attributes.isEmpty()) {
			attributes = "*";
		}
		List<Service_definition_method> items = new ArrayList<Service_definition_method>();
		int total_pages = 1;
		for (int i = 1; i < total_pages + 1; i++) { 
			String resContainer = callMasheryAPI("object.query", "service_definition_methods", attributes , whereClause, i, limit);
			Service_definition_methodQueryJSONResultContainer resContainerJSON =  (Service_definition_methodQueryJSONResultContainer) gson.fromJson(resContainer,Service_definition_methodQueryJSONResultContainer.class);
			items.addAll(resContainerJSON.getResult().getItems());
			total_pages = resContainerJSON.getResult().getTotal_pages();
		}
		return items;
	}

}