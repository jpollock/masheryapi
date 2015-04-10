package com.mashery.pm.api.sdk.service;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import com.mashery.pm.api.sdk.domain.Application;
import com.mashery.pm.api.sdk.gson.ApplicationQueryJSONResultContainer;
import com.mashery.pm.api.sdk.gson.ApplicationJSONResult;

public class ApplicationAPIService extends MasheryAPIBase {
	public ApplicationAPIService() throws Exception {
		super();
	}
	private HashMap<String,HashMap<String,Application>> itemsMap;
	public Application create(Application application)  throws Exception {
		String jsonString = "{\"method\":\"application.create\",\"id\":1,\"params\":["+gson.toJson(application)+"]}";
		String resContainer = callMasheryAPI(jsonString);
		ApplicationJSONResult resContainerJSON =  (ApplicationJSONResult) gson.fromJson(resContainer,ApplicationJSONResult.class);
		if (resContainerJSON.getError() != null)
		throw new Exception();
		return resContainerJSON.getResult();

	}
	public Application fetch(int id)  throws Exception {
		String jsonString = "{\"method\":\"application.fetch\",\"id\":1,\"params\":["+id+"]}";
		String resContainer = callMasheryAPI(jsonString);
		ApplicationJSONResult resContainerJSON =  (ApplicationJSONResult) gson.fromJson(resContainer,ApplicationJSONResult.class);
		if (resContainerJSON.getError() != null)
		throw new Exception();
		return resContainerJSON.getResult();

	}
	public Application update(Application application)  throws Exception {
		String jsonString = "{\"method\":\"application.update\",\"id\":1,\"params\":["+gson.toJson(application)+"]}";
		String resContainer = callMasheryAPI(jsonString);
		ApplicationJSONResult resContainerJSON =  (ApplicationJSONResult) gson.fromJson(resContainer,ApplicationJSONResult.class);
		if (resContainerJSON.getError() != null)
		throw new Exception();
		return resContainerJSON.getResult();

	}
	public void delete(int id)  throws Exception {
		String jsonString = "{\"method\":\"application.delete\",\"id\":1,\"params\":["+id+"]}";
		String resContainer = callMasheryAPI(jsonString);
		ApplicationJSONResult resContainerJSON =  (ApplicationJSONResult) gson.fromJson(resContainer,ApplicationJSONResult.class);
		if (resContainerJSON.getError() != null)
		throw new Exception();


	}
	public void validate(int id)  throws Exception {
		String jsonString = "{\"method\":\"application.validate\",\"id\":1,\"params\":["+id+"]}";
		String resContainer = callMasheryAPI(jsonString);
		ApplicationJSONResult resContainerJSON =  (ApplicationJSONResult) gson.fromJson(resContainer,ApplicationJSONResult.class);
		if (resContainerJSON.getError() != null)
		throw new Exception();


	}
	public List<Application> getAllApplications(String attributes, String whereClause) throws Exception {
		if (attributes == null || attributes.isEmpty()) {
			attributes = "*";
		}
		List<Application> items = new ArrayList<Application>();
		int total_pages = 1;
		for (int i = 1; i < total_pages + 1; i++) { 
			String resContainer = callMasheryAPI("object.query", "applications", attributes , whereClause, i, limit);
			ApplicationQueryJSONResultContainer resContainerJSON =  (ApplicationQueryJSONResultContainer) gson.fromJson(resContainer,ApplicationQueryJSONResultContainer.class);
			items.addAll(resContainerJSON.getResult().getItems());
			total_pages = resContainerJSON.getResult().getTotal_pages();
		}
		return items;
	}

}