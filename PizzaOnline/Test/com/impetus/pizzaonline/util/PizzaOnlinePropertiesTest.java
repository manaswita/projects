package com.impetus.pizzaonline.util;

import static org.junit.Assert.*;

import org.junit.Test;

public class PizzaOnlinePropertiesTest {

	@Test
	public void testLoadProperty() {
		
		PizzaOnlineProperties pizzaProps = new PizzaOnlineProperties();
		pizzaProps.loadProperty();
		assertEquals("pizzaitalianoonline@gmail.com", pizzaProps.getUsername());
		assertEquals("impetusonline",pizzaProps.getPassword());
		assertEquals("true",pizzaProps.getSmtpAuth());
		assertEquals("true",pizzaProps.getTlsEnable());
		assertEquals("smtp.gmail.com",pizzaProps.getHostName());
		assertEquals("587",pizzaProps.getPortNumber());
		
	}
	

}
