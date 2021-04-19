package com.SeleniumConcepts;

class staticblankfinalVariable {
	static final int data;// static blank final variable
	static {
		data = 50;
	}

	public static void main(String args[]) {
		System.out.println(staticblankfinalVariable.data);
	}
}

/*
 * Note : static blank final variable: A static final variable that is not
 * initialized at the time of declaration is known as static blank final
 * variable. It can be initialized only in static block.
 */