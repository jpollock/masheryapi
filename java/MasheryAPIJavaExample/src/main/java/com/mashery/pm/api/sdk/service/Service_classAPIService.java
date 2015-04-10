package com.mashery.pm.api.sdk.service;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import com.mashery.pm.api.sdk.domain.Service_class;
import com.mashery.pm.api.sdk.gson.Service_classQueryJSONResultContainer;
import com.mashery.pm.api.sdk.gson.Service_classJSONResult;

public class Service_classAPIService extends MasheryAPIBase {
	public Service_classAPIService() throws Exception {
		super();
	}
	private HashMap<String,HashMap<String,Service_class>> itemsMap;
	public Service_class update(Service_class service_class)  throws Exception {
		String jsonString = "{\"method\":\"service_class.update\",\"id\":1,\"params\":["+gson.toJson(service_class)+"]}";
		String resContainer = callMasheryAPI(jsonString);
		Service_classJSONResult resContainerJSON =  (Service_classJSONResult) gson.fromJson(resContainer,Service_classJSONResult.class);
		if (resContainerJSON.getError() != null)
		throw new Exception();
		return resContainerJSON.getResult();

	}
	public Service_class fetch(int id)  throws Exception {
		String jsonString = "{\"method\":\"service_class.fetch\",\"id\":1,\"params\":["+id+"]}";
		String resContainer = callMasheryAPI(jsonString);
		Service_classJSONResult resContainerJSON =  (Service_classJSONResult) gson.fromJson(resContainer,Service_classJSONResult.class);
		if (resContainerJSON.getError() != null)
		throw new Exception();
		return resContainerJSON.getResult();

	}
	public void validate(int id)  throws Exception {
		String jsonString = "{\"method\":\"service_class.validate\",\"id\":1,\"params\":["+id+"]}";
		String resContainer = callMasheryAPI(jsonString);
		Service_classJSONResult resContainerJSON =  (Service_classJSONResult) gson.fromJson(resContainer,Service_classJSONResult.class);
		if (resContainerJSON.getError() != null)
		throw new Exception();


	}
	public List<Service_class> getAllService_class(String attributes, String whereClause) throws Exception {
		if (attributes == null || attributes.isEmpty()) {
			attributes = "*";
		}
		List<Service_class> items = new ArrayList<Service_class>();
		int total_pages = 1;
		for (int i = 1; i < total_pages + 1; i++) { 
			String resContainer = callMasheryAPI("object.query", "service_class", attributes , whereClause, i, limit);
			Service_classQueryJSONResultContainer resContainerJSON =  (Service_classQueryJSONResultContainer) gson.fromJson(resContainer,Service_classQueryJSONResultContainer.class);
			items.addAll(resContainerJSON.getResult().getItems());
			total_pages = resContainerJSON.getResult().getTotal_pages();
		}
		return items;
	}

}