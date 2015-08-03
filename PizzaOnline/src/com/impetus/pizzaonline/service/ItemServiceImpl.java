package com.impetus.pizzaonline.service;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.impetus.pizzaonline.dao.ItemDAO;
import com.impetus.pizzaonline.model.Item;
import com.impetus.pizzaonline.model.ItemModel;
import com.impetus.pizzaonline.model.Topping;

/**
 * 
 * this interface declares all the methods used in ItemService class
 * 
 * 
 * @author nimmi.menon
 *
 */
public interface ItemServiceImpl {
	
public Map<String, MapValObj> getPizza();
public Map<String, MapValObj> getSideDish();
public Map<String, MapValObj> getDessert();
public Map<String, MapValObj> getBeverages();
public ArrayList<Topping> getTopping();
public ArrayList<Topping> getTpg();
public ArrayList<ItemModel> getPizzaList() throws IllegalAccessException, InvocationTargetException;
public ArrayList<ItemModel> getSideList() throws IllegalAccessException, InvocationTargetException;
public ArrayList<ItemModel> getDessertList() throws IllegalAccessException, InvocationTargetException;
public ArrayList<ItemModel> getBeverageList() throws IllegalAccessException, InvocationTargetException;
public boolean insertItem(Item item);
public boolean removeItem(Item item);
public List<Item> getItem(String itemName, String itemSize);
public List<Item> getItem();
public boolean insertTopping(Topping topping);
public boolean removeTopping(Topping topping);
}
