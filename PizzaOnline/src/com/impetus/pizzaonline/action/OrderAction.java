package com.impetus.pizzaonline.action;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.impetus.pizzaonline.constants.ApplConstants;
import com.impetus.pizzaonline.model.ItemModel;
import com.impetus.pizzaonline.model.Offer;
import com.impetus.pizzaonline.model.Order;
import com.impetus.pizzaonline.model.User;
import com.impetus.pizzaonline.service.OfferService;
import com.impetus.pizzaonline.service.OfferServiceImpl;
import com.impetus.pizzaonline.service.OrderService;
import com.impetus.pizzaonline.service.OrderServiceImpl;
import com.impetus.pizzaonline.util.PizzaUtil;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

/**
 * Consists of all the methods in order to generate a proper a order or bill.
 * 
 * @author Nimmi
 *
 */
public class OrderAction extends ActionSupport{
	private static final Logger LOGGER = Logger.getLogger(OrderAction.class);
	Map<String, Object> session;
	private List<ItemModel> cartList = new ArrayList<ItemModel>();
	private List<Order> historyList = new ArrayList<Order>();
	private String userName;
	private String errorMsg;
	DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
	private String offer;
	private List<Offer> offerList = new ArrayList<Offer>();
	private String items;
	private int numberItems ;
	private double tax;
	private double totalPrice;
	private double discount;
	private String flatNo;
	private String bldgName;
	private String lane;
	private String area;
	private String city;
	private String pin;
	private String couponNumber;
	private String delivery;
	private String cardNumber;
	private String cvv;
	private String name;
	private String bank;
	private String mode;
	
	
	
	//public getters and setters
	
	public List<Order> getHistoryList() {
		return historyList;
	}

	public String getMode() {
		return mode;
	}

	public void setMode(String mode) {
		this.mode = mode;
	}

	public void setHistoryList(List<Order> historyList) {
		this.historyList = historyList;
	}

	public List<ItemModel> getCartList() {
		return cartList;
	}

