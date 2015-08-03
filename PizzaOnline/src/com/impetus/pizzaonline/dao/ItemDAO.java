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
import com.impetus.pizzaonline.model.Item;
import com.impetus.pizzaonline.model.Staff;
import com.impetus.pizzaonline.model.Topping;
import com.impetus.pizzaonline.util.HibernateUtil;

/**
 * This class is used for all the items related transactrions in the database.The tables used here are the item table and the toppings table.The transactions include fetching,inseting and updating.
 * @author nimmi.menon
 *
 */
public class ItemDAO {
	private final static Logger LOGGER = Logger.getLogger(UserAction.class);
	/**
	 * 
	 * this method is used to get a list of items which have the category name as Pizza and which have active indicator as 'Y'
	 * @return List of item objects with category Pizza
	 */
	public List<Item> getPizza(){
		Session ses = HibernateUtil.getSession();
		ArrayList<Item> item = new ArrayList<Item>();
		
		try{
			    Criteria criteria= ses.createCriteria(Item.class);
			    criteria.add(Restrictions.like("itemCategory","PIZZA",MatchMode.EXACT));
				criteria.add(Restrictions.like("actInd", ApplConstants.ACT_IND_Y));
			item=(ArrayList<Item>) criteria.list();
			LOGGER.info("Successful database connection,itemList populated");
		}catch(Exception e){
			LOGGER.error("error",e);
			e.printStackTrace();
			
			}
		
	
		return item;
		
	}
	
	
	/**
	 * this method is used to get a list of items which have the category name as SideDish and which have active indicator as 'Y'
	 * 
	 * 
	 * @return List of item objects with category side dish
	 */
	public List<Item> getSideDish(){
		Session ses = HibernateUtil.getSession();
		ArrayList<Item> item = new ArrayList<Item>();
		
		try{
			    Criteria criteria= ses.createCriteria(Item.class);
			    criteria.add(Restrictions.like("itemCategory","SIDEDISH",MatchMode.EXACT));
				criteria.add(Restrictions.like("actInd", ApplConstants.ACT_IND_Y));
			item=(ArrayList<Item>) criteria.list();
			LOGGER.info("Successful database connection,itemList populated");
		}catch(Exception e){
			LOGGER.error("error",e);
			e.printStackTrace();
			}
		
		return item;
		
	}
	
	/**
	 * this method is used to get a list of items which have the category name as Dessert and which have active indicator as 'Y'
	 * @return List of item objects with category dessert
	 */
	public List<Item> getDessert(){
		Session ses = HibernateUtil.getSession();
		ArrayList<Item> item = new ArrayList<Item>();
		
		try{
			    Criteria criteria= ses.createCriteria(Item.class);
			    criteria.add(Restrictions.like("itemCategory","DESSERT",MatchMode.EXACT));
				criteria.add(Restrictions.like("actInd", ApplConstants.ACT_IND_Y));
			item=(ArrayList<Item>) criteria.list();
			LOGGER.info("Successful database connection,itemList populated");
		}catch(Exception e){
			LOGGER.error("error",e);
			e.printStackTrace();
		
			}
		
	
		return item;
		
	}
	/**
	 * 
	 * this method is used to get a list of items which have the category name as Beverages and which have active indicator as 'Y'
	 * @return List of item objects with category Beverage
	 */
	public List<Item> getBeverages(){
		Session ses = HibernateUtil.getSession();
		ArrayList<Item> item = new ArrayList<Item>();
		
		try{
			    Criteria criteria= ses.createCriteria(Item.class);
			    criteria.add(Restrictions.like("itemCategory","BEVERAGES",MatchMode.EXACT));
				criteria.add(Restrictions.like("actInd", ApplConstants.ACT_IND_Y));
			item=(ArrayList<Item>) criteria.list();
			LOGGER.info("Successful database connection,itemList populated");
		}catch(Exception e){
			LOGGER.error("error",e);
			e.printStackTrace();
	
			}
		
	
		return item;
		
	}
	
	/**
	 * this method is used to get a list of toppings which have the category name having Pizza and which have active indicator as 'Y'
	 * @return List
	 */
	public List<Topping> getTopping(){
		
		Session ses = HibernateUtil.getSession();
		ArrayList<Topping> item = new ArrayList<Topping>();
		
		try{
			    Criteria criteria= ses.createCriteria(Topping.class);
			    criteria.add(Restrictions.like("category","PIZZA",MatchMode.ANYWHERE));
				criteria.add(Restrictions.like("act_ind", ApplConstants.ACT_IND_Y));
			item=(ArrayList<Topping>) criteria.list();
			LOGGER.info("Successful database connection,itemList populated");
		}catch(Exception e){
			LOGGER.error("error",e);
			e.printStackTrace();
		
			}
		
	
		return item;
		
	}
	
public List<Topping> getTpg(){
		
		Session ses = HibernateUtil.getSession();
		ArrayList<Topping> item = new ArrayList<Topping>();
		
		try{
			    Criteria criteria= ses.createCriteria(Topping.class);
				criteria.add(Restrictions.like("act_ind", ApplConstants.ACT_IND_Y));
			item=(ArrayList<Topping>) criteria.list();
			LOGGER.info("Successful database connection,itemList populated");
		}catch(Exception e){
			LOGGER.error("error",e);
			e.printStackTrace();
		
			}
		
	
		return item;
		
	}
	
