package com.bytes.assignemt2;
import java.util.Scanner;
public class Evenfinal{
	public static void main(String args[]){
		Scanner inp=new Scanner(System.in);
	    int range,i;
	    System.out.println("enter range");
	    range=inp.nextInt();
	    for(i=1;i<=range;i++) {
	    	if(i%2==0) {
	    		System.out.print("the even numbers are="+i);
	    	}
	    	
	    	
	 
	    }
	    for(i=1;i<=range;i++) {
	    	if(i%2==1) {
	    		System.out.println("the odd numbers are="+i);
	    	}
	    	
	 
	    }
	}

}
