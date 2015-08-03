package com.impetus.pizzaonline.action;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import com.impetus.pizzaonline.BaseTestCase;
import com.opensymphony.xwork2.ActionContext;

public class UserActionTest extends BaseTestCase {

	public UserActionTest(String name) {
		super(name);
		// TODO Auto-generated constructor stub
	}

	@Test
	public void testRegister() throws Exception {
		createAction(BPOAction.class, "/pages", "registerUser", "register");
		Map<String, Object> params = new HashMap<String, Object>();
		Map param = new HashMap(); 
		params.put("name", "Sree");
		params.put("flat_no", "A08");
		params.put("bldg_name", "Sree");
		params.put("lane", "ABC");
		params.put("area", "Bellandur");
		params.put("city", "Bangalore");
		params.put("mobile", "9876543211");
		params.put("email", "nimmisasimenon@gmail.com");
		params.put("userName", "sree");
		params.put("password", "sree");
		params.put("pin", "560103");
		params.put("delivery", "Home Delivery");
		params.put("confirmPassword","sree");
		ActionContext.getContext().setSession(param); 
		proxy.getInvocation().getInvocationContext().setParameters(params);
		String result = proxy.execute();  
		assertEquals(result,"success"); 
	}

	@Test
	public void testLogin() throws Exception {
		createAction(BPOAction.class, "/pages", "loginUser", "login");
		Map<String, Object> params = new HashMap<String, Object>();
		Map param = new HashMap(); 
		params.put("loginName", "sree");
		params.put("password", "sree");
		params.put("delivery", "Home Delivery");
		ActionContext.getContext().setSession(param); 
		proxy.getInvocation().getInvocationContext().setParameters(params);
		String result = proxy.execute();  
		assertEquals(result,"success"); 
	}

	@Test
	public void testLogOut() throws Exception {
		createAction(BPOAction.class, "/pages", "logout", "logOut");
		Map<String, Object> params = new HashMap<String, Object>();
		Map param = new HashMap(); 
		
		param.put("getUser","");
		param.put("cartList","");
		param.put("delivery","");
		param.put("totalPrice","");
		
		ActionContext.getContext().setSession(param); 
		proxy.getInvocation().getInvocationContext().setParameters(params);
		String result = proxy.execute();  
		assertEquals(result,"success"); 
	}

	@Test
	public void testGetHome() throws Exception {
		createAction(BPOAction.class, "/pages", "home", "getHome");
		Map<String, Object> params = new HashMap<String, Object>();
		proxy.getInvocation().getInvocationContext().setParameters(params);
		String result = proxy.execute();  
		assertEquals(result,"success"); 
	}

	

	@Test
	public void testForgotPassword() throws Exception {
		createAction(BPOAction.class, "/pages", "forgotPassword", "forgotPassword");
		Map<String, Object> params = new HashMap<String, Object>();
		Map param = new HashMap(); 
		params.put("userName", "sree");
		params.put("email", "nimmisasimenon@gmail.com");
		proxy.getInvocation().getInvocationContext().setParameters(params);
		String result = proxy.execute();  
		assertEquals(result,"success"); 
	}

}