	public void setCartList(List<ItemModel> cartList) {
		this.cartList = cartList;
	}

	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}
	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	public String getCvv() {
		return cvv;
	}

	public void setCvv(String cvv) {
		this.cvv = cvv;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBank() {
		return bank;
	}

	public void setBank(String bank) {
		this.bank = bank;
	}

	public String getDelivery() {
		return delivery;
	}

	public void setDelivery(String delivery) {
		this.delivery = delivery;
	}

	public String getCouponNumber() {
		return couponNumber;
	}

	public void setCouponNumber(String couponNumber) {
		this.couponNumber = couponNumber;
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

	public String getItems() {
		return items;
	}

	public void setItems(String items) {
		this.items = items;
	}

	public int getNumberItems() {
		return numberItems;
	}

	public void setNumberItems(int numberItems) {
		this.numberItems = numberItems;
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

	public double getDiscount() {
		return discount;
	}

	public void setDiscount(double discount) {
		this.discount = discount;
	}

	public List<Offer> getOfferList() {
		return offerList;
	}

	public void setOfferList(List<Offer> offerList) {
		this.offerList = offerList;
	}

	public String getOffer() {
		return offer;
	}

	public void setOffer(String offer) {
		this.offer = offer;
	}

	
	
	/**
	 * to validate the offer days,coupon number and minimum bill.
	 * to calculate the items in the cartlist and insert into the database
	 * 
	 * @return
	 */
	public String insertOrder() {
		session = ActionContext.getContext().getSession();
		
		User user = new User();
		User cust = new User();
		cust=(User) session.get("customer");
		user = (User) session.get("getUser");
		
		String email="";
		errorMsg = null;
		boolean insert = false;
		boolean sendMail=false;
		String day = "";
		OrderServiceImpl newOrder = new OrderService();
		Order order = new Order();
		double tax = 0;
		
		
		String userName;
		int Cust_id = 0;
		int Stf_id = 0;
		int tempQuantity = 0;
		double finalTotal = 0;
		double tempDiscount=0;
		
		int ofrId=101;
		StringBuffer string = new StringBuffer();
		
		Calendar cal = new GregorianCalendar();
		Offer ofr = new Offer();

		OfferServiceImpl newOffer = new OfferService();
		if(offer!=null){
		offerList = newOffer.getOffer(this.offer.toUpperCase());
		}
		if(offerList!=null && offerList.size()!=0){
		ofr = offerList.get(0);
		}
		
		StringBuffer str = new StringBuffer();
		String validDays = ofr.getValidDays();
		ItemModel tempItr = null;
		double tempPrice = 0;

		if (this.offer != null)
		/*to check the day for the offer is valid
*/			{
			int today = cal.get(Calendar.DAY_OF_WEEK);
			switch (today) {
			case 1:
				day = "SUNDAY";
				break;
			case 2:
				day = "MONDAY";
				break;
			case 3:
				day = "TUESDAY";
				break;
			case 4:
				day = "WEDNESDAY";
				break;
			case 5:
				day = "THURSDAY";
				break;
			case 6:
				day = "FRIDAY";
				break;
			case 7:
				day = "SATURDAY";
				break;
			}
		} 

		boolean validDay = PizzaUtil.validateOffer(day, validDays);
		if(this.couponNumber.equals("")){
			couponNumber="NO COUPON";
		}
		boolean validate = newOffer.validateOffer(couponNumber, validDays);
		boolean validBill = false;
		
		if (cartList.isEmpty() || cartList.size() == 0) {
			
			
			cartList = (List<ItemModel>) session.get("cartList");
			totalPrice = (Double) session.get("totalPrice");
			
		}
		if (cartList != null && cartList.size() != 0) {
			Iterator<ItemModel> iterator = cartList.iterator();

			while (iterator.hasNext()) {
				tempItr = iterator.next();
				tempPrice += tempItr.getTotalPrice();

			}
		}
		
		if (offerList!=null && offerList.size()!=0){
		if (tempPrice >= offerList.get(0).getMinBill()) {

			validBill = true;

		} else {

			validBill = false;

		}
		}
		else
		{
			
			validBill=false;
		}
		
		
		if (validDay == true && validate == true && validBill == true) {
								
			userName = user.getUserName();
			delivery=(String) session.get("delivery");
			
			//to set the customer and user id in the table
			if (user.getRole().equals("Customer") ) {
				Cust_id = user.getId();
				Stf_id = 101;
				email=user.getEmail();
			} else {
				Cust_id = cust.getId();
				Stf_id = user.getId();
				email=cust.getEmail();
			}
		
		if(user.getRole().equals("Customer")){
		if(this.flatNo!=null ){
			
			string.append(flatNo + ",");
		}
		if(this.bldgName!=null){
			
			string.append(bldgName + ",");
		}
		if(this.lane!=null){
			
			string.append(lane+ ",");
		}
		if(this.area!=null){
			
			string.append(area + ",");
		}
		if(this.city!=null){
			
			string.append(city + ",");
		}
		if(this.pin!=null){
			string.append("Pin:"+ pin);
			
		}
		}	else{
			
			if(cust.getFlatNo()!=null ){
				
				string.append(cust.getFlatNo() + ",");
			}
			if(cust.getBldgName()!=null){
				
				string.append(cust.getBldgName() + ",");
			}
			if(cust.getRoad()!=null){
				
				string.append(cust.getRoad()+ ",");
			}
			if(cust.getPlace()!=null){
				
				string.append(cust.getPlace() + ",");
			}
			if(cust.getCity()!=null){
				
				string.append(getCity() + ",");
			}
			if(cust.getPin()!=0){
				string.append("Pin:"+ cust.getPin());
				
			}
			
			
			
		}
		if (offerList!=null && offerList.size()!=0){
			
			 ofrId=offerList.get(0).getId();
			discount=offerList.get(0).getDiscount();
			
		}
		if (cartList != null && cartList.size() != 0) {
			 tempPrice = 0;
			Iterator<ItemModel> iterator = cartList.iterator();

			while (iterator.hasNext()) {
				tempItr = iterator.next();

				str.append(tempItr.getItemName() + ",");
				tempPrice += tempItr.getTotalPrice();
				tempQuantity += tempItr.getQuantity();

			}
			tax = (ApplConstants.TAX * tempPrice);
			
			tempDiscount=totalPrice *((discount)*0.01);
			finalTotal = totalPrice - tempDiscount;
			//setting the order object
			order.setItemNames(str.toString().substring(0, str.length() - 1));
			order.setItemPrice(tempPrice);
			order.setTotalAmount(finalTotal);
			order.setCust_id(Cust_id);
			order.setStaff_id(Stf_id);                                                                       
			order.setOfr_id(ofrId);
			order.setOrderAddress(string.toString());
			order.setTax(tax);
			order.setTotalItems(tempQuantity);
			order.setOrderDate(formatter.format(new Date()));
			order.setDelivery(delivery);
			
			LOGGER.info("inserting the order object into order table(OrderACtion,insertOrder)");
			insert = newOrder.insertOrder(order);
			
			
			setDiscount(discount);
			setNumberItems(order.getTotalItems());
			setItems(order.getItemNames());
			setTax(order.getTax());
			setTotalPrice(order.getTotalAmount());

			session.remove("cartList");
		}

		if (insert == true) {
			String newLine = System.getProperty("line.separator");
			String	getPrice=new DecimalFormat("#.##").format(order.getTotalAmount());
			String msg="Your Confirmation from Pizza Italiano"+newLine+"Your Items:"+order.getItemNames()+newLine+"Total Amount:"+getPrice;
				 
			sendMail=PizzaUtil.sendMail(msg,email);
			
			LOGGER.info("Successful insertion (new order)");
			
			return "success";
		} else {
		
			addActionError(ApplConstants.ERROR_OFFER);
			return "error";
		}
		}
		
		else {
			
			addActionError(ApplConstants.ERROR_OFFER);
			LOGGER.error("Unsuccessful insertion (new order)");
			return "error";
		}
	}

	/**
	 * 
	 * to get the userHistory according to the logged in user
	 * @return
	 */
	public String getUserHistory() {
		OrderServiceImpl orderService = new OrderService();
		User user = new User();
		User cust = new User();
		int userId = 0;
		session = ActionContext.getContext().getSession();
		user = (User) session.get("getUser");
		if (user != null && user.getRole().equals("Customer")) {
			userId = user.getId();

			historyList = orderService.getUserHistory(userId);
		}
		else
		{
			cust=(User) session.get("customer");
			userId=cust.getId();
			historyList = orderService.getUserHistory(userId);
		}
		if (historyList != null && !historyList.isEmpty()  ) {
			LOGGER.info("successful retrieving of userHistory)");
			return "success";
		} else {
			LOGGER.error("Error at getUserHistory()");
			return "error";
		}
	}

	
}
