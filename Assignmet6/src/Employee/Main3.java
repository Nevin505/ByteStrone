package Employee;

import java.util.Scanner;
public class Main3 {
public static void main(String[] args) {
	Scanner inp=new Scanner(System.in);
	Employee em=new Employee("hi", 400000, 12);
	
//	em.setSalary(80000);
	double v2=em.getSalary();
	double v=em.giveRaise(v2);
	System.out.println(v);
	inp.close();
}
}
