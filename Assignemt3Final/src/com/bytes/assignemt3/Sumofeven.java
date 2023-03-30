package com.bytes.assignemt3;
import java.util.Scanner;
public class Sumofeven {

		public static void main(String[] args) {
			Scanner inp=new Scanner(System.in);
			int n;
			System.out.println("Enter number");
			n=inp.nextInt();
			int array1[]=new int[n];
			int i;
			
			int sum1=0;		
			for(i=0;i<n;i++) {
				System.out.println("Enter array elements ");
				array1[i]=inp.nextInt();
			}
			for(i=0;i<n;i++) {
				if(array1[i]%2==0) {
					sum1=sum1+array1[i];
				}
			}
			System.out.println("the sum of even elements in the array1="+sum1);
		}

	}

