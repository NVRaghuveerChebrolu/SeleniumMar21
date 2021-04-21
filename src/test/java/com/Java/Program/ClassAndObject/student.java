//Java Program to illustrate how to define a class and fields  
package com.Java.Program.ClassAndObject;

public class student { 
		 //defining fields  
		 int id;//field or data member or instance variable  
		 String name;  
		 //creating main method inside the Student class  
		 public static void main(String args[]){  
		  //Creating an object or instance  
			 student s1=new student();//creating an object of Student  
		  //Printing values of the object  
		  System.out.println(s1.id);//accessing member through reference variable  
		  System.out.println(s1.name);  
		 }
}
