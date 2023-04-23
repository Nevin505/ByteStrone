
public class Enginner extends Employee {
//	String department;
	String trade;
	public Enginner(String first_Name, String last_name, String d_o_b,String trade,int emp_id) {
		super(first_Name,last_name, d_o_b,emp_id);
		// TODO Auto-generated constructor stub
		this.trade=trade;		
	}
	
	@Override
	public String toString() {
		return "Enginner [trade=" + trade + ", First_Name=" + First_Name + ", Last_name=" + Last_name + ", d_o_b="
				+ d_o_b + "]";
	}
	
	
}
