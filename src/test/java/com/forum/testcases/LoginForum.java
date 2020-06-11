package com.forum.testcases;


import org.openqa.selenium.support.PageFactory;

import org.testng.annotations.Test;

import com.forum.pages.BaseClass;
import com.forum.pages.LoginPage;

import com.forum.utility.Helper;
public class LoginForum extends BaseClass{
		
	
    @Test
	public void loginApp()
	{
    	
    	logger=report.createTest("Login To Forum");
    
		LoginPage loginPage=PageFactory.initElements(driver, LoginPage.class);
		
		logger.info("Starting Application");
		
		loginPage.loginToForum(excel.getStringData("Login",0,0),excel.getStringData("Login",0,1));
		
		logger.pass("Login done");
		
		Helper.captureScreenshot(driver);
	
	}
}
