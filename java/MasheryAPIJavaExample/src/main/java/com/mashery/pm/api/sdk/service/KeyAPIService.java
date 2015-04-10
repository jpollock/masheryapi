package com.mashery.pm.api.sdk.service;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import com.mashery.pm.api.sdk.domain.Key;
import com.mashery.pm.api.sdk.gson.KeyQueryJSONResultContainer;
import com.mashery.pm.api.sdk.gson.KeyJSONResult;

public class KeyAPIService extends MasheryAPIBase {
	public KeyAPIService() throws Exception {
		super();
	}
	private HashMap<String,HashMap<String,Key>> itemsMap;
	public Key update(Key key)  throws Exception {
		String jsonString = "{\"method\":\"key.update\",\"id\":1,\"params\":["+gson.toJson(key)+"]}";
		String resContainer = callMasheryAPI(jsonString);
		KeyJSONResult resContainerJSON =  (KeyJSONResult) gson.fromJson(resContainer,KeyJSONResult.class);
		if (resContainerJSON.getError() != null)
		throw new Exception();
		return resContainerJSON.getResult();

	}
	public Key create(Key key)  throws Exception {
		String jsonString = "{\"method\":\"key.create\",\"id\":1,\"params\":["+gson.toJson(key)+"]}";
		String resContainer = callMasheryAPI(jsonString);
		KeyJSONResult resContainerJSON =  (KeyJSONResult) gson.fromJson(resContainer,KeyJSONResult.class);
		if (resContainerJSON.getError() != null)
		throw new Exception();
		return resContainerJSON.getResult();

	}
	public Key fetch(Key key)  throws Exception {
		String jsonString = "{\"method\":\"key.fetch\",\"id\":1,\"params\":["+gson.toJson(key)+"]}";
		String resContainer = callMasheryAPI(jsonString);
		KeyJSONResult resContainerJSON =  (KeyJSONResult) gson.fromJson(resContainer,KeyJSONResult.class);
		if (resContainerJSON.getError() != null)
		throw new Exception();
		return resContainerJSON.getResult();

	}

	public Key fetch(int id)  throws Exception {
		String jsonString = "{\"method\":\"key.fetch\",\"id\":1,\"params\":["+id+"]}";
		String resContainer = callMasheryAPI(jsonString);
		KeyJSONResult resContainerJSON =  (KeyJSONResult) gson.fromJson(resContainer,KeyJSONResult.class);
		if (resContainerJSON.getError() != null)
		throw new Exception();
		return resContainerJSON.getResult();

	}
	public void validate(int id)  throws Exception {
		String jsonString = "{\"method\":\"key.validate\",\"id\":1,\"params\":["+id+"]}";
		String resContainer = callMasheryAPI(jsonString);
		KeyJSONResult resContainerJSON =  (KeyJSONResult) gson.fromJson(resContainer,KeyJSONResult.class);
		if (resContainerJSON.getError() != null)
		throw new Exception();


	}
	public void delete(int id)  throws Exception {
		String jsonString = "{\"method\":\"key.delete\",\"id\":1,\"params\":["+id+"]}";
		String resContainer = callMasheryAPI(jsonString);
		KeyJSONResult resContainerJSON =  (KeyJSONResult) gson.fromJson(resContainer,KeyJSONResult.class);
		if (resContainerJSON.getError() != null)
		throw new Exception();


	}
	public List<Key> getAllKeys(String attributes, String whereClause) throws Exception {
		if (attributes == null || attributes.isEmpty()) {
			attributes = "*";
		}
		List<Key> items = new ArrayList<Key>();
		int total_pages = 1;
		for (int i = 1; i < total_pages + 1; i++) { 
			String resContainer = callMasheryAPI("object.query", "keys", attributes , whereClause, i, limit);
			KeyQueryJSONResultContainer resContainerJSON =  (KeyQueryJSONResultContainer) gson.fromJson(resContainer,KeyQueryJSONResultContainer.class);
			items.addAll(resContainerJSON.getResult().getItems());
			total_pages = resContainerJSON.getResult().getTotal_pages();
	
		}
		return items;
	}

}