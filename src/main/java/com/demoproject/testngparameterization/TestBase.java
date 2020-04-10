package com.demoproject.testngparameterization;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

/**
 * Purpose:TestBase class for loading config.properties file and launching the required browser 
 * FileName:TestBase.java
 * 
 * @author pratik_dongare
 *
 */

public class TestBase {

	public static WebDriver driver;
	public static Properties prop = null;
	public static final Logger log = LogManager.getLogger(TestBase.class.getName());
	public static final String propFileName = "config.properties";
	protected TestBase base;

	public TestBase() {
		getPropValues();
	}

	public void getPropValues() {
		InputStream inputStream = null;

		try {
			prop = new Properties();
			ClassLoader loader = Thread.currentThread().getContextClassLoader();

			inputStream = loader.getResourceAsStream(propFileName);

			prop.load(inputStream);

		} catch (FileNotFoundException e) {
			System.out.println("Exception occured" + "property file" + propFileName + " not found in the classpath");
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static WebDriver launchBrowser(String browser) {
		// TODO Auto-generated method stub
		if (browser.equalsIgnoreCase("firefox")) {
			log.info("Launching Firefox browser");
			driver = new FirefoxDriver();
		} else if (browser.equalsIgnoreCase("chrome")) {
			log.info("Launching Chrome browser");
			driver = new ChromeDriver();
		}
		return driver;
	}
}
