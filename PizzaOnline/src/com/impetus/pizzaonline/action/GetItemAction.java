package com.impetus.pizzaonline.action;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.apache.log4j.Logger;
import java.util.Map;
import java.util.Set;
import com.impetus.pizzaonline.constants.ApplConstants;
import com.impetus.pizzaonline.model.Item;
import com.impetus.pizzaonline.model.ItemModel;
import com.impetus.pizzaonline.model.Topping;
import com.impetus.pizzaonline.service.ItemService;
import com.impetus.pizzaonline.service.ItemServiceImpl;
import com.impetus.pizzaonline.service.MapValObj;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

/**
 * 
 * This class is used as the action class in struts 2 for all the item related actions.Here we have the variables that are used in the jsps and thier corresponding getters and setters.
 * This covers all methods to get the items from the DAO and to render it in the front end.
 * It also has all the addToCart methods for each category of item.
 * 
 * @author nimmi.menon
 *
 */
public class GetItemAction extends ActionSupport {
	private static final long serialVersionUID = 1L;
	private static final Logger LOGGER = Logger.getLogger(GetItemAction.class);
	private Map<String, MapValObj> items;
	private List<String> pizzaList = new ArrayList<String>();
	private List<String> chooseSize;
	private List<Integer> quantity;
	private List<String> sideList = new ArrayList<String>();
	private List<String> dessertList = new ArrayList<String>();
	private List<String> beverageList = new ArrayList<String>();
	private List<Topping> toppingList = new ArrayList<Topping>();
	private List<String> selectTopping = new ArrayList<String>();
	private List<ItemModel> cartList = new ArrayList<ItemModel>();
	private String selectSize;// for getting values of checkbox choosesize
	private String selectQuantity;
	private String dessertName;
	private String pizzaName;
	private String sideName;
	private List<String> selectSidesTopping = new ArrayList<String>();
	private String defaultTopping;
	private String defaultSize;
	private List<ItemModel> itemCartList;
	private String checkFlag = null;
	private int cartSize;
	private String beverageName;
	Map<String, Object> session;
	List<Topping> sideToppingList = new ArrayList<Topping>();

	
//all getters and setters for variables accessed in jsp page
	
	public String getBeverageName() {
		return beverageName;
	}

	public void setBeverageName(String beverageName) {
		this.beverageName = beverageName;
	}

	public String getDessertName() {
		return dessertName;
	}

	public void setDessertName(String dessertName) {
		this.dessertName = dessertName;
	}

	public List<ItemModel> getItemCartList() {
		return itemCartList;
	}

	public void setItemCartList(List<ItemModel> itemCartList) {
		this.itemCartList = itemCartList;
	}

	public List<String> getSelectSidesTopping() {
		return selectSidesTopping;
	}

	public void setSelectSidesTopping(List<String> selectSidesTopping) {
		this.selectSidesTopping = selectSidesTopping;
	}

	public String getSideName() {
		return sideName;
	}

	public void setSideName(String sideName) {
		this.sideName = sideName;
	}

	public List<Topping> getSideToppingList() {
		return sideToppingList;
	}

	public void setSideToppingList(List<Topping> sideToppingList) {
		this.sideToppingList = sideToppingList;
	}

	public int getCartSize() {
		return cartSize;
	}

	public void setCartSize(int cartSize) {
		this.cartSize = cartSize;
	}

	public String getCheckFlag() {
		return checkFlag;
	}

	public void setCheckFlag(String checkFlag) {
		this.checkFlag = checkFlag;
	}

	public String getDefaultSize() {
		return "SMALL";
	}

	public void setDefaultSize(String defaultSize) {
		this.defaultSize = defaultSize;
	}

	public String getDefaultTopping() {
		return "THIN CRUST";
	}

	public void setDefaultTopping(String defaultTopping) {
		this.defaultTopping = defaultTopping;
	}

	public String getPizzaName() {
		return pizzaName;
	}

