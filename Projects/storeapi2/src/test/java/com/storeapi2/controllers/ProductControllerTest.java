package com.storeapi2.controllers;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.storeapi2.dao.ModelDAO;
import com.storeapi2.models.Product;

@RunWith(SpringRunner.class)
@WebMvcTest(value = ProductController.class, secure = false)
public class ProductControllerTest {
	Gson gson;

	Product productTest;

	@Autowired
	private MockMvc mockMvc;

	@Before
	public void setUp() throws Exception {
		gson = new GsonBuilder().create();

		productTest = new Product(0, "Caixa de bombom", 7,
				"Caixa de bombom nestlê, muito utilizada para comemorar bons momentos e demonstrar APIs!",
				"https://www.paodeacucar.com/img/uploads/1/456/529456.jpg?type=product");

		ModelDAO<Product> productDao = new ModelDAO<Product>(Product.class);
		productDao.save(productTest);
	}

	@Test
	public void getProductsTest() throws Exception {
		RequestBuilder requesBuilder = MockMvcRequestBuilders.get("/storeapi/v2/products")
				.accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requesBuilder).andReturn();
		String jsonResult = result.getResponse().getContentAsString();
		Product[] products = gson.fromJson(jsonResult, Product[].class);

		assertTrue(products.length > 0);
	}

	@Test
	public void getProductTest() throws Exception {
		RequestBuilder requesBuilder = MockMvcRequestBuilders.get("/storeapi/v2/products/0")
				.accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requesBuilder).andReturn();
		String jsonResult = result.getResponse().getContentAsString();
		Product product = gson.fromJson(jsonResult, Product.class);

		assertTrue(product.getName().equals(productTest.getName()));
	}

	@Test
	public void requestOverFlowTest() throws Exception {
		int countFail = 0;
		int countSuccess = 0;
		int count = 0;

		while (count < 1000) {
			RequestBuilder requesBuilder = MockMvcRequestBuilders.get("/storeapi/v2/products/0")
					.accept(MediaType.APPLICATION_JSON);

			MvcResult result = mockMvc.perform(requesBuilder).andReturn();
			count++;
			
			if(result.getResponse().getStatus() != HttpStatus.OK.value()) {
				countFail++;
			}else {
				countSuccess++;
			}
			

		}
		String text = String.format("%s sucesso e %s falhas", countSuccess, countFail);
		System.out.println(text);
		assertTrue(text, count == 1000);
	}
	
	@Test
	public void waitTimeRequestTest() throws Exception {
		int countFail = 0;
		int countSuccess = 0;
		int count = 0;
		int countTest = 0;

		while (countTest < 5) {
			RequestBuilder requesBuilder = MockMvcRequestBuilders.get("/storeapi/v2/products/0")
					.accept(MediaType.APPLICATION_JSON);

			MvcResult result = mockMvc.perform(requesBuilder).andReturn();
			count++;
			
			if(result.getResponse().getStatus() != HttpStatus.OK.value()) {
				countFail++;
			}else {
				countSuccess++;
			}
			
			System.out.println(String.format("%s sucesso e %s falhas", countSuccess, countFail));
			
			if(count == 4) {
				Thread.sleep(60000 * 6);
				count = 0;
				countTest++;
			}
			
			
		}
		String text = String.format("%s sucesso e %s falhas", countSuccess, countFail);
		System.out.println(text);
		assertTrue(countFail == 0);
	}
}
