package com.testcases;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.AfterSuite;

public class TestNgClass1 {
	@Test(priority=0)
	public void Testcase1() {
		System.out.println("inside Testcase1");
	}

	@Test(priority=3,invocationCount=8)
	public void Aestcase2() {
		System.out.println("inside Testcase2");
	}

	@Test(priority=-3)
	public void Testcase3() {
		System.out.println("inside Testcase3");
	}

	@BeforeMethod
	public void beforeMethod() {
		System.out.println("inside beforeMethod");
	}

	@AfterMethod
	public void afterMethod() {
		System.out.println("inside afterMethod");
	}

	@BeforeClass
	public void beforeClass() {
		System.out.println("inside beforeClass");
	}

	@AfterClass
	public void afterClass() {
		System.out.println("inside afterClass");
	}

	@BeforeTest
	public void beforeTest() {
		System.out.println("inside beforeTest");
		//data driven reading
		//connection to database
	}

	@AfterTest
	public void afterTest() {
		System.out.println("inside afterTest");
		//close the file
	}

	@BeforeSuite
	public void beforeSuite() {
		System.out.println("inside beforeSuite");
		//readPropertiesfile();
		//reading the configuration files 
		//url and user id and password 
		//SIT/UAT
		//Browser
	}

	@AfterSuite
	public void afterSuite() {
		System.out.println("inside afterSuite");
		
		//driver.quit
		//close the database connections
	}

}
