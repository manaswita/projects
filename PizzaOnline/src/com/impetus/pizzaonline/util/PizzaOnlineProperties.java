package com.impetus.pizzaonline.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Properties;

import org.apache.log4j.Logger;

import com.impetus.pizzaonline.action.UserAction;
import com.impetus.pizzaonline.constants.ApplConstants;

/**
 * to load the properties file into the system
 * @author nimmi.menon
 *
 */
public class PizzaOnlineProperties {
	/**
	 * Declaring the neccessary variables
	 */
	private final static Logger LOGGER = Logger.getLogger(PizzaOnlineProperties.class);
	
	private String username;

	private String password;

	private String smtpAuth;
	
	private String tlsEnable;
	
	private String hostName;
	
	private String portNumber;
	
	private String homeDeliveryCharges;
	
	private String taxRate;
	
	/**
	 * Method Name : loadProperty Method Description : This method loads the
	 * value from the properties file.
	 * 
	 */
	public void loadProperty() {
		Properties properties = new Properties();
		try {
				
			properties.load(Thread.currentThread().getContextClassLoader().getResourceAsStream(ApplConstants.PROPERTY_LOCATION));
				
			/* Assigning the Properties to variables */
			username = properties.getProperty("mail.username");
			password = properties.getProperty("mail.password");
			smtpAuth =  properties.getProperty("mail.smtp.auth");
			tlsEnable =  properties.getProperty("mail.smtp.starttls.enable");
			hostName = properties.getProperty("mail.smtp.host");
			portNumber = properties.getProperty("mail.smtp.port");
			homeDeliveryCharges = properties.getProperty("pizza.homedelivery.charges");
			taxRate = properties.getProperty("pizza.taxRate");

		}

		catch (FileNotFoundException e) {
			LOGGER.error("error",e);
		} catch (Exception e) {
			LOGGER.error("error",e);
		}
	}

	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getSmtpAuth() {
		return smtpAuth;
	}
	public void setSmtpAuth(String smtpAuth) {
		this.smtpAuth = smtpAuth;
	}
	public String getTlsEnable() {
		return tlsEnable;
	}
	public void setTlsEnable(String tlsEnable) {
		this.tlsEnable = tlsEnable;
	}
	public String getHostName() {
		return hostName;
	}
	public void setHostName(String hostName) {
		this.hostName = hostName;
	}
	public String getPortNumber() {
		return portNumber;
	}
	public void setPortNumber(String portNumber) {
		this.portNumber = portNumber;
	}
	public String getHomeDeliveryCharges() {
		return homeDeliveryCharges;
	}

	public void setHomeDeliveryCharges(String homeDeliveryCharges) {
		this.homeDeliveryCharges = homeDeliveryCharges;
	}
	public String getTaxRate() {
		return taxRate;
	}

	public void setTaxRate(String taxRate) {
		this.taxRate = taxRate;
	}
}
