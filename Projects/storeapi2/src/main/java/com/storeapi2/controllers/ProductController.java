package com.storeapi2.controllers;

import java.util.ArrayList;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.storeapi2.domains.ProductDomain;
import com.storeapi2.helpers.RouteHelper;
import com.storeapi2.models.Product;

@CrossOrigin
@RestController
@RequestMapping(RouteHelper.API_URL + "products")
public class ProductController {
	ProductDomain domain;
	
	public ProductController() {
		domain = new ProductDomain();

	}
	
	@RequestMapping()
	public ArrayList<Product> getProducts() {
		return domain.getAll();
	}
	
	@RequestMapping(value = "{id}")
	public Product getProduct(@PathVariable int id) {
		return domain.get(id);
	}                                                                                         
}