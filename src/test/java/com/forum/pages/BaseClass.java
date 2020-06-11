package com.forum.pages;

import java.io.File;
import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.forum.utility.BrowserFactory;
import com.forum.utility.ConfigDataProvider;
import com.forum.utility.ExcelDataProvider;
import com.forum.utility.Helper;

public class BaseClass {
	
	public WebDriver driver;
	public ExcelDataProvider excel;
	public ConfigDataProvider config;
	public ExtentReports report;
	public ExtentTest logger;
	
	@BeforeSuite
	public void setUpSuite() {
		
		Reporter.log("Setting up reports and Test is getting ready",true);
		
		excel=new ExcelDataProvider();
		config=new ConfigDataProvider();
		
		ExtentHtmlReporter extent=new ExtentHtmlReporter(new File(System.getProperty("user.dir")+"/Reports/Forum_"+Helper.getCurrentDateTime()+".html"));
		report=new ExtentReports();
		report.attachReporter(extent);
		
		Reporter.log("Setting done - Test can be started",true);
	}

	@BeforeClass
	public void setup() {
		
		Reporter.log("Trying to start Browser and getting application ready",true);
		driver= BrowserFactory.startApplication(driver, config.getBrowser(),config.getProdURL());
		Reporter.log("Browser and application up and running",true);
	}
	
	@AfterClass
	public void teardown() {
		
		BrowserFactory.quitBrowser(driver);
	}
	
	@AfterMethod
	public void teardownMethod(ITestResult result) throws IOException {
		
		    Reporter.log("Test is about to end ",true);
			
			if(result.getStatus()==ITestResult.FAILURE) {
				
				logger.fail("Test Fail", MediaEntityBuilder.createScreenCaptureFromPath(Helper.captureScreenshot(driver)).build());
			}
			
			else if(result.getStatus()==ITestResult.SUCCESS){
				
				logger.pass("Test Passed", MediaEntityBuilder.createScreenCaptureFromPath(Helper.captureScreenshot(driver)).build());
			}
			
			else if(result.getStatus()==ITestResult.SKIP){
							
				logger.skip("Test Skip", MediaEntityBuilder.createScreenCaptureFromPath(Helper.captureScreenshot(driver)).build());
			}
			report.flush();
			
			Reporter.log("Test completed >>> Report genrated ",true);
		}
	
}
