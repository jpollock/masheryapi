package com.mashery.pm.api.sdk.service;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import com.mashery.pm.api.sdk.domain.Package;
import com.mashery.pm.api.sdk.gson.PackageQueryJSONResultContainer;
import com.mashery.pm.api.sdk.gson.PackageJSONResult;

public class PackageAPIService extends MasheryAPIBase {
	public PackageAPIService() throws Exception {
		super();
	}
	private HashMap<String,HashMap<String,Package>> itemsMap;
	public Package create(Package packageObject)  throws Exception {
		String jsonString = "{\"method\":\"package.create\",\"id\":1,\"params\":["+gson.toJson(packageObject)+"]}";
		String resContainer = callMasheryAPI(jsonString);
		PackageJSONResult resContainerJSON =  (PackageJSONResult) gson.fromJson(resContainer,PackageJSONResult.class);
		if (resContainerJSON.getError() != null)
		throw new Exception();
		return resContainerJSON.getResult();

	}
	public void clone(int id)  throws Exception {
		String jsonString = "{\"method\":\"package.clone\",\"id\":1,\"params\":["+id+"]}";
		String resContainer = callMasheryAPI(jsonString);
		PackageJSONResult resContainerJSON =  (PackageJSONResult) gson.fromJson(resContainer,PackageJSONResult.class);
		if (resContainerJSON.getError() != null)
		throw new Exception();


	}
	public Package fetch(int id)  throws Exception {
		String jsonString = "{\"method\":\"package.fetch\",\"id\":1,\"params\":["+id+"]}";
		String resContainer = callMasheryAPI(jsonString);
		PackageJSONResult resContainerJSON =  (PackageJSONResult) gson.fromJson(resContainer,PackageJSONResult.class);
		if (resContainerJSON.getError() != null)
		throw new Exception();
		return resContainerJSON.getResult();

	}
	public void validate(int id)  throws Exception {
		String jsonString = "{\"method\":\"package.validate\",\"id\":1,\"params\":["+id+"]}";
		String resContainer = callMasheryAPI(jsonString);
		PackageJSONResult resContainerJSON =  (PackageJSONResult) gson.fromJson(resContainer,PackageJSONResult.class);
		if (resContainerJSON.getError() != null)
		throw new Exception();


	}
	public void delete(int id)  throws Exception {
		String jsonString = "{\"method\":\"package.delete\",\"id\":1,\"params\":["+id+"]}";
		String resContainer = callMasheryAPI(jsonString);
		PackageJSONResult resContainerJSON =  (PackageJSONResult) gson.fromJson(resContainer,PackageJSONResult.class);
		if (resContainerJSON.getError() != null)
		throw new Exception();


	}
	public Package update(Package packageObject)  throws Exception {
		String jsonString = "{\"method\":\"package.update\",\"id\":1,\"params\":["+gson.toJson(packageObject)+"]}";
		String resContainer = callMasheryAPI(jsonString);
		PackageJSONResult resContainerJSON =  (PackageJSONResult) gson.fromJson(resContainer,PackageJSONResult.class);
		if (resContainerJSON.getError() != null)
		throw new Exception();
		return resContainerJSON.getResult();

	}
	public List<Package> getAllPackages(String attributes, String whereClause) throws Exception {
		if (attributes == null || attributes.isEmpty()) {
			attributes = "*";
		}
		List<Package> items = new ArrayList<Package>();
		int total_pages = 1;
		for (int i = 1; i < total_pages + 1; i++) { 
			String resContainer = callMasheryAPI("object.query", "packages", attributes , whereClause, i, limit);
			PackageQueryJSONResultContainer resContainerJSON =  (PackageQueryJSONResultContainer) gson.fromJson(resContainer,PackageQueryJSONResultContainer.class);
			items.addAll(resContainerJSON.getResult().getItems());
			total_pages = resContainerJSON.getResult().getTotal_pages();
		}
		return items;
	}

}