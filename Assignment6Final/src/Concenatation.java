import java.util.Scanner;

public class Concenatation {
	
	public static void main(String[] args) {
		Scanner inp=new Scanner(System.in);
		String string1;
		String string2;
		String result;
		System.out.println("Enter String1");
		string1=inp.nextLine();
		System.out.println("Enter String2");
		string2=inp.nextLine();
		result=string1.concat(string2);
		System.out.println("the concectated result="+result);
		
		
	}

}
