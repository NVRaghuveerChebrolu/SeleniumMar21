package com.SeleniumConcepts;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class frames {

	public static void main(String[] args) {

		WebDriver driver = null;
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.get("http://demo.automationtesting.in/Frames.html");
		driver.manage().window().maximize();
		// implicit wait
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		// driver.findElement(By.xpath("https://editor.datatables.net/examples/inline-editing/simple")).click();
		
		driver.switchTo().frame("SingleFrame");
		driver.findElement(By.xpath("//input[@type='text']")).sendKeys("Hello inside Single iframe");
		driver.switchTo().defaultContent();
		driver.findElement(By.xpath("//a[contains(text(),'Iframe with in an Iframe')]")).click();
		
		List<WebElement> Allframes=driver.findElements(By.tagName("iframe"));
		System.out.println(Allframes.size());
		
		
		driver.switchTo().frame(1);
		WebElement ele= driver.findElement(By.xpath("/html/body/section/div/div/iframe"));
		driver.switchTo().frame(ele);
		driver.findElement(By.xpath("//input[@type='text']")).sendKeys("Hello inside iframe in another iframe");
		driver.switchTo().defaultContent();
		
		
		driver.findElement(By.xpath("//a[contains(text(),'Register')]")).click();
	}

}
