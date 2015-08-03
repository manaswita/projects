package com.impetus.pizzaonline.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

import com.impetus.pizzaonline.action.UserAction;
import com.impetus.pizzaonline.constants.ApplConstants;
import com.impetus.pizzaonline.model.Item;
import com.impetus.pizzaonline.model.Order;
import com.impetus.pizzaonline.model.User;
import com.impetus.pizzaonline.util.HibernateUtil;

/**
 * this class deals with the all transactions related to the  order table using the order class
 *
 * @author nimmi.menon 
 */
public class OrderDAO {

	Map<String, Object> session;
	private final static Logger LOGGER = Logger.getLogger(UserAction.class);

	/**
	 * used to insert a new order into the order table
	 * 
	 * @param order
	 * @return boolean
	 */
	public boolean insertOrder(Order order) {
		Session ses = HibernateUtil.getSession();
		try {

			Transaction tx = ses.beginTransaction();
			ses.save(order);
			tx.commit();
			ses.flush();
			return true;

		} catch (Exception e) {
			LOGGER.error("error", e);
			e.printStackTrace();
			return false;

		}
	}

	/**
	 * to get the the orders done by a particular user
	 * 
	 * @param userId
	 * @return List of order objects
	 */
	public List<Order> getUserHistory(int userId) {

		Session ses = HibernateUtil.getSession();

		List<Order> item = new ArrayList<Order>();

		try {
			Criteria criteria = ses.createCriteria(Order.class);
			criteria.add(Restrictions.eq("cust_id", userId));

			item = (ArrayList<Order>) criteria.list();

		} catch (Exception e) {
			LOGGER.error("error", e);
			e.printStackTrace();
			
		}

		if (item != null) {
			return item;
		} else {

			return item = new ArrayList<Order>();
		}
	}

	/**
	 * 
	 * to get a list of all the orders by all customers
	 * 
	 * @return List
	 */
	public List<Order> getCustomerOrder() {

		Session ses = HibernateUtil.getSession();

		List<Order> item = new ArrayList<Order>();

		try {
			Criteria criteria = ses.createCriteria(Order.class);

			item = (ArrayList<Order>) criteria.list();

		} catch (Exception e) {
			LOGGER.error("error", e);
			e.printStackTrace();
			
		}

		if (item != null) {

			return item;
		} else {
			LOGGER.info("item=null here.....");
			return item = new ArrayList<Order>();
		}
	}
}
