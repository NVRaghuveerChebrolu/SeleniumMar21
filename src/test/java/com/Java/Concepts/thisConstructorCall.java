package com.Java.Concepts;

class B {
	thisConstructorCall obj;

	B(thisConstructorCall obj) {
		this.obj = obj;
	}

	void display() {
		System.out.println(obj.data);// using data member of A4 class
	}
}

public class thisConstructorCall {
	int data = 10;

	thisConstructorCall() {
		B b = new B(this);
		b.display();
	}

	public static void main(String args[]) {
		thisConstructorCall a = new thisConstructorCall();
	}
}