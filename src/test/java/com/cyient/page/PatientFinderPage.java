package com.cyient.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class PatientFinderPage {
	
	private By newPatient = By.id("create_patient_btn1");
	
	private WebDriver driver;
	    
	
	public PatientFinderPage(WebDriver driver)
	{
		
		this.driver = driver;
	}
	
	public void switchToFinFrmae()
	{
		
		driver.switchTo().frame("fin");
	}

	public void addNewPatient()
	{
		driver.findElement(newPatient).click();
		driver.switchTo().defaultContent();
	}
}
