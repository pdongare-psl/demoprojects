package com.demoproject.pageobjectfactory;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;
import com.relevantcodes.extentreports.ExtentTest;

/**
 * Purpose:This is a PageFactory object class for writing locators using different ways like id,xpath,name etc.
 * File name:HomePageObject
 * @author pratik_dongare
 *
 */
public class HomePageObject {
	WebDriver driver;
	String baseURL;

	public HomePageObject(WebDriver driver, ExtentTest test) {

		// TODO Auto-generated constructor stub
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//div[@id='comp-jgmoxws4']")
	protected WebElement closeButton;

	@FindBy(id = "comp-iiqg1vggactionTitle")
	protected WebElement signupLink;

	@FindBy(id = "signUpDialogswitchDialogLink")
	protected WebElement loginLink;

	@FindBy(xpath = "//div[@id='memberLoginDialogemail']//input")
	protected WebElement emailField;

	@FindBy(xpath = "//div[@id='memberLoginDialogpassword']//input")
	protected WebElement passwordField;

	@FindBy(id = "memberLoginDialogokButton")
	protected WebElement goButton;

	@FindBy(id = "DrpDwnMn03bg")
	protected WebElement practicepageButton;

	@FindBy(xpath = "//div[@id='block-1069048']//h1[contains(text(),'Practice Page')]")
	protected WebElement practicePageText;

	@FindBy(xpath = "//div[text()='Hello test']")
	protected WebElement welcomeText;

	@FindBys({ @FindBy(xpath = "//div[@id='checkbox-example']//input") })
	protected List<WebElement> checkboxList;

	@FindBy(xpath = "//div[@id='checkbox-example']//label[@for='bmw']")
	protected WebElement bmwText;

	@FindBy(xpath = "//div[@id='checkbox-example']//label[@for='benz']")
	protected WebElement benzText;

	@FindBy(xpath = "//div[@id='checkbox-example']//label[@for='honda']")
	protected WebElement hondaText;
}
