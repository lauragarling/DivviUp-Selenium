package tests;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

//NEED: Join with blank string 
// NEED: test Past Bills 

public class HomePage {
	WebDriver driver;
	
	public void launchBrowser() {
		System.setProperty("webdriver.chrome.driver", "/Users/lauragarling/Desktop/Selenium/chromedriver");
		driver = new ChromeDriver();
		driver.get("http://localhost:3000/home");
	}
	
	
	public void emptyRoomName() throws InterruptedException {
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
		driver.findElement(By.name("create")).click();
		//driver.switchTo().activeElement();
		driver.findElement(By.name("box1")).click();
		driver.findElement(By.name("box1")).sendKeys("");
		driver.findElement(By.name("create2")).click();
		
		String ActualErr = driver.findElement(By.name("error")).getText();
		String ExpectedErr = "Need a room name";
		Assert.assertEquals(ExpectedErr, ActualErr);
		
		driver.navigate().refresh();
		Thread.sleep(2000);
		
		System.out.println("Test 1 of 5: PASSED");

	}
	
	public void createValidRoom() throws InterruptedException {
		Thread.sleep(3000);
		if (driver.getCurrentUrl().equals("http://localhost:3000/start")) { // logs in if previous cookie DNE
			driver.findElement(By.name("loginbutton")).click();
			Thread.sleep(2000);
			driver.findElement(By.id("exampleInputEmail1")).sendKeys("garling@wisc.edu");
			driver.findElement(By.id("exampleInputPassword1")).sendKeys("owen");
			driver.findElement(By.name("submit")).click();
			Thread.sleep(2000);
		}
		
		Thread.sleep(3000);
		driver.findElement(By.name("create")).click();
		//driver.switchTo().activeElement();
		driver.findElement(By.name("box1")).click();
		driver.findElement(By.name("box1")).sendKeys("Preparing for Botanic Panic");
		driver.findElement(By.name("create2")).click();
		
		Thread.sleep(3000);
		
		if (!driver.getCurrentUrl().contains("http://localhost:3000/billroompayer/")) {
			System.out.println("FAIL: New room not generated.");
		}
		
		System.out.println("Test 2 of 5: PASSED");
		
		driver.navigate().back();
		
		
	}
	
	public void joinValidRoom() throws InterruptedException {
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
		driver.findElement(By.name("join")).click();
		driver.findElement(By.name("box1")).click();
		driver.findElement(By.name("box1")).sendKeys("45000");
		driver.findElement(By.name("join2")).click();
		Thread.sleep(3000);
		
		String ActualURL = driver.getCurrentUrl();
		String ExpectedURL = "http://localhost:3000/billroomnonpayer/45000";
		Assert.assertEquals(ExpectedURL, ActualURL);
		
		Thread.sleep(3000);
		String ActualTxt = driver.findElement(By.name("code")).getText();
		String ExpectedTxt = "Room Code: 45000";
		Assert.assertEquals(ExpectedTxt, ActualTxt);
		
		System.out.println("Test 3 of 5: PASSED");

		driver.navigate().back();
		
	}
	
	public void invalidRoomCode() throws InterruptedException {
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
		driver.findElement(By.name("join")).click();
		driver.findElement(By.name("box1")).click();
		driver.findElement(By.name("box1")).sendKeys("XXXXX");
		driver.findElement(By.name("join2")).click();
		Thread.sleep(3000);
		
		String ActualURL = driver.getCurrentUrl();
		String ExpectedURL = "http://localhost:3000/home";
		Assert.assertEquals(ExpectedURL, ActualURL);
		
		Thread.sleep(3000);
		String ActualErr = driver.findElement(By.name("error")).getText();
		String ExpectedErr = "Room does not exist, enter valid code";
		Assert.assertEquals(ExpectedErr, ActualErr);
		
		System.out.println("Test 4 of 5: PASSED");

		driver.navigate().back();
	}
	
	
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

		System.out.println("Test 5 of 5: PASSED");
		
	}
	
	public void closeBrowser() {
		driver.quit();
	}
	
	public static void main(String[] args) throws InterruptedException {
		HomePage obj = new HomePage();
		obj.launchBrowser();
		obj.emptyRoomName();
		obj.createValidRoom();
		obj.joinValidRoom();
		obj.invalidRoomCode();
		obj.logout();
		System.out.println("Tests have finished running.");
		obj.closeBrowser();
	}
}