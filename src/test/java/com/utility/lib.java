package com.utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.maven.surefire.shade.org.apache.maven.shared.utils.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

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
		property.getProperty("GmoOnlineURL");
		System.out.println(property.getProperty("GmoOnlineURL"));
		System.out.println(property.getProperty("browser"));
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
		}

		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);// Implicit
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
}
