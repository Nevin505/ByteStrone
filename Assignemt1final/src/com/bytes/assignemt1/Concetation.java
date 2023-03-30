package com.bytes.assignemt1;
import java.util.Scanner;
public class Concetation {
	public static void main(String args[]) {
		Scanner inp=new Scanner(System.in);
		String s,s1,s2=" ";
		System.out.println("Enter string1");
		s=inp.nextLine();
		System.out.println("Enter second string");
		s1=inp.nextLine();
		s2=s2+s+" "+s1;
		System.out.println("the final covetaed String is="+s2);
		
	}

}
