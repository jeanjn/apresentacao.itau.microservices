package com.storeapi2.models;

public class Product {
	private int id;
	private String name;
	private float price;
	private String description;
	private String imageUrl;
	
	public Product(int id, String name, float price, String description, String imageUrl) {
		this.id = id;
		this.name = name;
		this.price = price;
		this.description = description;
		this.imageUrl = imageUrl;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}

	public String getImage() {
		return imageUrl;
	}

	public void setImage(String image) {
		this.imageUrl = image;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
