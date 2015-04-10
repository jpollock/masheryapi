package com.mashery.pm.api.sdk.service;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import com.mashery.pm.api.sdk.domain.Method_override;
import com.mashery.pm.api.sdk.gson.Method_overrideQueryJSONResultContainer;
import com.mashery.pm.api.sdk.gson.Method_overrideJSONResult;

public class Method_overrideAPIService extends MasheryAPIBase {
	public Method_overrideAPIService() throws Exception {
		super();
	}
	private HashMap<String,HashMap<String,Method_override>> itemsMap;
	public Method_override fetch(int id)  throws Exception {
		String jsonString = "{\"method\":\"method_override.fetch\",\"id\":1,\"params\":["+id+"]}";
		String resContainer = callMasheryAPI(jsonString);
		Method_overrideJSONResult resContainerJSON =  (Method_overrideJSONResult) gson.fromJson(resContainer,Method_overrideJSONResult.class);
		if (resContainerJSON.getError() != null)
		throw new Exception();
		return resContainerJSON.getResult();

	}
	public void delete(int id)  throws Exception {
		String jsonString = "{\"method\":\"method_override.delete\",\"id\":1,\"params\":["+id+"]}";
		String resContainer = callMasheryAPI(jsonString);
		Method_overrideJSONResult resContainerJSON =  (Method_overrideJSONResult) gson.fromJson(resContainer,Method_overrideJSONResult.class);
		if (resContainerJSON.getError() != null)
		throw new Exception();


	}
	public Method_override create(Method_override method_override)  throws Exception {
		String jsonString = "{\"method\":\"method_override.create\",\"id\":1,\"params\":["+gson.toJson(method_override)+"]}";
		String resContainer = callMasheryAPI(jsonString);
		Method_overrideJSONResult resContainerJSON =  (Method_overrideJSONResult) gson.fromJson(resContainer,Method_overrideJSONResult.class);
		if (resContainerJSON.getError() != null)
		throw new Exception();
		return resContainerJSON.getResult();

	}
	public void validate(int id)  throws Exception {
		String jsonString = "{\"method\":\"method_override.validate\",\"id\":1,\"params\":["+id+"]}";
		String resContainer = callMasheryAPI(jsonString);
		Method_overrideJSONResult resContainerJSON =  (Method_overrideJSONResult) gson.fromJson(resContainer,Method_overrideJSONResult.class);
		if (resContainerJSON.getError() != null)
		throw new Exception();


	}
	public Method_override update(Method_override method_override)  throws Exception {
		String jsonString = "{\"method\":\"method_override.update\",\"id\":1,\"params\":["+gson.toJson(method_override)+"]}";
		String resContainer = callMasheryAPI(jsonString);
		Method_overrideJSONResult resContainerJSON =  (Method_overrideJSONResult) gson.fromJson(resContainer,Method_overrideJSONResult.class);
		if (resContainerJSON.getError() != null)
		throw new Exception();
		return resContainerJSON.getResult();

	}
	public List<Method_override> getAllMethod_overrides(String attributes, String whereClause) throws Exception {
		if (attributes == null || attributes.isEmpty()) {
			attributes = "*";
		}
		List<Method_override> items = new ArrayList<Method_override>();
		int total_pages = 1;
		for (int i = 1; i < total_pages + 1; i++) { 
			String resContainer = callMasheryAPI("object.query", "method_overrides", attributes , whereClause, i, limit);
			Method_overrideQueryJSONResultContainer resContainerJSON =  (Method_overrideQueryJSONResultContainer) gson.fromJson(resContainer,Method_overrideQueryJSONResultContainer.class);
			items.addAll(resContainerJSON.getResult().getItems());
		}
		return items;
	}

}