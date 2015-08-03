package com.impetus.pizzaonline.action;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import com.impetus.pizzaonline.BaseTestCase;
import com.impetus.pizzaonline.model.ItemModel;
import com.impetus.pizzaonline.model.Topping;
import com.impetus.pizzaonline.model.User;
import com.opensymphony.xwork2.ActionContext;

public class OrderActionTest extends BaseTestCase {

	

	public OrderActionTest(String name) {
		super(name);
		// TODO Auto-generated constructor stub
	}
	@Test
	public void testInsertOrder() throws Exception {
		createAction(BPOAction.class, "/pages", "order", "insertOrder");
		Map<String, Object> params = new HashMap<String, Object>();
		List<ItemModel> cartList = new ArrayList<ItemModel>();
		List<Topping> toppingList = new ArrayList<Topping>();
		Map param = new HashMap(); 
		User user= new User();
		user.setName("Nimmi");
		user.setId(1);
		user.setRole("Customer");
		user.setEmail("nimmisasimenon@gmail.com");
		param.put("getUser", user);
		
		User cust = new User();
		
		
		params.put("cvv", "111");
		params.put("cardNumber", "1111111111111111");
		param.put("cust",cust);
		params.put("mode", "Debit");
		params.put("offer", "NO OFFER");
		params.put("couponNumber","");
		params.put("name","Nimmi");
		Topping topping = new Topping();
		topping.setAct_ind('Y');
		topping.setCategory("PIZZA");
		topping.setId(7);
		topping.setName("BLACK OLIVES");
		topping.setPrice("30");
		topping.setType("VEGETARIAN");
		toppingList.add(topping);
		ItemModel item = new ItemModel();
		
		item.setItemName("PIZZA SPICY CHICKEN");
		item.setItemPrice("400");
		item.setItemSize("MEDIUM");
		item.setActInd('Y');
		item.setItemDesc("SPICY PIZZA WITH MIX OF ITALIAN CUISINE AND INDIAN TADKA ");
		item.setQuantity(2);
		item.setToppingList(toppingList);
		item.setTotalPrice(830);
		item.setType("VEGETARIAN");
		
		cartList.add(item);
		param.put("cartList",cartList);
		Double totalPrice=929.6;
		
		param.put("totalPrice",totalPrice);
		param.put("delivery","PICK UP");
		param.put("area", "Koramangala");
		param.put("city", "bengaluru");
		param.put("pin", "560034");
		ActionContext.getContext().setSession(param); 
		proxy.getInvocation().getInvocationContext().setParameters(params);
       
		String result = proxy.execute();  
		assertEquals(result,"success"); 
	}
	@Test
	public void testGetUserHistory() throws Exception {
		createAction(BPOAction.class, "/pages", "userHistory", "getUserHistory");
		Map<String, Object> params = new HashMap<String, Object>();

		Map param = new HashMap(); 
		
		User user= new User();
		user.setName("Nimmi");
		user.setId(1);
		user.setRole("Customer");
		param.put("getUser", user);
		 ActionContext.getContext().setSession(param); 
		proxy.getInvocation().getInvocationContext().setParameters(params);
       
		String result = proxy.execute();  
		assertEquals(result,"success"); 
	}

}
