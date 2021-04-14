package tests;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;


public class Logins {
	WebDriver driver;
	
	public void launchBrowser() {
		System.setProperty("webdriver.chrome.driver", "/Users/lauragarling/Desktop/Selenium/chromedriver");
		driver = new ChromeDriver();
		driver.get("http://localhost:3000/start");
	}
	
	public void logout() {
		driver.findElement(By.name("logout")).click();
	}
	
	
	public void correctLogin() throws InterruptedException {
		Thread.sleep(2000);
		driver.findElement(By.name("loginbutton")).click();
		Thread.sleep(2000);
		driver.findElement(By.id("exampleInputEmail1")).sendKeys("garling@wisc.edu");
		driver.findElement(By.id("exampleInputPassword1")).sendKeys("owen");
		driver.findElement(By.name("submit")).click();
		Thread.sleep(4000);
		
		String ActualURL = driver.getCurrentUrl();
		String ExpectedURL = "http://localhost:3000/home";
		Assert.assertEquals(ExpectedURL, ActualURL);
		
		String ActualName = driver.findElement(By.name("usercard")).getText();
		String ExpectedName = "laura garling";
		Assert.assertEquals(ExpectedName, ActualName);
		
		System.out.println("Test 1 of 3: PASSED");

		
	}
	
	public void attemptInvalidEmail() throws InterruptedException {
		Thread.sleep(2000);
		driver.findElement(By.name("loginbutton")).click();
		Thread.sleep(2000);
		driver.findElement(By.id("exampleInputEmail1")).sendKeys("garlingwisc.edu");
		driver.findElement(By.id("exampleInputPassword1")).sendKeys("owen");
		driver.findElement(By.name("submit")).click();
		Thread.sleep(2000);
		
		String ActualURL = driver.getCurrentUrl();
		String ExpectedURL = "http://localhost:3000/login";
		Assert.assertEquals(ExpectedURL, ActualURL);
		
		String ErrorMess = driver.findElement(By.name("invalid")).getText();
		Assert.assertEquals("This account doesn't exist or isn't confirmed!", ErrorMess);
		
		driver.navigate().back();
		
		System.out.println("Test 2 of 3: PASSED");

		
	}
	
	public void corrEmailIncorrPass() throws InterruptedException {
		Thread.sleep(2000);
		driver.findElement(By.name("loginbutton")).click();
		Thread.sleep(2000);
		driver.findElement(By.id("exampleInputEmail1")).sendKeys("garling@wisc.edu");
		driver.findElement(By.id("exampleInputPassword1")).sendKeys("owen7");
		driver.findElement(By.name("submit")).click();
		Thread.sleep(2000);
		
		String ActualURL = driver.getCurrentUrl();
		String ExpectedURL = "http://localhost:3000/login";
		Assert.assertEquals(ExpectedURL, ActualURL);
		
		String ErrorMess = driver.findElement(By.name("invalid")).getText();
		Assert.assertEquals("This account doesn't exist or isn't confirmed!", ErrorMess); //this needs to be updated to reflect correct error message
		
		System.out.println("Test 3 of 3: PASSED");

	}
	
	public void closeBrowser() {
		driver.quit();
	}
	
	public static void main(String[] args) throws InterruptedException {
		Logins obj = new Logins();
		obj.launchBrowser();
		obj.correctLogin();
		obj.logout();
		obj.attemptInvalidEmail();
		obj.corrEmailIncorrPass();
		System.out.println("Tests have finished running.");
		obj.closeBrowser();
	}

}
