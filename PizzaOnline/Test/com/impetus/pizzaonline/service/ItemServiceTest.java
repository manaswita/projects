package com.impetus.pizzaonline.service;

import static org.junit.Assert.*;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.junit.Test;

import com.impetus.pizzaonline.dao.ItemDAO;
import com.impetus.pizzaonline.model.Item;
import com.impetus.pizzaonline.model.Topping;

public class ItemServiceTest {

	@Test
	public void testGetPizza() {
		
		ItemServiceImpl item = new ItemService();
		assertNotNull("Result1", item.getPizza() );
	}

	@Test
	public void testGetSideDish() {
		ItemServiceImpl item = new ItemService();
		assertNotNull("Result2", item.getSideDish() );
	}

	@Test
	public void testGetDessert() {
		ItemServiceImpl item = new ItemService();
		assertNotNull("Result3", item.getDessert() );
	}

	@Test
	public void testGetBeverages() {
		ItemServiceImpl item = new ItemService();
		assertNotNull("Result4", item.getBeverages() );
	}

	@Test
	public void testGetTopping() {
		ItemServiceImpl item = new ItemService();
		assertNotNull("Result5", item.getTopping() );
	}
	@Test
	public void testGetTpg() {
		ItemServiceImpl item = new ItemService();
		assertNotNull("Result6", item.getTpg());
	}
	@Test
	public void testGetPizzaList() throws IllegalAccessException, InvocationTargetException {
		ItemServiceImpl item = new ItemService();
		assertNotNull("Result7", item.getPizzaList() );
	}

	@Test
	public void testGetSideList() throws IllegalAccessException, InvocationTargetException {
		ItemServiceImpl item = new ItemService();
		assertNotNull("Result8", item.getSideList() );
	}

	@Test
	public void testGetDessertList() throws IllegalAccessException, InvocationTargetException {
		ItemServiceImpl item = new ItemService();
		assertNotNull("Result9", item.getDessertList() );
	}

	@Test
	public void testGetBeverageList() throws IllegalAccessException, InvocationTargetException {
		ItemServiceImpl item = new ItemService();
		assertNotNull("Result10", item.getBeverageList() );
	}

	@Test
	public void testInsertItem() {
		ItemServiceImpl item = new ItemService();
		List<Topping> topping = new ArrayList<Topping>();
 		Item itm = new Item();
		itm.setActInd('Y');
		itm.setItemCategory("SIDEDISH");
		itm.setItemDesc("CHICKEN PIECES ON SKEWS");
		itm.setItemName("CHIC SKEWERS");
		itm.setItemPrice("200");
		itm.setItemSize("NO SIZE");
		itm.setToppingList(topping);
		itm.setType("NONVEGETARIAN");
		
		assertNotNull("Result11", item.insertItem(itm));
	}

	@Test
	public void testRemoveItem() {
		
		ItemServiceImpl item = new ItemService();
		Item itm = new Item();
		String itemName="CHIC SKEWERS";
		String itemSize="NO SIZE";
		List<Item> itemList = new ArrayList<Item>();
		if(item.getItem(itemName,itemSize)!=null){
		itemList =item.getItem(itemName,itemSize);
		itm = itemList.get(0);
		itm.setActInd('N');
	}
		assertNotNull("Result12", item.removeItem(itm));
	}
	

	@Test
	public void testGetItem() {
		ItemServiceImpl item = new ItemService();
		assertNotNull("Result13", item.getItem());
	}

	@Test
	public void testInsertTopping() {
		Topping topping = new Topping();
		ItemServiceImpl item = new ItemService();
		topping.setAct_ind('Y');
		topping.setCategory("PIZZA");
		topping.setName("TUNA");
		topping.setPrice("50");
		topping.setType("NONVEGETARIAN");
		assertNotNull("Result14", item.insertTopping(topping));
	}

	@Test
	public void testRemoveTopping() {
		List<Topping> tpgList = new ArrayList<Topping>();
		ItemServiceImpl item = new ItemService();
		Topping topping = new Topping();
		String tpgName="TUNA";
		String tpgType="NONVEGETARIAN";
		
		
		if (item.getTopping() != null) {
			tpgList = item.getTopping();
			Iterator<Topping> iterator = tpgList.iterator();
			while (iterator.hasNext()) {
				Topping temp = iterator.next();
				if (temp.getName().equalsIgnoreCase(tpgName)
						&& temp.getType().equalsIgnoreCase(tpgType)) {
					topping = temp;
				}

			}
			topping.setAct_ind('N');
		}
		assertNotNull("Result15", item.removeTopping(topping));
	}
	

}
