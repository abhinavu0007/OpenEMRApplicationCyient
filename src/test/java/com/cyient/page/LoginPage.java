package com.cyient.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {
	
	private WebDriver driver;
	private By usernameLocator = By.id("authUser");
	private By passwordLocator=  By.id("clearPass");
	private By languageLocator=  By.name("languageChoice");
	private By loginLocator= By.xpath("//button[@type='submit']");
	private By errorLocator= By.xpath("//div[contains(text(),'Invalid')]");
	
	public LoginPage(WebDriver driver)
	{
		this.driver= driver;
	}
	
	
	public  void sendUsername(String username)
	{
		
		driver.findElement(usernameLocator).sendKeys(username);
		
	}
	
	public  void sendPassword(String password)
	{
		
		driver.findElement(passwordLocator).sendKeys(password);
	}
	
	public void selectLanguageByText(String language)
	{
		driver.findElement(languageLocator).sendKeys(language);
	}
	public void clickOnLogin()
	{
		driver.findElement(loginLocator).click();
		
	}
	
	public String getErrorMessage()
    {
        return driver.findElement(errorLocator).getText().trim();
    }
	

}
