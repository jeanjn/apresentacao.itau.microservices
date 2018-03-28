package com.store.controllers;

import java.util.ArrayList;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.store.models.Product;

@CrossOrigin
@RestController
@RequestMapping(ProductController.URI)
public class ProductController {
	public static final String URI = "storeapi/v1/products";
	
	ArrayList<Product> products = new ArrayList<Product>() ;
	
	public ProductController() {
		products.add(
				new Product(0, "Caixa de bombom", 7,
						"Caixa de bombom nestlê, muito utilizada para comemorar bons momentos e demonstrar APIs!",
						"https://www.paodeacucar.com/img/uploads/1/456/529456.jpg?type=product"));
		
	}

	@RequestMapping()
	public ArrayList<Product> getProducts() {
		return products;
	}
	
	@RequestMapping(value = "{id}")
	public Product getProduct(@PathVariable int id) {
		return products.get(id);
	}
}
