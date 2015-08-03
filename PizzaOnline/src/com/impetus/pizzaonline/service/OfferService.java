package com.impetus.pizzaonline.service;

import java.util.ArrayList;
import java.util.List;

import com.impetus.pizzaonline.dao.ItemDAO;
import com.impetus.pizzaonline.dao.OfferDAO;
import com.impetus.pizzaonline.model.Item;
import com.impetus.pizzaonline.model.Offer;
/**
 * 
 * this class acts like a service class to manipulate the objects received from the DAO class and render them to the action class.
 * @author nimmi.menon
 *
 *
 */
public class OfferService implements OfferServiceImpl{
	public boolean insertOffer(Offer offer){
		
		boolean insert=false;
		OfferDAO newOffer = new OfferDAO();
		
		insert=newOffer.insertOffer(offer);
		return insert;
	}
	public boolean removeOffer(Offer offer){
		boolean insert=false;
		OfferDAO newOffer = new OfferDAO();
		
		insert=newOffer.removeOffer(offer);
		return insert;
		
	}
	public List<Offer> getOffer(String name){
		
		OfferDAO offerdao=new OfferDAO();
		ArrayList<Offer> offerList = (ArrayList<Offer>) offerdao.getOffer(name);	
		return offerList;
		
	}
public List<Offer> getOffer(){
		
		OfferDAO offerdao=new OfferDAO();
		ArrayList<Offer> offerList = (ArrayList<Offer>) offerdao.getOffer();	
		return offerList;
		
	}

public boolean validateOffer(String coupon,String validDays){
	
	boolean validate= false;
	OfferDAO offer = new OfferDAO();
	validate=offer.validateOffer(coupon,validDays);
	
	return validate;
}
}
