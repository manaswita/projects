package com.impetus.pizzaonline.constants;



import static org.junit.Assert.*;

import org.junit.Test;

public class ApplConstantsTest {

	@Test
	public void testGetDelivery() {
		
		
		assertNotNull("Result",ApplConstants.getDelivery());
	}

	@Test
	public void testGetTax() {
		assertNotNull("Result",ApplConstants.getTax());
	}

}
