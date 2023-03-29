package com.myshop.product;

import java.util.Scanner;

public class Product {
	double productId;
	String productName;
	double price;
	
	public void getProductdetails() {
		Scanner inp=new Scanner(System.in);
		System.out.println("Enter product id");
		productId=inp.nextDouble();
		System.out.println("Enter product Name");
//		productName=inp.nextLine();
		productName=inp.next();
		System.out.println("Enter product Price");
		price=inp.nextDouble();
	}
	
	public void displayProduct() {
		System.out.println("The product details");
		System.out.println("The Product id is="+productId);
		System.out.println("the Product Name is="+productName);
		System.out.println("The Product price is="+price);
	}
	
	
	
}
