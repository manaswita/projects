package com.impetus.pizzaonline.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table (name="TOPPING")
public class Topping {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
@Column (name="TPG_ID")
private int id;
@Column (name="TOPPING_NAME")
private String name;
@Column (name="TOPPING_CATEGORY")
private String category;
@Column (name="TOPPING_TYPE")
private String type;
@Column (name="ACT_IND")
private char act_ind;
@Column (name="PRICE")
private String price;

public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public String getCategory() {
	return category;
}
public void setCategory(String category) {
	this.category = category;
}
public String getType() {
	return type;
}
public void setType(String type) {
	this.type = type;
}
public char getAct_ind() {
	return act_ind;
}
public void setAct_ind(char act_ind) {
	this.act_ind = act_ind;
}
public String getPrice() {
	return price;
}
public void setPrice(String price) {
	this.price = price;
}
/*
@Override public String toString() {
    StringBuilder result = new StringBuilder();
      
    result.append(this.name + "" + this.price );
        return result.toString();
  }*/

}
