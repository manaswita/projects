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

public class GetItemActionTest extends BaseTestCase {

	public GetItemActionTest(String name) {
		super(name);
		// TODO Auto-generated constructor stub
	}

	@Test
	public void testPizzaList() throws Exception {
		createAction(BPOAction.class, "/pages", "pizzaList", "pizzaList");
		Map<String, Object> params = new HashMap<String, Object>();
		List<ItemModel> cartList = new ArrayList<ItemModel>();
		List<Topping> toppingList = new ArrayList<Topping>();
		Map param = new HashMap(); 
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
		ActionContext.getContext().setSession(param); 
		proxy.getInvocation().getInvocationContext().setParameters(params);
       
		String result = proxy.execute();  
		assertEquals(result,"populate"); 
	}

	@Test
	public void testSideList() throws Exception {
		createAction(BPOAction.class, "/pages", "sideList", "sideList");
		Map<String, Object> params = new HashMap<String, Object>();
		List<ItemModel> cartList = new ArrayList<ItemModel>();
		List<Topping> toppingList = new ArrayList<Topping>();
		Map param = new HashMap(); 
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
		ActionContext.getContext().setSession(param); 
		proxy.getInvocation().getInvocationContext().setParameters(params);
       
		String result = proxy.execute();  
		assertEquals(result,"populate"); 
	}

	@Test
	public void testDessertList() throws Exception {
		createAction(BPOAction.class, "/pages", "dessertList", "dessertList");
		Map<String, Object> params = new HashMap<String, Object>();
		List<ItemModel> cartList = new ArrayList<ItemModel>();
		List<Topping> toppingList = new ArrayList<Topping>();
		Map param = new HashMap(); 
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
		ActionContext.getContext().setSession(param); 
		proxy.getInvocation().getInvocationContext().setParameters(params);
       
		String result = proxy.execute();  
		assertEquals(result,"populate"); 
	}

	@Test
	public void testBeverageList() throws Exception {
		createAction(BPOAction.class, "/pages", "beverageList", "beverageList");
		Map<String, Object> params = new HashMap<String, Object>();
		List<ItemModel> cartList = new ArrayList<ItemModel>();
		List<Topping> toppingList = new ArrayList<Topping>();
		Map param = new HashMap(); 
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
		ActionContext.getContext().setSession(param); 
		proxy.getInvocation().getInvocationContext().setParameters(params);
       
		String result = proxy.execute();  
		assertEquals(result,"populate"); 
	}

	@Test
	public void testToppingList() throws Exception {
		createAction(BPOAction.class, "/pages", "toppingList", "toppingList");
		Map<String, Object> params = new HashMap<String, Object>();
		proxy.getInvocation().getInvocationContext().setParameters(params);
	       
		String result = proxy.execute();  
		assertEquals(result,"populate"); 
	}

	@Test
	public void testBevToppingList() throws Exception {
		createAction(BPOAction.class, "/pages", "bevToppingList", "bevToppingList");
		Map<String, Object> params = new HashMap<String, Object>();
		proxy.getInvocation().getInvocationContext().setParameters(params);
	       
		String result = proxy.execute();  
		assertEquals(result,"populate"); 
	}

	@Test
	public void testNoToppingList() throws Exception {
		createAction(BPOAction.class, "/pages", "noToppingList", "noToppingList");
		Map<String, Object> params = new HashMap<String, Object>();
		proxy.getInvocation().getInvocationContext().setParameters(params);
	       
		String result = proxy.execute();  
		assertEquals(result,"populate"); 
	}

	@Test
	public void testSideToppingList() throws Exception {
		createAction(BPOAction.class, "/pages", "sideToppingList", "sideToppingList");
		Map<String, Object> params = new HashMap<String, Object>();
		proxy.getInvocation().getInvocationContext().setParameters(params);
	       
		String result = proxy.execute();  
		assertEquals(result,"populate"); 
	}

	@Test
	public void testAddToCartPizza() throws Exception {
		createAction(BPOAction.class, "/pages", "addToCart", "addToCartPizza");
		Map<String, Object> params = new HashMap<String, Object>();
		List<ItemModel> cartList = new ArrayList<ItemModel>();
		List<Topping> toppingList = new ArrayList<Topping>();
		List<String> selectTopping = new ArrayList<String>();
		Map param = new HashMap(); 
		param.put("cartList",cartList);
		params.put("chooseSize", "MEDIUM");
		params.put("selectQuantity","2");
		selectTopping.add("BLACK OLIVES");
		params.put("selectTopping",selectTopping);
		params.put("pizzaName", "PIZZA SPICY CHICKEN");
		ActionContext.getContext().setSession(param); 
		proxy.getInvocation().getInvocationContext().setParameters(params);
	       
		String result = proxy.execute();  
		assertEquals(result,"populate");
	}

	@Test
	public void testAddToCartSides() throws Exception {
		createAction(BPOAction.class, "/pages", "addToCartSides", "addToCartSides");
		Map<String, Object> params = new HashMap<String, Object>();
		List<ItemModel> cartList = new ArrayList<ItemModel>();
		List<Topping> toppingList = new ArrayList<Topping>();
		List<String> selectTopping = new ArrayList<String>();
		Map param = new HashMap(); 
		param.put("cartList",cartList);
		params.put("chooseSize", "NO SIZE");
		params.put("selectQuantity","2");
		selectTopping.add("EXTRA CHEESE");
		params.put("selectTopping",selectTopping);
		params.put("sideName", "GARLIC BREAD");
		ActionContext.getContext().setSession(param); 
		proxy.getInvocation().getInvocationContext().setParameters(params);
	       
		String result = proxy.execute();  
		assertEquals(result,"populate");
	}

	@Test
	public void testAddToCartDessert() throws Exception {
		createAction(BPOAction.class, "/pages", "addToCartDessert", "addToCartDessert");
		Map<String, Object> params = new HashMap<String, Object>();
		List<ItemModel> cartList = new ArrayList<ItemModel>();
		List<Topping> toppingList = new ArrayList<Topping>();
		Map param = new HashMap(); 
		param.put("cartList",cartList);
		params.put("chooseSize", "NO SIZE");
		params.put("selectQuantity","2");
		
		params.put("dessertName", "FRAPPE");
		ActionContext.getContext().setSession(param); 
		proxy.getInvocation().getInvocationContext().setParameters(params);
	       
		String result = proxy.execute();  
		assertEquals(result,"populate");
	}

	@Test
	public void testAddToCartBeverage() throws Exception {
		createAction(BPOAction.class, "/pages", "addToCartBeverage", "addToCartBeverage");
		Map<String, Object> params = new HashMap<String, Object>();
		List<ItemModel> cartList = new ArrayList<ItemModel>();
		List<Topping> toppingList = new ArrayList<Topping>();
		Map param = new HashMap(); 
		param.put("cartList",cartList);
		params.put("chooseSize", "NO SIZE");
		params.put("selectQuantity","1");
		params.put("chooseSize", "LARGE");
		params.put("beverageName", "ICED TEA");
		ActionContext.getContext().setSession(param); 
		proxy.getInvocation().getInvocationContext().setParameters(params);
	       
		String result = proxy.execute();  
		assertEquals(result,"populate");
	}

}
