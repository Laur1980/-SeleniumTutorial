package org.selenium.tutorial.main;

import static org.junit.Assert.*;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

public class AppTest {

	private WebDriver driver;
	private String baseUrl;
	private StringBuilder alertError;

	@Before
	public void setUp() {
		System.setProperty("webdriver.gecko.driver", "/home/laur/Documents/libs/geckodriver");
		driver = new FirefoxDriver();
		driver.manage().deleteAllCookies();
		// baseUrl = "www.google.com";
		// driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}
	
	@Ignore
	@Test
	public void testValidUrl() throws Exception {
		driver.get("http://www.euro-testing.com/");
//		driver.manage().window().maximize(); // wait for page to load and maximize
		driver.manage().window().setSize(new Dimension(1920,1080));//due to 
		String currentUrl = driver.getCurrentUrl();
		assertEquals("http://www.euro-testing.com/", currentUrl);
	}
	
	@Test
	public void goToExercisePage()throws Exception{
		driver.get("http://toolsqa.wpengine.com/automation-practice-form");
		driver.manage().window().setSize(new Dimension(1920,1080));
		Assert.assertEquals("http://toolsqa.com/automation-practice-form/",driver.getCurrentUrl());
	}
	
	@Test
	public void lastNameTextFieldIsDisplayed(){
		driver.get("http://toolsqa.wpengine.com/automation-practice-form");
		driver.manage().window().setSize(new Dimension(1920,1080));
		WebElement field1 = driver.findElement(By.name("firstname"));
		assertTrue(field1.isDisplayed());
	}
	
	@Test
	public void firstNameTextFieldIsDisplayed(){
		driver.get("http://toolsqa.wpengine.com/automation-practice-form");
		driver.manage().window().setSize(new Dimension(1920,1080));
		WebElement field2 = driver.findElement(By.xpath("/html/body/div/div[5]/div[2]/div/div/form/fieldset/div[1]/input[2]"));
		assertTrue(field2.isDisplayed());
	}
	
	@Test
	public void buttonIsDisplayed(){
		driver.get("http://toolsqa.wpengine.com/automation-practice-form");
		driver.manage().window().setSize(new Dimension(1920,1080));
		WebElement button = driver.findElement(By.id("submit"));
		assertTrue(button.isDisplayed());
	}
	
	@Test
	public void completeFirstNameAndLastNameFieldAndSubmit(){
		driver.get("http://toolsqa.wpengine.com/automation-practice-form");
		driver.manage().window().setSize(new Dimension(1920,1080));
		WebElement field1 = driver.findElement(By.name("firstname"));
		WebElement field2 = driver.findElement(By.xpath("/html/body/div/div[5]/div[2]/div/div/form/fieldset/div[1]/input[2]"));
		WebElement button = driver.findElement(By.id("submit"));
		field1.sendKeys("Laurentiu");
		field2.sendKeys("Nicolae");
		String valueOfField1 = field1.getAttribute("value");
		String valueOfField2 = field2.getAttribute("value");
		System.out.println(">>>Atribute value for field1 : "+valueOfField1+"<<<");
		System.out.println(">>>Atribute value for field2: "+valueOfField2+"<<<");
		assertTrue(valueOfField1.equals("Laurentiu") && valueOfField2.equals("Nicolae"));		
	}
	
	@After
	public void tearDown(){
		driver.quit();
	}
}