package com.SeleniumConcepts;

public class blankFinalVariable {
	final int speedlimit;// blank final variable

	blankFinalVariable() {
		speedlimit = 70;
		System.out.println(speedlimit);
	}

	public static void main(String args[]) {
		new blankFinalVariable();
	}
}
//blank final variable is used only inside a constructor