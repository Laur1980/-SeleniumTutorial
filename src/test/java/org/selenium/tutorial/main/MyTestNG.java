package org.selenium.tutorial.main;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.AssertJUnit;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class MyTestNG {
	
	private WebDriver driver;
	
	@BeforeClass
	public void setUp(){
		System.setProperty("webdriver.gecko.driver", "/home/laur/Documents/libs/geckodriver");
		driver = new FirefoxDriver();
	}
	
	@Test
    @Parameters({"author","searchKey"})
    public void testParameterWithXML( @Optional("Abc") String author, @Optional("Abc") String searchKey) throws InterruptedException{ 

        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("https://google.com");

        WebElement searchText = driver.findElement(By.name("q"));
        //Searching text in google text box
        searchText.sendKeys(searchKey);

        System.out.println("Welcome ->"+author+" Your search key is->"+searchKey);
        System.out.println("Thread will sleep now");
        Thread.sleep(3000);
        System.out.println("Value in Google Search Box = "+searchText.getAttribute("value") +" ::: Value given by input = "+searchKey);
        //verifying the value in google search box
        AssertJUnit.assertTrue(searchText.getAttribute("value").equalsIgnoreCase(searchKey));

	}
	
	@AfterClass
	public void tearDown(){
		driver.quit();
	}
}
