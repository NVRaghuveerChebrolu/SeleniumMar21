package com.SeleniumConcepts;

class Bike {
	 final void run() {
		System.out.println("running");
	}
}

public class finalMethod extends Bike {
	void run() {
		System.out.println("running safely with 100kmph");
	}

	public static void main(String args[]) {
		finalMethod honda = new finalMethod();
		honda.run();
	}
}