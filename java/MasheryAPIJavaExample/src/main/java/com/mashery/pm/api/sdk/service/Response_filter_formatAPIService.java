package com.mashery.pm.api.sdk.service;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import com.mashery.pm.api.sdk.domain.Response_filter_format;
import com.mashery.pm.api.sdk.gson.Response_filter_formatQueryJSONResultContainer;
import com.mashery.pm.api.sdk.gson.Response_filter_formatJSONResult;

public class Response_filter_formatAPIService extends MasheryAPIBase {
	public Response_filter_formatAPIService() throws Exception {
		super();
	}
	private HashMap<String,HashMap<String,Response_filter_format>> itemsMap;
	public void validate(int id)  throws Exception {
		String jsonString = "{\"method\":\"response_filter_format.validate\",\"id\":1,\"params\":["+id+"]}";
		String resContainer = callMasheryAPI(jsonString);
		Response_filter_formatJSONResult resContainerJSON =  (Response_filter_formatJSONResult) gson.fromJson(resContainer,Response_filter_formatJSONResult.class);
		if (resContainerJSON.getError() != null)
		throw new Exception();


	}
	public Response_filter_format create(Response_filter_format response_filter_format)  throws Exception {
		String jsonString = "{\"method\":\"response_filter_format.create\",\"id\":1,\"params\":["+gson.toJson(response_filter_format)+"]}";
		String resContainer = callMasheryAPI(jsonString);
		Response_filter_formatJSONResult resContainerJSON =  (Response_filter_formatJSONResult) gson.fromJson(resContainer,Response_filter_formatJSONResult.class);
		if (resContainerJSON.getError() != null)
		throw new Exception();
		return resContainerJSON.getResult();

	}
	public Response_filter_format fetch(int id)  throws Exception {
		String jsonString = "{\"method\":\"response_filter_format.fetch\",\"id\":1,\"params\":["+id+"]}";
		String resContainer = callMasheryAPI(jsonString);
		Response_filter_formatJSONResult resContainerJSON =  (Response_filter_formatJSONResult) gson.fromJson(resContainer,Response_filter_formatJSONResult.class);
		if (resContainerJSON.getError() != null)
		throw new Exception();
		return resContainerJSON.getResult();

	}
	public Response_filter_format update(Response_filter_format response_filter_format)  throws Exception {
		String jsonString = "{\"method\":\"response_filter_format.update\",\"id\":1,\"params\":["+gson.toJson(response_filter_format)+"]}";
		String resContainer = callMasheryAPI(jsonString);
		Response_filter_formatJSONResult resContainerJSON =  (Response_filter_formatJSONResult) gson.fromJson(resContainer,Response_filter_formatJSONResult.class);
		if (resContainerJSON.getError() != null)
		throw new Exception();
		return resContainerJSON.getResult();

	}
	public void delete(int id)  throws Exception {
		String jsonString = "{\"method\":\"response_filter_format.delete\",\"id\":1,\"params\":["+id+"]}";
		String resContainer = callMasheryAPI(jsonString);
		Response_filter_formatJSONResult resContainerJSON =  (Response_filter_formatJSONResult) gson.fromJson(resContainer,Response_filter_formatJSONResult.class);
		if (resContainerJSON.getError() != null)
		throw new Exception();


	}
	public List<Response_filter_format> getAllResponse_filter_formats(String attributes, String whereClause) throws Exception {
		if (attributes == null || attributes.isEmpty()) {
			attributes = "*";
		}
		List<Response_filter_format> items = new ArrayList<Response_filter_format>();
		int total_pages = 1;
		for (int i = 1; i < total_pages + 1; i++) { 
			String resContainer = callMasheryAPI("object.query", "response_filter_formats", attributes , whereClause, i, limit);
			Response_filter_formatQueryJSONResultContainer resContainerJSON =  (Response_filter_formatQueryJSONResultContainer) gson.fromJson(resContainer,Response_filter_formatQueryJSONResultContainer.class);
			items.addAll(resContainerJSON.getResult().getItems());
			total_pages = resContainerJSON.getResult().getTotal_pages();
		}
		return items;
	}

}