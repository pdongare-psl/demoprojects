package com.demoproject.testngparameterization;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.demoproject.pageclasses.HomePage;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import utilities.ExtentFactory;
import utilities.Screenshots;

/**
 * Purpose:Test class for Login page and navigating to Practice page
 * FileName:TestNGMultiBrowserAndParallelTest.java 
 * Suite Name:Parallel Test suite
 * Module Name:Login
 * 
 * @author pratik_dongare
 * @version 1.0
 *
 */
public class TestNGMultiBrowserAndParallelTest extends TestBase {

	private static WebDriver driver;
	private static String baseUrl;
	private static final Logger log = LogManager.getLogger(TestNGMultiBrowserAndParallelTest.class.getName());
	ExtentReports report;
	ExtentTest test;
	HomePage hp;
	TestNGMultiBrowserAndParallelTest multiBrowserTest;

	public TestNGMultiBrowserAndParallelTest() {
		super();

		log.info("Loading config.properties file");
	}

	@Parameters("browserType")
	@BeforeClass()
	public void setup(String browser) {
		log.info("This method is running from Before Class");

		multiBrowserTest = new TestNGMultiBrowserAndParallelTest();
		baseUrl = prop.getProperty("url");
		report = ExtentFactory.getInstance();
		log.info("Get Instance for Extent Reports");
		test = report.startTest("Test Suite");
		driver = TestBase.launchBrowser(browser);

		hp = new HomePage(driver, test);
		test.log(LogStatus.INFO, "Browser Started...");

		hp.maxmimizeBrowserWindow(driver);

		test.log(LogStatus.INFO, "Browser Maximized");
		hp.waitImplicitly(driver);

		hp.loadUrl(baseUrl, driver);

		test.log(LogStatus.INFO, "Web application opened");
	}

	@Test(priority = 0,description="Validate login functionality")
	public void testValidLoginTest() throws Exception {

		hp.login(prop.getProperty("username"), prop.getProperty("password"));
		hp.waitForSomeTime();
		boolean result = hp.isWelcomeTextPresent();
		Assert.assertTrue(result);
		test.log(LogStatus.PASS, "Verified Login test and Welcome Text");
	}

	@Test(priority = 1)
	public void navigateToPracticePage() {

		String actualText = hp.navigateToPracticePage();
		Assert.assertTrue(actualText.contains("Practice Page"), "Not navigated to Practice page");
		test.log(LogStatus.PASS, "Verified Navigation to Practice Page");
	}

	@Test(priority = 2)
	public void validateClickingCheckboxes() throws Exception {

		hp.waitForSomeTime();
		boolean selectedResult = hp.selectsMultipleCheckboxes();

		Assert.assertTrue(selectedResult);
		log.info("Verified all checkboxes are selected");
		test.log(LogStatus.PASS, "Verified Checkboxes selected");
	}

	@Test(priority = 3)
	public void validateCheckboxLabels() throws Exception {

		boolean result = hp.validateCheckboxes();
		Assert.assertTrue(result);
		log.info("Validated texts of checkboxes");
		test.log(LogStatus.PASS, "Verified Checkbox labels");
	}

	@AfterMethod
	public void tearDown(ITestResult result) throws IOException {
		if (result.getStatus() == ITestResult.FAILURE) {
			String path = Screenshots.takeScreenshot(driver, result.getName());
			String imagePath = test.addScreenCapture(path);
			test.log(LogStatus.FAIL, "Verify Welcome text failed ", imagePath);
		}
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
		report.endTest(test);
		report.flush();
	}
}