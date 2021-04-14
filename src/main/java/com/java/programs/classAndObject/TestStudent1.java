package com.java.programs.classAndObject;
//Java Program to demonstrate having the main method in   
//another class  
//Creating Student class.  
class student1{  
 int id;  
 String name;  
}  
//Creating another class TestStudent1 which contains the main method  
class TestStudent1{  
 public static void main(String args[]){  
	 student1 s1=new student1();  
  System.out.println(s1.id);  
  System.out.println(s1.name);  
 }  
}  