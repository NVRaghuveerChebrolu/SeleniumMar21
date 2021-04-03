package com.SeleniumConcepts;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class webtable {
	String salary;

	public static void main(String[] args) {
		WebDriver driver = null;
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.get("https://editor.datatables.net/examples/inline-editing/simple");
		driver.manage().window().maximize();
		// implicit wait
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		// driver.findElement(By.xpath("https://editor.datatables.net/examples/inline-editing/simple")).click();

		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,500)");
		String positionpassed = "Pre-Sales Support";
		// String salaryofpositionpassed = getSalaryBasedonPosition(driver,
		// positionpassed);
		//getSalaryBasedonPosition(driver, positionpassed);
		// System.out.println(salaryofpositionpassed);

		List<WebElement> AllNames = driver.findElements(By.xpath("//table/tbody/tr/td[3]"));
		int length = AllNames.size();
		for (int i = 1; i <= length; i++) {
			String lastName = driver.findElement(By.xpath("//table/tbody/tr["+i+"]/td[3]")).getText();
			System.out.println("i value: "+i);
			System.out.println("lastName: "+lastName);
			if (lastName.equals("Nash")) {
				String salary = driver.findElement(By.xpath("//table/tbody/tr[" + i + "]/td[7]")).getText();
				System.out.println(salary);
			}
		}
	}

	private static void getSalaryBasedonPosition(WebDriver driver, String positionreceived) {
		List<WebElement> All = driver.findElements(By.xpath("//*[@id='example']/tbody/tr/td[4]"));
		int size = All.size();
		for (int i = 1; i < size; i++) {
			String positionfromtable = driver.findElement(By.xpath("//*[@id='example']/tbody/tr[" + i + "]/td[4]"))
					.getText();
			if (positionfromtable.equals(positionreceived)) {
				String salary = driver.findElement(By.xpath("//*[@id='example']/tbody/tr[" + i + "]/td[7]")).getText();
				System.out.println(salary);
			}
		}
		// return salary;
	}

}
