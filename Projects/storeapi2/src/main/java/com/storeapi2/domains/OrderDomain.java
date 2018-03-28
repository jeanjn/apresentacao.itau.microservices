package com.storeapi2.domains;

import java.util.ArrayList;

import com.storeapi2.dao.ModelDAO;
import com.storeapi2.models.Order;

public class OrderDomain {
	private ModelDAO<Order> dao;
	
	public OrderDomain() {
		dao = new ModelDAO<Order>(Order.class);
	}
	
	public void save(Order order) {
		dao.save(order);
	}
	
	public ArrayList<Order> getAll() {
		return dao.getAll();
	}
}
