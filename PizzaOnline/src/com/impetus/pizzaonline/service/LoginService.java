package com.impetus.pizzaonline.service;

import java.util.ArrayList;
import java.util.List;

import com.impetus.pizzaonline.dao.ItemDAO;
import com.impetus.pizzaonline.dao.UserDAO;
import com.impetus.pizzaonline.model.Customer;
import com.impetus.pizzaonline.model.Item;
import com.impetus.pizzaonline.model.Staff;
import com.impetus.pizzaonline.model.User;
/**
 * this class acts like a service class to manipulate the objects received from the DAO class and render them to the action class.
 * 
 * 
 * @author nimmi.menon
 *
 *
 */
public class LoginService implements LoginServiceImpl{
public User validateUser(String role,String userName,String password){
	User user = new User();
	
	UserDAO loginDao = new UserDAO();
	
	user=loginDao.validateUser(role, userName, password);
	return user;
}
public boolean insertStaffUser(Staff staff){
	
	boolean register=false;
	UserDAO newLogin = new UserDAO();
	
	register=newLogin.insertStaffUser(staff);
	return register;
}
public boolean insertCustUser(Customer customer){
	
	boolean register=false;
	UserDAO newLogin = new UserDAO();
	
	register=newLogin.insertCustUser(customer);
	return register;
}
public List<Staff> getStaff(String name){
	
	UserDAO staffdao=new UserDAO();
	ArrayList<Staff> staffList = (ArrayList<Staff>) staffdao.getStaff(name);	
	return staffList;
}
public boolean removeStaff(Staff staff){
	
	boolean remove=false;
	UserDAO newItem = new UserDAO();
	
	remove=newItem.removeStaff(staff);
	return remove;
	
}
public List<Customer> getCustomer(Long mobile){
	
	UserDAO custdao=new UserDAO();
	ArrayList<Customer> custList = (ArrayList<Customer>) custdao.getCustomer(mobile);	
	return custList;
}
public boolean insertBPOCust(Customer customer){
	
	boolean register=false;
	UserDAO newLogin = new UserDAO();
	
	register=newLogin.insertBPOCust(customer);
	return register;
}
public List<Staff> getStaff(){
	
	UserDAO staffdao=new UserDAO();
	ArrayList<Staff> staffList = (ArrayList<Staff>) staffdao.getStaff();	
	return staffList;
	
}
public List<Customer> getCustomer(String userName){
	
	UserDAO custdao=new UserDAO();
	ArrayList<Customer> customerList = (ArrayList<Customer>) custdao.getCustomer(userName);	
	return customerList;
	
}
}
