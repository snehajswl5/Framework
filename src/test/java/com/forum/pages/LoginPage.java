package com.forum.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage {
	
	WebDriver driver;
	
	public LoginPage(WebDriver ldriver)
	{
		this.driver = ldriver;
		
	}
	
	@FindBy(xpath="//*[@id='headerDiv']/div[1]/div/div[2]/div[8]/a")WebElement loginLink;
	
	@FindBy(name="emailOrMobile")WebElement username;
	
	@FindBy(xpath="//*[@id='loginDiv']/div[1]/div[4]/button")WebElement nextButton;
	
	@FindBy(name="loginBean.passwordExistingUser")WebElement password;
	
	@FindBy(xpath="//*[@id='loginEmail']/div[1]/div[6]/button")WebElement loginbutton;
	
	public void loginToForum(String uname,String pass)
	{
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			
		}
		
		loginLink.click();
		username.sendKeys(uname);
		nextButton.click();
		password.sendKeys(pass);
		loginbutton.click();
		

	}
	

}
