package com.mashery.pm.api.sdk.service;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import com.mashery.pm.api.sdk.domain.Response_filter;
import com.mashery.pm.api.sdk.gson.Response_filterQueryJSONResultContainer;
import com.mashery.pm.api.sdk.gson.Response_filterJSONResult;

public class Response_filterAPIService extends MasheryAPIBase {
	public Response_filterAPIService() throws Exception {
		super();
	}
	private HashMap<String,HashMap<String,Response_filter>> itemsMap;
	public Response_filter fetch(int id)  throws Exception {
		String jsonString = "{\"method\":\"response_filter.fetch\",\"id\":1,\"params\":["+id+"]}";
		String resContainer = callMasheryAPI(jsonString);
		Response_filterJSONResult resContainerJSON =  (Response_filterJSONResult) gson.fromJson(resContainer,Response_filterJSONResult.class);
		if (resContainerJSON.getError() != null)
		throw new Exception();
		return resContainerJSON.getResult();

	}
	public Response_filter create(Response_filter response_filter)  throws Exception {
		String jsonString = "{\"method\":\"response_filter.create\",\"id\":1,\"params\":["+gson.toJson(response_filter)+"]}";
		String resContainer = callMasheryAPI(jsonString);
		Response_filterJSONResult resContainerJSON =  (Response_filterJSONResult) gson.fromJson(resContainer,Response_filterJSONResult.class);
		if (resContainerJSON.getError() != null)
		throw new Exception();
		return resContainerJSON.getResult();

	}
	public Response_filter update(Response_filter response_filter)  throws Exception {
		String jsonString = "{\"method\":\"response_filter.update\",\"id\":1,\"params\":["+gson.toJson(response_filter)+"]}";
		String resContainer = callMasheryAPI(jsonString);
		Response_filterJSONResult resContainerJSON =  (Response_filterJSONResult) gson.fromJson(resContainer,Response_filterJSONResult.class);
		if (resContainerJSON.getError() != null)
		throw new Exception();
		return resContainerJSON.getResult();

	}
	public void clone(int id)  throws Exception {
		String jsonString = "{\"method\":\"response_filter.clone\",\"id\":1,\"params\":["+id+"]}";
		String resContainer = callMasheryAPI(jsonString);
		Response_filterJSONResult resContainerJSON =  (Response_filterJSONResult) gson.fromJson(resContainer,Response_filterJSONResult.class);
		if (resContainerJSON.getError() != null)
		throw new Exception();


	}
	public void delete(int id)  throws Exception {
		String jsonString = "{\"method\":\"response_filter.delete\",\"id\":1,\"params\":["+id+"]}";
		String resContainer = callMasheryAPI(jsonString);
		Response_filterJSONResult resContainerJSON =  (Response_filterJSONResult) gson.fromJson(resContainer,Response_filterJSONResult.class);
		if (resContainerJSON.getError() != null)
		throw new Exception();


	}
	public void validate(int id)  throws Exception {
		String jsonString = "{\"method\":\"response_filter.validate\",\"id\":1,\"params\":["+id+"]}";
		String resContainer = callMasheryAPI(jsonString);
		Response_filterJSONResult resContainerJSON =  (Response_filterJSONResult) gson.fromJson(resContainer,Response_filterJSONResult.class);
		if (resContainerJSON.getError() != null)
		throw new Exception();


	}
	public List<Response_filter> getAllResponse_filters(String attributes, String whereClause) throws Exception {
		if (attributes == null || attributes.isEmpty()) {
			attributes = "*";
		}
		List<Response_filter> items = new ArrayList<Response_filter>();
		int total_pages = 1;
		for (int i = 1; i < total_pages + 1; i++) { 
			String resContainer = callMasheryAPI("object.query", "response_filters", attributes , whereClause, i, limit);
			Response_filterQueryJSONResultContainer resContainerJSON =  (Response_filterQueryJSONResultContainer) gson.fromJson(resContainer,Response_filterQueryJSONResultContainer.class);
			items.addAll(resContainerJSON.getResult().getItems());
			total_pages = resContainerJSON.getResult().getTotal_pages();
		}
		return items;
	}

}