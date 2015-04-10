package com.mashery.pm.api.sdk.service;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import com.mashery.pm.api.sdk.domain.Service_definition_endpoint;
import com.mashery.pm.api.sdk.gson.Service_definition_endpointQueryJSONResultContainer;
import com.mashery.pm.api.sdk.gson.Service_definition_endpointJSONResult;

public class Service_definition_endpointAPIService extends MasheryAPIBase {
	public Service_definition_endpointAPIService() throws Exception {
		super();
	}
	private HashMap<String,HashMap<String,Service_definition_endpoint>> itemsMap;
	public Service_definition_endpoint create(Service_definition_endpoint service_definition_endpoint)  throws Exception {
		String jsonString = "{\"method\":\"service_definition_endpoint.create\",\"id\":1,\"params\":["+gson.toJson(service_definition_endpoint)+"]}";
		String resContainer = callMasheryAPI(jsonString);
		Service_definition_endpointJSONResult resContainerJSON =  (Service_definition_endpointJSONResult) gson.fromJson(resContainer,Service_definition_endpointJSONResult.class);
		if (resContainerJSON.getError() != null)
		throw new Exception();
		return resContainerJSON.getResult();

	}
	public Service_definition_endpoint fetch(int id)  throws Exception {
		String jsonString = "{\"method\":\"service_definition_endpoint.fetch\",\"id\":1,\"params\":["+id+"]}";
		String resContainer = callMasheryAPI(jsonString);
		Service_definition_endpointJSONResult resContainerJSON =  (Service_definition_endpointJSONResult) gson.fromJson(resContainer,Service_definition_endpointJSONResult.class);
		if (resContainerJSON.getError() != null)
		throw new Exception();
		return resContainerJSON.getResult();

	}
	public List<Service_definition_endpoint> getAllService_definition_endpoints(String attributes, String whereClause) throws Exception {
		if (attributes == null || attributes.isEmpty()) {
			attributes = "*";
		}
		List<Service_definition_endpoint> items = new ArrayList<Service_definition_endpoint>();
		int total_pages = 1;
		for (int i = 1; i < total_pages + 1; i++) { 
			String resContainer = callMasheryAPI("object.query", "service_definition_endpoints", attributes , whereClause, i, limit);
			Service_definition_endpointQueryJSONResultContainer resContainerJSON =  (Service_definition_endpointQueryJSONResultContainer) gson.fromJson(resContainer,Service_definition_endpointQueryJSONResultContainer.class);
			items.addAll(resContainerJSON.getResult().getItems());
			total_pages = resContainerJSON.getResult().getTotal_pages();
		}
		return items;
	}

}