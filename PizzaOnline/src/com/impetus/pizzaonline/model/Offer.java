package com.impetus.pizzaonline.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "OFFER")
public class Offer {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO )
	@Column (name="OFR_ID")
private int id;
	@Column(name="COUPON_CODE")
private String couponCode;
	@Column(name="DESCRIPTION")
private String description;
	@Column(name="START_DATE")
private String startDate;
	@Column(name="END_DATE")
private String endDate;
	@Column(name="ACT_IND")
private char actInd;
	@Column(name="VALID_DAYS")
private String validDays;
	@Column(name="MIN_BILL")
	private double minBill;
	@Column(name="DISCOUNT")
private double discount;


public double getDiscount() {
		return discount;
	}
	public void setDiscount(double discount) {
		this.discount = discount;
	}
public double getMinBill() {
		return minBill;
	}
	public void setMinBill(double minBill) {
		this.minBill = minBill;
	}
public String getValidDays() {
	return validDays;
}
public void setValidDays(String validDays) {
	this.validDays = validDays;
}
public char getActInd() {
	return actInd;
}
public void setActInd(char actInd) {
	this.actInd = actInd;
}
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}

public String getCouponCode() {
	return couponCode;
}
public void setCouponCode(String couponCode) {
	this.couponCode = couponCode;
}
public String getDescription() {
	return description;
}
public void setDescription(String description) {
	this.description = description;
}
public String getStartDate() {
	return startDate;
}
public void setStartDate(String startDate) {
	this.startDate = startDate;
}
public String getEndDate() {
	return endDate;
}
public void setEndDate(String endDate) {
	this.endDate = endDate;
}



}
