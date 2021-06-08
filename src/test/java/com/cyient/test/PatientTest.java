package com.cyient.test;

import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.cyient.base.WebDriverWrapper;

@Test
public class PatientTest extends WebDriverWrapper {

	@Test
	public void addPatientTest() throws InterruptedException {

		/*
		 * System.setProperty("webdriver.chrome.driver",
		 * "src/test/resources/driver/chromedriver.exe"); WebDriver driver = new
		 * ChromeDriver(); driver.manage().window().maximize();
		 * driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		 */
		// driver.get("https://demo.openemr.io/a/openemr/interface/login/login.php?site=default");
		//driver.get("https://demo.openemr.io/a/openemr");
		driver.findElement(By.id("authUser")).sendKeys("admin");
		driver.findElement(By.id("clearPass")).sendKeys("pass");
		Select language = new Select(driver.findElement(By.xpath("//select[@class='form-control']")));
		language.selectByVisibleText("English (Indian)");
		driver.findElement(By.xpath("//button[@class='btn btn-login btn-lg']")).click();

		// Thread.sleep(5000);
		Actions action = new Actions(driver);

		action.moveToElement(
				driver.findElement(By.xpath("//div[@class='menuLabel dropdown-toggle oe-dropdown-toggle']"))).perform();

		driver.findElement(By.xpath("//div[text()='Patients']")).click();

		driver.switchTo().frame("fin");

		driver.findElement(By.id("create_patient_btn1")).click();
		driver.switchTo().defaultContent();

		driver.switchTo().frame("pat");

		driver.findElement(By.id("form_fname")).sendKeys("reshu");
		driver.findElement(By.id("form_lname")).sendKeys("troff");
		driver.findElement(By.id("form_DOB")).sendKeys("1990-3-2");

		Select sex = new Select(driver.findElement(By.id("form_sex")));
		sex.selectByVisibleText("Male");

		driver.findElement(By.name("create")).click();
		driver.switchTo().defaultContent();
		
		Thread.sleep(2000);
		driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@id='modalframe']")));
		driver.findElement(By.xpath("//input[@class='btn btn-primary']")).click();
		driver.switchTo().defaultContent();
		
		String actualAlert=  driver.switchTo().alert().getText();
		
		System.out.println(actualAlert);
		
		driver.switchTo().alert().accept();
		
		
		String actualvalue = driver.findElement(By.xpath("iframe[@name='pat']")).getText();
		Assert.assertEquals(actualvalue, "Medical Record Dashboard - Reshu Troff");
		
		
		

	}
}
