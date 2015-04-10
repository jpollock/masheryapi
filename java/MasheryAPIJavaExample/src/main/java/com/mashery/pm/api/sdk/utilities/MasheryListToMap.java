package com.mashery.pm.api.sdk.utilities;

import java.util.HashMap;
import java.util.List;

import com.mashery.pm.api.sdk.domain.*;

public class MasheryListToMap {
	

	public static HashMap<String, Object> getMapFromList(String type, List listOfObjects) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		for (Object object : listOfObjects) {
			if (type.equals("member")) {
				Member member = (Member) object;
				map.put(member.getUsername(), member);
				
			} else if (type.equals("service_definition")) {
				Service_definition service_definition = (Service_definition) object;
				map.put(service_definition.getService_key(), service_definition);
			} else if (type.equals("service_definition_endpoint")) {
				Service_definition_endpoint item = (Service_definition_endpoint) object;
				map.put(item.getId().toString(), item);
				
			} else if (type.equals("service_definition_method")) {
				
			} else if (type.equals("service_definition_endpoint")) {
				
			}


		}
		
		return map;
				
	}
}
