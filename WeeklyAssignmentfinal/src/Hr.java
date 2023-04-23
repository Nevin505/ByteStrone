
public class Hr extends Employee {
	String AreaOfOperation;

	public Hr(String first_Name, String last_name, String d_o_b, String AreaOfOperation,int emp_id) {
		super(first_Name, last_name, d_o_b, emp_id);
		// TODO Auto-generated constructor stub
		this.AreaOfOperation=AreaOfOperation;
	}

	@Override
	public String toString() {
		return "Hr [AreaOfOperation=" + AreaOfOperation + ", First_Name=" + First_Name + ", Last_name=" + Last_name
				+ ", d_o_b=" + d_o_b + ", emp_id=" + emp_id + "]";
	}
	
	

}
