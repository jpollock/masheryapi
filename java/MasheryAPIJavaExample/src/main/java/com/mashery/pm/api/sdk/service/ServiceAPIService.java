package com.mashery.pm.api.sdk.service;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import com.mashery.pm.api.sdk.domain.Service;
import com.mashery.pm.api.sdk.gson.ServiceQueryJSONResultContainer;
import com.mashery.pm.api.sdk.gson.ServiceJSONResult;

public class ServiceAPIService extends MasheryAPIBase {
	public ServiceAPIService() throws Exception {
		super();
	}
	private HashMap<String,HashMap<String,Service>> itemsMap;
	public void setCacheTTL(int id)  throws Exception {
		String jsonString = "{\"method\":\"service.setCacheTTL\",\"id\":1,\"params\":["+id+"]}";
		String resContainer = callMasheryAPI(jsonString);
		ServiceJSONResult resContainerJSON =  (ServiceJSONResult) gson.fromJson(resContainer,ServiceJSONResult.class);
		if (resContainerJSON.getError() != null)
		throw new Exception();


	}
	public void expireCacheObject(int id)  throws Exception {
		String jsonString = "{\"method\":\"service.expireCacheObject\",\"id\":1,\"params\":["+id+"]}";
		String resContainer = callMasheryAPI(jsonString);
		ServiceJSONResult resContainerJSON =  (ServiceJSONResult) gson.fromJson(resContainer,ServiceJSONResult.class);
		if (resContainerJSON.getError() != null)
		throw new Exception();


	}
	public void flushCache(int id)  throws Exception {
		String jsonString = "{\"method\":\"service.flushCache\",\"id\":1,\"params\":["+id+"]}";
		String resContainer = callMasheryAPI(jsonString);
		ServiceJSONResult resContainerJSON =  (ServiceJSONResult) gson.fromJson(resContainer,ServiceJSONResult.class);
		if (resContainerJSON.getError() != null)
		throw new Exception();


	}
	public Service create(Service service)  throws Exception {
		String jsonString = "{\"method\":\"service.create\",\"id\":1,\"params\":["+gson.toJson(service)+"]}";
		String resContainer = callMasheryAPI(jsonString);
		ServiceJSONResult resContainerJSON =  (ServiceJSONResult) gson.fromJson(resContainer,ServiceJSONResult.class);
		if (resContainerJSON.getError() != null)
		throw new Exception();
		return resContainerJSON.getResult();

	}
	public void getCacheStatus(int id)  throws Exception {
		String jsonString = "{\"method\":\"service.getCacheStatus\",\"id\":1,\"params\":["+id+"]}";
		String resContainer = callMasheryAPI(jsonString);
		ServiceJSONResult resContainerJSON =  (ServiceJSONResult) gson.fromJson(resContainer,ServiceJSONResult.class);
		if (resContainerJSON.getError() != null)
		throw new Exception();


	}
	public Service fetch(int id)  throws Exception {
		String jsonString = "{\"method\":\"service.fetch\",\"id\":1,\"params\":["+id+"]}";
		String resContainer = callMasheryAPI(jsonString);
		ServiceJSONResult resContainerJSON =  (ServiceJSONResult) gson.fromJson(resContainer,ServiceJSONResult.class);
		if (resContainerJSON.getError() != null)
		throw new Exception();
		return resContainerJSON.getResult();

	}
	public List<Service> list(String attributes, String whereClause) throws Exception {
		if (attributes == null || attributes.isEmpty()) {
			attributes = "*";
		}
		List<Service> items = new ArrayList<Service>();
		int total_pages = 1;
		for (int i = 1; i < total_pages + 1; i++) { 
			String resContainer = callMasheryAPI("object.query", "services", attributes , whereClause, i, limit);
			ServiceQueryJSONResultContainer resContainerJSON =  (ServiceQueryJSONResultContainer) gson.fromJson(resContainer,ServiceQueryJSONResultContainer.class);
			items.addAll(resContainerJSON.getResult().getItems());
			total_pages = resContainerJSON.getResult().getTotal_pages();
		}
		return items;
	}

}