	/**
	 * @param item
	 * 
	 * this is method is used to insert a new item into the table after validating whether the item and its size exists or not.
	 * @return boolean
	 */
	public boolean insertItem(Item item){
		Session ses = HibernateUtil.getSession();
		String itemName=item.getItemName();
		String itemSize=item.getItemSize();
		boolean isValidate=false;
		ArrayList<Item> itemList = new ArrayList<Item>();
		try {
			Criteria criteria =  ses.createCriteria(Item.class);
				if(null!= itemName && itemName!=""){
					criteria.add(Restrictions.like("itemName",itemName.toUpperCase(), MatchMode.EXACT));
				}
				if(null!= itemSize && itemSize!=""){
					criteria.add(Restrictions.like("itemSize",itemSize.toUpperCase(), MatchMode.EXACT));
				}
				criteria.add(Restrictions.like("actInd", ApplConstants.ACT_IND_Y));
				LOGGER.info("Successful database connection,itemList populated");
	itemList = (ArrayList<Item>) criteria.list();
		} catch (Exception e) {
			LOGGER.error("error",e);
			e.printStackTrace();
		} 
		if (itemList.isEmpty()){
			isValidate=false;
		}
		else{
			isValidate=true;
		}
		
		if(!isValidate){
		try{

			Transaction tx= ses.beginTransaction();
			ses.save(item);
			tx.commit();
			ses.flush();
			LOGGER.info("Successful database transaction,table updated");
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
	 * @param itemName
	 * @param itemSize
	 * 
	 * to get a list of all items which have the respective itemName and itemSize received from the action class
	 * @return List
	 */
	public List<Item> getItem(String itemName,String itemSize){
		Session ses = HibernateUtil.getSession();
		ArrayList<Item> item = new ArrayList<Item>();
		
		try{
			    Criteria criteria= ses.createCriteria(Item.class);
			    criteria.add(Restrictions.like("itemName",itemName,MatchMode.EXACT));
			    criteria.add(Restrictions.like("itemSize", itemSize,MatchMode.EXACT));
			    criteria.add(Restrictions.like("actInd", ApplConstants.ACT_IND_Y));
			
			item=(ArrayList<Item>) criteria.list();
			
		}catch(Exception e){
			LOGGER.error("error",e);
			e.printStackTrace();
			}
		
	
		return item;
		
	}
	/**
	 * 
	 * to get a list of all items with active indicator set as 'Y'
	 * @return List of Item objects
	 */
	public List<Item> getItem(){
		Session ses = HibernateUtil.getSession();
		ArrayList<Item> item = new ArrayList<Item>();
		
		try{
			    Criteria criteria= ses.createCriteria(Item.class);
			    
			    criteria.add(Restrictions.like("actInd", ApplConstants.ACT_IND_Y));
			
			item=(ArrayList<Item>) criteria.list();
			
		}catch(Exception e){
			LOGGER.error("error",e);
			e.printStackTrace();
			
			}
		
	
		return item;
		
	}
	
	/**
	 * @param item
	 * 
	 * used to update the active indicator column of the the given item object as 'N'
	 * @return boolean
	 */
	public boolean removeItem(Item item){
		Session ses = HibernateUtil.getSession();
		
	
		try{

			Transaction tx= ses.beginTransaction();
			ses.save(item);
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
	public boolean insertTopping(Topping topping){
		Session ses = HibernateUtil.getSession();
		String tpgName=topping.getName();
		String tpgType=topping.getType();
		boolean isValidate=false;
		ArrayList<Topping> tpgList = new ArrayList<Topping>();
		try {
			Criteria criteria =  ses.createCriteria(Topping.class);
				if(null!= tpgName && tpgName!=""){
					criteria.add(Restrictions.like("name",tpgName.toUpperCase(), MatchMode.EXACT));
				}
				if(null!= tpgType && tpgType!=""){
					criteria.add(Restrictions.like("type",tpgType.toUpperCase(), MatchMode.EXACT));
				}
				criteria.add(Restrictions.like("act_ind", ApplConstants.ACT_IND_Y));
				LOGGER.info("Successful database connection,itemList populated");
				tpgList = (ArrayList<Topping>) criteria.list();
		} catch (Exception e) {
			LOGGER.error("error",e);
			e.printStackTrace();
		} 
		if (tpgList.isEmpty()){
			isValidate=false;
		}
		else{
			isValidate=true;
		}
		
		if(!isValidate){
		try{

			Transaction tx= ses.beginTransaction();
			ses.save(topping);
			tx.commit();
			ses.flush();
			LOGGER.info("Successful database transaction,table updated");
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
	public boolean removetopping(Topping topping){
		Session ses = HibernateUtil.getSession();
		
	
		try{

			Transaction tx= ses.beginTransaction();
			ses.save(topping);
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
}
