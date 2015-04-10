package com.mashery.pm.api.sdk.gson;

import java.util.List;

import com.mashery.pm.api.sdk.domain.Member;

public class MemberQueryJSONResult extends BaseQueryJSONResultContainer {
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

	public List<Member> getItems() {
		return items;
	}

	public void setItems(List<Member> items) {
		this.items = items;
	}

	private int total_items;
	private int total_pages;
	private int items_per_page;
	private int current_page;
	private List<Member> items;
	
	MemberQueryJSONResult()
	{
		
	}
	
}
