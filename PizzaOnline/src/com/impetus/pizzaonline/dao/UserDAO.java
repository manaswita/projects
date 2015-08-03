package com.impetus.pizzaonline.dao;
import java.util.ArrayList;
import java.util.List;


import org.apache.commons.beanutils.BeanUtils;
import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

import com.impetus.pizzaonline.action.UserAction;
import com.impetus.pizzaonline.constants.ApplConstants;
import com.impetus.pizzaonline.model.Customer;
import com.impetus.pizzaonline.model.Item;
import com.impetus.pizzaonline.model.Staff;
import com.impetus.pizzaonline.model.User;
import com.impetus.pizzaonline.util.HibernateUtil;

/**
 * This DAO class is used for all the user table transactions(here there are two user tables,Customer and Staff) at the database such as fetching,inserting and updating.
 * @author nimmi.menon
 *
 *
 *
 */
public class UserDAO {
	
	private final static Logger LOGGER= Logger.getLogger(UserDAO.class);
	//Session ses = HibernateUtil.getSession();

	/**
	 * @param role
	 * @param userName
	 * @param password
	 * 
	 * 
	 * this method calls the validateCustomer or validateStaff method to validate the logged in user.
	 * @return User object
	 */
	public User validateUser(String role,String userName,String password){
	
		Session ses = HibernateUtil.getSession();
		List userList = new ArrayList();
		Customer c = new Customer();
		User u = new User();
		Staff s = new Staff();
		 try{
		    if (null!=role && !role.equals("")){
			  
			if (role.equals("Customer") ){
				userList=validateCustomer(userName, password,ses);
				if(userList.size()!=0){
				c=(Customer) userList.get(0);
				BeanUtils.copyProperties(u,c);			
				u.setRole("Customer");
				
				}
				
			} else if(role.equals("Staff")){
				userList = validateStaff(userName, password,ses);
				if(userList.size()!=0){
				s=(Staff) userList.get(0);
				BeanUtils.copyProperties(u,s);
				/*u.setRole(s.getRole());
				u.setId(s.getId());
				u.setUserName(s.getUserName());*/
			}
			}		    }   
        }catch (Exception e) {
        	LOGGER.error("exception occurred:",e);
        	e.printStackTrace();
	}
		 return u;
		  }
		 
	
	/**
	 * @param userName
	 * @param password
	 * @param ses
	 * 
	 * this method returns a list with the validated username and password if present else it returns a null list
	 * @return list of customer objects
	 */
	@SuppressWarnings("unchecked")
	private ArrayList<Customer> validateCustomer(String userName, String password, Session ses)
	{
		
			ArrayList<Customer> userList = new ArrayList<Customer>();
		try {
			Criteria criteria =  ses.createCriteria(Customer.class);
		
				if(null!= userName && userName!=""){
					criteria.add(Restrictions.like("userName",userName.toLowerCase(), MatchMode.EXACT));
				}
				if(null!= password && password!=""){
					criteria.add(Restrictions.like("password", password, MatchMode.EXACT));
				}
							
	userList = (ArrayList<Customer>) criteria.list();
		} catch (Exception e) {
			LOGGER.error("exception occurred:validateCustomer",e);
			e.printStackTrace();
		} 
		return userList;
	}
	/**
	 * @param userName
	 * @param password
	 * @param ses
	 * 
	 * this method returns a list with the validated username and password if present else it returns a null list
	 * @return list of staff objects
	 *
	 */
	private ArrayList<Staff> validateStaff(String userName,String password,Session ses)
	 {  
		
		ArrayList<Staff> staffList = new ArrayList<Staff>();
		try {
			Criteria criteria =  ses.createCriteria(Staff.class);
		
				if(null!= userName && userName!=""){
					criteria.add(Restrictions.like("userName",userName.toLowerCase(), MatchMode.EXACT));
				}
				if(null!= password && password!=""){
					criteria.add(Restrictions.like("password", password, MatchMode.EXACT));
				}
				
			
	staffList = (ArrayList<Staff>) criteria.list();
		} catch (Exception e) {
			LOGGER.error("exception occurred:validateStaff",e);
			e.printStackTrace();
		} 
		return staffList;
	 }
	
	
	/**
	 * @param staff
	 * 
	 * this method is used for inserting a new staff into the database with staff object got from the action class
	 * 
	 * 
	 * @return boolean value
	 */
	public boolean insertStaffUser(Staff staff){
		Session ses = HibernateUtil.getSession();
		String userName=staff.getUserName();
		String password=staff.getPassword();
		char act_ind=staff.getAct_ind();
		boolean isValidate=false;
		ArrayList<Staff> userList = new ArrayList<Staff>();
		try {
			Criteria criteria =  ses.createCriteria(Staff.class);
				if(null!= userName && userName!=""){
					criteria.add(Restrictions.like("userName",userName.toLowerCase(), MatchMode.EXACT));
				}
				if(null!= password && password!=""){
					criteria.add(Restrictions.like("password", password, MatchMode.EXACT));
				}
				
				criteria.add(Restrictions.like("act_ind", ApplConstants.ACT_IND_Y));
			
							
	userList = (ArrayList<Staff>) criteria.list();
		} catch (Exception e) {
			LOGGER.error("exception occurred:insertStaffUser",e);
			e.printStackTrace();
		} 
		if (userList.isEmpty()){
			isValidate=false;
		}
		else{
			isValidate=true;
		}
		
		if(!isValidate){
		try{

			Transaction tx= ses.beginTransaction();
			ses.save(staff);
			tx.commit();
			ses.flush();
			
			return true;
		
		}
		catch (Exception e) {
			LOGGER.error("exception occurred:insertStaffUser(transaction)",e);
			e.printStackTrace();
			return false;
			
		} 
		}
		else
		{
			
			return false;
		}
		
	
	}
		

