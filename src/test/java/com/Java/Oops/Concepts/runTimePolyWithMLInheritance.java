package com.Java.Oops.Concepts;

class Animal7457 {
	void eat() {
		System.out.println("Animal7457 is eating...");
	}
}

class dog1234 extends Animal7457 {
	void eat() {
		System.out.println("dog is eating...");
	}
}

public class runTimePolyWithMLInheritance extends dog1234 {
	/*
	 * void eat() { System.out.println(
	 * "runTimePolyWithMLInheritance is eating..."); }
	 */
	public static void main(String args[]) {
		Animal7457 a = new runTimePolyWithMLInheritance();
		a.eat();
	}
}