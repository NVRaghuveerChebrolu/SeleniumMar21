package com.SeleniumConcepts;

import java.net.HttpURLConnection;
import java.net.URL;
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

public class brokenLinks {
	String salary;

	public static void main(String[] args) {
		WebDriver driver = null;
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.get("https://demoqa.com/broken");
		driver.manage().window().maximize();
		// implicit wait
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		// driver.findElement(By.xpath("https://editor.datatables.net/examples/inline-editing/simple")).click();

		// Storing the links in a list and traversing through the links
		List<WebElement> links1 = driver.findElements(By.tagName("a"));

		// This line will print the number of links and the count of links.
		System.out.println("No of links are " + links1.size());

		// checking the links fetched.
		for (int i = 0; i < links1.size(); i++) {
			WebElement E1 = links1.get(i);
			String url = E1.getAttribute("href");
			System.out.println("url from browser : "+url);
			verifyLinks(url);
		}
	}

	private static void verifyLinks(String linkUrl) {
		try {
			URL url1 = new URL(linkUrl);

			// Now we will be creating url connection and getting the response
			// code
			HttpURLConnection httpURLConnect = (HttpURLConnection) url1.openConnection();
			httpURLConnect.setConnectTimeout(5000);
			httpURLConnect.connect();
			int responsecode = httpURLConnect.getResponseCode();
			if (responsecode >= 400) {
				System.out.println(linkUrl + " - " + httpURLConnect.getResponseMessage() + "is a broken link");
			}

			// Fetching and Printing the response code obtained
			else if (responsecode == 200) {
				System.out.println(linkUrl + " - " + httpURLConnect.getResponseMessage());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
