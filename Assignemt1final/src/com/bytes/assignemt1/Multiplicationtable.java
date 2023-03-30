package com.bytes.assignemt1;

import java.util.Scanner;

public class Multiplicationtable {
	public static void main(String[] args) {
		Scanner inp=new Scanner(System.in);
		int number;
		int i;
		System.out.println("Enter whose multiplication needed");
		number=inp.nextInt();
		for(i=1;i<=10;i++) {
			System.out.println("The multiplication table of "+number+"="+i*number);
		}
	}

}
