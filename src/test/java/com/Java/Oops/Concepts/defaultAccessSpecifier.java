package com.Java.Oops.Concepts;

 
import com.Java.Program.ClassAndObject.A; 
 
class defaultAccessSpecifier{  
public static void main(String args[]){  
 A obj = new A();//Compile Time Error  
 obj.msg();//Compile Time Error  
}  
}