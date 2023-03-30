package com.bytes.assignemt1;
import java.util.Scanner;

public class pro1 {
	public static void main(String args[]) {
		Scanner inp = new Scanner(System.in);
		int number1, number2, product;
		System.out.println("Enter a number1");
		number1 = inp.nextInt();
		System.out.println("enter the second number");
		number2 = inp.nextInt();
		product = number1 * number2;
		System.out.println("the porduct " + product);
	}

}
