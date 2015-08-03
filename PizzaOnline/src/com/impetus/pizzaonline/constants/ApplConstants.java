package com.impetus.pizzaonline.constants;

import java.util.Properties;

import org.apache.log4j.Logger;

import com.impetus.pizzaonline.util.PizzaOnlineProperties;

public class ApplConstants {

	private static final Logger LOGGER = Logger.getLogger(ApplConstants.class);

	public final static String DUPLICATE_USER = "User already exists";
	public final static String INTRENAL_ERROR = "Internal Error";
	public final static String EMPTY_STRING = " ";
	public final static String ACT_IND_Y = "Y";
	public final static String MONDAY = "MONDAY";
	public final static String TUESDAY = "TUESDAY";
	public final static String WEDNESDAY = "WEDNESDAY";
	public final static String THURSDAY = "THURSDAY";
	public final static String FRIDAY = "FRIDAY";
	public final static String SATURDAY = "SATURDAY";
	public final static String SUNDAY = "SUNDAY";
	public final static double TAX = 0.12;
	public final static String ERROR_SIZE = "Please select quantity and size!!";
	public final static String ERROR_QUANT = "Please select quantity !!";
	public final static String ERROR_OFFER = "Offer is not applicable.please check coupon number , min bill and valid days!!";
	public final static String SIZE_NA = "requested size not available!!!";
	public final static String USER_ERROR = "user does not exist!";
	public final static String ERROR = "error occurred";
	public static final String PROPERTY_LOCATION = "properties/pizza.properties"; 
	public final static String ITEM_SUCCESS = "Item Added successfully!!";
	public final static String ITMRMV_SUCC = "Item Removed successfully!!";
	public final static String DUPLICATE_ITEM = "ITEM already exists";
	public final static String STAFF_SUCCESS = "Staff Added successfully!!";
	public final static String STAFF_REMOVE = " Staff Removed successfully";
	public final static String SUCCESS = "Successfull!!";
	public final static double HOMEDELIVERY_CHARGES = getDelivery();
	public final static String DAY_MSG="Please enter the days!!";
	public final static String DUPLICATE_OFFER="Offer Exists!!";
	public static double getDelivery() {
		double charges = 0;
		
		PizzaOnlineProperties pizzaProps = new PizzaOnlineProperties(); 

		pizzaProps.loadProperty();
		final String delivery = pizzaProps.getHomeDeliveryCharges();

		

		charges = Double.parseDouble(delivery);
		LOGGER.info("Home Delivery Charges -------------------- "+charges);
		return charges;
	}
	
	public static double getTax() {
		double taxRate = 0;
		
		PizzaOnlineProperties pizzaProps = new PizzaOnlineProperties();

		pizzaProps.loadProperty();
		final String tax = pizzaProps.getTaxRate();

		

		taxRate = Double.parseDouble(tax);
		LOGGER.info("Home Delivery Charges -------------------- "+taxRate);
		return taxRate;
	}
}
