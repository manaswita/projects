package com.impetus.pizzaonline.action;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.impetus.pizzaonline.constants.ApplConstants;
import com.impetus.pizzaonline.model.ItemModel;
import com.impetus.pizzaonline.model.Offer;
import com.impetus.pizzaonline.model.Order;
import com.impetus.pizzaonline.model.Topping;
import com.impetus.pizzaonline.model.User;
import com.impetus.pizzaonline.service.LoginService;
import com.impetus.pizzaonline.service.LoginServiceImpl;
import com.impetus.pizzaonline.service.OfferService;
import com.impetus.pizzaonline.service.OfferServiceImpl;
import com.impetus.pizzaonline.service.OrderService;
import com.impetus.pizzaonline.service.OrderServiceImpl;
import com.impetus.pizzaonline.util.PizzaUtil;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

/**
 * This class consists of all the methods dealing with the payment by user.
 * 
 * @author Nimmi
 *
 */
public class PaymentAction extends ActionSupport {
	private List<ItemModel> cartList = new ArrayList<ItemModel>();
	private List<Integer> monthList = new ArrayList<Integer>();
	private List<Integer> yearList = new ArrayList<Integer>();
	private List<String> bankList = new ArrayList<String>();
	Map<String, Object> session;
	
	private List<String> removeCartItem = new ArrayList<String>();
	private String defaultCart;
	private List<Offer> offerList = new ArrayList<Offer>();
	private List<String> populateList = new ArrayList<String>();
	private String offer;
	private String couponNumber;
	private String flatNo;
	private String bldgName;
	private String lane;
	private String area;
	private String city;
	private String pin;
	private double totalPrice;
	private int totalItems;
	private double tax;
	private double deliveryCharges;
	private double discount = 0;
	private String mobile;

	
	
	
	

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public double getDiscount() {
		return discount;
	}

	public void setDiscount(double discount) {
		this.discount = discount;
	}

	public double getDeliveryCharges() {
		return deliveryCharges;
	}

	public void setDeliveryCharges(double deliveryCharges) {
		this.deliveryCharges = deliveryCharges;
	}

	public double getTax() {
		return tax;
	}

	public void setTax(double tax) {
		this.tax = tax;
	}

	public double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public int getTotalItems() {
		return totalItems;
	}

	public void setTotalItems(int totalItems) {
		this.totalItems = totalItems;
	}

	public List<Offer> getOfferList() {
		return offerList;
	}

	public void setOfferList(List<Offer> offerList) {
		this.offerList = offerList;
	}

	public String getFlatNo() {
		return flatNo;
	}

	public void setFlatNo(String flatNo) {
		this.flatNo = flatNo;
	}

	public String getBldgName() {
		return bldgName;
	}

	public void setBldgName(String bldgName) {
		this.bldgName = bldgName;
	}

	public String getLane() {
		return lane;
	}

