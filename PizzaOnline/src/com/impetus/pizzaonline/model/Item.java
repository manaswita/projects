package com.impetus.pizzaonline.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
@Entity 
@Table(name="ITEM")
public class Item {
@Id
@GeneratedValue(strategy=GenerationType.AUTO)
@Column (name="ITEM_ID")
private int itemId;
@Column (name="ITEM_NAME")
private String itemName;
@Column (name="ITEM_CATEGORY")
private String itemCategory;
@Column (name="ITEM_SIZE")
private String itemSize;
@Column (name="ITEM_PRICE")
private String itemPrice;
@Column (name="TYPE")
private String type;
@Column (name="ITEM_DESCRIPTION")
private String itemDesc;
@Column (name="ACT_IND") 
private char actInd;
@OneToMany
private List<Topping>toppingList;



public List<Topping> getToppingList() {
	return toppingList;
}
public void setToppingList(List<Topping> toppingList) {
	this.toppingList = toppingList;
}
public int getItemId() {
	return itemId;
}
public void setItemId(int itemId) {
	this.itemId = itemId;
}
public String getItemName() {
	return itemName;
}
public void setItemName(String itemName) {
	this.itemName = itemName;
}
public String getItemCategory() {
	return itemCategory;
}
public void setItemCategory(String itemCategory) {
	this.itemCategory = itemCategory;
}
public String getItemSize() {
	return itemSize;
}
public void setItemSize(String itemSize) {
	this.itemSize = itemSize;
}
public String getItemPrice() {
	return itemPrice;
}
public void setItemPrice(String itemPrice) {
	this.itemPrice = itemPrice;
}
public String getType() {
	return type;
}
public void setType(String type) {
	this.type = type;
}
public String getItemDesc() {
	return itemDesc;
}
public void setItemDesc(String itemDesc) {
	this.itemDesc = itemDesc;
}
public char getActInd() {
	return actInd;
}
public void setActInd(char actInd) {
	this.actInd = actInd;
}


}
