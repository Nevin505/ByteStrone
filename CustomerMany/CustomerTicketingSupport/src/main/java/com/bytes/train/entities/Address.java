
package com.bytes.train.entities;

public class Address {
	
	private String Lane1;
	private String Lane2;
	private long pinCode;
	private String city;
	private String district;
	private String state;
	private String phoneNumber;  
	public Address() {

	}
	public Address( String lane1, String lane2, long pinCode, String city, String district, String state,
			String phoneNumber) {
		super();
		
		Lane1 = lane1;
		Lane2 = lane2;
		this.pinCode = pinCode;
		this.city = city;
		this.district = district;
		this.state = state;
		this.phoneNumber = phoneNumber;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getLane1() {
		return Lane1;
	}

	public void setLane1(String lane1) {
		Lane1 = lane1;
	}

	public String getLane2() {
		return Lane2;
	}

	public void setLane2(String lane2) {
		Lane2 = lane2;
	}

	public long getPinCode() {
		return pinCode;
	}

	public void setPinCode(long pinCode) {
		this.pinCode = pinCode;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

}
