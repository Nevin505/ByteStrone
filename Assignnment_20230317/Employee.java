
public class Employee {
	int id;
	String name;
	int age;
	Address addr;
	
	public Employee() {
		id=0;
		name=" ";
		age=0;
		addr=null;
	}
//	Address address=new Address();
	
	public Employee(int employeeid,String employeename,int employeeage,Address addr1) {
		this.id=employeeid;
		this.name=employeename;
		this.age=employeeage;
		this.addr=addr1;
	}
	
	
	public boolean Compareage(Employee emp2) {
		if(this.age==emp2.age) {
			return true;
		}
		else {
			return false;
		}
	}
	public boolean Comparestate(Employee emp2){
		if(this.addr.buildingno.equals(emp2.addr.buildingno)) {
			return true;
		}
		else {
			return false;
		}
		
		
	}


}
