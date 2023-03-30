package com.bytes.assignemt5;
import java.util.Scanner;
public class Exercise3Main  {
 public static void main(String[] args) {
	Scanner inp=new Scanner(System.in);
//	Employee ids of 2 persons
	int employeeid1;
	int employeeid2;
//	employee age of 2 persons
	int employeeage1;
	int employeeage2;
//	employee name of 2 persons
	String employeename1;
	String employeename2;
//	building names of 2 employess
	String employeebulidng1;
	String employeebulidng2;
//	streetname of 2 employess
	String employeestreet1;
	String employeestreet2;
//	district names of two employees
	String distrct1;
	String district2;
//	state names of two employees
	String employeestate1;
	String employeestate2;
//	to enter employee id of 2 persons
	System.out.println("Enter employee id of 1 person");
	employeeid1=inp.nextInt();
	System.out.println("Enter employee id of 2 person");
	employeeid2=inp.nextInt();
//to enter employee name of 2 persons	
	System.out.println("Enter employee nam of 1 persons");
	employeename1=inp.next();
	System.out.println("Enter employee nam of 2 persons");
	employeename2=inp.next();
//	to enter employee age of 2 persons
	System.out.println("Enter employee age of 1 person");
	employeeage1=inp.nextInt();
	System.out.println("Enter employee age of 2 person");
	employeeage2=inp.nextInt();
//	to enter building name of two persons
	System.out.println("Enter building number of 1 person");
	employeebulidng1=inp.next();
	System.out.println("Enter building number of 2 person");
	employeebulidng2=inp.next();
//to enter building street of two persons	
	System.out.println("Enter street of 1 employee");
	employeestreet1=inp.next();
	System.out.println("Enter street of 2 employee");
	employeestreet2=inp.next();
//to enter district name of two persons		
	System.out.println("Enter the district name of 1 person");
	distrct1=inp.next();
	System.out.println("Enter the district name of 2 person");
	district2=inp.next();
//to enter state name of two persons		
	System.out.println("Enter state name of 1 person");
	employeestate1=inp.next();
	System.out.println("Enter state name of 2 person");
	employeestate2=inp.next();
//to enter employee age of 2 person
	System.out.println("Enter employee age of 1 person");
	employeeage1=inp.nextInt();
	System.out.println("Enter employee age of  2 person ");
	employeeage2=inp.nextInt(); 
	
	Address address1=new Address(employeebulidng1,employeestreet1,distrct1,employeestate1);
	
	Employee emp1=new Employee(employeeid1,employeename1,employeeage1,address1);
	
	Address address2=new Address(employeebulidng2,employeestreet2,district2,employeestate2);
	
	Employee emp2=new Employee(employeeid2,employeename2,employeeage2,address2);
//	to compare ages of two employee
	if(emp1.Compareage(emp2)) {
		System.out.println("its age are same");
	}
	else {
		System.out.println("the age are not same");
	}
	if(address1.Comparebuilding(address2)) {
		System.out.println("Same buildin");
	}
	else {
		System.out.println("not same building");
	}
	
	if(emp1.Comparestate(emp2))
//	em
//	p1.id=empid;
//	emp1.age=empage;
//	emp1.addr=address;
//	emp1.addr.street=street2;
//	emp1.addr.state=state2;
//	emp1.addr.buildingno=bulidn;
	
	
	
   	
 }

}
