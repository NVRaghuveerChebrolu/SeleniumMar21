package com.testcases;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Properties;
import java.util.concurrent.TimeUnit;


import org.apache.poi.ss.usermodel.DataFormatter;
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

import io.github.bonigarcia.wdm.WebDriverManager;

public class TestNgClass2 extends lib {
	//WebDriver driver;
	HashMap<String, String> hm = new HashMap<String, String>();

	@Test(priority = 0)
	public void validateGmoOnlineLoadedSuccessfully() {
		String TestCaseName=new Object(){}.getClass().getEnclosingMethod().getName();
		System.out.println("InsideTestCase : "+TestCaseName);
		System.out.println();
		Extenttest = ExtentReport.createTest("validateGmoOnlineLoadedSuccessfully");
		System.out.println("inside Testcase1");
		String actualheader = driver.findElement(By.xpath(objectRepository.actualheader)).getText();
		System.out.println("actualheader : " + actualheader);
		String expectedHeader = "GMO OnLine";
		Assert.assertEquals(actualheader, expectedHeader);
	}

	@Test(priority = 1, dependsOnMethods = ("validateGmoOnlineLoadedSuccessfully"))
	public void validateOnLineCatalogLoadedSuccessfully() {
		Extenttest = ExtentReport.createTest("validateOnLineCatalogLoadedSuccessfully");
		System.out.println("inside Testcase2");
		driver.findElement(By.xpath(objectRepository.EnterGmoOnline)).click();
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
		driver.findElement(By.xpath(objectRepository.QTY_GLASSES)).sendKeys("3");
		driver.findElement(By.xpath(objectRepository.orderQuantitySubmitButton)).click();
		int quantity = 3;
		String Acutaltitle = driver.getTitle();
		String Expectedtitle = "Place Order";
		Assert.assertEquals(Acutaltitle, Expectedtitle);
		String Unitprice = driver.findElement(By.xpath(objectRepository.orderQuantityUnitPrice)).getText();
		System.out.println("Unitprice : " + Unitprice);
		String Price = Unitprice.substring(2);
		System.out.println(Price);
		String Totalprice = driver.findElement(By.xpath(objectRepository.orderQuantityTotalPrice)).getText();
		float priceofSigleUnit = Float.parseFloat(Price);
		float ActualPrice = quantity * priceofSigleUnit;
		System.out.println("ActualPrice : " + ActualPrice);
		System.out.println("Totalprice : " + Totalprice);
		Extenttest.createNode("Comparing ActualPrice and Totalprice");
		softAssert.assertEquals("$" + ActualPrice, Totalprice);
		// Assert.assertEquals("$" + ActualPrice, Totalprice);

		driver.findElement(By.xpath(objectRepository.orderQtysubmitButton)).click();
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
			for (int rowNumber = 1; rowNumber < RowsCount; rowNumber++) {
				readTestData(rowNumber, objXSSFSheet);
				if (hm.get("RunMode").equals("Yes")) {
					driver.findElement(By.xpath(objectRepository.customerbillName)).clear();
					driver.findElement(By.xpath(objectRepository.customerbillName)).sendKeys(hm.get("Name"));
					driver.findElement(By.xpath(objectRepository.customerbillAddress)).clear();
					driver.findElement(By.xpath(objectRepository.customerbillAddress)).sendKeys(hm.get("Address"));
					driver.findElement(By.xpath(objectRepository.customerbillCity)).clear();
					driver.findElement(By.xpath(objectRepository.customerbillCity)).sendKeys(hm.get("City"));
					driver.findElement(By.xpath(objectRepository.customerbillState)).clear();
					driver.findElement(By.xpath(objectRepository.customerbillState)).sendKeys(hm.get("State"));
					driver.findElement(By.xpath(objectRepository.customerbillZipCode)).clear();
					driver.findElement(By.xpath(objectRepository.customerbillZipCode)).sendKeys(hm.get("Zip"));
					driver.findElement(By.xpath(objectRepository.customerbillPhone)).clear();
					driver.findElement(By.xpath(objectRepository.customerbillPhone)).sendKeys(hm.get("Phone"));
					driver.findElement(By.xpath(objectRepository.customerbillEmail)).clear();
					driver.findElement(By.xpath(objectRepository.customerbillEmail)).sendKeys(hm.get("Email"));
					SelectValueFromDropdown(driver, objectRepository.customerCardType, hm.get("CreditCard"));
					driver.findElement(By.xpath(objectRepository.customerCardNumber)).clear();
					driver.findElement(By.xpath(objectRepository.customerCardNumber)).sendKeys(hm.get("CardNumber"));
					driver.findElement(By.xpath(objectRepository.customerCardDate)).clear();
					driver.findElement(By.xpath(objectRepository.customerCardDate)).sendKeys(hm.get("ExpirationDate"));
				} else {
					System.out.println("RunMode is not marked as Yes for row number " + rowNumber);
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
		System.out.println("browser : "+property.getProperty("browser"));
		System.out.println("Application : "+property.getProperty("ApplicationGMO_Online"));
		lib.startBrowser(property.getProperty("browser"),property.getProperty("ApplicationGMO_Online") );
	
	}

	@AfterClass
	public void afterClass() {
		System.out.println("inside afterClass");
	}

	@BeforeTest
	public void beforeTest() {
		System.out.println("inside beforeTest");
		TestNgClass2 obj = new TestNgClass2();
		obj.startReport();
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
		lib.readPropertiesFile();

	}

	@AfterSuite
	public void afterSuite() {
		System.out.println("inside afterSuite");

		// driver.quit
		// close the database connections
	}

}
