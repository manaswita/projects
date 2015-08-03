package com.impetus.pizzaonline.action;

import static org.junit.Assert.*;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSessionContext;

import org.junit.Test;

import com.impetus.pizzaonline.BaseTestCase;
import com.opensymphony.xwork2.ActionContext;

public class BPOActionTest extends BaseTestCase {

	public BPOActionTest(String name) {
		super(name);
		
	}
/*
	@Test
	public void testValidateCust() throws Exception  {
		createAction(BPOAction.class, "/pages", "validateCust", "validateCust");
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("mobile", "8050519930");
		 Map param = new HashMap(); 
		 ActionContext.getContext().setSession(param); 
		proxy.getInvocation().getInvocationContext().setParameters(params);
        
		String result = proxy.execute();  
		assertEquals(result,"success"); 
	}*/

	

	@Test
	public void testRegisterCust() throws Exception {
		createAction(BPOAction.class, "/pages", "registerCust", "registerCust");
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("name", "Madhu");
		params.put("flat_no","No:09");
		params.put("bldg_name","ABC");
		params.put("lane","Venkatyapura");
		params.put("area","Bharwad");
		params.put("city","indore");
		params.put("mobile","9876567890");
		params.put("email","nimmisasimenon@gmail.com");
		params.put("pin","670008");
		/*params.put("userName", "guest user");
		params.put("password", "guestuser");*/
		 Map param = new HashMap(); 
		 ActionContext.getContext().setSession(param); 
		proxy.getInvocation().getInvocationContext().setParameters(params);
        
		String result = proxy.execute();  
		assertEquals(result,"success"); 
	}

}
