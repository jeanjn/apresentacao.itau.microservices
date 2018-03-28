package com.storeapi2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.storeapi2.App;
import com.storeapi2.dao.ModelDAO;
import com.storeapi2.models.Product;

@SpringBootApplication
public class App {
	public static void main(String[] args) {
		SpringApplication.run(App.class, args);

		ModelDAO<Product> productDao = new ModelDAO<Product>(Product.class);
		productDao.save(new Product(0, "Caixa de bombom", 7,
				"Caixa de bombom nestlê, muito utilizada para comemorar bons momentos e demonstrar APIs!",
				"https://www.paodeacucar.com/img/uploads/1/456/529456.jpg?type=product"));
	}
}
