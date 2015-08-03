package com.impetus.pizzaonline.service;

import java.lang.reflect.InvocationTargetException;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.beanutils.BeanUtils;

import com.impetus.pizzaonline.dao.ItemDAO;
import com.impetus.pizzaonline.dao.UserDAO;
import com.impetus.pizzaonline.model.Item;
import com.impetus.pizzaonline.model.ItemModel;
import com.impetus.pizzaonline.model.Topping;

/**
 * this class acts like a service class to manipulate the objects received from the DAO class and render them to the action class.
 * @author nimmi.menon
 *
 *
 */

public class ItemService implements ItemServiceImpl{
		
	public Map<String, MapValObj> getPizza() {
		
		ItemDAO itemdao=new ItemDAO();
		ItemService its = new ItemService();
		
		ArrayList<Item> pizza = (ArrayList<Item>) itemdao.getPizza();
		
		Map<String, MapValObj> pizzaItem=its.getItem(pizza);
		return pizzaItem;
		
	}
public Map<String, MapValObj> getSideDish(){
		
		ItemDAO itemdao=new ItemDAO();
		ItemService its = new ItemService();
		ArrayList<Item> sideDish = (ArrayList<Item>) itemdao.getSideDish();
		
		Map<String, MapValObj> sideItem=its.getItem(sideDish);
		return sideItem;
		
	}
public Map<String, MapValObj> getDessert(){
	
	ItemDAO itemdao=new ItemDAO();
	ItemService its = new ItemService();
	ArrayList<Item> dessert = (ArrayList<Item>) itemdao.getDessert();
	
	Map<String, MapValObj> dessertItem=its.getItem(dessert);
	return dessertItem;
	
}
public Map<String, MapValObj> getBeverages(){
	
	ItemDAO itemdao=new ItemDAO();
	ItemService its = new ItemService();
	ArrayList<Item> beverage = (ArrayList<Item>) itemdao.getBeverages();
	
	Map<String, MapValObj> beverageItem=its.getItem(beverage);
	return beverageItem;
	
}

public ArrayList<Topping> getTopping(){
	
	ItemDAO itemdao=new ItemDAO();
	ItemService its = new ItemService();
	ArrayList<Topping> toppingList = (ArrayList<Topping>) itemdao.getTopping();
	
	
	return toppingList;
	
}
public ArrayList<Topping> getTpg(){
	
	ItemDAO itemdao=new ItemDAO();
	ItemService its = new ItemService();
	ArrayList<Topping> toppingList = (ArrayList<Topping>) itemdao.getTopping();
	
	
	return toppingList;
	
}

public ArrayList<ItemModel> getPizzaList() throws IllegalAccessException, InvocationTargetException{
	
	ItemDAO itemdao=new ItemDAO();
	ItemService its = new ItemService();
	ArrayList<ItemModel> pizzaModelList = new ArrayList<ItemModel>();
	ArrayList<Item> pizzaList = (ArrayList<Item>) itemdao.getPizza();
	Item item = new Item();
	ItemModel itemModel = null;
	Iterator<Item> iterator = pizzaList.iterator();
	
	while(iterator.hasNext()){
		item=iterator.next();
		itemModel = new ItemModel();
		BeanUtils.copyProperties(itemModel,item);
		pizzaModelList.add(itemModel);
		
	}
	
	return pizzaModelList;
	
}
/* (non-Javadoc)
 * @see com.impetus.pizzaonline.service.ItemServiceImpl#getSideList()
 */
public ArrayList<ItemModel> getSideList() throws IllegalAccessException, InvocationTargetException{
	
	ItemDAO itemdao=new ItemDAO();
	ItemService its = new ItemService();
	ArrayList<ItemModel> sidesModelList = new ArrayList<ItemModel>();
	ArrayList<Item> sideList = (ArrayList<Item>) itemdao.getSideDish();
	Item item = new Item();
	ItemModel itemModel = null;
	Iterator<Item> iterator = sideList.iterator();
	
	while(iterator.hasNext()){
		item=iterator.next();
		itemModel = new ItemModel();
		BeanUtils.copyProperties(itemModel,item);
		sidesModelList.add(itemModel);
		
	}
	
	return sidesModelList;
	
}
/* (non-Javadoc)
 * @see com.impetus.pizzaonline.service.ItemServiceImpl#getDessertList()
 */
public ArrayList<ItemModel> getDessertList() throws IllegalAccessException, InvocationTargetException{
	
	ItemDAO itemdao=new ItemDAO();
	ItemService its = new ItemService();
	ArrayList<ItemModel> sidesModelList = new ArrayList<ItemModel>();
	ArrayList<Item> sideList = (ArrayList<Item>) itemdao.getDessert();
	Item item = new Item();
	ItemModel itemModel = null;
	Iterator<Item> iterator = sideList.iterator();
	
	while(iterator.hasNext()){
		item=iterator.next();
		itemModel = new ItemModel();
		BeanUtils.copyProperties(itemModel,item);
		sidesModelList.add(itemModel);
		
	}
	
	return sidesModelList;
	
}
/* (non-Javadoc)
 * 
 * @see com.impetus.pizzaonline.service.ItemServiceImpl#getBeverageList()
 */
public ArrayList<ItemModel> getBeverageList() throws IllegalAccessException, InvocationTargetException{
	
	ItemDAO itemdao=new ItemDAO();
	ItemService its = new ItemService();
	ArrayList<ItemModel> sidesModelList = new ArrayList<ItemModel>();
	ArrayList<Item> sideList = (ArrayList<Item>) itemdao.getBeverages();
	Item item = new Item();
	ItemModel itemModel = null;
	Iterator<Item> iterator = sideList.iterator();
	
	while(iterator.hasNext()){
		item=iterator.next();
		itemModel = new ItemModel();
		BeanUtils.copyProperties(itemModel,item);
		sidesModelList.add(itemModel);
		
	}
	
	return sidesModelList;
	
}
	/**
	 * 
	 * this method takes the list from the DAo class and with help of the MapValueObject class returns a hasmap consisting of item names as the key and the pricelist,description and type as values.
	 * @param itm
	 * @return
	 */
	private Map<String, MapValObj> getItem(ArrayList<Item> itm){
			
			HashMap<String, MapValObj> hm = new HashMap<String, MapValObj>();
			
			for (int i = 0; i < itm.size(); i++) {
				Item item = itm.get(i);
				MapValObj mapValObj = new MapValObj();

				List<String> priceList = new ArrayList<String>();
				
				priceList.add(item.getItemPrice());
				
				// Setting of all values
				mapValObj.setValues(priceList);
				mapValObj.setDescription(item.getItemDesc());
                mapValObj.setType(item.getType());
                mapValObj.setName(item.getItemName());
                
				if (hm.get(item.getItemName()) != null) {
					MapValObj getMapValObj = hm.get(item.getItemName());
					List<String> listValues = getMapValObj.getValues();
					listValues.add(item.getItemPrice());

					hm.put(item.getItemName(), getMapValObj);
				} else {
					hm.put(item.getItemName(), mapValObj);
				}

			}

			return hm;
	}
	

public boolean insertItem(Item item){
	
	boolean insert=false;
	ItemDAO newItem = new ItemDAO();
	
	insert=newItem.insertItem(item);
	return insert;
	
}
public boolean removeItem(Item item){
	
	boolean insert=false;
	ItemDAO newItem = new ItemDAO();
	
	insert=newItem.removeItem(item);
	return insert;
	
}
public List<Item> getItem(String itemName,String itemSize){

	ItemDAO itemdao=new ItemDAO();
	ArrayList<Item> itemList = (ArrayList<Item>) itemdao.getItem(itemName,itemSize);	
	return itemList;
}
public List<Item> getItem(){
	
	ItemDAO itemdao=new ItemDAO();
	ArrayList<Item> itemList = (ArrayList<Item>) itemdao.getItem();	
	return itemList;
	
}
public boolean insertTopping(Topping topping){
	
	boolean insert=false;
	ItemDAO newTopping = new ItemDAO();
	
	insert=newTopping.insertTopping(topping);
	return insert;
	
}
public boolean removeTopping(Topping topping){
	
	boolean insert=false;
	ItemDAO newItem = new ItemDAO();
	
	insert=newItem.removetopping(topping);
	return insert;
	
}
}	

