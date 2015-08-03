package com.impetus.pizzaonline.service;

import java.util.ArrayList;
import java.util.List;

import com.impetus.pizzaonline.dao.OrderDAO;
import com.impetus.pizzaonline.model.Item;
import com.impetus.pizzaonline.model.Order;
/**
 * this class acts like a service class to manipulate the objects received from the DAO class and render them to the action class.
 * @author nimmi.menon
 *
 *
 */
public class OrderService implements OrderServiceImpl {
public boolean insertOrder(Order order){
		
		boolean insert=false;
		OrderDAO newOrder = new OrderDAO();
		
		insert=newOrder.insertOrder(order);
		return insert;
	}

public List<Order> getUserHistory(int userId){
	
	OrderDAO order = new OrderDAO();
	ArrayList<Order> item = (ArrayList<Order>) order.getUserHistory(userId);
	return item;
}
public List<Order> getCustomerOrder(){
	
	OrderDAO order = new OrderDAO();
	ArrayList<Order> item = (ArrayList<Order>) order.getCustomerOrder();
	return item;
}
}
