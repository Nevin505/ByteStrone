//package Mathuti;

import java.util.Scanner;

class Mathutil{
	
	 private static double number1;
	 private static double number2;

	
	static {	
		System.out.println("class is being loaded");
	}
	
	
	
	   public Mathutil(double number1,double number2) {
		   
		Mathutil.number1=number1;
		Mathutil.number2=number2;
	}
		
		public static double getNumber1() {
			return number1;
		}

		public static void setNumber1(double number1) {
			Mathutil.number1 = number1;
		}

		public static double getNumber2() {
			return number2;
		}

		public static void setNumber2(double number2) {
			Mathutil.number2 = number2;
		}

	
	public void getValues() {
		
		Scanner inp=new Scanner(System.in);
		System.out.println("Enter number1");
		number1=inp.nextDouble();
		System.out.println("Enter number2");
		number2=inp.nextDouble();
		
	}
	
	public static double multiply(double number,double number2) {
		
		return Mathutil.number1 * Mathutil.number2;
		
	}
//	public static double multiply(double number1,double number2) {
//		return number1*number2;
//		
//	}
//	
	
}