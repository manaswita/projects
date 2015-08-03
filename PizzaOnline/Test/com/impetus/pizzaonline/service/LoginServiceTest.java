package com.impetus.pizzaonline.service;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import junit.framework.Assert;

import org.junit.Test;

import com.impetus.pizzaonline.model.Customer;
import com.impetus.pizzaonline.model.Staff;

public class LoginServiceTest {

	@Test
	public void testValidateUser() {
		LoginServiceImpl user = new LoginService();
		
		String role="Customer";
		String userName="nimmisasimenon";
		String password="nimmi123";
		assertNotNull("Result1",user.validateUser(role, userName, password));
	}

	@Test
	public void testInsertStaffUser() {
		LoginServiceImpl user = new LoginService();
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
		Assert.assertNotNull("Result ",user.insertStaffUser(staff));
		
	}

	@Test
	public void testInsertCustUser() {
		LoginServiceImpl user = new LoginService();
		Customer cust =new Customer();
		
		cust.setEmail("nimmisasimenon@gmail.com");
		cust.setName("Nimmi");
		cust.setUserName("nimmisasimenon");
		cust.setPassword("nimmi123");
		Assert.assertNotNull("Result ",user.insertCustUser(cust));
	}

	@Test
	public void testGetStaffString() {
		String name="NIMMI";
		LoginServiceImpl user = new LoginService();
		Assert.assertNotNull("Result ",user.getStaff(name));
	}

	@Test
	public void testRemoveStaff() {
		Staff staff = new Staff();
		LoginServiceImpl user = new LoginService();
		List<Staff> staffList = new ArrayList<Staff>();
		String name="NITASHA";
		staffList =user.getStaff(name);
		staff=staffList.get(0);
		staff.setAct_ind('N');
		Assert.assertNotNull("Result ",user.removeStaff(staff));
	}

	@Test
	public void testGetCustomerLong(){
		LoginServiceImpl user = new LoginService();
		String mobile ="8050519930";
		Assert.assertNotNull("Result ",user.getCustomer(Long.parseLong(mobile)));
	}

	@Test
	public void testInsertBPOCust() {
		LoginServiceImpl user = new LoginService();
		Customer cust =new Customer();
		
		cust.setEmail("kavya@gmail.com");
		cust.setName("Kavya");
		cust.setUserName("guestUser");
		cust.setPassword("guestuser");
		cust.setMobile(Long.parseLong("8844556678"));
		cust.setCity("BANGALORE");
		cust.setPin(560089);
		cust.setPlace("KORAMANGALA");
		Assert.assertNotNull("Result ",user.insertBPOCust(cust));
	}

	@Test
	public void testGetStaff() {
		LoginServiceImpl user = new LoginService();
		Assert.assertNotNull("Result ",user.getStaff());
	}

	@Test
	public void testGetCustomerString() {
		LoginServiceImpl user = new LoginService();
		String userName="nimmisasimenon";
		Assert.assertNotNull("Result ",user.getCustomer(userName));
	}

}
