package com.java.programs;

public class factorial {

	public static void main(String[] args) {
		// 5!=5*4*3*2*1=120
		// n(n-1)!
		int i, factorial = 1;
		int num = 7;
		for (i = 1; i <= num; i++) {
			factorial = factorial * i;
			System.out.println(i);
			System.out.println("factorial: "+factorial);
		}
		System.out.println("factorial of " + num + " is " + factorial);
	}

}
