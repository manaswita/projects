package com.impetus.pizzaonline.dao;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.impetus.pizzaonline.model.Offer;

public class OfferDAOTest {

	@Test
	public void testGetOfferString() {
		
		OfferDAO offerDao = new OfferDAO();
		assertNotNull("Result1", offerDao.getOffer("50% OFF (ON MON AND TUE  ON A MIN BILL OF 700)"));
		assertNotNull("Result2", offerDao.getOffer("NO OFFER"));
		assertNotNull("Result3", offerDao.getOffer("30% OFF(ON THUR AND FRI ON MIN BILL OF 500)"));
		
	}
	@Test
	public void testInsertOffer(){
		
		Offer offer =new Offer();
		OfferDAO offerDao = new OfferDAO();
		offer.setActInd('Y');
		offer.setDescription("50% OFF (ON MON AND TUE  ON A MIN BILL OF 700)");
		offer.setCouponCode("CTX143143");
		offer.setDiscount(30);
		offer.setMinBill(500);
		offer.setValidDays("1100000");
		offer.setStartDate("20-01-2012");
		offer.setEndDate("25-01-2012");
		
		assertNotNull("Result1", offerDao.insertOffer(offer));
	}
	@Test
	public void testRemoveOffer() {
		Offer offer =new Offer();
		List<Offer> offerList= new ArrayList<Offer>();
		OfferDAO offerDao = new OfferDAO();
		String selectOffer="50% OFF (ON MON AND TUE  ON A MIN BILL OF 700)";
		offerList =offerDao.getOffer(selectOffer);
		offer=offerList.get(0);
		offer.setActInd('N');
		
		assertNotNull("Result1", offerDao.removeOffer(offer));
		
		
		
	}
	
	@Test
	public void testValidateOffer(){
		Offer offer =new Offer();
		OfferDAO offerDao = new OfferDAO();
		
		String couponCode1="CTX12345";
		String validDays1="0001100";
		
		assertNotNull("Result1", offerDao.validateOffer(couponCode1, validDays1));
		
	}
	
	@Test
	public void testGetOffer(){
		OfferDAO offerDao = new OfferDAO();
		
		assertNotNull("Result1", offerDao.getOffer());
		
	}

	
}
