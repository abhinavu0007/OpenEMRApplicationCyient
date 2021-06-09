package com.cyient.test;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.cyient.base.WebDriverWrapper;
import com.cyient.page.DashboardPage;
import com.cyient.page.LoginPage;
import com.cyient.utilities.DataProviderUtils;

public class LoginTest  extends WebDriverWrapper{
	

	
	@Test(dataProvider = "invalidData", dataProviderClass = DataProviderUtils.class)
	public void invalidCredantialTest(String username, String password,String language,String getText) 
	{

		LoginPage login = new  LoginPage(driver);
		login.sendUsername("admin");
		login.sendPassword("pass");
		login.selectLanguageByText("English (Indian)");
		login.clickOnLogin();
		
		
		//WebDriver driver = new ChromeDriver();
	
		driver.findElement(By.id("authUser")).sendKeys("admin");
		driver.findElement(By.id("clearPass")).sendKeys("pass1");
		
		
		Select selectlanguage = new Select(driver.findElement(By.name("languageChoice")));
		selectlanguage.selectByVisibleText("English (Indian)");
		//driver.findElement(By.id()).click();
		driver.findElement(By.xpath("//button[@class= 'btn btn-login btn-lg']")).click();
		String actualValue= driver.findElement(By.xpath("//div[@class='alert alert-danger login-failure m-1']")).getText();
		
		
		Assert.assertEquals(actualValue, "Invalid username or password");
		
		
			
	}
	
	@Test
	public void validCredentialTest()
	
	{
		
		
		driver.findElement(By.id("authUser")).sendKeys("admin");
		driver.findElement(By.id("clearPass")).sendKeys("pass");
		
		
		Select selectlanguage = new Select(driver.findElement(By.name("languageChoice")));
		selectlanguage.selectByVisibleText("English (Indian)");
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		Assert.assertEquals(driver.getTitle(), "OpenEMR");
		
		
		
		String actualPasswordPlaceholderavlue = driver.findElement(By.id("clearPass")).getAttribute("placeholder");
		
		Assert.assertEquals(actualPasswordPlaceholderavlue, "OpenEMR");
	}
	

	@Test(dataProvider = "validCredentialExcelData", dataProviderClass = DataProviderUtils.class )
	public void validCredantialTest(String username, String password,String language,String getText)
	{
		LoginPage login = new LoginPage(driver);
		login.sendUsername(username);
		login.sendPassword(password);
		login.selectLanguageByText(language);
		login.clickOnLogin();
		
		DashboardPage dashboard = new DashboardPage(driver);
		String actualvalue = dashboard.getDashboardPageTitle();
		Assert.assertEquals(actualvalue, getText);
		
	}
	
	
	

}
