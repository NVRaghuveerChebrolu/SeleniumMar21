package com.Java.Oops.Concepts;

import com.Java.Program.ClassAndObject.B224455;

/*using inhertence
protected method which is located in another package is accessable*/

class protectedAccessModifier extends B224455 {
	public static void main(String args[]) {
		protectedAccessModifier obj1234 = new protectedAccessModifier();
		System.out.println("using inhertence");
		obj1234.msgThroughInherit();
	}
}