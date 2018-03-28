package com.storeapi2.domains;

import java.util.ArrayList;

import com.storeapi2.dao.ModelDAO;
import com.storeapi2.models.Product;

public class ProductDomain {
	private ModelDAO<Product> dao;
	
	public ProductDomain() {
		dao = new ModelDAO<Product>(Product.class);

	}
	
	public ArrayList<Product> getAll(){
		return dao.getAll();
	}
	
	public Product get(int index) {
		return dao.get(index);
	}
}
