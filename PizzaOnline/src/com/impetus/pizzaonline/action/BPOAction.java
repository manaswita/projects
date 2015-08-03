package com.impetus.pizzaonline.action;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.log4j.Logger;

import com.impetus.pizzaonline.constants.ApplConstants;
import com.impetus.pizzaonline.model.Customer;
import com.impetus.pizzaonline.model.User;
import com.impetus.pizzaonline.service.LoginService;
import com.impetus.pizzaonline.service.LoginServiceImpl;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

/**
 * This class contains the actions that are related to the BPO user.
 * 
 * @author nimmi.menon
 *
 *
 */
public class BPOAction extends ActionSupport {
	
	
private static final Logger LOGGER = Logger.getLogger(BPOAction.class);

private String mobile;
private String email;
private String name;
private String address;
private String flatNo;
private String bldgName;
private String lane;
private String area;
private String city;
private double pin;
Map<String,Object> session;
private String userName="guestUser";
private String password="guestuser";


//public getters and setters
public String getLane() {
	return lane;
}
public void setLane(String lane) {
	this.lane = lane;
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
public String getArea() {
	return area;
}
public void setArea(String area) {
	this.area = area;
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
public String getMobile() {
	return mobile;
}
public void setMobile(String mobile) {
	this.mobile = mobile;
}
public String getEmail() {
	return email;
}
public void setEmail(String email) {
	this.email = email;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public String getAddress() {
	return address;
}
public void setAddress(String address) {
	this.address = address;
}

/**
 * this methods validates if the user is present in the database using his mobile number.
 * 
 * 
 * @return a String for struts action
 * @throws IllegalAccessException
 * @throws InvocationTargetException
 */
public String validateCust() throws IllegalAccessException, InvocationTargetException{
	
	LoginServiceImpl getCust= new LoginService();
	session = ActionContext.getContext().getSession();
	
	
	ArrayList<Customer> custList = (ArrayList<Customer>) getCust.getCustomer(Long.parseLong(mobile));
	User user =new User();
	Customer customer = new Customer();
	
	
	if (custList!=null && custList.size()!=0){
		addActionMessage("Customer Available");
		customer=custList.get(0);
		BeanUtils.copyProperties(user, customer);
		LOGGER.info("In validateCust,BPOAction...successful validation");
		session.put("customer", user);
		
		return "success";
	}	
		else
		{
			
		addActionError(ApplConstants.USER_ERROR);
		LOGGER.error("In validateCust,BPOAction...unsuccessful validation");
		return "error";
		}
		
	}

/**
 * sets the customer object to send to the DAO class for saving in the database
 * 
 * @return a STring for Struts action
 * @throws IllegalAccessException
 * @throws InvocationTargetException
 */
public String registerCust() throws IllegalAccessException, InvocationTargetException {
	User user =new User();
	Customer customer = new Customer();
	boolean register = false;
	LoginServiceImpl newLogin = new LoginService();
	customer.setName(this.name.toLowerCase());
	customer.setFlatNo(this.flatNo.toLowerCase());
	customer.setBldgName(this.bldgName.toLowerCase());
	customer.setRoad(this.lane.toLowerCase());
	customer.setPlace(this.area.toLowerCase());
	customer.setCity(this.city.toLowerCase());
	if(mobile!=null && !mobile.equals("")){
	customer.setMobile(Long.parseLong(this.mobile));
	}
	else
	{
		customer.setMobile(0);
	}
	customer.setEmail(this.email.toLowerCase());
	customer.setUserName(this.userName.toLowerCase());
	customer.setPassword(this.password.toLowerCase());
	customer.setPin(this.pin);
	register = newLogin.insertBPOCust(customer);
	
	if (register == true) {
		
		
		user.setRole("Customer");
		BeanUtils.copyProperties(user, customer);
		session = ActionContext.getContext().getSession();
		session.put("customer", user);
		addActionMessage(ApplConstants.SUCCESS);
		return "success";
	} else {
		addActionError(ApplConstants.ERROR);
		return "errorMsg";
	}

}
	
}



