package com.impetus.pizzaonline.dao;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.junit.Test;

import com.impetus.pizzaonline.model.Item;
import com.impetus.pizzaonline.model.Topping;

public class ItemDAOTest {

	@Test
	public void testGetPizza() {
		ItemDAO item = new ItemDAO();
		
		
		assertNotNull("Result1", item.getPizza() );
	}

	@Test
	public void testGetSideDish() {
		ItemDAO item = new ItemDAO();
		assertNotNull("Result1", item.getSideDish());
	}

	@Test
	public void testGetDessert() {
		ItemDAO item = new ItemDAO();
		assertNotNull("Result1", item.getDessert());
	}

	@Test
	public void testGetBeverages() {
		ItemDAO item = new ItemDAO();
		assertNotNull("Result1", item.getBeverages());
	}

	@Test
	public void testGetTopping() {
		ItemDAO item = new ItemDAO();
		assertNotNull("Result1", item.getTopping());
	}

	@Test
	public void testGetTpg() {
		ItemDAO item = new ItemDAO();
		assertNotNull("Result1", item.getTpg());
	}

	@Test
	public void testInsertItem() {
		ItemDAO item = new ItemDAO();
		List<Topping> topping = new ArrayList<Topping>();
 		Item itm = new Item();
		itm.setActInd('Y');
		itm.setItemCategory("DESSERT");
		itm.setItemDesc("CHOCOLATE CAKE");
		itm.setItemId(456);
		itm.setItemName("CHOCO GATEAU");
		itm.setItemPrice("150");
		itm.setItemSize("NO SIZE");
		itm.setToppingList(topping);
		itm.setType("VEGETARIAN");
		
		
		assertTrue("Result1", item.insertItem(itm));
		assertFalse("Result2", item.insertItem(itm));
	}

	@Test
	public void testGetItemStringString() {
		ItemDAO item = new ItemDAO();
		
		
		String itemName="CHOCO GATEAU";
		String itemSize="NO SIZE";
		assertNotNull("Result1", item.getItem(itemName, itemSize));
		
		
	}

	@Test
	public void testGetItem() {
		ItemDAO item = new ItemDAO();
		
		assertNotNull("Result1", item.getItem());
	}

	@Test
	public void testRemoveItem() {
		ItemDAO item = new ItemDAO();
		Item itm = new Item();
		String itemName="CHOCO GATEAU";
		String itemSize="NO SIZE";
		List<Item> itemList = new ArrayList<Item>();
		if(item.getItem(itemName,itemSize)!=null){
		itemList =item.getItem(itemName,itemSize);
		itm = itemList.get(0);
		itm.setActInd('N');
		assertTrue("Result1", item.removeItem(itm));
	}
	}
	@Test
	public void testInsertTopping() {
		Topping topping = new Topping();
		ItemDAO item = new ItemDAO();
		topping.setAct_ind('Y');
		topping.setCategory("PIZZA");
		topping.setName("BABY CORN");
		topping.setPrice("40");
		topping.setType("VEGETARIAN");
		
		assertTrue("Result1", item.insertTopping(topping));
		assertFalse("Result2", item.insertTopping(topping));
	}

	@Test
	public void testRemovetopping() {
		List<Topping> tpgList = new ArrayList<Topping>();
		ItemDAO item = new ItemDAO();
		Topping topping = new Topping();
		String tpgName="BABY CORN";
		String tpgType="VEGETARIAN";
		
		
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
		assertTrue("Result1", item.removetopping(topping));
	}

}
