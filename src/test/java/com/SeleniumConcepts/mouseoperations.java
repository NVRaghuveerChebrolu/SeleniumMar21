package com.SeleniumConcepts;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.maven.surefire.shade.org.apache.maven.shared.utils.io.FileUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import com.utility.lib;

import io.github.bonigarcia.wdm.WebDriverManager;

public class mouseoperations {

	public static void main(String[] args) throws InterruptedException {

		WebDriver driver;
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.get("http://swisnl.github.io/jQuery-contextMenu/demo.html");
		driver.manage().window().maximize();
		// implicit wait
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

		Actions action = new Actions(driver);
		/*
		 * WebElement ele=driver.findElement(By.xpath(
		 * "//span[contains(text(),'right click me')]"));
		 * action.contextClick(ele).build().perform();
		 * 
		 * driver.findElement(By.xpath(
		 * "//ul[@class='context-menu-list context-menu-root']/li[3]/span"
		 * )).click(); String copy=driver.findElement(By.xpath(
		 * "//ul[@class='context-menu-list context-menu-root']/li[3]/span"
		 * )).getText(); System.out.println(copy);
		 * 
		 * Alert alert = driver.switchTo().alert(); alert.accept();
		 * 
		 * driver.navigate().to("https://jqueryui.com/droppable/");
		 * 
		 * WebElement
		 * frame=driver.findElement(By.xpath("//iframe[@class='demo-frame']"));
		 * driver.switchTo().frame(frame);
		 * 
		 * WebElement source =
		 * driver.findElement(By.xpath("//div[@id='draggable']")); WebElement
		 * target = driver.findElement(By.xpath("//div[@id='droppable']"));
		 * 
		 * action.dragAndDrop(source, target).build().perform();
		 */
		
		driver.navigate().to("http://api.jquery.com/dblclick/");

		JavascriptExecutor js = (JavascriptExecutor) driver;
		// js.executeScript("window.scrollBy(0,1250)", "");

		WebElement iframe = driver.findElement(By.xpath("//iframe"));
		//driver.switchTo().frame(iframe);
		lib.switchToFrameWithWebElement(iframe);
		

		/*
		 * WebElement ele2 = driver.findElement(By.xpath(
		 * "//span[contains(text(),'Double click the block')]"));
		 * js.executeScript("arguments[0].scrollIntoView()", ele2);
		 */
		try {
			Boolean flag = driver.findElement(By.xpath("//span[contains(text(),'Double click the block')]/../div"))
					.isDisplayed();
			System.out.println(flag);

			if (flag == true) {
				WebElement doubleclick = driver
						.findElement(By.xpath("//span[contains(text(),'Double click the block')]/../div"));
				/*js.executeScript("arguments[0].scrollIntoView()", doubleclick);
				action.doubleClick(doubleclick).build().perform();*/
				lib.javascriptExecutorScroolIntoView(doubleclick, action);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		
		// js.executeScript("window.scrollBy(0,1250)", ""); //scrolling y axis
		// down by 1250 pixles ->vertical scroll bar
		// js.executeScript("window.scrollBy(0,-1250)", ""); //scrolling y axis
		// Up by 1250 pixles ->vertical scroll bar
		// js.executeScript("window.scrollBy(500,0)", ""); //scrolling x axis
		// right side by 500 pixles ->horizontal scroll bar
		// js.executeScript("window.scrollBy(-500,0)", ""); //scrolling x axis
		// left side by 500 pixles ->horizontal scroll bar
		// js.executeScript("window.scrollTo(document.body.scrollHeight,0)");//to
		// scroll vertically up
		Thread.sleep(3000);
		System.out.println("scroll up");
		// to scroll vertically up
		//((JavascriptExecutor) driver).executeScript("window.scrollTo(document.body.scrollHeight,0)");
		lib.javascriptExecutorScroolUP(driver,"window.scrollTo(document.body.scrollHeight,0)");
		Thread.sleep(3000);
		System.out.println("scroll down");
		// to scroll vertically down
		// js.executeScript("window.scrollTo(0,document.body.scrollHeight)");

		//((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight)");
		lib.javascriptExecutorScroolDown("window.scrollTo(0, document.body.scrollHeight)");
		
		File obj = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		try {
			String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
			System.out.println(dateName);
			FileUtils.copyFile(obj, new File(
					System.getProperty("user.dir") + "//test-output//screenshots//" + dateName + "captured.png"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}