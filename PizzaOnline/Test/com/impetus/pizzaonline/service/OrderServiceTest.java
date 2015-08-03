package com.impetus.pizzaonline.service;

import static org.junit.Assert.*;

import org.junit.Test;

import com.impetus.pizzaonline.dao.OrderDAO;
import com.impetus.pizzaonline.model.Order;

public class OrderServiceTest {

	@Test
	public void testInsertOrder() {
		Order order = new Order();
		OrderServiceImpl orderSrv = new OrderService();
		order.setCust_id(796);
		order.setId(110);
		order.setDelivery("Home");
		order.setItemNames("PIZZA SPICY CHICKEN");
		order.setItemPrice(900);
		order.setOfr_id(101);
		order.setOrderAddress("pritech park,ecospace,bellandur,bangalore,Pin:560103.0");
		order.setOrderDate("04/05/2012");
		order.setStaff_id(101);
		order.setTax(108);
		order.setTotalAmount(1058);
		order.setTotalItems(2);
		
		assertTrue("Result 1", orderSrv.insertOrder(order) );
	}

	@Test
	public void testGetUserHistory() {
		OrderServiceImpl orderSrv = new OrderService();
		
		int custId1=1;
		int custId2=120;
		assertNotNull("Result1", orderSrv.getUserHistory(custId1));
		assertNotNull("Result2",orderSrv.getUserHistory(custId2));
	}

	@Test
	public void testGetCustomerOrder() {
		OrderServiceImpl orderSrv = new OrderService();
		
		assertNotNull("Result1", orderSrv.getCustomerOrder());
	}

}
