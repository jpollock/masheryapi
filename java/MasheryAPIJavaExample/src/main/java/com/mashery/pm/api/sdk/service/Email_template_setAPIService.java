package com.mashery.pm.api.sdk.service;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import com.mashery.pm.api.sdk.domain.Email_template_set;
import com.mashery.pm.api.sdk.gson.Email_template_setQueryJSONResultContainer;
import com.mashery.pm.api.sdk.gson.Email_template_setJSONResult;

public class Email_template_setAPIService extends MasheryAPIBase {
	public Email_template_setAPIService() throws Exception {
		super();
	}
	private HashMap<String,HashMap<String,Email_template_set>> itemsMap;
	public Email_template_set fetch(int id)  throws Exception {
		String jsonString = "{\"method\":\"email_template_set.fetch\",\"id\":1,\"params\":["+id+"]}";
		String resContainer = callMasheryAPI(jsonString);
		Email_template_setJSONResult resContainerJSON =  (Email_template_setJSONResult) gson.fromJson(resContainer,Email_template_setJSONResult.class);
		if (resContainerJSON.getError() != null)
		throw new Exception();
		return resContainerJSON.getResult();

	}
	public Email_template_set create(Email_template_set email_template_set)  throws Exception {
		String jsonString = "{\"method\":\"email_template_set.create\",\"id\":1,\"params\":["+gson.toJson(email_template_set)+"]}";
		String resContainer = callMasheryAPI(jsonString);
		Email_template_setJSONResult resContainerJSON =  (Email_template_setJSONResult) gson.fromJson(resContainer,Email_template_setJSONResult.class);
		if (resContainerJSON.getError() != null)
		throw new Exception();
		return resContainerJSON.getResult();

	}
	public void delete(int id)  throws Exception {
		String jsonString = "{\"method\":\"email_template_set.delete\",\"id\":1,\"params\":["+id+"]}";
		String resContainer = callMasheryAPI(jsonString);
		Email_template_setJSONResult resContainerJSON =  (Email_template_setJSONResult) gson.fromJson(resContainer,Email_template_setJSONResult.class);
		if (resContainerJSON.getError() != null)
		throw new Exception();


	}
	public void validate(int id)  throws Exception {
		String jsonString = "{\"method\":\"email_template_set.validate\",\"id\":1,\"params\":["+id+"]}";
		String resContainer = callMasheryAPI(jsonString);
		Email_template_setJSONResult resContainerJSON =  (Email_template_setJSONResult) gson.fromJson(resContainer,Email_template_setJSONResult.class);
		if (resContainerJSON.getError() != null)
		throw new Exception();


	}
	public List<Email_template_set> getAllEmail_template_sets(String attributes, String whereClause) throws Exception {
		if (attributes == null || attributes.isEmpty()) {
			attributes = "*, email_templates";
		}
		List<Email_template_set> items = new ArrayList<Email_template_set>();
		int total_pages = 1;
		for (int i = 1; i < total_pages + 1; i++) { 
			String resContainer = callMasheryAPI("object.query", "email_template_sets", attributes , whereClause, i, limit);
			Email_template_setQueryJSONResultContainer resContainerJSON =  (Email_template_setQueryJSONResultContainer) gson.fromJson(resContainer,Email_template_setQueryJSONResultContainer.class);
			items.addAll(resContainerJSON.getResult().getItems());
			total_pages = resContainerJSON.getResult().getTotal_pages();
		}
		return items;
	}

}