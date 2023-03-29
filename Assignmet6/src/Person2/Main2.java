package Person2;

import java.util.Scanner;

public class Main2 {
	public static void main(String[] args) {
		Scanner inp=new Scanner(System.in);
		String name;
		int v1;
		System.out.println("Enter name");
		name=inp.nextLine();
		Person persn=new Person(name);
		Person persn1=new Person(name);
		persn.displayName();
		persn1.displayName();
		v1=Person.getCount();
		System.out.println(v1);
	}

}
