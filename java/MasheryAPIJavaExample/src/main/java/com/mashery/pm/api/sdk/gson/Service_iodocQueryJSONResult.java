package com.mashery.pm.api.sdk.gson;

import java.util.List;

import com.mashery.pm.api.sdk.domain.Service_iodoc;

public class Service_iodocQueryJSONResult {
	public int getTotal_items() {
		return total_items;
	}

	public void setTotal_items(int total_items) {
		this.total_items = total_items;
	}

	public int getTotal_pages() {
		return total_pages;
	}

	public void setTotal_pages(int total_pages) {
		this.total_pages = total_pages;
	}

	public int getItems_per_page() {
		return items_per_page;
	}

	public void setItems_per_page(int items_per_page) {
		this.items_per_page = items_per_page;
	}

	public int getCurrent_page() {
		return current_page;
	}

	public void setCurrent_page(int current_page) {
		this.current_page = current_page;
	}

	public List<Service_iodoc> getItems() {
		return items;
	}

	public void setItems(List<Service_iodoc> items) {
		this.items = items;
	}

	private int total_items;
	private int total_pages;
	private int items_per_page;
	private int current_page;
	private List<Service_iodoc> items;
	
	Service_iodocQueryJSONResult()
	{
		
	}
	
}
