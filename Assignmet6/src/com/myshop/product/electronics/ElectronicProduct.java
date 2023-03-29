package com.myshop.product.electronics;

import java.util.Scanner;
import java.time.*;
public class ElectronicProduct {
	String brand;
	String day1;
	String month1;
	String year1;
	String warranty=" ";
	Scanner inp=new Scanner(System.in);
	public void getProduct() {
		System.out.println("Enter electronic brand details");
		
		System.out.println("Enter brand ");
//		brand=inp.nextLine();
		brand=inp.next();
		System.out.println("Enter warrantry details");
		System.out.println("Enter day");
//		day1=inp.nextLine();
		day1=inp.next();
		System.out.println("Enter month");
//		month1=inp.nextLine();
		month1=inp.next();
		System.out.println("Enter year");
//		year1=inp.nextLine();
		year1=inp.next();
		warranty=year1+"-"+month1+"-"+day1;
		
		
	}
//	LocalDate warranty=LocalDate.of(year1, month1, day1);
//	LocalDate warrnty=LocalDate.of(2021, "Janaury", 12);
	public void display() {
		System.out.println("Electronic Product detials");
		System.out.println("The Brand name="+brand);
		System.out.println("The wrannaty details of the product="+warranty);
	}
	

}
