package com.SeleniumConcepts;

public class finalparamter {
	int cube(final int n) {
		n = n + 2;// can't be changed as n is final

		System.out.println(n * n * n);
		return n * n * n;
	}

	public static void main(String args[]) {
		finalparamter b = new finalparamter();
		b.cube(5);
	}
}

/*Q) Can we declare a constructor final?
No, because constructor is never inherited.*/