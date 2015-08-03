package com.impetus.pizzaonline.util;

import java.text.DecimalFormat;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.log4j.Logger;

import com.impetus.pizzaonline.action.PaymentAction;
import com.impetus.pizzaonline.constants.ApplConstants;
import com.impetus.pizzaonline.model.ItemModel;
import com.opensymphony.xwork2.ActionContext;

/**
 * 
 * this class consists of some util methods utilised in other action classes.
 * @author nimmi.menon
 *
 */
public class PizzaUtil {
	
	private final static Logger LOGGER = Logger.getLogger(PizzaUtil.class);
	private static Pattern pattern;
	  private static Matcher matcher;

	  private static final String EMAIL_PATTERN = 
                 "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
	public enum Day
	 {
	     SUNDAY, MONDAY, TUESDAY, WEDNESDAY, 
	     THURSDAY, FRIDAY, SATURDAY; 
	 }
	
	 
	public static LinkedHashMap<String,String>  weekMap(){
		
		LinkedHashMap<String,String> weekMap=new LinkedHashMap<String,String>();
		weekMap.put(ApplConstants.MONDAY, "0");
		weekMap.put(ApplConstants.TUESDAY, "0");
		weekMap.put(ApplConstants.WEDNESDAY, "0");
		weekMap.put(ApplConstants.THURSDAY, "0");
		weekMap.put(ApplConstants.FRIDAY, "0");
		weekMap.put(ApplConstants.SATURDAY, "0");
		weekMap.put(ApplConstants.SUNDAY, "0");
		
		return weekMap;
		
	}
/**
 * 
 * used to validate day in the offer 
 * @param day
 * @param x
 * @return boolean
 */
public static boolean validateOffer(String day,String x){
	
	 boolean validate=false;
	StringBuffer str= new StringBuffer();
	str=str.append(x);
	int n=0;
	switch (Day.valueOf(day))
	  {
	      case MONDAY:  
	    	  if(str.charAt(0)=='1'){
	    	   	 validate=true;
	    }
	       break;
	       
	      case TUESDAY:
	    	  if(str.charAt(1)=='1'){
		    	    validate=true;
	    	  }
	       break;
	       
	      case WEDNESDAY:
	    	  if(str.charAt(2)=='1'){
	    		  validate=true;
	    	  }
	       break;
	       
	      case THURSDAY:
	    	  if(str.charAt(3)=='1'){
	    		  validate=true;
	    	  }
	       break;
	       
	      case FRIDAY:
	    	  if(str.charAt(4)=='1'){
	    		  validate=true;
	    	  }
	       break;
	       
	      case SATURDAY:
	    	  if(str.charAt(5)=='1'){
	    		  validate=true;
	    	  }
	       break;
	       
	      case SUNDAY:
	    	  if(str.charAt(6)=='1'){
	    		  validate=true;
	    	  }
	       break;
	       default:
	    	   
	    	   validate=false;
	  } 

return validate;
}




	/**
	 * 
	 * used to calculate the total price of items in the cartlist including tax and home deliver charges
	 * @param cartList
	 * @param p
	 * @return double
	 */
	public static double calculateTotal(List<ItemModel> cartList,
			PaymentAction p) {

		ItemModel tempItr;
		double tmpPrice = 0;
		int tempQuantity = 0;
		double tax = 0;
		double totalPrice = 0;

		Map<String, Object> session;
		session = ActionContext.getContext().getSession();
		String delivery;
		delivery = (String) session.get("delivery");

		if (cartList != null && cartList.size() != 0) {
			Iterator<ItemModel> iterator = cartList.iterator();

			while (iterator.hasNext()) {
				tempItr = iterator.next();

				tmpPrice += tempItr.getTotalPrice();
				tempQuantity += tempItr.getQuantity();

			}
			tax = (ApplConstants.TAX * tmpPrice);
			p.setTax(tax);
			if (delivery.equals("Home Delivery")) {
				totalPrice = tax + tmpPrice
						+ ApplConstants.HOMEDELIVERY_CHARGES;
				p.setTotalPrice(totalPrice);
				p.setDeliveryCharges(ApplConstants.HOMEDELIVERY_CHARGES);

			} else {
				totalPrice = tax + tmpPrice;
				p.setTotalPrice(totalPrice);
				p.setDeliveryCharges(0);
			}

		}

		return totalPrice;
	}

/**
 * 
 * used for sending the confirmation mail by BPO to customer and to the user after order is done.
 * @param order
 * @param email
 * @param price
 * @return double
 */
public static boolean sendMail(String msg,String email){
	
	boolean send=false;
	

	PizzaOnlineProperties pizzaProps = new PizzaOnlineProperties();
	
	pizzaProps.loadProperty();
		
	String getPrice;

	Properties props = new Properties();
	
	final String username = pizzaProps.getUsername();
	final String password = pizzaProps.getPassword();
	final String smtpAuth = pizzaProps.getSmtpAuth();
	final String tlsEnable = pizzaProps.getTlsEnable();	
	final String hostName = pizzaProps.getHostName();
	final String portNo = pizzaProps.getPortNumber();
	
	LOGGER.info("Email User Name --->  "+username);
	LOGGER.info("Email password --->  "+password);
	LOGGER.info("Email smtpAuth --->  "+smtpAuth);
	LOGGER.info("Email tlsEnable --->  "+tlsEnable);
	LOGGER.info("Email  hostName --->  "+hostName);
	LOGGER.info("Email  portNo --->  "+portNo);
	
	props.put("mail.smtp.auth", smtpAuth);
	props.put("mail.smtp.starttls.enable", tlsEnable);
	props.put("mail.smtp.host", hostName);
	props.put("mail.smtp.port", portNo);
	

	Session session = Session.getInstance(props,
	  new javax.mail.Authenticator() {
		protected PasswordAuthentication getPasswordAuthentication() {
			return new PasswordAuthentication(username, password);
		}
	  });

	try {

		Message message = new MimeMessage(session);
		message.setFrom(new InternetAddress(username));
		pattern = Pattern.compile(EMAIL_PATTERN);
		 matcher = pattern.matcher(email);
		 
		 boolean matches=matcher.matches();
		 
		 if(matches==true){
		message.setRecipients(Message.RecipientType.TO,
			InternetAddress.parse(email));
		 }
		 else{
			 
			 return matches;
		 }
		message.setSubject("Order Confirmation");
		
		
		message.setText(msg );

		Transport.send(message);
		send=true;
		

	} catch (MessagingException e) {
		send=false;
		throw new RuntimeException(e);
	}
	
	return send;
}
	
	
	
	

}
