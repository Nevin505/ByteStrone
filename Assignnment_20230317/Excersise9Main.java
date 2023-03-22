import java.util.Scanner;
public class Reversewords {
	public static void main(String[] args) {
		Scanner inp=new Scanner(System.in);
		String s;
		System.out.println("Enter word to be reversed");
		s=inp.nextLine();
		s=s.trim();
		int length;
		
		int i;
		String[] s1=s.split(" ");
		length=s1.length; 
		String revString[]=new String[length];
		int k=0;
//		String s2=" "
		for(int j=0;j<s1.length;j++) {
			String s2=s1[j];
			char ch;
			String rev="";
			int len1=s2.length();
			System.out.println(len1);
			for(i=len1-1;i>=0;i--) {
				ch=s2.charAt(i);
				rev=rev+ch;
				
//				revString[k++]=rev;			
			}	
			System.out.println(rev);
			revString[j]=rev;
		}
		String result=String.join(" ", revString);
		System.out.println(result);
	}

}
