package utilities;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

/**
 * Purpose:This class is for capturing screenshot for failed test case and storing the file in screenshots folder
 *  File name:Screenshots.java
 * 
 * @author pratik_dongare
 *
 */
public class Screenshots {

	public static String takeScreenshot(WebDriver driver, String fileName) throws IOException {
		fileName = fileName + ".png";
		String directory = System.getProperty("user.dir");
		File sourceFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(sourceFile, new File(directory + "\\screenshots\\" + fileName));
		String destination = directory + fileName;
		return destination;
	}
}