	public void setPizzaName(String pizzaName) {
		this.pizzaName = pizzaName;
	}

	public String getSelectQuantity() {
		return selectQuantity;
	}

	public void setSelectQuantity(String selectQuantity) {
		this.selectQuantity = selectQuantity;
	}

	public List<ItemModel> getCartList() {
		return cartList;
	}

	public void setCartList(List<ItemModel> cartList) {
		this.cartList = cartList;
	}

	public List<String> getSelectTopping() {
		return selectTopping;
	}

	public void setSelectTopping(List<String> selectTopping) {
		this.selectTopping = selectTopping;
	}

	public String getSelectSize() {
		return selectSize;
	}

	public void setSelectSize(String selectSize) {
		this.selectSize = selectSize;
	}

	public List<String> getBeverageList() {
		return beverageList;
	}

	public void setBeverageList(List<String> beverageList) {
		this.beverageList = beverageList;
	}

	public List<String> getDessertList() {
		return dessertList;
	}

	public void setDessertList(List<String> dessertList) {
		this.dessertList = dessertList;
	}

	public List<Topping> getToppingList() {
		return toppingList;
	}

	public void setToppingList(List<Topping> toppingList) {
		this.toppingList = toppingList;
	}

	public List<Integer> getQuantity() {
		return quantity;
	}

	public void setQuantity(List<Integer> quantity) {
		this.quantity = quantity;
	}

	public List<String> getSideList() {
		return sideList;
	}

	public void setSideList(List<String> sideList) {
		this.sideList = sideList;
	}

	public List<String> getChooseSize() {
		return chooseSize;
	}

	public void setChooseSize(List<String> chooseSize) {
		this.chooseSize = chooseSize;
	}

	public List<String> getPizzaList() {
		return pizzaList;
	}

	public void setPizzaList(List<String> pizzaList) {
		this.pizzaList = pizzaList;
	}

	public Map<String, MapValObj> getItems() {
		return items;
	}

	public void setItems(Map<String, MapValObj> items) {
		this.items = items;
	}


