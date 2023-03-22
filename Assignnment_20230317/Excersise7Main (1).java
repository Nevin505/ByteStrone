import java.util.Scanner;
public class Mainpass7 {
	public static void main(String[] args) {
//		Scanner inp=new Scanner(System.in);
		String result;
//		String name;
//		int total;
//		boolean value;
//		System.out.println("Enter name");
//		name=inp.nextLine();
//		System.out.println("Enter total marks");
//		total=inp.nextInt();
		
		Passmark7 p=new Passmark7(1);
		p.setdetails();
//		p.isPassed(p.totalmarks);
		result=p.isPassed(p.totalmarks);
		System.out.println(result);
		
		Passmark7 pass=new Passmark7("HI",1);
		pass.setMarks();
		result=pass.isPassed(pass.totalmarks);
//		value=pass.isPassed(total);
		System.out.println(result);
		}

}
