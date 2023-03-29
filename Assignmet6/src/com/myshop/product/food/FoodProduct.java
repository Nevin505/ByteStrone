package com.myshop.product.food;
import java.time.LocalDate;
import java.util.Scanner;
import java.time.*;
public class FoodProduct {
	String month;
	String day;
	String year;
//	int expiryDate;
	String manufacturer;
	String expirydate=" ";
	Scanner inp=new Scanner(System.in);
	public void getFoodData() {
		System.out.println("Enter the manufacturer name");
//		manufacturer=inp.nextLine();
		manufacturer=inp.next();
		System.out.println("Enter expiry date");
		System.out.println("-----------------");
		System.out.println("Enter day");
//		day=inp.nextLine();
		day=inp.next();
		System.out.println("Enter month");
//		month=inp.nextLine();
		month=inp.next();
		System.out.println("Enter year");
//		year=inp.nextLine();
		year=inp.next();
		
//		System.out.println(expirydate);
	}

//	LocalDate expirydate=LocalDate.of(year, month,day );
	
	public void displayFoodData() {
		System.out.println("The Food details");
		System.out.println("The Manufacturer is="+manufacturer);
		System.out.println("The expiry date="+expirydate);
	}
	

}
