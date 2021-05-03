package com.testNG.concepts;

import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;

public class testNgDataProvider {

	@BeforeClass
	public void beforeClass() {
		System.out.println("Before class executed");
	}

	@Test(dataProvider = "dp")
	public void add(String user, String pwd) {
		System.out.println("inside test case add");
		System.out.println("user: " + user);
		System.out.println("pwd: " + pwd);

	}

	@DataProvider
	public Object[][] dp() {
		return new Object[][] { new Object[] { "user1", "pwd1" }, new Object[] { "user2", "pwd2" }, };
	}
}
