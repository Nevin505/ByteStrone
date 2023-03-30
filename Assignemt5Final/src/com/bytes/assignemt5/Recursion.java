package com.bytes.assignemt5;
import java.util.Scanner;
public class Recursion {
	Scanner inp = new Scanner(System.in);
	public  int Factorial(int n1) {

		if (n1 == 0 || n1 == 1) {
			return 1;
		} else {
			return n1 * Factorial(n1-1);
		}

	}

}
