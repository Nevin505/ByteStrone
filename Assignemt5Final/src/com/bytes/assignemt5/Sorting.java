package com.bytes;
import java.util.Scanner;
public class Sorting {
	
public static void main(String[] args) {
	Scanner inp=new Scanner(System.in);
	int arraylength;
	arraylength=inp.nextInt();
	int array1[]=new int[arraylength];
	int i;
	for(i=0;i<arraylength;i++) {
		array1[i]=inp.nextInt();	
	}
	int temproaryvariable;
	int j;
	
	for(i=0;i<arraylength-1;i++) {
		for(j=0;j<arraylength-i-1;j++) {
			if(array1[j]>array1[j+1]) {
				temproaryvariable=array1[j];
				array1[j]=array1[j+1];
				array1[j+1]=temproaryvariable;
			}
			
		}
	}
	
	for(i=0;i<arraylength;i++) {
		System.out.println(array1[i]);
	}
	
}
}
