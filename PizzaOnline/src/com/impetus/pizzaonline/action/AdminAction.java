package com.impetus.pizzaonline.action;

import java.awt.Image;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.interceptor.ServletRequestAware;


import com.impetus.pizzaonline.constants.ApplConstants;
import com.impetus.pizzaonline.model.Item;
import com.impetus.pizzaonline.model.Offer;
import com.impetus.pizzaonline.model.Order;
import com.impetus.pizzaonline.model.Staff;
import com.impetus.pizzaonline.model.Topping;
import com.impetus.pizzaonline.model.User;
import com.impetus.pizzaonline.service.ItemService;
import com.impetus.pizzaonline.service.ItemServiceImpl;
import com.impetus.pizzaonline.service.LoginService;
import com.impetus.pizzaonline.service.LoginServiceImpl;
import com.impetus.pizzaonline.service.OfferService;
import com.impetus.pizzaonline.service.OfferServiceImpl;
import com.impetus.pizzaonline.service.OrderService;
import com.impetus.pizzaonline.service.OrderServiceImpl;
import com.impetus.pizzaonline.util.PizzaUtil;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.validator.annotations.RequiredStringValidator;
import com.opensymphony.xwork2.validator.annotations.Validation;
import com.opensymphony.xwork2.validator.annotations.VisitorFieldValidator;


/**
 * this class consists of all the methods dealing with the admin actions
 * @author Nimmi
 *
 */

