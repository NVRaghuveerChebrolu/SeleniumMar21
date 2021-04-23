package com.Java.Oops.Concepts;

class Animal123 {
	void eat() {
		System.out.println("eating...");
	}
}

class Dog456 extends Animal123 {
	void bark() {
		System.out.println("barking...");
	}
}

public class Hierarchial_Inheritance extends Animal {
	void meow() {
		System.out.println("meowing...");
	}

	public static void main(String args[]) {
		Hierarchial_Inheritance c = new Hierarchial_Inheritance();
		c.meow();
		c.eat();
		//c.bark();//Compile Time Error
	}
}