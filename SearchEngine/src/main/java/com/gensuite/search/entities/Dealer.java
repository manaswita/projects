package com.gensuite.search.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

@Document(indexName = "dealer",type = "dealer", indexStoreType = "memory")
public class Dealer {
	
	@Id
	private Long intermediaryId;
	private String intermediaryName;
	private String intermediaryDescription;
	private Long billtoAddressId;
	private Long shiptoAddressId;
    private boolean active;
    private Long intermediaryTypeId;
    private String intermediaryCode;
    private String city;
    private String state;
    private String zipCode;
    private String phoneNumber;
    private String address1;
    private String address2;
    private String address3;
    private String country;
    private String fax;
    private String IntermediaryType;
    
    public Dealer(){
    	
    }
    public Dealer(Long intermediaryId,String intermediaryName,String intermediaryDescription,Long billtoAddressId,
    		Long shiptoAddressId,boolean active,Long intermediaryTypeId,String intermediaryCode,String city,String state,
    		String zipCode,String phoneNumber ,String address1,String address2,String address3,String country,String fax,String IntermediaryType){
    		this.intermediaryId = intermediaryId;
    		this.intermediaryName = intermediaryName;
    		this.intermediaryDescription = intermediaryDescription;
    		this.billtoAddressId = billtoAddressId;
    		this.shiptoAddressId = shiptoAddressId;
    		this.active = active;
    		this.intermediaryTypeId = intermediaryTypeId;
    		this.intermediaryCode = intermediaryCode;
    		this.city = city;
    		this.state = state;
    		this.zipCode = zipCode;
    		
    		this.address1=address1;
    		this.address2=address2;
    		this.address3=address3;
    		this.phoneNumber=phoneNumber;
    		this.fax=fax;
    		this.IntermediaryType=IntermediaryType;
    		
    }
	public Long getIntermediaryId() {
		return intermediaryId;
	}
	public void setIntermediaryId(Long intermediaryId) {
		this.intermediaryId = intermediaryId;
	}
	public String getIntermediaryName() {
		return intermediaryName;
	}
	public void setIntermediaryName(String intermediaryName) {
		this.intermediaryName = intermediaryName;
	}
	public String getIntermediaryDescription() {
		return intermediaryDescription;
	}
	public void setIntermediaryDescription(String intermediaryDescription) {
		this.intermediaryDescription = intermediaryDescription;
	}
	public Long getBilltoAddressId() {
		return billtoAddressId;
	}
	public void setBilltoAddressId(Long billtoAddressId) {
		this.billtoAddressId = billtoAddressId;
	}
	public Long getShiptoAddressId() {
		return shiptoAddressId;
	}
	public void setShiptoAddressId(Long shiptoAddressId) {
		this.shiptoAddressId = shiptoAddressId;
	}
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
	public Long getIntermediaryTypeId() {
		return intermediaryTypeId;
	}
	public void setIntermediaryTypeId(Long intermediaryTypeId) {
		this.intermediaryTypeId = intermediaryTypeId;
	}
	public String getIntermediaryCode() {
		return intermediaryCode;
	}
	public void setIntermediaryCode(String intermediaryCode) {
		this.intermediaryCode = intermediaryCode;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getZipCode() {
		return zipCode;
	}
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getAddress1() {
		return address1;
	}
	public void setAddress1(String address1) {
		this.address1 = address1;
	}
	public String getAddress2() {
		return address2;
	}
	public void setAddress2(String address2) {
		this.address2 = address2;
	}
	public String getAddress3() {
		return address3;
	}
	public void setAddress3(String address3) {
		this.address3 = address3;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getFax() {
		return fax;
	}
	public void setFax(String fax) {
		this.fax = fax;
	}
	public String getIntermediaryType() {
		return IntermediaryType;
	}
	public void setIntermediaryType(String intermediaryType) {
		IntermediaryType = intermediaryType;
	}
	
}
