package com.bytes.shapes;
import java.util.Scanner;

public class Excersise2Main {
	public static void main(String[] args) {
		Scanner inp = new Scanner(System.in);
		int side1, length, breadth, side3;
		double radius;
		String choice;
		int cont1;
		System.out.println("Enter side of square");
		side1 = inp.nextInt();
		System.out.println("Enter length of rectangle");
		length = inp.nextInt();
		System.out.println("Enter breadth of rectangle");
		breadth = inp.nextInt();
		System.out.println("Enter third side of rectangle");
		side3 = inp.nextInt();
		System.out.println("Enter radius of radius of circle");
		radius = inp.nextDouble();
		do {
			
			
			System.out.println("1..Square..");
			System.out.println("2..Rectangle..");
			System.out.println("3..Circle..");
			System.out.println("4..Triangle..");
			System.out.println("Enter Choice");
			choice = inp.next();

			switch (choice) {
			case "Square":
				int a1, p1;
				Square s1 = new Square();
				a1 = s1.getArea(side1);
				p1 = s1.getPerimeter(side1);
				System.out.println("the Area of Square=" + a1);
				System.out.println("the Perimeter of Square=" + p1);
				break;
			case "Rectangle":
				int a2, p2;
				Rectangle rect = new Rectangle();
				a2 = rect.getArea(length, breadth);
				p2 = rect.getPerimeter(length, breadth);
				System.out.println("the area of rectangle=" + a2);
				System.out.println("the perimeter of rectangle=" + p2);
				break;
			case "Circle":
				double a3, p3;
				Circle circ = new Circle();
				a3 = circ.getArea(radius);
				p3 = circ.getPerimeter(radius);
				System.out.println("the area of circle=" + a3);
				System.out.println("the perimeter of circle=" + p3);
				break;
			case "Triangle":
				double a4, p4;
				Triangle tria = new Triangle();
				a4 = tria.getArea(length, breadth);
				p4 = tria.getPerimeter(length, breadth, side3);
				System.out.println("the arra of triangle=" + a4);
				System.out.println("the perimeter of trinagle=" + p4);
				break;

			default:
				System.out.println("the User has Inputed wrong choice");
			}

			System.out.println("do you want to continue 1-Continue 2-Exit ");
			cont1 = inp.nextInt();
		} while (cont1 == 1);

	}
}