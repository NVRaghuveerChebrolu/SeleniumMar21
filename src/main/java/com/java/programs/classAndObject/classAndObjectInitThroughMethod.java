package com.java.programs.classAndObject;

class student123 {
	int rollno;
	String name;

	void insertRecord(int r, String n) {
		rollno = r;
		name = n;
	}

	void displayInformation() {
		System.out.println(rollno + " " + name);
	}
}

 class classAndObjectInitThroughMethod {
	public static void main(String args[]) {
		student123 s1 = new student123();
		student123 s2 = new student123();
		s1.insertRecord(111, "Karan");
		s2.insertRecord(222, "Aryan");
		s1.displayInformation();
		s2.displayInformation();
	}
}
