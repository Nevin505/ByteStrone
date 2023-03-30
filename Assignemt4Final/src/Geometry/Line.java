package Geometry;

public class Line {
	int x1cordinates;
	int y1cordinates;
	int x2cordinates;
	int y2cordinates;
	
	int length1;
	int length2;
	
	public Line() {
		this.x1cordinates=0;
		this.y1cordinates=0;
		this.x2cordinates=0;
		this.y2cordinates=0;		
	}
	
	public Line(int x1,int y1,int x2,int y2) {
		this.x1cordinates=x1;
		this.y1cordinates=y1;
		this.x2cordinates=x2;
		this.y2cordinates=y2;	
		
	}
	public boolean isLinesEqual(Line L4) {
		int xdiff=0;
		xdiff=this.x1cordinates-this.x2cordinates;
		int ydiff=0;
		ydiff=this.y2cordinates-this.y1cordinates;
		length1=(int)Math.sqrt((xdiff*xdiff)-(ydiff*ydiff));
		
		int xdiff1=0;
		xdiff1=L4.x1cordinates-L4.x2cordinates;
		int ydiff1=0;
		ydiff1=L4.y2cordinates-L4.y1cordinates;
		length2=(int)Math.sqrt((xdiff1*xdiff1)-(ydiff1*ydiff1));
		
		if(length1==length2) {
			return true;
		}
		else {
			return false;
		}
		
		
		
				
		
	}

}
