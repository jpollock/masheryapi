package com.mashery.pm.api.sdk.service;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import com.mashery.pm.api.sdk.domain.Developer_class;
import com.mashery.pm.api.sdk.gson.Developer_classQueryJSONResultContainer;
import com.mashery.pm.api.sdk.gson.Developer_classJSONResult;

public class Developer_classAPIService extends MasheryAPIBase {
	public Developer_classAPIService() throws Exception {
		super();
	}
	private HashMap<String,HashMap<String,Developer_class>> itemsMap;
	public Developer_class fetch(int id)  throws Exception {
		String jsonString = "{\"method\":\"developer_class.fetch\",\"id\":1,\"params\":["+id+"]}";
		String resContainer = callMasheryAPI(jsonString);
		Developer_classJSONResult resContainerJSON =  (Developer_classJSONResult) gson.fromJson(resContainer,Developer_classJSONResult.class);
		if (resContainerJSON.getError() != null)
		throw new Exception();
		return resContainerJSON.getResult();

	}
	public Developer_class update(Developer_class developer_class)  throws Exception {
		String jsonString = "{\"method\":\"developer_class.update\",\"id\":1,\"params\":["+gson.toJson(developer_class)+"]}";
		String resContainer = callMasheryAPI(jsonString);
		Developer_classJSONResult resContainerJSON =  (Developer_classJSONResult) gson.fromJson(resContainer,Developer_classJSONResult.class);
		if (resContainerJSON.getError() != null)
		throw new Exception();
		return resContainerJSON.getResult();

	}
	public Developer_class create(Developer_class developer_class)  throws Exception {
		String jsonString = "{\"method\":\"developer_class.create\",\"id\":1,\"params\":["+gson.toJson(developer_class)+"]}";
		String resContainer = callMasheryAPI(jsonString);
		Developer_classJSONResult resContainerJSON =  (Developer_classJSONResult) gson.fromJson(resContainer,Developer_classJSONResult.class);
		if (resContainerJSON.getError() != null)
		throw new Exception();
		return resContainerJSON.getResult();

	}
	public void validate(int id)  throws Exception {
		String jsonString = "{\"method\":\"developer_class.validate\",\"id\":1,\"params\":["+id+"]}";
		String resContainer = callMasheryAPI(jsonString);
		Developer_classJSONResult resContainerJSON =  (Developer_classJSONResult) gson.fromJson(resContainer,Developer_classJSONResult.class);
		if (resContainerJSON.getError() != null)
		throw new Exception();


	}
	public void delete(int id)  throws Exception {
		String jsonString = "{\"method\":\"developer_class.delete\",\"id\":1,\"params\":["+id+"]}";
		String resContainer = callMasheryAPI(jsonString);
		Developer_classJSONResult resContainerJSON =  (Developer_classJSONResult) gson.fromJson(resContainer,Developer_classJSONResult.class);
		if (resContainerJSON.getError() != null)
		throw new Exception();


	}
	public List<Developer_class> getAllDeveloper_class(String attributes, String whereClause) throws Exception {
		if (attributes == null || attributes.isEmpty()) {
			attributes = "*";
		}
		List<Developer_class> items = new ArrayList<Developer_class>();
		int total_pages = 1;
		for (int i = 1; i < total_pages + 1; i++) { 
			String resContainer = callMasheryAPI("object.query", "developer_classes", attributes , whereClause, i, limit);
			Developer_classQueryJSONResultContainer resContainerJSON =  (Developer_classQueryJSONResultContainer) gson.fromJson(resContainer,Developer_classQueryJSONResultContainer.class);
			items.addAll(resContainerJSON.getResult().getItems());
		}
		return items;
	}

}