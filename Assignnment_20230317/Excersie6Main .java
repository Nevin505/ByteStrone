import java.util.Scanner;
public class Stringreversal {
	public static void main(String[] args) {
		Scanner inp=new Scanner(System.in);
		String name;
		System.out.println("Enter Name");
		name=inp.nextLine();
		String s1=" ";
		int length;
		length=name.length();
		int i;
		char ch;
		for(i=length-1;i>=0;i--) {
			ch=name.charAt(i);
			s1=s1+ch;
		}
		
		System.out.println("Threverse string is="+s1);
		if(s1.equals(name)) {
			System.out.println("its a palindrome");
		}
		else {
			System.out.println("its not a plaindrome");
		}
	}

}
