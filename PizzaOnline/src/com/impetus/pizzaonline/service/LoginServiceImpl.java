package com.impetus.pizzaonline.service;

import java.util.List;

import com.impetus.pizzaonline.dao.UserDAO;
import com.impetus.pizzaonline.model.Customer;
import com.impetus.pizzaonline.model.Item;
import com.impetus.pizzaonline.model.Staff;
import com.impetus.pizzaonline.model.User;

/**
 * 
 * this interface declares all the methods used in LoginService class
 * @author nimmi.menon
 *
 */
public interface LoginServiceImpl {
	
	public User validateUser(String role,String userName,String password);
	public boolean insertStaffUser(Staff staff);
	public boolean insertCustUser(Customer customer);
	public List<Staff> getStaff(String name);
	public boolean removeStaff(Staff staff);
	public List<Customer> getCustomer(Long mobile);
	public boolean insertBPOCust(Customer customer);
	public List<Staff> getStaff();
	public List<Customer> getCustomer(String userName);
}
