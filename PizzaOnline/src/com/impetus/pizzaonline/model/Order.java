package com.impetus.pizzaonline.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table (name="CUST_ORD_HIST")
public class Order {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column (name="ORDER_ID")
private int id;
	@Column (name="CUST_ID")
private int cust_id;
	@Column (name="STAFF_STAFF_ID")
private int staff_id;
	@Column (name="OFR_ID")
private int ofr_id;
	@Column (name="ITEM_NAMES")
private String itemNames;
	@Column (name="TOTAL_ITEMS")
private int totalItems;
	@Column (name="ITEM_PRICE")
private double itemPrice;
	@Column (name="TAX")
private double tax;
	@Column (name="TOTAL_AMOUNT")
private double totalAmount;
	@Column(name="ORDER_DATE")
private String orderDate;	
	@Column(name="ADDRESS")
private String orderAddress;
	@Column(name="DELIVERY")
private String delivery;


public String getDelivery() {
	return delivery;
}
public void setDelivery(String delivery) {
	this.delivery = delivery;
}
public String getOrderAddress() {
		return orderAddress;
	}
	public void setOrderAddress(String orderAddress) {
		this.orderAddress = orderAddress;
	}
public String getOrderDate() {
	return orderDate;
}
public void setOrderDate(String orderDate) {
	this.orderDate = orderDate;
}
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public int getCust_id() {
	return cust_id;
}
public void setCust_id(int cust_id) {
	this.cust_id = cust_id;
}
public int getStaff_id() {
	return staff_id;
}
public void setStaff_id(int staff_id) {
	this.staff_id = staff_id;
}
public int getOfr_id() {
	return ofr_id;
}
public void setOfr_id(int ofr_id) {
	this.ofr_id = ofr_id;
}
public String getItemNames() {
	return itemNames;
}
public void setItemNames(String itemNames) {
	this.itemNames = itemNames;
}
public int getTotalItems() {
	return totalItems;
}
public void setTotalItems(int totalItems) {
	this.totalItems = totalItems;
}
public double getItemPrice() {
	return itemPrice;
}
public void setItemPrice(double itemPrice) {
	this.itemPrice = itemPrice;
}
public double getTax() {
	return tax;
}
public void setTax(double tax) {
	this.tax = tax;
}
public double getTotalAmount() {
	return totalAmount;
}
public void setTotalAmount(double totalAmount) {
	this.totalAmount = totalAmount;
}

}
