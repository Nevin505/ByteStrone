package com.bytes.assignemt3;
public class pattern5{
	public static void main(String[] args) {
		int i,j,k,n;
		n=5;
		for(i=0;i<=n;i++) {
			for(j=0;j<i;j++) {
				System.out.print(" ");
			}
			for(k=0;k<=n-i;k++) {
				System.out.print("* ");
			}
			System.out.println(" ");
		}
	}
}