package com.java.programs;

public class overloadMain {

	public static void main(String a[]) {
		System.out.println("main method");
		main(10);
		main(10, 20);
	}

	public static void main(int a) {
		System.out.println("main method " + a);
	}

	public static void main(int a, int b) {
		System.out.println("main method " + a + b);
	}

	// Below main methods are not executed/called by JVM because JVM calls the
	// main method which is having String array of arguments as parameter
	public static void main(String args) {
		System.out.println("main with String");
	}

	public static void main() {
		System.out.println("main without args");
	}
	
}
