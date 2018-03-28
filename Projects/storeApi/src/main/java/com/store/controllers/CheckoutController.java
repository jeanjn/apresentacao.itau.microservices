package com.store.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.store.data.Order;
import com.store.data.OrderControl;

@CrossOrigin
@RestController
@RequestMapping(CheckoutController.URI)
public class CheckoutController {
	public static final String URI = "storeapi/v1/checkout";

	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<?> buyProduct(@RequestBody Order order) {

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
		
		//FORÇAR QUEDA
		//Supomos que o servidor atingiu o seu limite de requisições
		// e comecou a ficar lento, nao consegue responder ninguem 
		// demora pra responder e quando responde é 500
		if(OrderControl.size() > 4) {
			try {
				Thread.sleep(10000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}

		OrderControl.addOrder(order);
		return ResponseEntity.ok(order);
	}
}
