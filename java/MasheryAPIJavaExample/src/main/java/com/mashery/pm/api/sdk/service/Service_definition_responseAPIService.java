package com.mashery.pm.api.sdk.service;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;

import org.json.simple.JSONObject;

import com.google.gson.JsonObject;
import com.mashery.pm.api.sdk.domain.Data;
import com.mashery.pm.api.sdk.domain.Service_definition_response;
import com.mashery.pm.api.sdk.gson.Service_definition_responseQueryJSONResultContainer;
import com.mashery.pm.api.sdk.gson.Service_definition_responseJSONResult;

public class Service_definition_responseAPIService extends MasheryAPIBase {
	public Service_definition_responseAPIService() throws Exception {
		super();
	}
	private HashMap<String,HashMap<String,Service_definition_response>> itemsMap;
	public Service_definition_response create(Service_definition_response service_definition_response)  throws Exception {
		
		String definition =service_definition_response.getDefinition(); 
		definition = definition.replaceAll("\\n", "");
		if (service_definition_response.getFormat().equals("json")) {
			definition = JSONObject.escape(definition);
		}
	
		String jsonString = "{\"method\":\"service_definition_response.create\",\"id\":1,\"params\":[{\"service_definition_method\":{\"id\":"+service_definition_response.getService_definition_method().getId()+"},\"format\":\""+service_definition_response.getFormat()+"\",\"definition\":\""+definition+"\"}]}";
		String resContainer = callMasheryAPI(jsonString);
		Service_definition_responseJSONResult resContainerJSON =  (Service_definition_responseJSONResult) gson.fromJson(resContainer,Service_definition_responseJSONResult.class);
		if (resContainerJSON.getError() != null) {
			boolean throwError = true;
			if (resContainerJSON.getError().getData() != null)
			{
				
				for (Data data : resContainerJSON.getError().getData()) {
					if (data.getMessage().equals("There may only be one response of that format for the method."));
						throwError = false;
				}
			} 
			if (throwError)
				throw new Exception();
		}
			
		
		return resContainerJSON.getResult();

	}
	public void validate(int id)  throws Exception {
		String jsonString = "{\"method\":\"service_definition_response.validate\",\"id\":1,\"params\":["+id+"]}";
		String resContainer = callMasheryAPI(jsonString);
		Service_definition_responseJSONResult resContainerJSON =  (Service_definition_responseJSONResult) gson.fromJson(resContainer,Service_definition_responseJSONResult.class);
		if (resContainerJSON.getError() != null)
		throw new Exception();


	}
	public Service_definition_response update(Service_definition_response service_definition_response)  throws Exception {
		String jsonString = "{\"method\":\"service_definition_response.update\",\"id\":1,\"params\":["+gson.toJson(service_definition_response)+"]}";
		String resContainer = callMasheryAPI(jsonString);
		Service_definition_responseJSONResult resContainerJSON =  (Service_definition_responseJSONResult) gson.fromJson(resContainer,Service_definition_responseJSONResult.class);
		if (resContainerJSON.getError() != null)
		throw new Exception();
		return resContainerJSON.getResult();

	}
	public void delete(int id)  throws Exception {
		String jsonString = "{\"method\":\"service_definition_response.delete\",\"id\":1,\"params\":["+id+"]}";
		String resContainer = callMasheryAPI(jsonString);
		Service_definition_responseJSONResult resContainerJSON =  (Service_definition_responseJSONResult) gson.fromJson(resContainer,Service_definition_responseJSONResult.class);
		if (resContainerJSON.getError() != null)
		throw new Exception();


	}
	public Service_definition_response fetch(int id)  throws Exception {
		String jsonString = "{\"method\":\"service_definition_response.fetch\",\"id\":1,\"params\":["+id+"]}";
		String resContainer = callMasheryAPI(jsonString);
		Service_definition_responseJSONResult resContainerJSON =  (Service_definition_responseJSONResult) gson.fromJson(resContainer,Service_definition_responseJSONResult.class);
		if (resContainerJSON.getError() != null)
		throw new Exception();
		return resContainerJSON.getResult();

	}
	public List<Service_definition_response> getAllService_definition_responses(String attributes, String whereClause) throws Exception {
		if (attributes == null || attributes.isEmpty()) {
			attributes = "*";
		}
		List<Service_definition_response> items = new ArrayList<Service_definition_response>();
		int total_pages = 1;
		for (int i = 1; i < total_pages + 1; i++) { 
			String resContainer = callMasheryAPI("object.query", "service_definition_responses", attributes , whereClause, i, limit);
			Service_definition_responseQueryJSONResultContainer resContainerJSON =  (Service_definition_responseQueryJSONResultContainer) gson.fromJson(resContainer,Service_definition_responseQueryJSONResultContainer.class);
			items.addAll(resContainerJSON.getResult().getItems());
			total_pages = resContainerJSON.getResult().getTotal_pages();
		}
		return items;
	}

}