package com.mashery.pm.api.sdk.gson;

import java.lang.reflect.Type;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.mashery.pm.api.sdk.domain.Member;

public class DeserializerString implements JsonDeserializer<String>{

	
	public String deserialize(JsonElement json, Type typeOfT,
			JsonDeserializationContext context) throws JsonParseException {
		// TODO Auto-generated method stub
		try {
			JsonObject o = json.getAsJsonObject();
			System.out.println(json.getAsJsonPrimitive().getAsString());
			if (Member.class.getField(json.getAsJsonPrimitive().getAsString()) != null)
				System.out.println("here");
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchFieldException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new String(json.getAsJsonPrimitive().getAsString());
		//return "test";
	}

}
