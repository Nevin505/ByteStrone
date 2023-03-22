import java.util.Scanner;
public class Wordcounter {
	public static void main(String[] args) {
		Scanner inp=new Scanner(System.in);
		String word;
		System.out.println("Enter line");
		word=inp.nextLine();
		int i,count=1;
		int len=word.length();
//		word=word.trim();
		for(i=0;i<=len-1;i++) {
			if(word.charAt(i)==' '){
				count++;
			}
		}
		System.out.println("the total number of words present in the line is="+count);
	}

}
