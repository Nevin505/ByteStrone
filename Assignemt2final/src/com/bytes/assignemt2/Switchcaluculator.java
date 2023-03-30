package com.bytes.assignemt2;
import java.util.Scanner;
public class Switchcaluculator {
	public static void main(String args[]) {
		Scanner inp=new Scanner(System.in);
		int contin;
		int firstnumber,secondnumber,choice;
		do {
			System.out.println("Enter first number");
			firstnumber=inp.nextInt();
			System.out.println("Enter Second Number");
			secondnumber=inp.nextInt();
			System.out.println("1..Addition");
			System.out.println("2..Subtraction");
			System.out.println("3..Multiplication");
			System.out.println("4..Divsion");
			System.out.println("Enter Choice");
			choice=inp.nextInt();
			
			switch(choice){
				case 1:
					int sum=0;
					sum=firstnumber+secondnumber;
					System.out.println("the sum of two number="+sum);
					break;
				case 2:
					int subt=0;
					if(firstnumber>secondnumber)
					{
						subt=firstnumber-secondnumber;
					}
					else {
						subt=secondnumber-firstnumber;
					}
					System.out.println("the subtracted values="+subt);
					break;
				case 3:
					int multi=1;
					multi=firstnumber*secondnumber;
					System.out.println("the multiplied values="+multi);
					break;
				case 4:
					int div=1;
					div=firstnumber/secondnumber;
					System.out.println("the divident="+div);
					break;
			    default:
			    	System.out.println("the wrong case you are given please try again");
			}
			
			
				
		System.out.println("do you want to continue Y(1)/N(0)");
		contin=inp.nextInt();
		}
	
		while(contin==1);
		
	}
}


