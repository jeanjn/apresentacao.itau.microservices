package com.storeapi2.models;

import java.util.Date;

public class Order {
	private String product;
	private String client;
	private Date date;
	
	public Order() {
		this.date = new Date();
	}
	
	public String getProduct() {
		return product;
	}
	public void setProduct(String product) {
		this.product = product;
	}
	public String getClient() {
		return client;
	}
	public void setClient(String client) {
		this.client = client;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
}
