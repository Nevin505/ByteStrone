package Geometry;

public class Point1 { 
	int i;
	int xcordinate;
	int ycordinate;
	
	public Point1() {
		xcordinate=0;
		ycordinate=0;	
	}
	public Point1(int x,int y) {
		System.out.println("Calling constructor");
		this.xcordinate=x;
		this.ycordinate=y;
	}
	public boolean arePointEqual(Point1 p2) {
		return this.xcordinate==p2.xcordinate && this.ycordinate==p2.ycordinate;
		
		
			
}
	

}
