package com.impetus.pizzaonline.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table (name="STAFF")
public class Staff {
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE)
	@Column (name="STAFF_ID")
private int id;
	@Column (name="STAFF_NAME")
private String name;
	@Column (name="DESIGNATION")
private String role;
	@Column (name="STAFF_USERNAME")
private String userName;
	@Column (name="STAFF_PASSWORD")
private String password;
	@Column (name="BIRTHDATE")
private String dob;
	@Column (name="LOCATION")
private String location;
	@Column (name="MOBILE")
private double mobile;
	@Column (name="EMAIL_ID")
private String email;
	@Column (name="ACT_IND")
private char act_ind;
	@Column (name="CITY")
private String city;
public String getCity() {
	return city;
}


public void setCity(String city) {
	this.city = city;
}


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
public String getRole() {
	return role;
}
public void setRole(String role) {
	this.role = role;
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
public String getDob() {
	return dob;
}
public void setDob(String dob) {
	this.dob = dob;
}
public String getLocation() {
	return location;
}
public void setLocation(String location) {
	this.location = location;
}
public double getMobile() {
	return mobile;
}
public void setMobile(double mobile) {
	this.mobile = mobile;
}
public String getEmail() {
	return email;
}
public void setEmail(String email) {
	this.email = email;
}
public char getAct_ind() {
	return act_ind;
}
public void setAct_ind(char act_ind) {
	this.act_ind = act_ind;
}

}
