package com.mashery.pm.api.sdk.service;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import com.mashery.pm.api.sdk.domain.Service;
import com.mashery.pm.api.sdk.domain.Service_iodoc;
import com.mashery.pm.api.sdk.gson.ServiceQueryJSONResultContainer;
import com.mashery.pm.api.sdk.gson.ServiceJSONResult;
import com.mashery.pm.api.sdk.gson.Service_iodocJSONResult;
import com.mashery.pm.api.sdk.gson.Service_iodocQueryJSONResultContainer;

public class Service_iodocAPIService extends MasheryAPIBase {
	public Service_iodocAPIService() throws Exception {
		super();
	}
	private HashMap<String,HashMap<String,Service>> itemsMap;
	public Service_iodoc create(Service_iodoc service_iodoc)  throws Exception {
		String jsonString = "{\"method\":\"service_iodoc.create\",\"id\":1,\"params\":["+gson.toJson(service_iodoc)+"]}";
		String resContainer = callMasheryAPI(jsonString);
		Service_iodocJSONResult resContainerJSON =  (Service_iodocJSONResult) gson.fromJson(resContainer,Service_iodocJSONResult.class);
		if (resContainerJSON.getError() != null)
		throw new Exception();
		return resContainerJSON.getResult();

	}
	public Service_iodoc fetch(int id)  throws Exception {
		String jsonString = "{\"method\":\"service.fetch\",\"id\":1,\"params\":["+id+"]}";
		String resContainer = callMasheryAPI(jsonString);
		Service_iodocJSONResult resContainerJSON =  (Service_iodocJSONResult) gson.fromJson(resContainer,Service_iodocJSONResult.class);
		if (resContainerJSON.getError() != null)
		throw new Exception();
		return resContainerJSON.getResult();

	}
	public List<Service_iodoc> list(String attributes, String whereClause) throws Exception {
		if (attributes == null || attributes.isEmpty()) {
			attributes = "*";
		}
		List<Service_iodoc> items = new ArrayList<Service_iodoc>();
		int total_pages = 1;
		for (int i = 1; i < total_pages + 1; i++) { 
			String resContainer = callMasheryAPI("object.query", "service_iodocs", attributes , whereClause, i, limit);
			Service_iodocQueryJSONResultContainer resContainerJSON =  (Service_iodocQueryJSONResultContainer) gson.fromJson(resContainer,Service_iodocQueryJSONResultContainer.class);
			items.addAll(resContainerJSON.getResult().getItems());
			total_pages = resContainerJSON.getResult().getTotal_pages();
		}
		return items;
	}

}