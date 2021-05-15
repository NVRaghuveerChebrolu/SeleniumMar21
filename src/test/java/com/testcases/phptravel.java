package com.testcases;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Properties;
import java.util.concurrent.TimeUnit;


import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.utility.lib;
import com.utility.objectRepository;
import com.utility.constants;

import io.github.bonigarcia.wdm.WebDriverManager;

public class phptravel extends lib {
	// WebDriver driver;
	HashMap<String, String> hm = new HashMap<String, String>();

	@Test(priority = 0)
	public void validatePhpLogin() {
		String TestCaseName = new Object() {
		}.getClass().getEnclosingMethod().getName();
		System.out.println("InsideTestCase : " + TestCaseName);
		System.out.println();
		Extenttest = ExtentReport.createTest("validatePhpLogin");
		System.out.println("inside Testcase1");
		lib.findElement(driver, objectRepository.phptraveluserName).sendKeys(property.getProperty("phpusername"));
		lib.findElement(driver, objectRepository.phptravelpassword).sendKeys("demouser");
		lib.findElement(driver, objectRepository.phptravelloginButton).click();
		/*
		String actualheader = lib.findElement(driver, objectRepository.actualheader).getText();
		System.out.println("actualheader : " + actualheader);
		String expectedHeader = "GMO OnLine";
		Assert.assertEquals(actualheader, expectedHeader);*/
	}

	
	@BeforeMethod
	public void beforeMethod() {
		System.out.println("inside beforeMethod");
		//lib.startBrowser(property.getProperty("browser"), property.getProperty("ApplicationGMO_Online"));
	}

	@AfterMethod
	public void afterMethod(ITestResult result) throws Exception {
		System.out.println("inside afterMethod");
		if (result.getStatus() == ITestResult.FAILURE) {
			Extenttest.log(Status.FAIL, "Test Cases Failed is : " + result.getName());
			Extenttest.log(Status.FAIL, "Test Cases Failed is : " + result.getThrowable());
			String SSpath = lib.takescreeshot(driver, result.getName());
			Extenttest.addScreenCaptureFromPath(SSpath);
		} else if (result.getStatus() == ITestResult.SUCCESS) {
			Extenttest.log(Status.PASS, "Test Cases pass is : " + result.getName());
		} else if (result.getStatus() == ITestResult.SKIP) {
			Extenttest.log(Status.SKIP, "Test Cases Skipped is : " + result.getName());
		}
	
	}

	@BeforeClass
	public void beforeClass() {
		System.out.println("inside beforeClass");
		System.out.println("browser : " + property.getProperty("browser"));
		System.out.println("Application : " + property.getProperty("ApplicationGMO_Online"));
		// Starting the browser by passing arguments browser type and
		// Application Name
		lib.startBrowser(property.getProperty("browser"), property.getProperty("Applicationphp"));
		// disabling console logs
		//lib.DisableConsoleLogs();
	}

	@AfterClass
	public void afterClass() {
		System.out.println("inside afterClass");
		//closing the browser which is having current instance. 
		driver.close();
	}

	@BeforeTest
	public void beforeTest() {
		System.out.println("inside beforeTest");
		// starting our extents reports
		lib.startReport();
		

	}

	@AfterTest
	public void afterTest() {
		System.out.println("inside afterTest");
		ExtentReport.flush();
	}

	@BeforeSuite
	public void beforeSuite() throws FileNotFoundException, Exception {
		System.out.println("inside beforeSuite");
		// Reading the properties file which contain Application URL ,
		// User Name and Password and Browser
		lib.readPropertiesFile();

	}

	@AfterSuite
	public void afterSuite() {
		System.out.println("inside afterSuite");

		// driver.quit
		// close the database connections
	}

}