	/**
	 * get the map of pizza items
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String pizzaList() {
		
		ItemModel tempItem;
		quantity = new ArrayList<Integer>();
		int count = 0;
		for (int i = 1; i <= 10; i++) {
			quantity.add(i);
		}
		ItemServiceImpl itemService = new ItemService();
		items = itemService.getPizza();
		//updating the cartsize
		if (cartList.isEmpty() || cartList.size() == 0) {
			session = ActionContext.getContext().getSession();
			cartList = (List<ItemModel>) session.get("cartList");

		} else {
			cartList = new ArrayList<ItemModel>();
		}
		if (cartList == null || cartList.size() == 0) {
			count = 0;
		} else {

			Iterator<ItemModel> iterator = cartList.iterator();

			while (iterator.hasNext()) {
				tempItem = iterator.next();
				count += tempItem.getQuantity();

			}
		}

		if (count <= 1) {
			cartSize = null == cartList ? 0 : cartList.size();
		} else {

			cartSize = count;
		}
		LOGGER.info("updating cartsize(Pizza..");
		return "populate";
	}
	/**
	 * get the map of side dishes
	 * 
	 * @return
	 */
	public String sideList() {
		quantity = new ArrayList<Integer>();

		for (int i = 1; i <= 10; i++) {
			quantity.add(i);
		}
		ItemModel tempItem;
		int count = 0;

		ItemServiceImpl itemService = new ItemService();
		items = itemService.getSideDish();
		
		
		//updating the cartsize
		if (cartList.isEmpty() || cartList.size() == 0) {
			session = ActionContext.getContext().getSession();
			cartList = (List<ItemModel>) session.get("cartList");
			LOGGER.info("getting cartList..");
		} else {
			cartList = new ArrayList<ItemModel>();
		}
		if (cartList == null || cartList.size() == 0) {
			count = 0;
		} else {

			Iterator<ItemModel> iterator = cartList.iterator();

			while (iterator.hasNext()) {
				tempItem = iterator.next();
				count += tempItem.getQuantity();

			}
		}

		if (count <= 1) {
			cartSize = null == cartList ? 0 : cartList.size();
		} else {

			cartSize = count;
		}
		LOGGER.info("updating cartsize(sideDish..");
		return "populate";
	}
	/**
	 * get the desserts from service
	 * 
	 * @return
	 */
	public String dessertList() {

		quantity = new ArrayList<Integer>();

		for (int i = 1; i <= 10; i++) {
			quantity.add(i);
		}
		ItemModel tempItem;
		int count = 0;
		ItemServiceImpl itemService = new ItemService();
		items = itemService.getDessert();
				
		//updating the cart size
		if (cartList.isEmpty() || cartList.size() == 0) {
			session = ActionContext.getContext().getSession();
			cartList = (List<ItemModel>) session.get("cartList");

		} else {
			cartList = new ArrayList<ItemModel>();
		}
		if (cartList == null || cartList.size() == 0) {
			count = 0;
		} else {

			Iterator<ItemModel> iterator = cartList.iterator();

			while (iterator.hasNext()) {
				tempItem = iterator.next();
				count += tempItem.getQuantity();

			}
		}

		if (count <= 1) {
			cartSize = null == cartList ? 0 : cartList.size();
		} else {

			cartSize = count;
		}
		LOGGER.info("updating cartsize(desserts..");
		return "populate";
	}

	
	/**
	 * get a map of beverage items
	 * 
	 * @return
	 */
	public String beverageList() {
		ItemModel tempItem;
		int count = 0;
		quantity = new ArrayList<Integer>();

		for (int i = 1; i <= 10; i++) {
			quantity.add(i);
		}
		ItemServiceImpl itemService = new ItemService();
		items = itemService.getBeverages();
	
		//updating cart size
		if (cartList.isEmpty() || cartList.size() == 0) {
			session = ActionContext.getContext().getSession();
			cartList = (List<ItemModel>) session.get("cartList");

		} else {
			cartList = new ArrayList<ItemModel>();
		}
		if (cartList == null || cartList.size() == 0) {
			count = 0;
		} else {

			Iterator<ItemModel> iterator = cartList.iterator();

			while (iterator.hasNext()) {
				tempItem = iterator.next();
				count += tempItem.getQuantity();

			}
		}

		if (count <= 1) {
			cartSize = null == cartList ? 0 : cartList.size();
		} else {

			cartSize = count;
		}
		LOGGER.info("updating cartsize(Beverages..");
		return "populate";
	}

	/**
	 * get the toppings
	 * 
	 * @return
	 */
	public String toppingList() {
		chooseSize = new ArrayList<String>();
		chooseSize.add("REGULAR");
		chooseSize.add("MEDIUM");
		chooseSize.add("LARGE");
		quantity = new ArrayList<Integer>();

		for (int i = 1; i <= 10; i++) {
			quantity.add(i);
		}
		ItemServiceImpl itemService = new ItemService();
		toppingList = itemService.getTopping();

		return "populate";
	}
	
	
	/**
	 * 
	 * for populating the topping pop up page for beverages
	 * @return
	 */
	public String bevToppingList() {
		chooseSize = new ArrayList<String>();
		chooseSize.add("REGULAR");
		chooseSize.add("MEDIUM");
		chooseSize.add("LARGE");
		quantity = new ArrayList<Integer>();

		for (int i = 1; i <= 10; i++) {
			quantity.add(i);
		}
		

		return "populate";
	}
	
