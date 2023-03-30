package com.bytes.assignemt1;
import java.util.Scanner;
public class Arithemtic {
	public static void main(String args[]) {
		Scanner inp=new Scanner(System.in);
		int number1,number2,multiply,sum,subtract,divide,mod;
		
		System.out.println("eneter number1");
		number1=inp.nextInt();
		System.out.println("enter the number 2");
		number2=inp.nextInt();
		
		multiply=number1*number2;
		
		sum=number1+number2;
		
		subtract=number1-number2;
		
		divide=number1/number2;
		
		
		multiply=number1*number2;
		
		mod=number1%number2;
		
		System.out.println("The multiplied name="+multiply);
		System.out.println("The sum of two numbers="+sum);
		System.out.println("subtracted values="+subtract);
		System.out.println("Divison="+divide);
		System.out.println("modulus="+mod);
	}

}
