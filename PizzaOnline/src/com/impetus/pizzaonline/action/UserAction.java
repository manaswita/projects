package com.impetus.pizzaonline.action;

import java.lang.reflect.InvocationTargetException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.log4j.Logger;

import com.impetus.pizzaonline.model.Customer;
import com.impetus.pizzaonline.model.Offer;
import com.impetus.pizzaonline.model.User;
import com.impetus.pizzaonline.service.LoginService;
import com.impetus.pizzaonline.service.LoginServiceImpl;
import com.impetus.pizzaonline.util.PizzaUtil;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

/**
 * This class consists of all the actions related to user ie,customer,BPO and Admin.here there are usecases such as login,register and  logout.
 *It also contains all the variables and their getters and setters used for rendering in the login and register pages.
 *
 * @author nimmi.menon
 *
 */

public class UserAction extends ActionSupport {

	
	private final static  Logger LOGGER = Logger.getLogger(UserAction.class);
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String name;
	private String flatNo;
	private String bldgName;
	private String lane;
	private String area;
	private String city;
	private String userName;
	private String loginName;
	private String password;
	// private String errorMsg;
	private String user;
	
	Map<String, Object> session;

	private ArrayList<String> cityList;
	private String pin;
	private String mobile;
	private String email;
	private String confirmPassword;
	private String errorMsg=null;
	private List<Offer> offerList = new ArrayList<Offer>();
	private String delivery;
	
	//public getters and setters

		public String getCity() {
			return city;
		}

		public void setCity(String city) {
			this.city = city;
		}

		public ArrayList<String> getCityList() {
			return cityList;
		}

		public void setCityList(ArrayList<String> cityList) {
			this.cityList = cityList;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
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

		public String getPin() {
			return pin;
		}

		public void setPin(String pin) {
			this.pin = pin;
		}

		public String getMobile() {
			return mobile;
		}

		public void setMobile(String mobile) {
			this.mobile = mobile;
		}

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
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


		public String getDelivery() {
			return delivery;
		}

		public void setDelivery(String delivery) {
			this.delivery = delivery;
		}

		public List<Offer> getOfferList() {
			return offerList;
		}

		public void setOfferList(List<Offer> offerList) {
			this.offerList = offerList;
		}



		public String getConfirmPassword() {
			return confirmPassword;
		}

		public void setConfirmPassword(String confirmPassword) {
			this.confirmPassword = confirmPassword;
		}

		

		public String getLoginName() {
			return loginName;
		}

		public void setLoginName(String loginName) {
			this.loginName = loginName;
		}
		

	
	/**
	 * 
	 * to populate the values in the Register page
	 * @return string
	 */
	public String populate() {

		cityList = new ArrayList<String>();
		cityList.add("Bengaluru");
		cityList.add("Noida");
		cityList.add("Indore");
		return "populate";

	}

	/**
	 * 
	 * to enter the registered information and set the customer oject to send to the DAO
	 * @return String
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 */
	public String register() throws IllegalAccessException, InvocationTargetException {
		session = ActionContext.getContext().getSession();
		User user =new User();
		Customer customer = new Customer();
		boolean register = false;
		LoginServiceImpl newLogin = new LoginService();
		customer.setName(this.name.toLowerCase());
		if(flatNo!=null ){
		customer.setFlatNo(this.flatNo.toLowerCase());
		}
		if(bldgName!=null){
		customer.setBldgName(this.bldgName.toLowerCase());
		}
		if(lane!=null){
		customer.setRoad(this.lane.toLowerCase());
		}
		customer.setPlace(this.area.toLowerCase());
		customer.setCity(this.city.toLowerCase());
		customer.setMobile(Long.parseLong(this.mobile));
		customer.setEmail(this.email.toLowerCase());
		customer.setUserName(this.userName.toLowerCase());
		customer.setPassword(this.password.toLowerCase());
		customer.setPin(Double.parseDouble(this.pin));
		register = newLogin.insertCustUser(customer);
		session.put("delivery",this.delivery);
		if (register == true) {
			
			BeanUtils.copyProperties(user, customer);
			user.setRole("Customer");
			session.put("getUser", user);
			LOGGER.info("Successful registeration");
			return "success";
		} else {
			LOGGER.error("Unsuccessful registeration");
			addActionError("UserName exists");
			return "errorMsg";
		}

	}
	
	

	/**
	 * 
	 * this method is used for validating the entered details and then putting the validated user in session
	 * @return string 
	 */
	public String login() {
		LOGGER.info("Inside Log in Page.....");
	
		User getUser = new User();
		LoginServiceImpl loginService = new LoginService();
		
		
			session = ActionContext.getContext().getSession();
			
			session.put("delivery",this.delivery);
		if (this.loginName.substring(0, 3).equals("bpo")||this.loginName.substring(0, 3).equals("adm")){
			user="Staff";
			
		}else{
			
			
			user="Customer";
		}
			getUser = loginService.validateUser(user, this.loginName,
					this.password);
	
			
			if (getUser.getUserName()!=null) {
				
				session.put("getUser", getUser);
				LOGGER.info("Inside Log in Page.....");
				return "success";
			}

			
		 else {
			 LOGGER.error("Inside Log in Page.....");
			 addActionError("Invalid Username and password");
			return errorMsg="error";
		}
}
	
	
	
	/**
	 * 
	 * to clear the session objects
	 * 
	 * @return string
	 */
	public String logOut(){
		
		session = ActionContext.getContext().getSession();
		
		session.remove("getUser");
		session.remove("cartList");
		session.remove("delivery");
		session.remove("totalPrice");
		//Session ses = HibernateUtil.getSession();
		//ses.close();
		LOGGER.info("Inside Logout in Page.....");
		return "success";
	}
	
	
	
	/**
	 * 
	 * to get the home page
	 * 
	 * @return string
	 */
	public String getHome(){
		
		LOGGER.info("Inside getHome() action");
		return "success";
		
	}
 /**
  * 
  * This method prepopulates the register.jsp page with the cusotmer information in the database
  * 
 * @return String
 */
public String editProfile(){
	 
	 session = ActionContext.getContext().getSession();
	 User user= new User();
	 user=(User) session.get("getUser");
	 setName(user.getName());
	 setFlatNo(user.getFlatNo());
	 setCity(user.getCity());
	 setMobile(Long.toString(user.getMobile()));
	 setBldgName(user.getBldgName());
	 setUserName(user.getUserName());
	 setArea(user.getPlace());
	 setLane(user.getRoad());
	 setPin(Double.toString(user.getPin()));
	 setEmail(user.getEmail());
	 
	 return "populate";
	 
 }

/**
 * 
 * method for rendering the password to the user through email
 * @return
 */
public String forgotPassword(){
	
	List<Customer> getUser = new ArrayList<Customer>();
	LoginServiceImpl loginService = new LoginService();
	 getUser= loginService.getCustomer(userName);
	 boolean sendMail=false;
	 
		if (getUser != null && getUser.size() != 0) {
			String newLine = System.getProperty("line.separator");
			String msg = "Your password is :"+getUser.get(0).getPassword()+newLine+"Please Login with userName and the password!!";

			sendMail = PizzaUtil.sendMail(msg, email);

			LOGGER.info("Successful mail with password");

			return "success";

		}
	 else
	 {
		 addActionError("Please enter valid userName");
		 return "error";
	 }
}
	
}