public class AdminAction extends ActionSupport implements ServletRequestAware {
private String itemName;
private String itemType;
private String itemSize;
private List<String> chooseSize = new ArrayList<String>();
private String description;
private String actInd;
private String itemPrice;
private String itemCategory;
private List<Order> historyList = new ArrayList<Order>();
private List<Item> itemList = new ArrayList<Item>();
private List<Topping> tpgList = new ArrayList<Topping>();
private List<Offer> offerList = new ArrayList<Offer>();
private String designation;
private String name;
private String city;
private String location;
private String dob;
private String userName;
private String password;
private double mobile;
private String email;
private List<Staff> staffList = new ArrayList<Staff>();
private String startDate;
private String endDate;
private String coupon;
private String minBill;
private String discount;
private List<String> daysList = new ArrayList<String>();
private List<String> checkList= new ArrayList<String>();
Map<String,Object> session;
private List<String> populateList = new ArrayList<String>();
private String selectOffer;
private File userImage;
private String userImageContentType;
private String userImageFileName;
private HttpServletRequest servletRequest;
private String tpgName;
private String tpgType;
private String tpgPrice;
private String tpgCategory;
private List<String> popTpgList=new ArrayList<String>();


public List<String> getPopTpgList() {
	return popTpgList;
}
public void setPopTpgList(List<String> popTpgList) {
	this.popTpgList = popTpgList;
}
public List<Topping> getTpgList() {
	return tpgList;
}
public void setTpgList(List<Topping> tpgList) {
	this.tpgList = tpgList;
}
public String getTpgName() {
	return tpgName;
}
public void setTpgName(String tpgName) {
	this.tpgName = tpgName;
}
public String getTpgType() {
	return tpgType;
}
public void setTpgType(String tpgType) {
	this.tpgType = tpgType;
}
public String getTpgPrice() {
	return tpgPrice;
}
public void setTpgPrice(String tpgPrice) {
	this.tpgPrice = tpgPrice;
}
public String getTpgCategory() {
	return tpgCategory;
}
public void setTpgCategory(String tpgCategory) {
	this.tpgCategory = tpgCategory;
}
public void setServletRequest(HttpServletRequest servletRequest) {
	this.servletRequest = servletRequest;
}
public File getUserImage() {
	return userImage;
}
public void setUserImage(File userImage) {
	this.userImage = userImage;
}
public String getUserImageContentType() {
	return userImageContentType;
}
public void setUserImageContentType(String userImageContentType) {
	this.userImageContentType = userImageContentType;
}
public String getUserImageFileName() {
	return userImageFileName;
}
public void setUserImageFileName(String userImageFileName) {
	this.userImageFileName = userImageFileName;
}
public String getSelectOffer() {
	return selectOffer;
}
public void setSelectOffer(String selectOffer) {
	this.selectOffer = selectOffer;
}
public List<String> getPopulateList() {
	return populateList;
}
public void setPopulateList(List<String> populateList) {
	this.populateList = populateList;
}

public List<String> getCheckList() {
	return checkList;
}  
public void setCheckList(List<String> checkList) {
	this.checkList = checkList;
}
public String getCoupon() {
	return coupon;
}
public void setCoupon(String coupon) {
	this.coupon = coupon;
}
public String getMinBill() {
	return minBill;
}
public void setMinBill(String minBill) {
	this.minBill = minBill;
}
public String getDiscount() {
	return discount;
}
public void setDiscount(String discount) {
	this.discount = discount;
}
public List<String> getDaysList() {
	return daysList;
}
public void setDaysList(List<String> daysList) {
	this.daysList = daysList;
}
public List<Offer> getOfferList() {
	return offerList;
}
public void setOfferList(List<Offer> offerList) {
	this.offerList = offerList;
}
public String getStartDate() {
	return startDate;
}
public void setStartDate(String startDate) {
	this.startDate = startDate;
}
public String getEndDate() {
	return endDate;
}
public void setEndDate(String endDate) {
	this.endDate = endDate;
}
public List<Staff> getStaffList() {
	return staffList;
}
public void setStaffList(List<Staff> staffList) {
	this.staffList = staffList;
}
public String getDesignation() {
	return designation;
}
public void setDesignation(String designation) {
	this.designation = designation;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public String getCity() {
	return city;
}
public void setCity(String city) {
	this.city = city;
}
public String getLocation() {
	return location;
}
public void setLocation(String location) {
	this.location = location;
}
public String getDob() {
	return dob;
}
public void setDob(String dob) {
	this.dob = dob;
}
public String getUserName() {
	return userName;
}
public void setUserName(String userName) {
	this.userName = userName;
}
public String getPassword() {
	return password;
}
public void setPassword(String password) {
	this.password = password;
}
public double getMobile() {
	return mobile;
}
public void setMobile(double mobile) {
	this.mobile = mobile;
}
public String getEmail() {
	return email;
}
public void setEmail(String email) {
	this.email = email;
}
public List<Item> getItemList() {
	return itemList;
}
public void setItemList(List<Item> itemList) {
	this.itemList = itemList;
}
public List<Order> getHistoryList() {
	return historyList;
}
public void setHistoryList(List<Order> historyList) {
	this.historyList = historyList;
}
public String getItemCategory() {
	return itemCategory;
}
public void setItemCategory(String itemCategory) {
	this.itemCategory = itemCategory;
}
public String getItemName() {
	return itemName;
}
public void setItemName(String itemName) {
	this.itemName = itemName;
}
public String getItemType() {
	return itemType;
}
public void setItemType(String itemType) {
	this.itemType = itemType;
}
public String getItemSize() {
	return itemSize;
}
public void setItemSize(String itemSize) {
	this.itemSize = itemSize;
}
public List<String> getChooseSize() {
	return chooseSize;
}
public void setChooseSize(List<String> chooseSize) {
	this.chooseSize = chooseSize;
}
public String getDescription() {
	return description;
}
public void setDescription(String description) {
	this.description = description;
}
public String getActInd() {
	return actInd;
}
public void setActInd(String actInd) {
	this.actInd = actInd;
}
public String getItemPrice() {
	return itemPrice;
}
public void setItemPrice(String itemPrice) {
	this.itemPrice = itemPrice;
}


/**
 * Populating a choose size list
 * 
 * @return String
 */
public String populateSize(){
	
	chooseSize.add("NO SIZE");
	chooseSize.add("REGULAR");
	chooseSize.add("MEDIUM");
	chooseSize.add("LARGE");
	ItemServiceImpl newItem = new ItemService();
	if(newItem.getItem()!=null){
	itemList =newItem.getItem();
	for (Item s : itemList) {
		populateList.add(s.getItemName());
	}
	}
	if(newItem.getTpg()!=null){
		
		tpgList=newItem.getTpg();
				
		for ( Topping t : tpgList) {
			popTpgList.add(t.getName());
		}
	}
	
	return "populate";
}

public String populateStaff(){
	
	LoginServiceImpl newStaff = new LoginService();
	
	if(newStaff.getStaff()!=null){
		staffList =newStaff.getStaff();
	for (Staff s : staffList) {
		populateList.add(s.getName());
	}
	}
	
	return "populate";
}
/**
 * to set the values of item class got from the user
 * 
 * @return String to struts2
 */
public String insertItem(){
	Item item = new Item();
	boolean insert=false;
	ItemServiceImpl newItem = new ItemService();
	item.setActInd('Y');
	item.setItemCategory(this.itemCategory.toUpperCase());
	item.setItemDesc(this.description.toUpperCase());
	item.setItemName(this.itemName.toUpperCase());
	item.setItemPrice(this.itemPrice.toUpperCase());
	item.setItemSize(this.itemSize.toUpperCase());
	item.setType(this.itemType.toUpperCase());
	item.setToppingList(new ArrayList<Topping>());
	insert =newItem.insertItem(item);
 
	
	if (insert==true){
		try {
	         String filePath = servletRequest.getSession().getServletContext().getRealPath("/");
	        
	         String tempName=this.itemName.replace(" ","").toUpperCase();
	         File fileToCreate = new File( "/PizzaOnline/WebContent/images/", tempName + ".gif");

	         FileUtils.copyFile(this.userImage, fileToCreate);
	     } catch (Exception e) {
	         e.printStackTrace();
	         addActionError(e.getMessage());

	         return INPUT;
	     }
		addActionMessage(ApplConstants.ITEM_SUCCESS);
	return "success";
	}
	else
	{
		addActionError("Item Exists!");
		return "error";
	}
	

}
/**
 * To get the value from the user to remove the items
 * 
 * @return String
 */
public String removeItem(){
	Item item = new Item();
	boolean remove=false;
	ItemServiceImpl newItem = new ItemService();
	if(newItem.getItem(this.itemName,this.itemSize)!=null){
	itemList =newItem.getItem(this.itemName,this.itemSize);
	item = itemList.get(0);
	item.setActInd('N');
	}
	remove=newItem.removeItem(item);
	if (remove==true){
		addActionMessage(ApplConstants.ITMRMV_SUCC);
	return "success";
	}
	else
	{
		addActionError(ApplConstants.ERROR);
		return "errorMsg";
	}
}

public String insertTpg(){
	Topping topping =  new Topping();
	ItemServiceImpl newTopping = new ItemService();
	boolean insert=false;
	
	topping.setAct_ind('Y');
	topping.setCategory(this.tpgCategory.toUpperCase());
	topping.setName(this.tpgName.toUpperCase());
	topping.setPrice(this.tpgPrice);
	topping.setType(this.tpgType.toUpperCase());
	
	insert= newTopping.insertTopping(topping);
	if (insert==true){
		addActionMessage(ApplConstants.ITEM_SUCCESS);
		return "success";
	}
	else{
		
		addActionError("Item Exists!");
		return "error";
		
	}
	
}
public String removeTopping(){
	Topping topping =  new Topping();
	ItemServiceImpl newTopping = new ItemService();
	boolean remove=false;
	
		if (newTopping.getTopping() != null) {
			tpgList = newTopping.getTopping();
			Iterator<Topping> iterator = tpgList.iterator();
			while (iterator.hasNext()) {
				Topping temp = iterator.next();
				if (temp.getName().equalsIgnoreCase(this.tpgName)
						&& temp.getType().equalsIgnoreCase(this.tpgType)) {
					topping = temp;
				}

			}
			topping.setAct_ind('N');
		}
	remove=newTopping.removeTopping(topping);
	if (remove==true){
		addActionMessage(ApplConstants.ITMRMV_SUCC);
	return "success";
	}
	else
	{
		addActionError(ApplConstants.ERROR);
		return "errorMsg";
	}
}
/**
 * to insert the staff details and set the staff object
 * 
 * @return String
 */
public String registerStaff(){
	Staff staff = new Staff();
	boolean register=false;
	LoginServiceImpl newLogin = new LoginService();
	staff.setRole(this.designation.toUpperCase());
	staff.setName(this.name.toUpperCase());
	staff.setCity(this.city.toUpperCase());
	staff.setLocation(this.location.toUpperCase());
	staff.setDob(this.dob);
	staff.setMobile(this.mobile);
	staff.setEmail(this.email.toLowerCase());
	staff.setUserName(this.userName.toLowerCase());
	staff.setPassword(this.password.toLowerCase());
	staff.setAct_ind('Y');
	register =newLogin.insertStaffUser(staff);
	if (register==true){
		addActionMessage(ApplConstants.STAFF_SUCCESS);
	return "success";
	}
	else
	{
		addActionError(ApplConstants.DUPLICATE_USER);
		return "errorMsg";
	}
	
	
}

/**
 * to send the name of staff to be removed and setting the act ind as N
 * 
 * @return String
 */
public String removeStaff(){
	Staff staff = new Staff();
	boolean remove=false;
	LoginServiceImpl newStaff = new LoginService();
	if(newStaff.getStaff(this.name.toUpperCase())!=null){
	staffList =newStaff.getStaff(this.name.toUpperCase());
	staff = staffList.get(0);
	staff.setAct_ind('N');
	}
	remove=newStaff.removeStaff(staff);
	if (remove==true){
		addActionMessage(ApplConstants.STAFF_REMOVE);
	return "success";
	}
	else
	{
		addActionError(ApplConstants.ERROR);
		return "errorMsg";
	}
}


/**
 * to get the list of customer orders
 * 
 * @return String
 */
public String getCustomerOrder() {
	OrderServiceImpl orderService = new OrderService();
	
	
	
	historyList = orderService.getCustomerOrder();
	
	if (historyList!=null && !historyList.isEmpty()) {
		return "success";
	} else {
		addActionError(ApplConstants.ERROR);
		return "error";
	}
}


/**
 * To set the values of offer object to insert an offer in the database
 * 
 * @return String
 */
public String insertOffer(){
	int tempMinBill=Integer.parseInt(this.minBill);
	int tempDiscount=Integer.parseInt(this.discount);
	session = ActionContext.getContext().getSession();
	daysList=(List<String>) session.get("daysList");
	StringBuffer str = new StringBuffer();
	LinkedHashMap<String,String>  weekMap;
	weekMap=PizzaUtil.weekMap();
	
	 Iterator<Map.Entry<String, String>> it = weekMap.entrySet().iterator();
	 /*to set the valid days string selected by the admin*/	
	if (checkList!=null && checkList.size()!=0){ 
	 while(it.hasNext()){
		 
		 Map.Entry<String, String> pairs = it.next();
		 String key=pairs.getKey();
		
		 for (String s :checkList){
			 
			 if(key.equalsIgnoreCase(s)){
				 weekMap.put((String) pairs.getKey(), "1");
				 //pairs.put(key,"1");
				 break;
			 }
			 
		 }
	 
	 }
	
	 for (String value : weekMap.values()) {
		    str.append(value);
		}
	 
	String tempdays=str.toString();
	
	boolean insert=false;
	Offer offer = new Offer();
	OfferServiceImpl newOffer = new OfferService();
	offer.setDiscount(tempDiscount);
	offer.setValidDays(tempdays);
	offer.setMinBill(tempMinBill);
	offer.setCouponCode(this.coupon.toUpperCase());
	offer.setActInd('Y');
	offer.setDescription(this.description.toUpperCase());
	offer.setStartDate(this.startDate);
	offer.setEndDate(this.endDate);
	insert =newOffer.insertOffer(offer);
	
	if (insert==true){
		addActionMessage(ApplConstants.SUCCESS);
	return "success";
	}
	else
	{
		addActionError(ApplConstants.ERROR);
		return "errorMsg";
	}	
}
	
	else{
		
		addActionError(ApplConstants.DAY_MSG);
		return "errorMsg";
	}
}



/**to populate the list of days in the insert offer table
 * 
 * @return String
 */
public String populateDays(){
	
	daysList.add("Sunday");
	daysList.add("Monday");
	daysList.add("Tuesday");
	daysList.add("Wednesday");
	daysList.add("Thursday");
	daysList.add("Friday");
	daysList.add("Saturday");
	OfferServiceImpl offer = new OfferService();
	offerList = offer.getOffer();
	for (Offer s : offerList) {
		populateList.add(s.getDescription());
	}
	session = ActionContext.getContext().getSession();
	session.put("daysList", daysList);
	
	return "populate";
}

/**
 * for setting the active indicator for the selected offer as N
 * @return String
 */
public String removeOffer(){
	
	Offer offer = new Offer();
	boolean remove=false;
	OfferServiceImpl newOffer = new OfferService();
	offerList =newOffer.getOffer(this.selectOffer.toUpperCase());
	offer = offerList.get(0);
	offer.setActInd('N');
	remove=newOffer.removeOffer(offer);
	if (remove==true) {
		addActionMessage(ApplConstants.SUCCESS);
	return "success";
	}
	else
	{
		addActionError(ApplConstants.DUPLICATE_OFFER);
		return "errorMsg";
	}
}



}
