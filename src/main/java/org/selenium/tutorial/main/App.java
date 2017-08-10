package org.selenium.tutorial.main;

import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

public class App {

	public static void main(String[] args) {
		// second();
		for (int i = 0; i < 10; i++) {
			third();
		}
	}

	private static void first() {
		WebDriver driver = init();
		// Launch the site
		driver.get("http://www.euro-testing.com/");
		// driver.manage().window().maximize(); // wait for page to load and
		// maximize
		driver.manage().window().setSize(new Dimension(1920, 1080));
		String currentUrl = driver.getCurrentUrl();
		if (currentUrl.equals("http://www.euro-testing.com/")) {
			System.out.println(">>>IS OK!<<<");
		} else {
			System.out.println(">>>IS BAD!<<<");
		}
		WebElement elem1 = driver.findElement(By.xpath("/html/body/div[3]/div[1]/div/div/div[1]/h2"));
		// Print a Log In message to the screen
		if (elem1.isDisplayed())
			System.out.println(">>>>Element " + elem1.toString() + " found<<<<");
		else
			System.out.println(">>>Element not found<<<");
		// Close the driver
		close(driver);
	}

	private static void second() {
		WebDriver driver = init();
		driver.get("http://toolsqa.com/automation-practice-form/");
		driver.manage().window().setSize(new Dimension(1920, 1080));
		WebElement check1 = driver.findElement(By.id("sex-0"));
		check1.click();
		String check1Val = check1.getAttribute("value");
		System.out.println(">>>" + check1Val + "<<<");
		close(driver);
	}

	private static void third() {
		WebDriver driver = init();
		driver.get("http://toolsqa.com/automation-practice-form/");
		driver.manage().window().setSize(new Dimension(1920, 1080));
		List<WebElement> radios = driver.findElements(By.name("exp"));
		StringBuilder sb = new StringBuilder();
		Random r = new Random();
		int poz = r.nextInt(radios.size());
		System.out.println(">>> poz =" + poz + "<<<");
		for (WebElement we : radios) {
			sb.append(we.getAttribute("value"));
			sb.append("<<<\n");
			if (Integer.parseInt(we.getAttribute("value")) == poz) {
				System.out.println("Prepare to CLICK");
				we.click();
			}
		}
		radios.forEach(we->{
			sb.append(">>>");
			sb.append(we.getAttribute("value"));
			sb.append("<<<\n");
		});
		System.out.println(sb.toString());
		// close(driver);
	}

	private static WebDriver init() {
		System.setProperty("webdriver.gecko.driver", "/home/laur/Documents/libs/geckodriver");
		WebDriver driver = new FirefoxDriver();
		driver.manage().deleteAllCookies();
		try {
			Thread.sleep(10 * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();

		}
		return driver;
	}

	private static void close(WebDriver driver) {
		if (driver != null)
			driver.quit();
	}
}
