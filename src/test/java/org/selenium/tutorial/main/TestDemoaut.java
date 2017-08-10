package org.selenium.tutorial.main;

import static org.junit.Assert.*;

import java.util.Date;
import java.util.List;
import java.util.Random;
import java.text.SimpleDateFormat;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.NoSuchElementException;

public class TestDemoaut {

	private WebDriver driver;
	private String baseUrl;
	private Random random;

	@Before
	public void setUp() {
		System.setProperty("webdriver.gecko.driver", "/home/laur/Documents/libs/geckodriver");
		driver = new FirefoxDriver();
		baseUrl = "http://newtours.demoaut.com/";
		random = new Random();
	}

	@Test
	public void testUrl() throws Exception {
		driver.get(baseUrl);
		driver.manage().window().setSize(new Dimension(1920, 1080));
		assertTrue(driver.getCurrentUrl().contains(baseUrl));
		WebElement data = driver.findElement(By.xpath(
				"/html/body/div/table/tbody/tr/td[2]/table/tbody/tr[4]/td/table/tbody/tr/td[2]/table/tbody/tr[2]/td[3]/form/table/tbody/tr[1]/td/font/b"));
		String theDate = data.getText();
		System.out.println(">>> theDate text = " + theDate + "<<<");
		SimpleDateFormat sdf = new SimpleDateFormat("MMM d, YYYY");
		System.out.println(">>> sdf = " + sdf.format(new Date()) + "<<<");
		assertEquals(sdf.format(new Date()), theDate);

		WebElement login = driver.findElement(By.name("userName"));
		System.out.println(">>>The login field is " + (login.isDisplayed() ? "present!" : "not present!") + "<<<");
		login.sendKeys("tutorial");
		WebElement pass = driver.findElement(By.name("password"));
		System.out.println(">>>The password field is " + (pass.isDisplayed() ? "present!" : "not present!") + "<<<");
		pass.sendKeys("tutorial");
		pass.submit();
		WebElement signOff = null;
		while (true) {
			try {
				signOff = driver.findElement(By.cssSelector("a[href*='mercurysignoff.php']"));
				break;
			} catch (NoSuchElementException e) {
				Thread.sleep(5 * 1000);
			}
		}
		System.out.println(">>>The password field is " + (signOff.isDisplayed() ? "present!" : "not present!") + "<<<");
		assertTrue(signOff.isDisplayed());
		
		List<WebElement> types = driver.findElements(By.name("tripType"));
		int ranType = random.nextInt(types.size());
		types.get(ranType).click();
		List<WebElement> passangers = driver.findElement(By.name("passCount")).findElements(By.tagName("option"));
		passangers.get(1).click();
		
		List<WebElement> departures = driver.findElement(By.name("fromPort")).findElements(By.tagName("option"));
		int ranDepart = random.nextInt(departures.size());
		WebElement departure = departures.get(ranDepart);
		departure.click();
		
		List<WebElement> onMonths = driver.findElement(By.name("fromMonth")).findElements(By.tagName("option"));
		int randMonth = random.nextInt(onMonths.size());
		WebElement departureMonth = onMonths.get(randMonth);
		departureMonth.click();
		
		List<WebElement> onDays = driver.findElement(By.name("fromDay")).findElements(By.tagName("option"));
		int randDayDepart = random.nextInt(onDays.size());
		WebElement departureDay = onDays.get(randDayDepart);
		departureDay.click();
		
		List<WebElement> arrivals = driver.findElement(By.name("toPort")).findElements(By.tagName("option"));
		int arrivalNum = random.nextInt(arrivals.size());
		WebElement arrival = arrivals.get(arrivalNum);
		while(arrival.getText().equals(departure.getText())){
			arrivalNum = random.nextInt(arrivals.size());
			arrival = arrivals.get(arrivalNum);
		}
		arrival.click();
		
		List<WebElement> toMonths = driver.findElement(By.name("toMonth")).findElements(By.tagName("option"));
		randMonth = random.nextInt(toMonths.size());
		WebElement arrivalMonth = toMonths.get(randMonth);
		while(Integer.parseInt(arrivalMonth.getAttribute("value")) < Integer.parseInt(departureMonth.getAttribute("value"))){
			randMonth = random.nextInt(toMonths.size());
			arrivalMonth = toMonths.get(randMonth);
		}
		arrivalMonth.click();
		
		List<WebElement> arrivalDays = driver.findElement(By.name("toDay")).findElements(By.tagName("option"));
		String month1 = departureMonth.getAttribute("value");
		System.out.println(">>>Value of month1="+month1+"<<<");
		String month2 = arrivalMonth.getAttribute("vintalue");
		System.out.println(">>>Value of month1="+month2+"<<<");
		randDayDepart = random.nextInt(arrivalDays.size());
		WebElement arrivalDay = arrivalDays.get(randDayDepart);
		if(month1.equals(month2)){
			while(Integer.parseInt(arrivalDay.getAttribute("value"))<Integer.parseInt(departureDay.getAttribute("value"))){
				randDayDepart = random.nextInt(arrivalDays.size());
				arrivalDay = arrivalDays.get(randDayDepart);
			}
		}
		arrivalDay.click();
		
		List<WebElement> serviceClass = driver.findElements(By.name("servClass"));
		randDayDepart = random.nextInt(serviceClass.size());
		serviceClass.get(randDayDepart).click();
		
		List<WebElement> airlines = driver.findElement(By.name("airline")).findElements(By.tagName("option"));
		randDayDepart = random.nextInt(airlines.size());
		while(randDayDepart == 0){
			randDayDepart = random.nextInt(airlines.size());
		}
		airlines.get(randDayDepart).click();
		
		driver.findElement(By.xpath("/html/body/div/table/tbody/tr/td[2]/table/tbody/tr[4]/td/table/tbody/tr/td[2]/table/tbody/tr[5]/td/form/table/tbody/tr[14]/td/input")).click();
	}

	@After
	public void tearDown() {
		//driver.quit();
	}
}
