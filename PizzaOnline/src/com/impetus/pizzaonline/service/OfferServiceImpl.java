package com.impetus.pizzaonline.service;

import java.util.List;

import com.impetus.pizzaonline.model.Offer;



/**
 * this interface declares all the methods used in OfferService class
 * @author nimmi.menon
 *
 */
public interface OfferServiceImpl {
	public boolean insertOffer(Offer offer);
	public boolean removeOffer(Offer offer);
	public List<Offer> getOffer(String name);
	public List<Offer> getOffer();
	public boolean validateOffer(String coupon,String validDays);
}
