package com.impetus.pizzaonline.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity 
@Table(name="CUSTOMER")
public class Customer {
@Id
@GeneratedValue(strategy=GenerationType.AUTO)
@Column (name="	CUST_ID")
private int id;
@Column (name="CUSTOMER_NAME")
private String name;
@Column (name="CUST_USERNAME")
private String userName;
@Column (name="CUST_PASSWORD")
private String password;
@Column (name="FLAT_NO_DOOR")
private String flatNo;
@Column (name="BLDG_NAME_BLDG_NO")
private String bldgName;
@Column (name="lANE_STREET_ROAD")
private String road;
@Column (name="PLACE")
private String place;
@Column (name="CITY")
private String city;
@Column (name="PIN")
private double pin;
@Column (name="MOBILE")
private long mobile;
@Column (name="EMAIL_ID")
private String email;


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
public String getUserName() {
	return userName;
}
public void setUserName(String userName) {
	this.userName = userName;
}
public String getPassword() {
	return password;
}
public void setPassword(String password) {
	this.password = password;
}
public String getFlatNo() {
	return flatNo;
}
public void setFlatNo(String flatNo) {
	this.flatNo = flatNo;
}
public String getBldgName() {
	return bldgName;
}
public void setBldgName(String bldgName) {
	this.bldgName = bldgName;
}
public String getRoad() {
	return road;
}
public void setRoad(String road) {
	this.road = road;
}
public String getPlace() {
	return place;
}
public void setPlace(String place) {
	this.place = place;
}
public String getCity() {
	return city;
}
public void setCity(String city) {
	this.city = city;
}
public double getPin() {
	return pin;
}
public void setPin(double pin) {
	this.pin = pin;
}
public long getMobile() {
	return mobile;
}
public void setMobile(long mobile) {
	this.mobile = mobile;
}
public String getEmail() {
	return email;
}
public void setEmail(String email) {
	this.email = email;
}

}
