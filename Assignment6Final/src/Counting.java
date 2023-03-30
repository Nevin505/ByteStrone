import java.util.Scanner;

public class Counting {
public static void main(String[] args) {
	Scanner inp=new Scanner(System.in);
	String word;
	int i;
	int cointdigit=0;
	int countalphabet=0;
	int countunique=0;
	System.out.println("Enter word");
	word=inp.nextLine();
	char ch;
	for(i=0;i<word.length();i++) {
		ch=word.charAt(i);
		if(Character.isDigit(ch)) {
			cointdigit++;
		}
		else if(Character.isLetter(ch)){
			countalphabet++;
		}
		else {
			countunique++;
		}
	}
	System.out.println("the number of digit present="+cointdigit);
	System.out.println("the number of letter present="+countalphabet);
}
}
