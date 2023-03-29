import java.util.Scanner;

public class mathmain {
	public static void main(String[] args) {
		Scanner inp=new Scanner(System.in);
		int n1;
		int n2;
		System.out.println("Enter number1");
		n1=inp.nextInt();
		System.out.println("Enter number2");
		n2=inp.nextInt();
		
		Mathutil m=new Mathutil(10,10);
		Mathutil.setNumber1(n1);
		Mathutil.setNumber2(n2);
		double val=0;
		val=Mathutil.multiply(Mathutil.getNumber1(),Mathutil.getNumber2());
		System.out.println(val);
		
	}

}
