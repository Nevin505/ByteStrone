package com.bytes.assignemt5;


public class Address {
	String buildingno;
	String street;
	String district;
	String state;
//	default constructor
	
	
	public Address() {
		buildingno=" ";
		street=" ";
		district=" ";
		state=" ";
	}
//	prameterised constructor
	public Address(String build,String Strt,String dst,String sta) {
		this.buildingno=build;
		this.street=Strt;
		this.district=dst;
		this.state=sta;
		
	}
	public boolean Comparebuilding(Address ad) {
		if(this.buildingno.equals(ad.buildingno)) {
			return true;
		}
		else {
			return false;
		}
		
	}
	

}