	/**
	 * for populating the quantity list used for desserts pop up page
	 * 
	 * @return
	 */
	public String noToppingList() {
		
		quantity = new ArrayList<Integer>();

		for (int i = 1; i <= 10; i++) {
			quantity.add(i);
		}
		

		return "populate";
	}
	/**
	 * get list of toppings for side dishes pop up page
	 * 
	 * @return
	 */
	public String sideToppingList() {

		Topping tempTpg = new Topping();

		quantity = new ArrayList<Integer>();
		int count = 0;
		for (int i = 1; i <= 10; i++) {
			quantity.add(i);
		}
		ItemServiceImpl itemService = new ItemService();
		toppingList = itemService.getTopping();

		Iterator<Topping> iterator = toppingList.iterator();

		while (iterator.hasNext()) {
			tempTpg = iterator.next();

			if (tempTpg.getName().equals("EXTRA CHEESE")
					|| tempTpg.getName().equals("BLACK OLIVES")
					|| tempTpg.getName().equals(" BARBEQUE CHICKEN")
					|| tempTpg.getName().equals("PANEER")) {
				sideToppingList.add(tempTpg);

			}

		}

		return "populate";
	}

	/**
	 * Add a pizza order to cart
	 * 
	 * @return
	 */
	public String addToCartPizza() {

		String tempString = null;
		ItemModel tempItem;
		double tempPrice = 0;
		ItemServiceImpl itemService = new ItemService();
		List<Topping> tpgList = new ArrayList<Topping>();// used to get the
															// string converted
															// selected
		if (chooseSize!=null && !selectQuantity.equals("-1")){										// toppings
		session = ActionContext.getContext().getSession();
		int getQuant;
		getQuant = Integer.parseInt(getSelectQuantity());
		LOGGER.info("parsing the string in addToCartPizza method");
		try {

			if (cartList.isEmpty() || cartList.size() == 0) {
				cartList = (List<ItemModel>) session.get("cartList");
			}

			/*
			 * check if the list from the session is null, if yes initialize it
			 * to avoid null pointers
			 */

			if (null == cartList) {
				cartList = new ArrayList<ItemModel>();
			}

			itemCartList = itemService.getPizzaList();

			Iterator<String> iterator = selectTopping.iterator();
			/* ListIterator<Topping> tpgIterator = toppingList.listIterator(); */
			toppingList = itemService.getTopping();
			
			
				//adding the chosen toppings to topping list
			while (iterator.hasNext()) {

				String tempName = iterator.next();

				for (Topping tpg : toppingList) {

					if (tempName.equals(tpg.getName())) {

						tpgList.add(tpg);
						break;
					}

				}

			}

			Iterator<ItemModel> itr = itemCartList.iterator();
			if(chooseSize!=null){
				
			//calculating the item price and setting the cartlist
			while (itr.hasNext()) {
				tempItem = itr.next();
				tempString = tempItem.getItemName().replace(" ", "");
				tempPrice = (Double.parseDouble(tempItem.getItemPrice()))
						* getQuant;
				/*
				 * tempSize=tempItem.getItemSize();
				 
				 * +tempString+" tempSize :"+tempSize);
				 */
				if (tempString.equalsIgnoreCase(pizzaName.replace(" ", ""))
						&& tempItem.getItemSize().equalsIgnoreCase(
								chooseSize.get(0))) {
					tempItem.setQuantity(getQuant);
					tempItem.setToppingList(tpgList);
					tempItem.setItemSize(chooseSize.get(0));
					tempItem.setTotalPrice(tempPrice);
					cartList.add(tempItem);

				}
				
				// to convert string list to list of topping objects

			}
			}
		
			if (cartList != null) {
				cartSize = cartList.size();
				
			}

			session.put("cartList", cartList);
			toppingList();
			
			//to check the flag in the getPizza jsp
			checkFlag = "Y";

			// getQuant = Integer.parseInt(selectQuantity);

		} catch (IllegalAccessException e) {
			
			
		} catch (InvocationTargetException e) {
			LOGGER.error("error at addToCartPizza() ",e);
			e.printStackTrace();
		} catch (Exception e) {
			LOGGER.error("error at addToCartPizza() ",e);
			e.printStackTrace();
		}

		return "populate";
		}
		else
		{
			toppingList();
			addActionError(ApplConstants.ERROR_SIZE);
			LOGGER.error("error at addToCartPizza() ");
		
			return "error";
		}
		
	}

	
	/**
	 * Add a side item order to cart
	 * 
	 * @return
	 */
	public String addToCartSides() {
		Topping tempTpg = new Topping();
		String tempString = null;
		ItemModel tempItem;
		int tempPrice = 0;
		ItemServiceImpl itemService = new ItemService();
		List<Topping> tpgList = new ArrayList<Topping>();// used to get the list of toppings(string)
		if ( !selectQuantity.equals("-1"))	{																									
		session = ActionContext.getContext().getSession();
		int getQuant;
		getQuant = Integer.parseInt(selectQuantity);
		try {

			if (cartList.isEmpty() || cartList.size() == 0) {
				cartList = (List<ItemModel>) session.get("cartList");
			}

			/*
			 * check if the list from the session is null, if yes initialize it
			 * to avoid null pointers
			 */

			if (null == cartList) {
				cartList = new ArrayList<ItemModel>();
			}

			itemCartList = itemService.getSideList();

			/* ListIterator<Topping> tpgIterator = toppingList.listIterator(); */
			toppingList = itemService.getTopping();
			Iterator<Topping> itr = toppingList.iterator();

			while (itr.hasNext()) {
				tempTpg = itr.next();

				if (tempTpg.getName().equals("EXTRA CHEESE")
						|| tempTpg.getName().equals("BLACK OLIVES")
						|| tempTpg.getName().equals(" BARBEQUE CHICKEN")
						|| tempTpg.getName().equals("PANEER")) {
					sideToppingList.add(tempTpg);

				}

			}

			Iterator<String> iterator = selectSidesTopping.iterator();
			while (iterator.hasNext()) {

				String tempName = iterator.next();

				for (Topping tpg : sideToppingList) {

					if (tempName.equals(tpg.getName())) {

						tpgList.add(tpg);
						break;
					}

				}

			}

			Iterator<ItemModel> iter = itemCartList.iterator();
			while (iter.hasNext()) {
				tempItem = iter.next();
				tempString = tempItem.getItemName().replace(" ", "");
				tempPrice = (Integer.parseInt(tempItem.getItemPrice()))
						* getQuant;
				/*
				 * tempSize=tempItem.getItemSize();
				"
				 * +tempString+" tempSize :"+tempSize);
				 */
				if (tempString.equalsIgnoreCase(sideName.replace(" ", ""))) {
					tempItem.setQuantity(getQuant);
					tempItem.setToppingList(tpgList);
					tempItem.setTotalPrice(tempPrice);
					cartList.add(tempItem);

				}
				// cartSize = cartList.size();
				// to convert string list to list of topping objects

			}

			
			if (cartList != null) {
				cartSize = cartList.size();
				
			}

			session.put("cartList", cartList);
			sideToppingList();
			checkFlag = "Y";

			// getQuant = Integer.parseInt(selectQuantity);

		} catch (IllegalAccessException e) {
			LOGGER.error("error at addToCartSides() ",e);
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			LOGGER.error("error at addToCartSides() ",e);
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			LOGGER.error("error at addToCartSides() ",e);
			e.printStackTrace();
		}

		return "populate";
		}
		else
		{
			sideToppingList();
			addActionError(ApplConstants.ERROR_QUANT);
			LOGGER.error("error at addToCartSides() ");
			return "error";
		}
		
	}
	/**
	 * Add a dessert item order to cart
	 * 
	 * @return String
	 */
	public String addToCartDessert() {

		List<Topping> tpgList = new ArrayList<Topping>();
		String tempString = null;
		ItemModel tempItem;
		int tempPrice = 0;
		ItemServiceImpl itemService = new ItemService();
															
		if ( !selectQuantity.equals("-1"))	{											
															
		session = ActionContext.getContext().getSession();
		int getQuant;
		getQuant = Integer.parseInt(selectQuantity);
		try {

			if (cartList.isEmpty() || cartList.size() == 0) {
				cartList = (List<ItemModel>) session.get("cartList");
			}

			/*
			 * check if the list from the session is null, if yes initialize it
			 * to avoid null pointers
			 */

			if (null == cartList) {
				cartList = new ArrayList<ItemModel>();
			}

			itemCartList = itemService.getDessertList();


			

			Iterator<ItemModel> iter = itemCartList.iterator();
			while (iter.hasNext()) {
				tempItem = iter.next();
				tempString = tempItem.getItemName().replace(" ", "");
				tempPrice = (Integer.parseInt(tempItem.getItemPrice()))
						* getQuant;
				
				if (tempString.equalsIgnoreCase(dessertName.replace(" ", ""))) {
					tempItem.setQuantity(getQuant);
					tempItem.setTotalPrice(tempPrice);
					tempItem.setToppingList(tpgList);
					cartList.add(tempItem);

				}
				
				// to convert string list to list of topping objects

			}

			
			if (cartList != null) {
				cartSize = cartList.size();
			
			}

			session.put("cartList", cartList);
			noToppingList();
			checkFlag = "Y";

			

		} catch (IllegalAccessException e) {
			LOGGER.error("error at addToCartDessert() ",e);
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			LOGGER.error("error at addToCartDessert() ",e);
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			LOGGER.error("error at addToCartDessert() ",e);
			e.printStackTrace();
		}

		return "populate";
		}
		else
		{
			noToppingList();
			addActionError(ApplConstants.ERROR_QUANT);
			LOGGER.error("error at addToCartDessert() ");
			return "error";
		}
		
	}
	
