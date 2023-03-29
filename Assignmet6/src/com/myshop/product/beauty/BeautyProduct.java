package com.myshop.product.beauty;

import java.time.LocalDate;
import java.util.Scanner;

public class BeautyProduct {
 String  brand;
   String day2;
   String month2;
   String year2;
   String expiry1="";
 public void getBeauty() {
	 Scanner inp=new Scanner(System.in);
	 System.out.println("Enter the beauty product details");
	 System.out.println("Enter the name of the brand");
//	 brand=inp.nextLine();
	 brand=inp.next();
	 
 
   System.out.println("Enter day");
//	day2=inp.nextLine();
   day2=inp.next();
	System.out.println("Enter month");
//	month2=inp.nextLine();
	month2=inp.next();
	System.out.println("Enter year");
//	year2=inp.nextLine();
	year2=inp.next();
	expiry1=year2+"-"+month2+"-"+day2;
	
	
}
//LocalDate expiry1=LocalDate.of(year2, month2,day2 );

public void display() {
	System.out.println("Electronic Product detials");
	System.out.println("The Brand name="+brand);
	System.out.println("The wrannaty details of the product="+expiry1);
}


 
}
