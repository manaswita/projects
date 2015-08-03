package com.impetus.pizzaonline.service;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.impetus.pizzaonline.dao.OfferDAO;
import com.impetus.pizzaonline.model.Offer;

public class OfferServiceTest {

	@Test
	public void testInsertOffer() {
		OfferServiceImpl offerSrv = new OfferService();
		Offer offer =new Offer();
		
		offer.setActInd('Y');
		offer.setDescription("50% OFF (ON MON AND TUE  ON A MIN BILL OF 700)");
		offer.setCouponCode("CTX143143");
		offer.setDiscount(30);
		offer.setMinBill(500);
		offer.setValidDays("1100000");
		offer.setStartDate("20-01-2012");
		offer.setEndDate("25-01-2012");
		
		assertNotNull("Result1", offerSrv.insertOffer(offer));
	}

	@Test
	public void testRemoveOffer() {
		Offer offer =new Offer();
		List<Offer> offerList= new ArrayList<Offer>();
		OfferServiceImpl offerSrv = new OfferService();
		String selectOffer="50% OFF (ON MON AND TUE  ON A MIN BILL OF 700)";
		offerList =offerSrv.getOffer(selectOffer);
		offer=offerList.get(0);
		offer.setActInd('N');
		
		assertNotNull("Result1", offerSrv.removeOffer(offer));
	}

	@Test
	public void testGetOfferString() {
		OfferServiceImpl offerSrv = new OfferService();
		assertNotNull("Result1", offerSrv.getOffer("50% OFF (ON MON AND TUE  ON A MIN BILL OF 700)"));
	}

	@Test
	public void testGetOffer() {
		OfferServiceImpl offerSrv = new OfferService();
		assertNotNull("Result1", offerSrv.getOffer());
	}

	@Test
	public void testValidateOffer() {
		OfferServiceImpl offerSrv = new OfferService();
		
		String couponCode1="CTX12345";
		String validDays1="0001100";
		
		assertNotNull("Result1", offerSrv.validateOffer(couponCode1, validDays1));
	}

}
