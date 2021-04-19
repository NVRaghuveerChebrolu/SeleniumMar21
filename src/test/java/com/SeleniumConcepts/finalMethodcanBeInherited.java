package com.SeleniumConcepts;

class Bike7643 {
	final void run() {
		System.out.println("running...");
	}
}

public class finalMethodcanBeInherited extends Bike7643 {
	public static void main(String args[]) {
		new finalMethodcanBeInherited().run();
	}
}

/*note : final method is inherited but you cannot override it*/