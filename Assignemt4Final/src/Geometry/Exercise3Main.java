package Geometry;

public class Exercise3Main {

	public static void main(String[] args) {
		boolean value,value1;
		Point1 p1 = new Point1(4,5);
		
		Point1 p2 = new Point1(4,5);
		
		value=p1.arePointEqual(p2);
		
		Line l1=new Line(4, 2, 1, 5);
//		line l3=new Line(0, 0, 0, 0)
		Line l2=new Line(4, 2, 1, 5);
		
		value1=l1.isLinesEqual(l2);
		
		System.out.println("the points are equal="+value);
		System.out.println("the lines are equal="+value1);

	}

}
