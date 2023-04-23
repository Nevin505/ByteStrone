
public class Admin extends Employee {
	
	String areaofoperation;
	
	
	public Admin(String first_Name, String last_name, String d_o_b, int emp_id,String areaofoperation ) {
		super(first_Name, last_name, d_o_b, emp_id);
		// TODO Auto-generated constructor stub
		this.areaofoperation=areaofoperation;
	}


	@Override
	public String toString() {
		return "Admin [areaofoperation=" + areaofoperation + ", First_Name=" + First_Name + ", Last_name=" + Last_name
				+ ", d_o_b=" + d_o_b + ", emp_id=" + emp_id + "]";
	}

	


}
