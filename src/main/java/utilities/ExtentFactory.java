package utilities;

import com.relevantcodes.extentreports.ExtentReports;

/**
 * Purpose:Class for storing Extent Reports and adding system info
 * File name:ExtentFactory.java
 * @author pratik_dongare
 *
 */
public class ExtentFactory {
	public static ExtentReports getInstance() {
		ExtentReports extent;
		String Path = System.getProperty("user.dir") + "\\extentreports\\report-demo.html";
		extent = new ExtentReports(Path, false);
		extent.addSystemInfo("Selenium Version", "3.14").addSystemInfo("Platform", "Windows");

		return extent;
	}
}