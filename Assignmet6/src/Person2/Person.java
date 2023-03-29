package Person2;

public class Person {
	static int count;
	String name;
	
	static{
		 count=0;
//		 count++;
		
	}
	
	public Person(String name) {
		this.name=name;
		count++;
		
	}
	public void displayName() {
		System.out.println("the name is="+name);
	}
	public static int getCount() {
		return count;
	}

}
