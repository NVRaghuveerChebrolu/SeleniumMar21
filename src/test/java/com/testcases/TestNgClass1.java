package com.testcases;

import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.AfterSuite;

public class TestNgClass1 {
	WebDriver driver;
	HashMap<String,String> hm= new HashMap<String,String>();
	
	@Test(priority=0)
	public void validateGmoOnlineLoadedSuccessfully() {
		System.out.println("inside Testcase1");
		String actualheader=driver.findElement(By.xpath("//font[contains(text(),'GMO OnLine')]")).getText();
		System.out.println("actualheader : "+actualheader);
		String expectedHeader="GMO OnLine";
		Assert.assertEquals(actualheader, expectedHeader);
	}

	@Test(priority=1)
	public void validateOnLineCatalogLoadedSuccessfully() {
		System.out.println("inside Testcase2");
		driver.findElement(By.xpath("//input[@type='button' and @value='Enter GMO OnLine']")).click();
		String actualtile=driver.getTitle();
		System.out.println(actualtile);
		String expectedtitle="OnLine Catalog";
		Assert.assertEquals(actualtile, expectedtitle);
		
	}

	@Test(priority=2)
	public void validateOrderQuantityGlacierSunGlasses() {
		System.out.println("inside Testcase3");
		driver.findElement(By.xpath("//input[@name='QTY_GLASSES']")).sendKeys("3");
		driver.findElement(By.xpath("//input[@name='bSubmit']")).click();
		int quantity=3;
		String Acutaltitle = driver.getTitle();
		String Expectedtitle="Place Order";
		Assert.assertEquals(Acutaltitle, Expectedtitle);
		String Unitprice=driver.findElement(By.xpath("//tbody/tr[2]/td[4]")).getText();
		System.out.println("Unitprice : "+Unitprice);
		String Price=Unitprice.substring(2);
		System.out.println(Price);
		String Totalprice=driver.findElement(By.xpath("//tbody/tr[2]/td[5]")).getText();
		float priceofSigleUnit=Float.parseFloat(Price);
		float ActualPrice=quantity*priceofSigleUnit;
		System.out.println("ActualPrice : "+ActualPrice);
		System.out.println("Totalprice : "+Totalprice);
		Assert.assertEquals("$ "+ActualPrice, Totalprice);
		driver.findElement(By.xpath("//input[@type='submit']")).click();
		String titleBillingInfo=driver.getTitle();
		String ExpectedtitleBillingInfo="Billing Information";
		Assert.assertEquals(titleBillingInfo, ExpectedtitleBillingInfo);
		
	}
	
	
	@Test(priority=3)
	public void ValidatingBillingInfoWithDataDriven() throws Exception{
		System.out.println("inside TestCase4");
		try {
			File objFile=new File(System.getProperty("user.dir")+"//src//test//resources//TestData.xlsx");
			FileInputStream objFileFileInputStream = new FileInputStream(objFile);
			System.out.println("objFileFileInputStream: "+ objFileFileInputStream);
			XSSFWorkbook objXSSFWorkbook = new XSSFWorkbook(objFileFileInputStream);
			XSSFSheet objXSSFSheet = objXSSFWorkbook.getSheet("BillingInfoValidation");
			int RowsCount=objXSSFSheet.getLastRowNum();
			System.out.println("RowsCount: "+RowsCount);
			for (int rowNumber=1;rowNumber<RowsCount;rowNumber++){
				readTestData(rowNumber,objXSSFSheet);
				if(hm.get("RunMode").equals("Yes")){
				driver.findElement(By.xpath("//input[@name='billName']")).clear();
				driver.findElement(By.xpath("//input[@name='billName']")).sendKeys(hm.get("Name"));
				driver.findElement(By.xpath("//input[@name='billAddress']")).clear();
				driver.findElement(By.xpath("//input[@name='billAddress']")).sendKeys(hm.get("Address"));
				driver.findElement(By.xpath("//input[@name='billCity']")).clear();
				driver.findElement(By.xpath("//input[@name='billCity']")).sendKeys(hm.get("City"));
				driver.findElement(By.xpath("//input[@name='billState']")).clear();
				driver.findElement(By.xpath("//input[@name='billState']")).sendKeys(hm.get("State"));
				driver.findElement(By.xpath("//input[@name='billZipCode']")).clear();
				driver.findElement(By.xpath("//input[@name='billZipCode']")).sendKeys(hm.get("Zip"));
				driver.findElement(By.xpath("//input[@name='billPhone']")).clear();
				driver.findElement(By.xpath("//input[@name='billPhone']")).sendKeys(hm.get("Phone"));
				driver.findElement(By.xpath("//input[@name='billEmail']")).clear();
				driver.findElement(By.xpath("//input[@name='billEmail']")).sendKeys(hm.get("Email"));
				SelectValueFromDropdown(driver,"//select[@name='CardType']",hm.get("CreditCard"));
				driver.findElement(By.xpath("//input[@name='CardNumber']")).clear();
				driver.findElement(By.xpath("//input[@name='CardNumber']")).sendKeys(hm.get("CardNumber"));
				driver.findElement(By.xpath("//input[@name='CardDate']")).clear();
				driver.findElement(By.xpath("//input[@name='CardDate']")).sendKeys(hm.get("ExpirationDate"));
				}
				else {
					System.out.println("RunMode is not marked as Yes for rome number "+ rowNumber);
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
		//List<WebElement> Alloptions = objselect.getOptions();
		//System.out.println(Alloptions);
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
		WebDriverManager.chromedriver().setup();
		driver=new ChromeDriver();
		driver.get("https://demo.borland.com/gmopost/");
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);//Implicit wait
		driver.manage().window().maximize();
		
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
