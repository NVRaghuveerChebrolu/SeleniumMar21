package com.SeleniumConcepts;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class windows {

	public static void main(String[] args) {

		WebDriver driver;
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.get("http://www.naukri.com/");
		driver.manage().window().maximize();
		// implicit wait
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		String parentwindow = driver.getWindowHandle();
		Set<String> Allwindows=driver.getWindowHandles();
		System.out.println(Allwindows);
		Iterator<String> itr = Allwindows.iterator();
		while (itr.hasNext()) {
			String childwindow = itr.next();
			if (!parentwindow.equals(Allwindows)) {
				driver.switchTo().window(childwindow);
				System.out.println(driver.getTitle());
				if (driver.getTitle().equals("HSBC")) {
					driver.close();// only close the current instance of
											// WebDriver
					// driver.quit(); //it will close all the active instances
					// of
					// WebDriver
				}
			}
		}
		driver.switchTo().window(parentwindow);
		System.out.println(driver.getCurrentUrl());
	}
}