import java.util.Scanner;
public class wordfinder {
	public static void main(String[] args) {
		Scanner inp=new Scanner(System.in);
		String sentance;
		System.out.println("Enter sentance");
		sentance=inp.nextLine();
		String word;
		System.out.println("Enter word to be searched");
		word=inp.next();
		String s[]=sentance.split(" ");
		int i;
		int j;
		int flag=0;
		int index=0;
		String words=" ";
		for(i=0;i<s.length;i++) {
			words=s[i].toUpperCase();
			if(words.equals(word.toUpperCase())){
//				
				flag=1;
				index=i;
				break;
			}
			
		}
		if(flag==1) {
			System.out.println("the word is presnt at"+index);
		}
		
		
	}

}
