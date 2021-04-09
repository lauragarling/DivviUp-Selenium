package tests;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

// need to figure out how to test popups
// Create Room, Join room, and PastBills not tested 

public class HomePage {
	WebDriver driver;
	
	public void launchBrowser() {
		System.setProperty("webdriver.chrome.driver", "/Users/lauragarling/Desktop/Selenium/chromedriver");
		driver = new ChromeDriver();
		driver.get("http://localhost:3000/home");
	}
	
	// can't figure out how to test popups
	/*
	public void emptyRoomName() throws InterruptedException {
		Thread.sleep(3000);
		if (driver.getCurrentUrl().equals("http://localhost:3000/start")) { // logs in if previous cookie DNE
			System.out.print("here");
			driver.findElement(By.name("loginbutton")).click();
			Thread.sleep(2000);
			driver.findElement(By.id("exampleInputEmail1")).sendKeys("garling@wisc.edu");
			driver.findElement(By.id("exampleInputPassword1")).sendKeys("owen");
			driver.findElement(By.name("submit")).click();
			Thread.sleep(2000);
		}
		
		Thread.sleep(2000);
		driver.findElement(By.name("create")).click();
		driver.switchTo().activeElement();
		driver.findElement(By.className("box1")).click();
		driver.findElement(By.className("box1")).sendKeys("");
		driver.findElement(By.className("create2")).click();
		
		String ActualErr = driver.findElement(By.name("error")).getText();
		String ExpectedErr = "Need a room name";
		Assert.assertEquals(ExpectedErr, ActualErr);
		
		driver.navigate().refresh();
		Thread.sleep(2000);
		
		System.out.print("Test 1 of 1: PASSED");

	}
	*/
	
	public void logout() throws InterruptedException {
		Thread.sleep(3000);
		if (driver.getCurrentUrl().equals("http://localhost:3000/start")) { // logs in if previous cookie DNE
			driver.findElement(By.name("loginbutton")).click();
			Thread.sleep(2000);
			driver.findElement(By.id("exampleInputEmail1")).sendKeys("garling@wisc.edu");
			driver.findElement(By.id("exampleInputPassword1")).sendKeys("owen");
			driver.findElement(By.name("submit")).click();
			Thread.sleep(2000);
		}
		
		Thread.sleep(2000);
		driver.findElement(By.name("logout")).click();
		
		String ActualURL = driver.getCurrentUrl();
		String ExpectedURL = "http://localhost:3000/start";
		Assert.assertEquals(ExpectedURL, ActualURL);

		System.out.println("Test 1 of 1: PASSED");
		
	}
	
	public void closeBrowser() {
		driver.quit();
	}
	
	public static void main(String[] args) throws InterruptedException {
		HomePage obj = new HomePage();
		obj.launchBrowser();
		//obj.emptyRoomName();
		obj.logout();
		System.out.println("Tests have finished running.");
		obj.closeBrowser();
	}
}