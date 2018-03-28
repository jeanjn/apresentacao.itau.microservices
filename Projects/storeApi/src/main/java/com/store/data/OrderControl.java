package com.store.data;

import java.util.ArrayList;

public class OrderControl {
	private static final ArrayList<Order> orders = new ArrayList<Order>();
	
	public static void addOrder(String name, String productName) {
		Order order = new Order();
		order.setClient(name);
		order.setProduct(productName);
		
		orders.add(order);
	}
	
	public static void addOrder(Order order) {
		orders.add(order);
	}
	
	
	
	public static ArrayList<Order> get(){
		return orders;
	}
	
	public static int size() {
		return orders.size();
	}
}
