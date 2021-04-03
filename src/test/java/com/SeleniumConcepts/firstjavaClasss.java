package com.SeleniumConcepts;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import io.github.bonigarcia.wdm.WebDriverManager;

public class firstjavaClasss {

	public static void main(String[] args) {
		WebDriver driver = null;
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.get("http://demo.automationtesting.in/Register.html");
		driver.manage().window().maximize();
		// implicit wait
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		// driver.findElement(By.xpath("//input[@value='Enter GMO
		// OnLine']")).click();

		WebElement ele = driver.findElement(By.xpath("//input[@value='Male']"));
		System.out.println("----------innerhtml------");
		System.out.println(ele.getAttribute("innerHTML"));
		System.out.println("----------outerhtml------");
		System.out.println(ele.getAttribute("outerHTML"));

		System.out.println("----------outerhtml------");
		System.out.println(ele.getAttribute("value"));

		String text = driver.findElement(By.xpath("//div[@class='form-group'][6]/label")).getText();
		System.out.println(text);
		// find the links in webpage and print them
		/*
		 * List<WebElement> AllLinks=driver.findElements(By.tagName("a"));
		 * System.out.println(AllLinks.size()); for(WebElement link: AllLinks){
		 * System.out.println(link.getText()); }
		 */

		/*
		 * WebDriverWait wait = new WebDriverWait(driver, 30); WebElement
		 * ele=driver.findElement(By.xpath("//input[@value='Enter GMO OnLine']"
		 * )); wait.until(ExpectedConditions.elementToBeSelected(By.xpath("")));
		 */

		/*
		 * String str=driver.getPageSource(); System.out.println();
		 */

		// drop downs:

		SelectValueFromDropdown(driver, "//select[@id='Skills']", "Android");
		/*
		 * SelectValueFromDropdown(driver,"//select[@id='countries']","Angola");
		 * SelectValueFromDropdown(driver,"//select[@id='yearbox']","1922");
		 * SelectValueFromDropdown(driver,"//select[@placeholder='Month']",
		 * "April");
		 * SelectValueFromDropdown(driver,"//select[@id='daybox']","15");
		 */

		ExtractValuesFromDropdown(driver, "//select[@id='Skills']/option", "Android");

		/*
		 * WebElement Skills= driver.findElement(By.xpath("")); Select objSkills
		 * = new Select(Skills); objSkills.selectByIndex(20);
		 * 
		 * 
		 * WebElement countries=
		 * driver.findElement(By.xpath("//select[@id='countries']")); Select
		 * objcountries = new Select(countries);
		 * objcountries.selectByValue("Algeria");
		 * 
		 * 
		 * WebElement yearbox=
		 * driver.findElement(By.xpath("//select[@id='yearbox']")); Select
		 * objyearbox = new Select(yearbox);
		 * objyearbox.selectByVisibleText("1918");
		 */

	}

	private static void SelectValueFromDropdown(WebDriver driver, String xpath, String dropdownvalue) {
		WebElement objweb = driver.findElement(By.xpath(xpath));
		Select objselect = new Select(objweb);
		// objselect.selectByValue(dropdownvalue);
		List<WebElement> Alloptions = objselect.getOptions();
		System.out.println(Alloptions);
	}

	private static void ExtractValuesFromDropdown(WebDriver driver, String xpath, String dropdownvalue) {
		List<WebElement> Alloptions = driver.findElements(By.xpath(xpath));
		int dropdowncount = Alloptions.size();
		for (int i = 0; i < dropdowncount; i++) {
			System.out.println(Alloptions.get(i).getText());
			String str = Alloptions.get(i).getText();
			if (str.equalsIgnoreCase("Processing")) {
				System.out.println("Processing found");
			} /*else {
				System.out.println("Processing not found");
			}*/
		}
	}

}
