package com.Java.Concepts;

public class FirstClassStringlearning {

	public static void main(String[] args) {
		String str = "LearningJava";// declaring a string using string literal
		String str2 = new String("LearningJava1"); // declaring a string using
		// new operator
		String str3 = "LearningJava";
		
		char[] ch = str2.toCharArray();
		for (int i = str2.length() - 1; i >= 0; i--) {
			System.out.println(ch[i]);
		}
	}

}
