package com.utility;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.maven.surefire.shade.org.apache.maven.shared.utils.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import io.github.bonigarcia.wdm.WebDriverManager;

public class lib {
	public static ExtentHtmlReporter htmlReporter;
	public static ExtentReports ExtentReport;
	public ExtentTest Extenttest;
	public static Properties property = new Properties();
	public static WebDriver driver;

	static {
		System.out.println("Execute before main");
	}

	/*
	 * Raghu: Below method is used to start the report and store the
	 * configuration information inside the report
	 */
	public static void startReport() {
		htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir") + "//test-output//ExtentReport.html");
		htmlReporter.config().setDocumentTitle("Automation Report-ExtentReport");
		htmlReporter.config().setReportName("Functional Testing-ExtentReport");
		htmlReporter.config().setTheme(Theme.DARK);

		ExtentReport = new ExtentReports();
		ExtentReport.attachReporter(htmlReporter);
		ExtentReport.setSystemInfo("Host Name", "localhost");
		ExtentReport.setSystemInfo("user", "raghu");
		ExtentReport.setSystemInfo("Environment", "SIT");
		ExtentReport.setSystemInfo("Browser", "Chrome");
	}

	public static void readPropertiesFile() throws Exception {
		FileInputStream File = new FileInputStream(
				new File(System.getProperty("user.dir") + "//src//test//resources//config.properties"));
		property.load(File);
		if(property.getProperty("environment").equalsIgnoreCase(constants.SITenvironment)){
			property.getProperty("GmoOnlineURL_SIT");
			System.out.println(property.getProperty("GmoOnlineURL_SIT"));
			System.out.println(property.getProperty("browser"));
		}else if(property.getProperty("environment").equalsIgnoreCase(constants.UATenvironment)){
			property.getProperty("GmoOnlineURL_UAT");
			System.out.println(property.getProperty("GmoOnlineURL_UAT"));
			System.out.println(property.getProperty("browser"));
		}else if (property.getProperty("environment").equalsIgnoreCase(constants.PRODenvironment)){
			property.getProperty("GmoOnlineURL_PROD");	
			System.out.println(property.getProperty("GmoOnlineURL_PROD"));
			System.out.println(property.getProperty("browser"));
		}
		
	}

