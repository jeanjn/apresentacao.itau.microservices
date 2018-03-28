package com.store.controllers;

import java.util.ArrayList;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.store.data.Order;
import com.store.data.OrderControl;

@CrossOrigin
@RestController
@RequestMapping(OrderController.URI)
public class OrderController {
	public static final String URI = "storeapi/v1/orders";
	
	@RequestMapping()
	public ArrayList<Order> getOrders() {
		return OrderControl.get();
	}

}
