package Mathuti;
import java.util.Scanner;

  class Mathutils{
		
		 static double number1,number2;
		
		static {	
			System.out.println("class is being loaded");
		}
		
		
		   public Mathutils(double number1,double number2) {
			   
			Mathutils.number1=number1;
			Mathutils.number2=number2;
		}
		
		public void getValues() {
			
			Scanner inp=new Scanner(System.in);
			System.out.println("Enter number1");
			number1=inp.nextDouble();
			System.out.println("Enter number2");
			number2=inp.nextDouble();
			
		}
		
		public static double multiply(Mathutils ma) {
			return Mathutils.number1*Mathutils.number2;
			
		}
//		public static double multiply(double number1,double number2) {
//			return number1*number2;
//			
//		}
	//	
		
	}