	/**
	 * Add a beverage item order to cart
	 * 
	 * @return String
	 */
	public String addToCartBeverage() {
		List<Topping> tpgList = new ArrayList<Topping>();
		String tempString = null;
		ItemModel tempItem;
		int tempPrice = 0;
		ItemServiceImpl itemService = new ItemService();
		if ( !selectQuantity.equals("-1"))	{
		session = ActionContext.getContext().getSession();
		int getQuant;
		getQuant = Integer.parseInt(selectQuantity);
		try {

			if (cartList.isEmpty() || cartList.size() == 0) {
				cartList = (List<ItemModel>) session.get("cartList");
			}

			/*
			 * check if the list from the session is null, if yes initialize it
			 * to avoid null pointers
			 */

			if (null == cartList) {
				cartList = new ArrayList<ItemModel>();
			}

			itemCartList = itemService.getBeverageList();

			
			Iterator<ItemModel> itr = itemCartList.iterator();
			while (itr.hasNext()) {
				tempItem = itr.next();
				tempString = tempItem.getItemName().replace(" ", "");
				tempPrice = (Integer.parseInt(tempItem.getItemPrice()))
						* getQuant;
				
				if (tempString.equalsIgnoreCase(beverageName.replace(" ", ""))
						&& tempItem.getItemSize().equalsIgnoreCase(
								chooseSize.get(0))) {
					tempItem.setQuantity(getQuant);
					tempItem.setItemSize(chooseSize.get(0));
					tempItem.setTotalPrice(tempPrice);
					tempItem.setToppingList(tpgList);
					cartList.add(tempItem);

				}
				
				// to convert string list to list of topping objects

			}

			
			if (cartList != null) {
				cartSize = cartList.size();
				
			}

			session.put("cartList", cartList);
			toppingList();
			checkFlag = "Y";

			

		} catch (IllegalAccessException e) {
			LOGGER.error("error at addToCartBeverage() ",e);
			// TODO Auto-generated catch block
			
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			LOGGER.error("error at addToCartBeverage() ",e);
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			LOGGER.error("error at addToCartBeverage() ",e);
			e.printStackTrace();
		}

		return "populate";

	
	}
	
	else{
		
		toppingList();
		addActionError(ApplConstants.ERROR_SIZE);
		LOGGER.error("error at addToCartBeverage() ");
		return "error";
	}
	
	}
}
