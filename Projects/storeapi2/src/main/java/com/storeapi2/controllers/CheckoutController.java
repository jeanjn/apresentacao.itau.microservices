package com.storeapi2.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.storeapi2.domains.OrderDomain;
import com.storeapi2.helpers.RouteHelper;
import com.storeapi2.models.Order;

@CrossOrigin
@RestController
@RequestMapping(RouteHelper.API_URL + "checkout")
public class CheckoutController {
	private OrderDomain domain;
	
	public CheckoutController() {
		domain = new OrderDomain();
	}
	
	

	@SuppressWarnings("rawtypes")
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity buyProduct(@RequestBody Order order) {

		if(order == null) {
			return ResponseEntity.badRequest().build();
		}
		
		if (order.getClient() == null || order.getClient().length() == 0) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body("Nome do cliente é obrigatório");
		}

		if (order.getProduct() == null || order.getProduct().length() == 0) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body("Nome do produto é obrigatório");
		}
		
		domain.save(order);
		return ResponseEntity.ok(order);
	}
}
