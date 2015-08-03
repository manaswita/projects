package com.impetus.pizzaonline.util;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.impetus.pizzaonline.BaseTestCase;
import com.impetus.pizzaonline.action.BPOAction;
import com.impetus.pizzaonline.action.PaymentAction;
import com.impetus.pizzaonline.model.ItemModel;
import com.impetus.pizzaonline.model.Topping;
import com.opensymphony.xwork2.ActionContext;

public class PizzaUtilTest  {

	@Test
	public void testValidateOffer() {
		
		assertEquals("Result",false, PizzaUtil.validateOffer("SUNDAY","1100000"));
		assertEquals("Result",true, PizzaUtil.validateOffer("MONDAY","1100000"));
		assertEquals("Result",true, PizzaUtil.validateOffer("TUESDAY","1100000"));
		assertEquals("Result",false, PizzaUtil.validateOffer("WEDNESDAY","1100000"));
		assertEquals("Result",false, PizzaUtil.validateOffer("THURSDAY","1100000"));
		assertEquals("Result",false, PizzaUtil.validateOffer("FRIDAY","1100000"));
	}
	
	@Test
public void testSendMail(){
	
	PizzaOnlineProperties pizzaProps = new PizzaOnlineProperties();
	pizzaProps.loadProperty();
	assertEquals("Result",true, PizzaUtil.sendMail("Your Order Items:PIZZA CAPPRIOSSA,PEPSI","nimmisasimenon@gmail.com"));
	assertEquals("Result",false, PizzaUtil.sendMail("Your Order Items:PIZZA CAPPRIOSSA,PEPSI","nimmisasimenon"));
	assertEquals("Result",false, PizzaUtil.sendMail("Your Order Items:PIZZA CAPPRIOSSA,PEPSI","123nimmisasimenon"));
	assertEquals("Result",false, PizzaUtil.sendMail("Your Order Items:PIZZA CAPPRIOSSA,PEPSI","nimmisasimenon@gmail@com"));
	}


}
