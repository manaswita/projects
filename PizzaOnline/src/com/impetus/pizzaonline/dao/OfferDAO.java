package com.impetus.pizzaonline.dao;

import java.util.ArrayList;
import java.util.List;

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
import com.impetus.pizzaonline.model.Offer;
import com.impetus.pizzaonline.util.HibernateUtil;

/**
 * this class does all the offer transactions to and from the data base for the offer table through the offer class.
 * @author nimmi.menon
 *
 */
public class OfferDAO {
	private final static Logger LOGGER = Logger.getLogger(UserAction.class);

	/**
	 * 
	 * inserts a new offer into the offer table after validating if the offer exists or not.
	 * @param offer
	 * @return boolean
	 */
	public boolean insertOffer(Offer offer){
		Session ses = HibernateUtil.getSession();
		String coupon=offer.getCouponCode();
		
		boolean isValidate=false;
		ArrayList<Offer> offerList = new ArrayList<Offer>();
		try {
			Criteria criteria =  ses.createCriteria(Offer.class);
				if(null!= coupon && coupon!=""){
					criteria.add(Restrictions.like("couponCode",coupon.toUpperCase(), MatchMode.EXACT));
				}
				
				criteria.add(Restrictions.like("actInd", ApplConstants.ACT_IND_Y));
				
	offerList = (ArrayList<Offer>) criteria.list();
	LOGGER.info("Successful database connection,List populated");
		} catch (Exception e) {
			LOGGER.error("error",e);
			e.printStackTrace();
		} 
		if (offerList.isEmpty()){
			isValidate=false;
		}
		else{
			isValidate=true;
		}
		
		if(!isValidate){
		try{

			Transaction tx= ses.beginTransaction();
			ses.save(offer);
			tx.commit();
			ses.flush();
			
			return true;
		
		}
		catch (Exception e) {
			LOGGER.error("error",e);
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
	 * 
	 * to get a list of offer objects from the table which has the description chosen by the user
	 * 
	 * @param name
	 * @return List of offer objects
	 */
	public List<Offer> getOffer(String name){
		Session ses = HibernateUtil.getSession();
		ArrayList<Offer> offer = new ArrayList<Offer>();
		
		try{
			    Criteria criteria= ses.createCriteria(Offer.class);
			    criteria.add(Restrictions.like("description",name,MatchMode.EXACT));
			    criteria.add(Restrictions.like("actInd", ApplConstants.ACT_IND_Y));
			offer=(ArrayList<Offer>) criteria.list();
			LOGGER.info("Successful database connection,List populated");
		}catch(Exception e){
			LOGGER.error("error",e);
			e.printStackTrace();
			
			}
		
	
		return offer;
		
	}
	/**
	 * 
	 * to get a list of all offer objects in the database
	 * 
	 * @return List
	 */
	public List<Offer> getOffer(){
		Session ses = HibernateUtil.getSession();
		ArrayList<Offer> offer = new ArrayList<Offer>();
		
		try{
			    Criteria criteria= ses.createCriteria(Offer.class);
			    criteria.add(Restrictions.like("actInd", ApplConstants.ACT_IND_Y));
			offer=(ArrayList<Offer>) criteria.list();
			LOGGER.info("Successful database connection,List populated");
		}catch(Exception e){
			LOGGER.error("error",e);
			e.printStackTrace();
		
			}
		
	
		return offer;
		
	}
	/**
	 * to update the active indicator of the chosen offer as 'N'
	 * 
	 * @param offer
	 * @return boolean value
	 */
	public boolean removeOffer(Offer offer){
		Session ses = HibernateUtil.getSession();
		
	
		try{

			Transaction tx= ses.beginTransaction();
			ses.save(offer);
			tx.commit();
			ses.flush();
			LOGGER.info("Successful database connection,table updated");
			return true;
		
		}
		catch (Exception e) {
			LOGGER.error("error",e);
			e.printStackTrace();
			return false;
			
		} 
		
		
	}
	
	
	
	/**
	 * 
	 * to validate the given coupon number and also the day on which the offer has been chosen
	 * in turn calls the getValidOffer Method
	 * 
	 * @param coupon
	 * @param validDays
	 * @return boolean
	 */
	public boolean validateOffer(String coupon,String validDays){
		
		boolean validate=false;
		ArrayList<Offer> offerList = new ArrayList<Offer>();
		
		offerList=getValidOffer(coupon,validDays);
		if (offerList!=null && offerList.size()!=0){
			LOGGER.info("Successful database connection,coupon validated");
			return true;
			
		}
		else{
			LOGGER.error("error......offerList=null");
			return false;
		}
		
		
		
		
	}
	/**
	 * gets a list of offer which has the given coupon number and valid day string
	 * 
	 * @param coupon
	 * @param validDays
	 * @return List
	 */
	private ArrayList<Offer> getValidOffer(String coupon, String validDays)
	{
		Session ses = HibernateUtil.getSession();
			ArrayList<Offer> offerList = new ArrayList<Offer>();
		try {
			Criteria criteria =  ses.createCriteria(Offer.class);
		
				if(null!= coupon && coupon!=""){
					criteria.add(Restrictions.like("couponCode",coupon.toUpperCase(), MatchMode.EXACT));
				}
				if(null!= validDays && validDays!=""){
					criteria.add(Restrictions.like("validDays",validDays, MatchMode.EXACT));
				}
				 criteria.add(Restrictions.like("actInd", ApplConstants.ACT_IND_Y));	
				 
				offerList = (ArrayList<Offer>) criteria.list();
				LOGGER.info("Successful database connection,list populated");
		} catch (Exception e) {
			LOGGER.error("error",e);
			e.printStackTrace();
		} 
		return offerList;
	}
}