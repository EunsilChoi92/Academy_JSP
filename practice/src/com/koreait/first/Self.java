package com.koreait.first;

public class Self {
	
	public static void main(String[] args) {
	
		stars(5);
		
	}
	
	
	
	
	

	public static void stars(int star) {
		for(int i=1; i<=star; i++) {
			for(int j=1; j<=i; j++) {
				System.out.print("*");
			}
			System.out.println();
		}
		
		
		for(int i=(star-1); i>0; i--) {
			for(int j=i; j>0; j--) {
				System.out.print("*");
			}
			System.out.println();
		}
		
	}
}
