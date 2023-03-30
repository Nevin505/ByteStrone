package com.bytes.assignemt2;
import java.util.Scanner;
public class Reverse{
	public static void main(String args[]) {
		
		Scanner inp=new Scanner(System.in);
		int number,reversed=0,d=0;
		System.out.println("Enter number to be reversed");
		number=inp.nextInt();
		while(number>0) {
			d=number%10;
			reversed=reversed*10+d;
			number=number/10;
			
		}
		System.out.println("the reversed number="+reversed);
	}

}
