package com.impetus.pizzaonline.service;

import java.util.List;

import com.impetus.pizzaonline.model.Offer;
import com.impetus.pizzaonline.model.Order;

/**
 * 
 * this interface declares all the methods used in OrderService class
 * @author nimmi.menon
 *
 */
public interface OrderServiceImpl {
	public boolean insertOrder(Order order);
	public List<Order> getUserHistory(int userId);
	public List<Order> getCustomerOrder();
	
}
