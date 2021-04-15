package com.Java.Concepts;

class ABCDE {
	ABCDE getABCDE() {
		return this;
		
	}

	void msg() {
		System.out.println("Hello java");
		System.err.println(this);
	}
}

class thisCurrentInstance {
	public static void main(String args[]) {
		
		ABCDE obj= new ABCDE(); 
		obj.msg();
		System.out.println("obj with class: " + obj);
		new ABCDE().getABCDE().msg();
		
	}
}