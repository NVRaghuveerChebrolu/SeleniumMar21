package com.java.programs;

import java.util.Scanner;

public class palindrome {

	public static void main(String[] args) {
		//madam
		//2345432
		String org,rev="";
		Scanner in=new Scanner(System.in);
		System.out.println("enter a string of your choice ");
		org=in.nextLine();
		int length=org.length();
		System.out.println("length: "+ length);
		for(int i=length-1;i>=0;i--){
			rev=rev+org.charAt(i);
			System.out.println("reverse string is : "+rev);
		}
		if(rev.equalsIgnoreCase(org)){
			System.out.println("The given string is a palindrome");
		}
	}

}
