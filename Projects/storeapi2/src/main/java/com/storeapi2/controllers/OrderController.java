package com.storeapi2.controllers;

import java.util.ArrayList;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.storeapi2.domains.OrderDomain;
import com.storeapi2.helpers.RouteHelper;
import com.storeapi2.models.Order;

@CrossOrigin
@RestController
@RequestMapping(RouteHelper.API_URL + "orders")
public class OrderController {
	OrderDomain domain;
	
	public OrderController() {
		domain = new OrderDomain();

	}
	
	@RequestMapping()
	public ArrayList<Order> getOrders() {
		return domain.getAll();
	}
}