	public void setLane(String lane) {
		this.lane = lane;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getPin() {
		return pin;
	}

	public void setPin(String pin) {
		this.pin = pin;
	}

	public String getCouponNumber() {
		return couponNumber;
	}

	public void setCouponNumber(String couponNumber) {
		this.couponNumber = couponNumber;
	}

	public String getOffer() {
		return offer;
	}

	public void setOffer(String offer) {
		this.offer = offer;
	}

	public List<String> getPopulateList() {
		return populateList;
	}

	public void setPopulateList(List<String> populateList) {
		this.populateList = populateList;
	}

	public String getDefaultCart() {
		return defaultCart = "PIZZA SPICY CHICKEN";
	}

	public void setDefaultCart(String defaultCart) {
		this.defaultCart = defaultCart;
	}

	public List<String> getRemoveCartItem() {
		return removeCartItem;
	}

	public void setRemoveCartItem(List<String> removeCartItem) {
		this.removeCartItem = removeCartItem;
	}


	public List<Integer> getMonthList() {
		return monthList;
	}

	public void setMonthList(List<Integer> monthList) {
		this.monthList = monthList;
	}

	public List<Integer> getYearList() {
		return yearList;
	}

	public void setYearList(List<Integer> yearList) {
		this.yearList = yearList;
	}

	public List<ItemModel> getCartList() {
		return cartList;
	}

	public void setCartList(List<ItemModel> cartList) {
		this.cartList = cartList;
	}

	public List<String> getBankList() {
		return bankList;
	}

	public void setBankList(List<String> bankList) {
		this.bankList = bankList;
	}

	
	
	
	/**
	 * 
	 * to calculate the item and topping prices and showing the cart
	 * @return String
	 */
	public String showCart() {
		
		

		int cartSize = 0;
		List<String> tempList;
		String tempItem;
		List<Topping> tempTopping = null;

		ItemModel tempItr = null;
		String tempPrice = null;

		if (cartList.isEmpty() || cartList.size() == 0) {
			session = ActionContext.getContext().getSession();
			cartList = (List<ItemModel>) session.get("cartList");
			offerList = (List<Offer>) session.get("offerList");

		}
		if (offerList != null && offerList.size() != 0) {
			setDiscount(offerList.get(0).getDiscount());

		}

		if (cartList != null && cartList.size() != 0) {

			Iterator<ItemModel> iterator = cartList.iterator();

			while (iterator.hasNext()) {
				StringBuffer str = new StringBuffer();
				int tempItemPrice = 0;
				int tpgPrice = 0;
				tempItr = iterator.next();
				tempItem = tempItr.getItemName();
				tempTopping = new ArrayList<Topping>();
				tempTopping = (List<Topping>) tempItr.getToppingList();
				if (tempTopping != null && !tempTopping.isEmpty()) {
					for (Topping t : tempTopping) {

						str.append(t.getName() + ",");
						tempPrice = t.getPrice();

						tpgPrice += Integer.parseInt(tempPrice);

					}
				}
				tempItemPrice += tempItr.getTotalPrice() + tpgPrice;

				tempItr.setTotalPrice(tempItemPrice);
				if (tempTopping.isEmpty() || tempTopping == null) {
					tempItr.setToppingName("");

				} else {
					tempItr.setToppingName(str.substring(0, str.length() - 1)
							.toString());
				}
			}

		}

		if (cartList != null && cartList.size() != 0) {
			totalPrice = PizzaUtil.calculateTotal(cartList, this) - discount;
			
		}
		
		session.put("totalPrice", totalPrice);
		session.put("cartList", cartList);
		cartSize = cartList.size();
		if (cartList == null || cartList.isEmpty()) {
			return "error";
		} else {
			return "success";
		}

	}

	/**
	 * 
	 * populating the bank list
	 * @return String
	 */
	public String payment() {

		session = ActionContext.getContext().getSession();

		User user = (User) session.get("getUser");

		if (user.getBldgName() != null) {
			setBldgName(user.getBldgName());
		} else {

			setBldgName("");

		}
		if (user.getPlace() != null) {
			setArea(user.getPlace());
		} else {

			setArea("");

		}
		if (user.getCity() != null) {
			setCity(user.getCity());
		} else {

			setCity("");

		}
		if (user.getRoad() != null) {
			setLane(user.getRoad());
		} else {

			setLane("");

		}
		if (user.getPin() != 0) {
			setPin(Double.toString(user.getPin()));
		} else {

			setPin("");

		}
		if (user.getMobile()!=0) {
			setMobile(Long.toString(user.getMobile()));
		} else {

			setMobile("");

		}

		for (int i = 0; i < 12; i++) {

			monthList.add(i);
		}
		for (int i = 0; i < 2020; i++) {

			yearList.add(i);
		}
		bankList.add("HDFC");
		bankList.add("CITIBANK");
		bankList.add("FEDERAL BANK");
		bankList.add("AXIS BANK");
		bankList.add("STATE BANK OF INDIA");
		bankList.add("STATE BANK OF TRAVENCORE");
		bankList.add("STATE BANK OF PUNJAB");
		bankList.add("CORPORATION BANK");
		bankList.add("SYNDICATE BANK");
		bankList.add("ICICI BANK");
		bankList.add("DHANALAXMI BANK");
		bankList.add("KOTAK MAHINDRA");
		bankList.add("MANSI BANK");
		bankList.add("NIMMI BANK");
		bankList.add("SANGEETHA BANK");

		OfferServiceImpl offer = new OfferService();
		offerList = offer.getOffer();
		for (Offer s : offerList) {
			if (s.getDescription() != null) {
				populateList.add(s.getDescription());
			}
		}
		return "success";
	}
	
	
	

	/**
	 * 
	 * to remove an item from cart
	 * @return String
	 */
	public String removeCart() {
		ItemModel item = new ItemModel();
		

		if (cartList.isEmpty() || cartList.size() == 0) {
			session = ActionContext.getContext().getSession();
			cartList = (List<ItemModel>) session.get("cartList");

		}
		if (cartList != null && cartList.size() != 0) {
			Iterator<ItemModel> iterator = cartList.iterator();

			for (String s : removeCartItem) {
				while (iterator.hasNext()) {
					item = iterator.next();
					if (s.equals(item.getItemName())) {

						iterator.remove();
						break;
					}

				}

			}
			if (cartList != null && cartList.size() != 0) {
				totalPrice = PizzaUtil.calculateTotal(cartList, this);
				session.put("totalPrice", totalPrice);
				session.put("cartList", cartList);
			}
		}

		return "success";
	}

	
	
/**
 * 
 * to display the available offers to customer
 * @return String
 */
public String includeOffer(){
		
		
		OfferServiceImpl offer = new OfferService();
		offerList = offer.getOffer();
	Iterator<Offer> iterator = offerList.iterator();
	while(iterator.hasNext()){
		
		Offer temp=iterator.next();
		
		if(!temp.getDescription().equals("NO OFFER") && temp.getDescription()!=null){
			 populateList.add(temp.getDescription());
			
		}
		
		
	}
	
		
		return "populate";
	}
	

}
