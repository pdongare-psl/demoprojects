package com.demoproject.pageclasses;

import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.demoproject.pageobjectfactory.HomePageObject;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;


/**
 * Purpose:This class has method implementations which belong to the Home page and Practice page
 * File Name:HomePage.java
 * @author pratik_dongare
 *
 */
public class HomePage extends HomePageObject {

	ExtentTest test;
	WebDriver driver = null;
	private static final Logger log = LogManager.getLogger(HomePage.class.getName());

	public HomePage(WebDriver driver, ExtentTest test) {
		super(driver, test);
		this.driver = driver;
		this.test = test;
		log.info("PageFactory initialization for locating elements");
	}

	public void waitForSomeTime() {
		try {
			Thread.sleep(5000);
			log.info("Wait for 5 secs");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void waitImplicitly(WebDriver driver) {
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		log.info("Implicit wait method called");
	}

	public void clickClosebutton() {
		try {
		log.info("Explicitly waiting  for back to site button to pop up");
			WebDriverWait wait = new WebDriverWait(driver, 10);
			  wait.until(
					ExpectedConditions.visibilityOf(closeButton));
			closeButton.click();
			log.info("Clicked on close button and back to site");
		} catch (NoSuchElementException e) {
			System.out.println(e.getMessage());
		}
	}

	public void clickSignUpLink() {
		signupLink.click();
		log.info("Clicked on signup link");
		test.log(LogStatus.INFO, "Clicked on signup link");

	}

	public void clickLoginLink() {
		
		loginLink.click();
		log.info("Clicked on login link");
		test.log(LogStatus.INFO, "Clicked on login link");
	}

	public void enterEmail(String email) {
		try {
			emailField.sendKeys(email);
			log.info("Entered username");
			test.log(LogStatus.INFO, "Enter email");
		} catch (NoSuchElementException e) {
			System.out.println(e.getMessage());
		}
	}

	public void enterPassword(String password) {
		// WebElement passwordField =
		// driver.findElement(By.xpath("//div[@id='memberLoginDialogpassword']//input"));
		try {
			passwordField.sendKeys(password);
			log.info("Entered password");
			test.log(LogStatus.INFO, "Enter password");

		} catch (NoSuchElementException e) {
			System.out.println(e.getMessage());

		}
	}

	public void clickGoLink() {
		// WebElement goButton =
		// driver.findElement(By.id("memberLoginDialogokButton"));
		try {
			goButton.click();
			log.info("Clicked on GoButton and Navigating to Practice page ");
			test.log(LogStatus.INFO, "Clicked Go button");
		} catch (NoSuchElementException e) {
			System.out.println(e.getMessage());
		}
	}

	public boolean isWelcomeTextPresent() {

		try {

			if (welcomeText != null) {
				return true;
			}
		} catch (NoSuchElementException e) {
			System.out.println(e.getMessage());
			return false;
		}
		return false;
	}

	public void login(String email, String password) throws Exception {
		clickClosebutton();
		clickSignUpLink();
		clickLoginLink();
		enterEmail(email);
		enterPassword(password);
		clickGoLink();
	}

	public void maxmimizeBrowserWindow(WebDriver driver2) {
		// TODO Auto-generated method stub
		driver.manage().window().maximize();
		log.info("Browser maximized");

	}

	public void loadUrl(String baseUrl, WebDriver driver) {
		// TODO Auto-generated method stub
		log.info("loading the given url");
		driver.get(baseUrl);

	}

	public boolean selectsMultipleCheckboxes() {
		log.info("Inside method Select multiple checkox");
		boolean checkbox1 = false;
		for (WebElement list : checkboxList) {
			list.click();
		}
		for (int i = 0; i < checkboxList.size(); i++) {
			checkbox1 = checkboxList.get(i).isSelected();
			if (checkbox1 == false) {
				break;
			}

		}
		return checkbox1;

	}

	public String navigateToPracticePage() {
		log.info("Navigating to Practice page");
		practicepageButton.click();

		String text = practicePageText.getText();

		return text;
	}

	public boolean validateCheckboxes() {
		log.info("Inside Validate checkbox method");

		if (benzText.getText().contains("Benz") && bmwText.getText().contains("BMW") && hondaText.getText().contains("Honda")) {
			return true;
		} else {
			return false;
		}

	}
}