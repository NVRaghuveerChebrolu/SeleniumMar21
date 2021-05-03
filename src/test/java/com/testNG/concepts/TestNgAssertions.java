package com.testNG.concepts;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TestNgAssertions {
	@Test
	public void Assertions() {
		SoftAssert softAssert = new SoftAssert();
		WebDriver driver;
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.get("https://demo.borland.com/gmopost/");
		String expectedtitle = "GMO OnLie";
		String pagetitle = driver.findElement(By.xpath("//font[contains(text(),'GMO OnLine')]")).getText();
		Assert.assertEquals(pagetitle, expectedtitle);
		softAssert.assertEquals(expectedtitle, pagetitle);
		System.out.println("verified title successfully");
		driver.close();
		softAssert.assertAll();
	}
}
