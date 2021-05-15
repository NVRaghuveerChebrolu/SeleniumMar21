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

public class TestNgClass3 extends lib {
	// WebDriver driver;
	HashMap<String, String> hm = new HashMap<String, String>();

	@Test(priority = 0)
	public void validateGmoOnlineLoadedSuccessfully() {
		String TestCaseName = new Object() {
		}.getClass().getEnclosingMethod().getName();
		System.out.println("InsideTestCase : " + TestCaseName);
		System.out.println();
		Extenttest = ExtentReport.createTest("validateGmoOnlineLoadedSuccessfully");
		System.out.println("inside Testcase1");
		// String actualheader =
		// driver.findElement(By.xpath(objectRepository.actualheader)).getText();
		String actualheader = lib.findElement(driver, objectRepository.actualheader).getText();
		System.out.println("actualheader : " + actualheader);
		String expectedHeader = "GMO OnLine";
		Assert.assertEquals(actualheader, expectedHeader);
	}

	@Test(priority = 1, dependsOnMethods = ("validateGmoOnlineLoadedSuccessfully"))
	public void validateOnLineCatalogLoadedSuccessfully() {
		Extenttest = ExtentReport.createTest("validateOnLineCatalogLoadedSuccessfully");
		System.out.println("inside Testcase2");
		// driver.findElement(By.xpath(objectRepository.EnterGmoOnline)).click();
		lib.findElement(driver, objectRepository.EnterGmoOnline).click();
		lib.waitForPageToLoad(driver);
		String actualtile = driver.getTitle();
		System.out.println(actualtile);
		String expectedtitle = "OnLine Catalog";
		Assert.assertEquals(actualtile, expectedtitle);
	}

	@Test(priority = 2)
	public void validateOrderQuantityGlacierSunGlasses() {
		System.out.println("inside Testcase3");
		SoftAssert softAssert = new SoftAssert();
		Extenttest = ExtentReport.createTest("validateOrderQuantityGlacierSunGlasses");
		// driver.findElement(By.xpath(objectRepository.QTY_GLASSES)).sendKeys("3");
		// driver.findElement(By.xpath(objectRepository.orderQuantitySubmitButton)).click();
		lib.findElement(driver, objectRepository.QTY_GLASSES).sendKeys(constants.orderQnty);
		lib.findElement(driver, objectRepository.orderQuantitySubmitButton).click();
		int quantity = Integer.parseInt(constants.orderQnty);
		String Acutaltitle = driver.getTitle();
		String Expectedtitle = "Place Order";
		Assert.assertEquals(Acutaltitle, Expectedtitle);
		// String Unitprice =
		// driver.findElement(By.xpath(objectRepository.orderQuantityUnitPrice)).getText();
		String Unitprice = lib.findElement(driver, objectRepository.orderQuantityUnitPrice).getText();
		System.out.println("Unitprice : " + Unitprice);
		String Price = Unitprice.substring(Integer.parseInt(constants.orderQnty));
		System.out.println(Price);
		// String Totalprice =
		// driver.findElement(By.xpath(objectRepository.orderQuantityTotalPrice)).getText();
		String Totalprice = lib.findElement(driver, objectRepository.orderQuantityTotalPrice).getText();
		float priceofSigleUnit = Float.parseFloat(Price);
		float ActualPrice = quantity * priceofSigleUnit;
		System.out.println("ActualPrice : " + ActualPrice);
		System.out.println("Totalprice : " + Totalprice);
		Extenttest.createNode("Comparing ActualPrice and Totalprice");
		softAssert.assertEquals(constants.DollerCurrency+" " + ActualPrice, Totalprice);
		// Assert.assertEquals("$" + ActualPrice, Totalprice);

		// driver.findElement(By.xpath(objectRepository.orderQtysubmitButton)).click();
		lib.findElement(driver, objectRepository.orderQtysubmitButton).click();
		lib.waitForPageToLoad(driver);
		String titleBillingInfo = driver.getTitle();
		String ExpectedtitleBillingInfo = "Billing Information";
		Extenttest.createNode("Comparing titleBillingInfo and ExpectedtitleBillingInfo");
		Assert.assertEquals(titleBillingInfo, ExpectedtitleBillingInfo);
		softAssert.assertAll();

	}

