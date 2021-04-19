package com.SeleniumConcepts;

public class finalVariable {
	final int speedlimit = 90;// final variable

	void run() {
		speedlimit = 400;
		System.out.println("speedlimit : " + speedlimit);
	}

	public static void main(String args[]) {
		finalVariable obj = new finalVariable();
		obj.run();
	}
	/*Note : compile time error is observed when trying to change the variable which is 
	declared as final*/
}