package com.impetus.pizzaonline.action;

import static org.junit.Assert.*;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSessionContext;

import org.apache.struts2.StrutsTestCase;
import org.junit.Test;

import com.impetus.pizzaonline.BaseTestCase;

public class AdminActionTest  extends BaseTestCase {

	public AdminActionTest(String name) {
		super(name);
		
	}

	@Test
	public void testPopulateSize() throws Exception {
		createAction(AdminAction.class, "/pages", "populateSize", "populateSize");
		String result = proxy.execute();  
		assertEquals("Result does not equal input", "input", result);
		
	}

	

	/*@Test
	public void testInsertItem() throws Exception {
		createAction(AdminAction.class, "/pages", "insertItem", "insertItem");
		AdminAction admin= new AdminAction();
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("itemCategory", "dessert");
		params.put("description","milk shakes");
		params.put("itemName","Crusher");
		params.put("itemPrice","80");
		params.put("itemSize","NO SIZE");
		params.put("itemType","VEGETARIAN");
		
		proxy.getInvocation().getInvocationContext().setParameters(params);
        
		String result = proxy.execute();  
		

		assertNotNull(admin.insertItem());
	}*/

	@Test
	public void testRemoveItem() throws Exception {
		AdminAction admin= new AdminAction();
		createAction(AdminAction.class, "/pages", "removeItem", "removeItem");
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("itemName","FRAPPE");
		params.put("itemSize","NO SIZE");
		proxy.getInvocation().getInvocationContext().setParameters(params);
		String result = proxy.execute(); 
		assertNotNull(admin.removeItem());
	}

	

	/*@Test
	public void testInsertTpg() {
		fail("Not yet implemented");
	}

	@Test
	public void testRemoveTopping() {
		fail("Not yet implemented");
	}

	@Test
	public void testRegisterStaff() {
		fail("Not yet implemented");
	}

	@Test
	public void testRemoveStaff() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetCustomerOrder() {
		fail("Not yet implemented");
	}

	@Test
	public void testInsertOffer() {
		fail("Not yet implemented");
	}

	@Test
	public void testPopulateDays() {
		fail("Not yet implemented");
	}

	@Test
	public void testRemoveOffer() {
		fail("Not yet implemented");
	}
*/
}
