package Mathuti;

public class Main {
	
	public static void main(String[] args) {
		double values,number1,number2;
		Mathutils ma=new Mathutils(10,20);
		ma.getValues();
		values=Mathutils.multiply(ma);
		System.out.println(values);
		
	}

}
