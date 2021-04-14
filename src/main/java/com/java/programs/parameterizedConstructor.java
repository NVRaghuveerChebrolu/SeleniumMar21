package com.java.programs;

//Java Program to demonstrate the use of the parameterized constructor.  
public class parameterizedConstructor {
	int id;
	String name;

	// creating a parameterized constructor
	parameterizedConstructor(int i, String n) {
		id = i;
		name = n;
	}

	// method to display the values
	void display() {
		System.out.println(id + " " + name);
	}

	public static void main(String args[]) {
		// creating objects and passing values
		parameterizedConstructor s1 = new parameterizedConstructor(111, "Karan");
		parameterizedConstructor s2 = new parameterizedConstructor(222, "Aryan");
		// calling method to display the values of object
		s1.display();
		s2.display();
	}
}