package com.impetus.pizzaonline.service;

import java.util.List;

/**
 * this class is a POJO which is used for the itemService class.
 * @author nimmi.menon
 *
 */
public class MapValObj {

	
	public String name;
	public List<String> values;
	public String type;
	public int id;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String description;
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<String> getValues() {
		return values;
	}
	public void setValues(List<String> values) {
		this.values = values;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

	
}