	public static void startBrowser(String browser, String Application) {
		System.out.println("inside beforeClass");
		if (browser.equals("chrome")) {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
		} else if (browser.equals("IE")) {
			WebDriverManager.iedriver().setup();
			driver = new InternetExplorerDriver();
		} else if (browser.equals("firefox")) {
			WebDriverManager.firefoxdriver();
			driver = new FirefoxDriver();
		}
		if (Application.equals("GMO_Online")) {
			driver.get(property.getProperty("GmoOnlineURL"));
		} else if (Application.equals("php")) {
			driver.get(property.getProperty("phpurl"));
		}

		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);// Implicit
																		// //
																		// wait
		driver.manage().window().maximize();
	}
	
	//overloaded above method
	public static void startBrowser(String browser, String Application,String env) {
		System.out.println("inside beforeClass");
		if (browser.equals("chrome")) {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
		} else if (browser.equals("IE")) {
			WebDriverManager.iedriver().setup();
			driver = new InternetExplorerDriver();
		} else if (browser.equals("firefox")) {
			WebDriverManager.firefoxdriver();
			driver = new FirefoxDriver();
		}
		if ((Application.equals("GMO_Online"))&&(env.equals(constants.SITenvironment))) {
			driver.get(property.getProperty("GmoOnlineURL_SIT"));
		} else if((Application.equals("GMO_Online"))&&(env.equals(constants.UATenvironment))) {
			driver.get(property.getProperty("GmoOnlineURL_UAT"));
		}else if((Application.equals("GMO_Online"))&&(env.equals(constants.PRODenvironment))) {
			driver.get(property.getProperty("GmoOnlineURL_PROD"));
		}
		else if (Application.equals("php")) {
			driver.get(property.getProperty("phpurl"));
		}

		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);// Implicit
																		// //
																		// wait
		driver.manage().window().maximize();
	}

	public static String takescreeshot(WebDriver driver2, String name) throws Exception {
		File source = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
		System.out.println(dateName);
		String destination = System.getProperty("user.dir") + "//test-output//screenshots//" + dateName + name
				+ "captured.png";
		FileUtils.copyFile(source, new File(destination));
		return destination;
	}

	public static void waitForPageToLoad(WebDriver driver) {
		ExpectedCondition<Boolean> pageLoadCondition = new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver driver) {
				return ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");
			}
		};
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(pageLoadCondition);
	}

	public static WebElement findElement(WebDriver driver, String Identifier) {
		WebElement element = null;
		WebDriverWait wait1 = new WebDriverWait(driver, 90);

		By search = null;
		// System.out.println("Identifier : "+Identifier);
		String locator = Identifier.split("&")[0].trim();
		String description = Identifier.split("&")[1].trim();
		if (locator.equals("id")) {
			search = By.id(description);
		} else if (locator.equals("name")) {
			search = By.name(description);
		} else if (locator.equals("classname")) {
			search = By.className(description);
		} else if (locator.equals("linktext")) {
			search = By.linkText(description);
		} else if (locator.equals("partiallinktext")) {
			search = By.partialLinkText(description);
		} else if (locator.equals("xpath")) {
			search = By.xpath(description);
		} else if (locator.equals("tagname")) {
			search = By.tagName(description);
		} else if (locator.equals("css")) {
			search = By.cssSelector(description);
		}
		System.out.println("locator : " + locator);
		System.out.println("description : " + description);
		// return element;
		// element=driver.findElement(search);
		wait1.until(ExpectedConditions.elementToBeClickable(search));
		return driver.findElement(search);

	}

	public static void javascriptExecutorScroolDown(String str) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript(str);
	}

	public static void javascriptExecutorScroolDown(WebDriver driver,int i, int j) {
		//Object x = (Integer) j;
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(" + Integer.toString(i) + "," + Integer.toString(j) + ")");
		 
	}

	public static void javascriptExecutorScroolUP(WebDriver driver,String str) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript(str);
	}

	public static void javascriptExecutorScroolUP(WebDriver driver,int x, int y) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(" + Integer.toString(x) + "," + Integer.toString(y) + ")", "");
	}

	public static void javascriptExecutorScroolLeft(WebDriver driver,int x, int y) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(" + Integer.toString(x) + "," + Integer.toString(y) + ")", "");
	}

	public static void javascriptExecutorScroolRight(WebDriver driver,int x, int y) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(" + Integer.toString(x) + "," + Integer.toString(y) + ")", "");
	}

	public static void javascriptExecutorScroolIntoView(WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView()", element);
	}

	public static void javascriptExecutorScroolIntoView(WebElement element, Actions obj) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView()", element);
		obj.doubleClick(element).build().perform();
	}

	public static void switchToFrameWithIndex(int index) {
		driver.switchTo().frame(index);
	}

	public static void switchToFrameWithString(String name) {
		driver.switchTo().frame(name);
	}

	public static void switchToFrameWithWebElement(WebElement element) {
		driver.switchTo().frame(element);
	}

	public static void setClipboardContents(String string) {
		StringSelection stringSelection = new StringSelection(string);
		Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
		clipboard.setContents(stringSelection, null);
		try {
			Transferable t = clipboard.getContents(null);
			if (t.isDataFlavorSupported(DataFlavor.stringFlavor))
				System.out.println(t.getTransferData(DataFlavor.stringFlavor));
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/*
	 * public static void DisableConsoleLogs(){ Set<String>
	 * artifactoryLoggers=new
	 * HashSet<>(Arrays.asList("org.apache.http","groovyx.net.http")); for
	 * (String log: artifactoryLoggers){ ch.qos.logback.classic.logger
	 * artlogger=(ch.qos.logbacnk.classic.logger)org.slf4j.LoggerFactory.
	 * getLogger(log); artlogger.setLevel(ch.qos.logback.classic.level.INFO);
	 * artlogger.setAdditive(false); } }
	 */
}
