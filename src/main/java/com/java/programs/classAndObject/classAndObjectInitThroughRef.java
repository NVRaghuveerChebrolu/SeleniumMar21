package com.java.programs.classAndObject;

class TestStudent2 {
	int id;
	String name;
}

public class classAndObjectInitThroughRef {
	public static void main(String args[]) {
		TestStudent2 s1 = new TestStudent2();
		s1.id = 101;
		s1.name = "Sonoo";
		//// printing members with a white space
		System.out.println(s1.id + " " + s1.name);
	}
}
