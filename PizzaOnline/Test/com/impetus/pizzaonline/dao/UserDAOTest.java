package com.impetus.pizzaonline.dao;





import java.util.ArrayList;
import java.util.List;

import junit.framework.Assert;

import org.junit.Test;

import com.impetus.pizzaonline.model.Customer;
import com.impetus.pizzaonline.model.Staff;

public class UserDAOTest {

	@Test
	public void testValidateUser() {
		UserDAO userDAO = new UserDAO();
		Assert.assertNotNull("Result ",userDAO.validateUser("Customer", "impetus", "impetus"));
		Assert.assertNotNull("Result ",userDAO.validateUser("Staff", "impetus", "impetus"));
	}

	@Test
	public void testInsertCustUser() {
		UserDAO userDAO = new UserDAO();
		Customer cust =new Customer();
		
		cust.setEmail("nimmisasimenon@gmail.com");
		cust.setName("Nimmi");
		cust.setUserName("nimmisasimenon");
		cust.setPassword("nimmi123");
		Assert.assertNotNull("Result ",userDAO.insertCustUser(cust));
	}
	@Test
	public void testInsertStaffUser(){
		UserDAO userDAO = new UserDAO();
		Staff staff = new Staff();
		
		staff.setAct_ind('Y');
		staff.setCity("BANGALORE");
		staff.setDob("20/03/1987");
		staff.setEmail("nitasha@gmail.com");
		staff.setLocation("INDIRANAGAR");
		staff.setMobile(987656789);
		staff.setName("NITASHA");
		staff.setPassword("nitasha");
		staff.setRole("BPO");
		staff.setUserName("bpo_nitasha");
		Assert.assertNotNull("Result ",userDAO.insertStaffUser(staff));
		
		
		
	}
	@Test
	public void testRemoveStaff(){
		
		UserDAO userDAO = new UserDAO();
		Staff staff = new Staff();
		
		List<Staff> staffList = new ArrayList<Staff>();
		String name="NITASHA";
		staffList =userDAO.getStaff(name);
		staff=staffList.get(0);
		staff.setAct_ind('N');
		Assert.assertNotNull("Result ",userDAO.removeStaff(staff));
	}
	@Test
	public void testGetStaffString(){
		
		UserDAO userDAO = new UserDAO();
		
		String name="NIMMI";
		Assert.assertNotNull("Result ",userDAO.getStaff(name));
	}
	
	@Test
	public void testGetStaff(){
		
		
		UserDAO userDAO = new UserDAO();
		
		Assert.assertNotNull("Result ",userDAO.getStaff());
	}
	@Test
	public void testGetCustomer(){
		
		UserDAO userDAO = new UserDAO();
		
		String mobile ="8050519930";
		Assert.assertNotNull("Result ",userDAO.getCustomer(Long.parseLong(mobile)));
		
		
		
		
	}
	@Test
	public void testGetCustomerSting(){
		
		UserDAO userDAO = new UserDAO();
		
		String userName="nimmisasimenon";
		Assert.assertNotNull("Result ",userDAO.getCustomer(userName));
	}
	@Test
	public void testInsertBPOCust(){
		
		UserDAO userDAO = new UserDAO();
		Customer cust =new Customer();
		
		cust.setEmail("kavya@gmail.com");
		cust.setName("Kavya");
		cust.setUserName("guestUser");
		cust.setPassword("guestuser");
		cust.setMobile(Long.parseLong("8844556678"));
		cust.setCity("BANGALORE");
		cust.setPin(560089);
		cust.setPlace("KORAMANGALA");
		Assert.assertNotNull("Result ",userDAO.insertBPOCust(cust));
	}
}
