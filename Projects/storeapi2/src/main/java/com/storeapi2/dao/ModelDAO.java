package com.storeapi2.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ModelDAO<T> {

	private static Map<String, Object> DB_MOCK = new HashMap<String, Object>();
	private String key;
	
	private ArrayList<T> models;
		
	@SuppressWarnings("unchecked")
	public ModelDAO(Class<T> type) {
		key = type.getName();
		
		
		if(DB_MOCK.containsKey(key)) {
			models = (ArrayList<T>) DB_MOCK.get(key);
		}else {
			models = new ArrayList<T>();
			updateDataBase();
		}
	}
	
	public void save(T model) {
		models.add(model);
		updateDataBase();
	}
	
	public ArrayList<T> getAll() {
		return getModels();
	}
	
	public T get(int index) {
		return getModels().get(index);
	}
	
	public void remove(int index) {
		getModels().remove(index);
		updateDataBase();
	}
	
	private void updateDataBase() {
		DB_MOCK.put(key, models);
	}
	
	@SuppressWarnings("unchecked")
	private ArrayList<T> getModels(){
		return (ArrayList<T>)  DB_MOCK.get(key);
	}
}