	/**
	 * @param cust
	 * 
	 * this method is used for inserting a new customer into the database with customer object got from the action class
	 * @return boolean
	 *
	 */
	public boolean insertCustUser(Customer cust){
		
		Session ses = HibernateUtil.getSession();
		String userName=cust.getUserName();
		
		boolean isValidate=false;
		ArrayList<Customer> userList = new ArrayList<Customer>();
		try {
			Criteria criteria =  ses.createCriteria(Customer.class);
		
				if(null!= userName && userName!=""){
					criteria.add(Restrictions.like("userName",userName.toLowerCase(), MatchMode.EXACT));
				}
											
	userList = (ArrayList<Customer>) criteria.list();
		} catch (Exception e) {
			LOGGER.error("exception occurred:insertCustUser",e);
			e.printStackTrace();
		} 
		if (userList.isEmpty()){
			isValidate=false;
		}
		else{
			isValidate=true;
		}
		
		if(!isValidate){
		try{

			Transaction tx= ses.beginTransaction();
			ses.save(cust);
			tx.commit();
			ses.flush();
			
			return true;
		
		}
		catch (Exception e) {
			LOGGER.error("exception occurred:insertCustUser(transaction)",e);
			e.printStackTrace();
			return false;
			
		} 
		}
		else
		{
			
			return false;
		}
		
	
	}
	
	/**
	 * @param name
	 * 
	 * 
	 * to get the list of staff with that particular name of the staff
	 * @return list of staff objects
	 */
	public List<Staff> getStaff(String name){
		Session ses = HibernateUtil.getSession();
		ArrayList<Staff> staff= new ArrayList<Staff>();
		
		try{
			    Criteria criteria= ses.createCriteria(Staff.class);
			    criteria.add(Restrictions.like("name",name,MatchMode.EXACT));
			    criteria.add(Restrictions.like("act_ind",ApplConstants.ACT_IND_Y,MatchMode.EXACT));
			
			staff=(ArrayList<Staff>) criteria.list();
			
		}catch(Exception e){
			LOGGER.error("exception occurred:getStaff(String name)",e);
			e.printStackTrace();
			
			}
		
	
		return staff;
		
	}
	
	
	/**
	 * 
	 * to get a list of all staff which have the active indicator as 'Y'
	 * @return list of staff objects
	 */
	public List<Staff> getStaff(){
		Session ses = HibernateUtil.getSession();
		ArrayList<Staff> staff= new ArrayList<Staff>();
		
		try{
			    Criteria criteria= ses.createCriteria(Staff.class);
			    criteria.add(Restrictions.like("act_ind",ApplConstants.ACT_IND_Y,MatchMode.EXACT));
			
			staff=(ArrayList<Staff>) criteria.list();
			
		}catch(Exception e){
			e.printStackTrace();
		
			}
		
	
		return staff;
		
	}
	
	/**
	 * @param staff
	 * 
	 * setting the active indicator column in the staff table as 'N' which mean Not Active
	 * @return boolean value
	 */
	public boolean removeStaff(Staff staff){
		Session ses = HibernateUtil.getSession();
		
		try{

			Transaction tx= ses.beginTransaction();
			ses.save(staff);
			tx.commit();
			ses.flush();
			
			return true;
		
		}
		catch (Exception e) {
			
			e.printStackTrace();
			return false;
			
		} 
		
		
	}
	
	/**
	 * @param mobile
	 * 
	 * to get a list of customer which has the  mobile  number got from action class
	 * @return list of customer objects
	 */
	public List<Customer> getCustomer(Long mobile){
		Session ses = HibernateUtil.getSession();
		ArrayList<Customer> customer= new ArrayList<Customer>();
		
		try{
			    Criteria criteria= ses.createCriteria(Customer.class);
			    criteria.add(Restrictions.eq("mobile",mobile));
			    
			
			    customer=(ArrayList<Customer>) criteria.list();
			
		}catch(Exception e){
			e.printStackTrace();
		
			}
		
	
		return customer;
		
	}
	
/**
 * @param cust
 * 
 * for the BPO to save a new customer into the customer table
 * @return boolean value
 */
public boolean insertBPOCust(Customer cust){
		
		Session ses = HibernateUtil.getSession();
		
		
		try{

			Transaction tx= ses.beginTransaction();
			ses.save(cust);
			tx.commit();
			ses.flush();
			
			return true;
		
		}
		catch (Exception e) {
			
			e.printStackTrace();
			return false;
			
		} 
	
	}
public List<Customer> getCustomer(String userName){
	Session ses = HibernateUtil.getSession();
	ArrayList<Customer> customer= new ArrayList<Customer>();
	
	try{
		    Criteria criteria= ses.createCriteria(Customer.class);
		    criteria.add(Restrictions.like("userName",userName,MatchMode.EXACT));
		  
		    customer=(ArrayList<Customer>) criteria.list();
		
	}catch(Exception e){
		LOGGER.error("exception occurred:getStaff(String name)",e);
		e.printStackTrace();
	
		}
	

	return customer;
	
}
	
}