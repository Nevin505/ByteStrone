import java.util.Scanner;
public class vowelscount {
	public static void main(String[] args) {
		Scanner inp=new Scanner(System.in);
		String word;
		System.out.println("Enter word");
		word=inp.nextLine();
		word=word.toUpperCase();
		int count1;
		count1=vowelscount(word);
		System.out.println(count1);
		
	}
	public static int vowelscount(String w1) {
		char ch;
		int i;
		int vow[]=new int[5];
		int countvowelsa=0;
		int countvowelse=0;
		int countvowelsi=0;
		int countvowelso=0;
		int countvowelsu=0;
		
		for(i=0;i<w1.length();i++) {
			ch=w1.charAt(i);
			if(ch=='A') {
				countvowelsa++;
				
			}
			else if(ch=='E') {
				countvowelse++;
			}
			else if(ch=='I') {
				countvowelsi++;
			}
			else if(ch=='O') {
				countvowelso++;
			}
			else if(ch=='U') {
				countvowelsu++;
			}							
		}
		return countvowelsa;
		
		
		
	}
	
	

}