	@Test(priority = 3)
	public void ValidatingBillingInfoWithDataDriven() throws Exception {
		System.out.println("inside TestCase4");
		try {
			Extenttest = ExtentReport.createTest("ValidatingBillingInfoWithDataDriven");
			File objFile = new File(System.getProperty("user.dir") + "//src//test//resources//TestData.xlsx");
			FileInputStream objFileFileInputStream = new FileInputStream(objFile);
			System.out.println("objFileFileInputStream: " + objFileFileInputStream);
			XSSFWorkbook objXSSFWorkbook = new XSSFWorkbook(objFileFileInputStream);
			XSSFSheet objXSSFSheet = objXSSFWorkbook.getSheet("BillingInfoValidation");
			int RowsCount = objXSSFSheet.getLastRowNum();
			System.out.println("RowsCount: " + RowsCount);
			for (int rowNumber = 1; rowNumber <= RowsCount; rowNumber++) {
				readTestData(rowNumber, objXSSFSheet);
				if (hm.get("RunMode").equals("Yes")) {
					lib.findElement(driver, objectRepository.customerbillName).clear();
					lib.findElement(driver, objectRepository.customerbillName).sendKeys(hm.get("Name"));
					lib.findElement(driver, objectRepository.customerbillAddress).clear();
					lib.findElement(driver, objectRepository.customerbillAddress).sendKeys(hm.get("Address"));
					lib.findElement(driver, objectRepository.customerbillCity).clear();
					lib.findElement(driver, objectRepository.customerbillCity).sendKeys(hm.get("City"));
					lib.findElement(driver, objectRepository.customerbillState).clear();
					lib.findElement(driver, objectRepository.customerbillState).sendKeys(hm.get("State"));
					lib.findElement(driver, objectRepository.customerbillZipCode).clear();
					lib.findElement(driver, objectRepository.customerbillZipCode).sendKeys(hm.get("Zip"));
					lib.findElement(driver, objectRepository.customerbillPhone).clear();
					lib.findElement(driver, objectRepository.customerbillPhone).sendKeys(hm.get("Phone"));
					lib.findElement(driver, objectRepository.customerbillEmail).clear();
					lib.findElement(driver, objectRepository.customerbillEmail).sendKeys(hm.get("Email"));
					SelectValueFromDropdown(driver, objectRepository.customerCardType, hm.get("CreditCard"));
					lib.findElement(driver, objectRepository.customerCardNumber).clear();
					lib.findElement(driver, objectRepository.customerCardNumber).sendKeys(hm.get("CardNumber"));
					lib.findElement(driver, objectRepository.customerCardDate).clear();
					lib.findElement(driver, objectRepository.customerCardDate).sendKeys(hm.get("ExpirationDate"));
					FileOutputStream objfileoutput = new FileOutputStream(objFile);
					uploadTheResultToExcel(objXSSFWorkbook,rowNumber);
					objXSSFWorkbook.write(objfileoutput);
					objfileoutput.close();
				} else {
					System.out.println("RunMode is not marked as Yes for row number " + rowNumber);
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void uploadTheResultToExcel(XSSFWorkbook objXSSFWorkbook, int rowNumber) {
		XSSFSheet objSheet=objXSSFWorkbook.getSheet("BillingInfoValidation");
		XSSFCellStyle CellStyle=objXSSFWorkbook.createCellStyle();
		//CellStyle.setBorderBottom(XSSFCellStyle.BORDER_THIN);
		System.out.println("Row Number in excel is :"+rowNumber);
		objSheet.getRow(rowNumber).createCell(12).setCellValue("PASS");	
		objSheet.getRow(rowNumber).getCell(12).setCellStyle(CellStyle);
		
		
	}

	private void readTestData(int row, XSSFSheet objXSSFSheet) {
		DataFormatter DataFormatterObj = new DataFormatter();
		hm.put("RunMode", objXSSFSheet.getRow(row).getCell(0).getStringCellValue());
		hm.put("TestCaseName", objXSSFSheet.getRow(row).getCell(1).getStringCellValue());
		hm.put("Name", objXSSFSheet.getRow(row).getCell(2).getStringCellValue());
		hm.put("Address", objXSSFSheet.getRow(row).getCell(3).getStringCellValue());
		hm.put("City", objXSSFSheet.getRow(row).getCell(4).getStringCellValue());
		hm.put("State", objXSSFSheet.getRow(row).getCell(5).getStringCellValue());
		String Zipcode = DataFormatterObj.formatCellValue(objXSSFSheet.getRow(row).getCell(6));
		hm.put("Zip", Zipcode);
		String Phone = DataFormatterObj.formatCellValue(objXSSFSheet.getRow(row).getCell(7));
		hm.put("Phone", Phone);
		hm.put("Email", objXSSFSheet.getRow(row).getCell(8).getStringCellValue());
		hm.put("CreditCard", objXSSFSheet.getRow(row).getCell(9).getStringCellValue());
		String CardNumber = DataFormatterObj.formatCellValue(objXSSFSheet.getRow(row).getCell(10));
		hm.put("CardNumber", CardNumber);
		String ExpirationDate = DataFormatterObj.formatCellValue(objXSSFSheet.getRow(row).getCell(11));
		hm.put("ExpirationDate", ExpirationDate);

	}

	private static void SelectValueFromDropdown(WebDriver driver, String xpath, String dropdownvalue) {
		WebElement objweb = driver.findElement(By.xpath(xpath));
		Select objselect = new Select(objweb);
		objselect.selectByVisibleText(dropdownvalue);
		// List<WebElement> Alloptions = objselect.getOptions();
		// System.out.println(Alloptions);
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
		lib.startBrowser(property.getProperty("browser"), property.getProperty("ApplicationGMO_Online"));
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
