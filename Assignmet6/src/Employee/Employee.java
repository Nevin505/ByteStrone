
package Employee;
public class Employee {
	String name; 
	private double salary;
	private int id;
	
	public Employee(String name,double salary,int id) {
		this.name=name;
		this.setSalary(salary);
		this.setId(id);
	}
	
	public double getSalary() {
		return salary;
	}
	
	public void setSalary(double salary) {
		this.salary = salary;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public double giveRaise(double Sal1) {
		double va;
		va=getSalary();
		return getSalary()+(0.10*va);
	}
	

}
