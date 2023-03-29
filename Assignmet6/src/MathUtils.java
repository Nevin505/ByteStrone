
import java.util.Scanner;
public class MathUtils {
	Scanner inp=new Scanner(System.in);
	double number1;
	double number2;
	
	
	
	static {
		System.out.println("classes are loaded");
	}
	
	public MathUtils(int number1,int number2) {
		this.number1=number1;
		this.number2=number2;
	}
	
		
	
	public static double  multiply(double number1,double number2) {
		return number1*number2;
		
	}

}
