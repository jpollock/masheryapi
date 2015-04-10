package com.mashery.pm.api.sdk.service;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import com.mashery.pm.api.sdk.domain.Email_template;
import com.mashery.pm.api.sdk.gson.Email_templateQueryJSONResultContainer;
import com.mashery.pm.api.sdk.gson.Email_templateJSONResult;

public class Email_templateAPIService extends MasheryAPIBase {
	public Email_templateAPIService() throws Exception {
		super();
	}
	private HashMap<String,HashMap<String,Email_template>> itemsMap;
	public Email_template fetch(int id)  throws Exception {
		String jsonString = "{\"method\":\"email_template.fetch\",\"id\":1,\"params\":["+id+"]}";
		String resContainer = callMasheryAPI(jsonString);
		Email_templateJSONResult resContainerJSON =  (Email_templateJSONResult) gson.fromJson(resContainer,Email_templateJSONResult.class);
		if (resContainerJSON.getError() != null)
		throw new Exception();
		return resContainerJSON.getResult();

	}
	public Email_template create(Email_template email_template)  throws Exception {
		String jsonString = "{\"method\":\"email_template.create\",\"id\":1,\"params\":["+gson.toJson(email_template)+"]}";
		String resContainer = callMasheryAPI(jsonString);
		Email_templateJSONResult resContainerJSON =  (Email_templateJSONResult) gson.fromJson(resContainer,Email_templateJSONResult.class);
		if (resContainerJSON.getError() != null)
		throw new Exception();
		return resContainerJSON.getResult();

	}
	public void delete(int id)  throws Exception {
		String jsonString = "{\"method\":\"email_template.delete\",\"id\":1,\"params\":["+id+"]}";
		String resContainer = callMasheryAPI(jsonString);
		Email_templateJSONResult resContainerJSON =  (Email_templateJSONResult) gson.fromJson(resContainer,Email_templateJSONResult.class);
		if (resContainerJSON.getError() != null)
		throw new Exception();


	}
	public void validate(int id)  throws Exception {
		String jsonString = "{\"method\":\"email_template.validate\",\"id\":1,\"params\":["+id+"]}";
		String resContainer = callMasheryAPI(jsonString);
		Email_templateJSONResult resContainerJSON =  (Email_templateJSONResult) gson.fromJson(resContainer,Email_templateJSONResult.class);
		if (resContainerJSON.getError() != null)
		throw new Exception();


	}
	public List<Email_template> getAllEmail_templates(String attributes, String whereClause) throws Exception {
		if (attributes == null || attributes.isEmpty()) {
			attributes = "*";
		}
		List<Email_template> items = new ArrayList<Email_template>();
		int total_pages = 1;
		for (int i = 1; i < total_pages + 1; i++) { 
			String resContainer = callMasheryAPI("object.query", "email_templates", attributes , whereClause, i, limit);
			Email_templateQueryJSONResultContainer resContainerJSON =  (Email_templateQueryJSONResultContainer) gson.fromJson(resContainer,Email_templateQueryJSONResultContainer.class);
			items.addAll(resContainerJSON.getResult().getItems());
			total_pages = resContainerJSON.getResult().getTotal_pages();
		}
		return items;
	}

}