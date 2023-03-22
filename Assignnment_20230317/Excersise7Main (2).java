import java.util.Scanner;

public class Passmark7 {
	Scanner inp=new Scanner(System.in);
	
	int rollno;
	String name;
	int totalmarks;
	
	public Passmark7() {
		rollno=0;
		name=" ";
		totalmarks=0;
	}
	
	public Passmark7(int roll) {
		this.rollno=roll;
	}
	
	public Passmark7(String name,int roll) {
		this.name=name;
		this.rollno=roll;
	}
	
	public void setdetails() {
		System.out.println("Enter name");
		name=inp.nextLine();
		System.out.println("Enter total marks");
		totalmarks=inp.nextInt();
	}
	public void setMarks() {
		System.out.println("Enter marks");
		totalmarks=inp.nextInt();
		
	}
	public String isPassed(int totalmarks) {
		if(this.totalmarks>50) {
			return "the student has been passed";
		}
		else {
			return "the student has not been passed";
		}
	}
	

}
