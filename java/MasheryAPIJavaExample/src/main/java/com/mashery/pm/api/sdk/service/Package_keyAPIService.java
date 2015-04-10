package com.mashery.pm.api.sdk.service;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import com.mashery.pm.api.sdk.domain.Package_key;
import com.mashery.pm.api.sdk.gson.Package_keyQueryJSONResultContainer;
import com.mashery.pm.api.sdk.gson.Package_keyJSONResult;

public class Package_keyAPIService extends MasheryAPIBase {
	public Package_keyAPIService() throws Exception {
		super();
	}
	private HashMap<String,HashMap<String,Package_key>> itemsMap;
	public Package_key fetch(int id)  throws Exception {
		String jsonString = "{\"method\":\"package_key.fetch\",\"id\":1,\"params\":["+id+"]}";
		String resContainer = callMasheryAPI(jsonString);
		Package_keyJSONResult resContainerJSON =  (Package_keyJSONResult) gson.fromJson(resContainer,Package_keyJSONResult.class);
		if (resContainerJSON.getError() != null)
		throw new Exception();
		return resContainerJSON.getResult();

	}
	public Package_key update(Package_key package_key)  throws Exception {
		String jsonString = "{\"method\":\"package_key.update\",\"id\":1,\"params\":["+gson.toJson(package_key)+"]}";
		String resContainer = callMasheryAPI(jsonString);
		Package_keyJSONResult resContainerJSON =  (Package_keyJSONResult) gson.fromJson(resContainer,Package_keyJSONResult.class);
		if (resContainerJSON.getError() != null)
		throw new Exception();
		return resContainerJSON.getResult();

	}
	public void validate(int id)  throws Exception {
		String jsonString = "{\"method\":\"package_key.validate\",\"id\":1,\"params\":["+id+"]}";
		String resContainer = callMasheryAPI(jsonString);
		Package_keyJSONResult resContainerJSON =  (Package_keyJSONResult) gson.fromJson(resContainer,Package_keyJSONResult.class);
		if (resContainerJSON.getError() != null)
		throw new Exception();


	}
	public Package_key create(Package_key package_key)  throws Exception {
		String jsonString = "{\"method\":\"package_key.create\",\"id\":1,\"params\":["+gson.toJson(package_key).replace("parentPackage", "package")+"]}";
		String resContainer = callMasheryAPI(jsonString);
		Package_keyJSONResult resContainerJSON =  (Package_keyJSONResult) gson.fromJson(resContainer,Package_keyJSONResult.class);
		if (resContainerJSON.getError() != null)
		throw new Exception();
		return resContainerJSON.getResult();

	}
	public void delete(int id)  throws Exception {
		String jsonString = "{\"method\":\"package_key.delete\",\"id\":1,\"params\":["+id+"]}";
		String resContainer = callMasheryAPI(jsonString);
		Package_keyJSONResult resContainerJSON =  (Package_keyJSONResult) gson.fromJson(resContainer,Package_keyJSONResult.class);
		if (resContainerJSON.getError() != null)
		throw new Exception();


	}
	public List<Package_key> getAllPackage_keys(String attributes, String whereClause) throws Exception {
		if (attributes == null || attributes.isEmpty()) {
			attributes = "*";
		}
		List<Package_key> items = new ArrayList<Package_key>();
		int total_pages = 1;
		for (int i = 1; i < total_pages + 1; i++) { 
			String resContainer = callMasheryAPI("object.query", "package_keys", attributes , whereClause, i, limit);
			Package_keyQueryJSONResultContainer resContainerJSON =  (Package_keyQueryJSONResultContainer) gson.fromJson(resContainer,Package_keyQueryJSONResultContainer.class);
			items.addAll(resContainerJSON.getResult().getItems());
			total_pages = resContainerJSON.getResult().getTotal_pages();
		}
		return items;
	}

}