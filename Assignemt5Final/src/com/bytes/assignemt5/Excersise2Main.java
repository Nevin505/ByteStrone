package com.bytes.assignemt5;
import java.util.Scanner;
public class Excersise2Main {
	
		public static void main(String[] args) {
			Recur re=new Recur();
			Scanner inp = new Scanner(System.in);
			int n;
			System.out.println("Enter number");
			n = inp.nextInt();
			int facto;
			facto =re.Factorial(n);
			System.out.println("The factorial of number="+facto);

		}



	}

}
