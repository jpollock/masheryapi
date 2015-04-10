package com.mashery.pm.api.sdk.domain;

import java.util.List;

public class Error {
	private String message;
	private int code;
	private List<Data> data;
	
	public Error() {
		
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	/*
	public Data getData() {
		return data;
	}
	public void setData(Data data) {
		this.data = data;
	}*/
	public List<Data> getData() {
		return data;
	}
	public void setData(List<Data> data) {
		this.data = data;
	}
	
}
