import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner inp=new Scanner(System.in);
		String firstName;
		String lastName;
		String dob;
		int department;
//		String trade;
		int emp_id;
		int choice;
		int arr;
		
		ArrayList<Employee> emp=new ArrayList<Employee>();
		
		do {
		
		
		System.out.println("1...Creating Details");
		System.out.println("2...Search Details");
		System.out.println("3...Details to be Updated.....");
		System.out.println("4...Sort.................");
		System.out.println("5...Display...............");
		System.out.println("Enter the choice");
		choice=inp.nextInt();
		
		
		switch(choice) {
		case 1:
			System.out.println("Enter the employee id");
			emp_id=inp.nextInt();
			System.out.println("Enter the first Name");
			firstName=inp.next();
			System.out.println("Enter the Last Name");
			lastName=inp.next();
			System.out.println("Enter the date of birth");
			dob=inp.next();
			System.out.println("1.Enginner");
			System.out.println("2.Admin");
			System.out.println("3.Hr");
			System.out.println("Enter the department");
			department=inp.nextInt();
			if(department==1) {
				String trade1;
				System.out.println("Enter the trade of Enginner");
				trade1=inp.next();
				Employee emp1=new  Enginner(firstName, lastName,dob, trade1,emp_id);
				emp.add(emp1);
				System.out.println(emp);			
			}
			else if(department==2) {
				String Area;
				System.out.println("Enter the admins area of operation");
				Area=inp.next();
				Employee emp2=new Admin(firstName, lastName,dob, emp_id,Area);
				emp.add(emp2);
				System.out.println(emp);					
		}
			else if(department==3) {
				String AreaofOperation;
				System.out.println("Enter the Hr area of operation");
				AreaofOperation=inp.next();
				Employee emp3=new Hr(firstName, lastName,dob,AreaofOperation,emp_id);
				emp.add(emp3);
				System.out.println(emp);
			}
			break;
			
		case 2:
			
			int empid;
			System.out.println("Enter the details to be Searched");
			empid=inp.nextInt();
			for (Employee employee : emp) {
				if(employee.emp_id==empid) {
					System.out.println(employee);
				}				
			}
			
			break;
			
		case 3:
			System.out.println("Enter the Employee id details  to be updated");
			int employeeid;
			employeeid=inp.nextInt();
			String nameFirst;
			String lastName1;
			String date;
			for (Employee employee : emp) {
				if(employee.emp_id==employeeid) {
					System.out.println("Enter the name to be updated");
					nameFirst=inp.next();
					employee.First_Name=nameFirst;
					System.out.println("Enter the name to be updated");
					lastName1=inp.next();
					employee.Last_name=lastName1;
					System.out.println("Enter the date of birth to be updated");
					date=inp.next();
					employee.d_o_b=date;
				}
			System.out.println(employee);	
			}
			
		case 4:
//			System.out.println("The Sort");
//			Collections.sort(emp);
//			for (Employee employee : emp) {
//				System.out.println(employee);
			Comparator<Employee> com=new Comparator<Employee>() {
				@Override
				public int compare(Employee o1, Employee o2) {
					// TODO Auto-generated method stub
					return o1.First_Name.compareTo(o2.First_Name);
					}				
			};			
			Comparator<Employee> bylastname=new Comparator<Employee>() {
				@Override
				public int compare(Employee o1, Employee o2) {
					// TODO Auto-generated method stub
					
					return o1.Last_name.compareTo(o2.Last_name);
				}
			};	
			
			Collections.sort(emp, com);
			System.out.println("Sorting based on First Name");
			for (Employee employee : emp) {
				System.out.println(employee);
			}
			
			Collections.sort(emp, bylastname);			
			System.out.println("Sorting based on Last Name");
			 for (Employee employee : emp) {
				System.out.println(employee);
			}
			 break;
		case 5:
			for (Employee employee : emp) {
				System.out.println(employee);
			}
			break;
		}
		
		System.out.println("do you want continue (1/0)");
		arr=inp.nextInt();	
		
	}
		
		while(arr==1);

}
}
