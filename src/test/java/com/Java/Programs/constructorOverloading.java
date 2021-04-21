package com.Java.Programs;

//Java program to overload constructors  
class constructorOverloading {
	int id;
	String name;
	int age;

	// creating two arg constructor
	constructorOverloading(int i, String n) {
		id = i;
		name = n;
	}

	// creating three arg constructor
	constructorOverloading(int i, String n, int a) {
		id = i;
		name = n;
		age = a;
	}

	void display() {
		System.out.println(id + " " + name + " " + age);
	}

	public static void main(String args[]) {
		constructorOverloading s1 = new constructorOverloading(111, "Karan");
		constructorOverloading s2 = new constructorOverloading(222, "Aryan", 25);
		s1.display();
		s2.display();
	}
}