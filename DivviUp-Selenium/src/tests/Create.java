package tests;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;


public class Create {
	WebDriver driver;
	
	public void launchBrowser() {
		System.setProperty("webdriver.chrome.driver", "/Users/lauragarling/Desktop/Selenium/chromedriver");
		driver = new ChromeDriver();
		driver.get("http://localhost:3000/start");
	}
	
	/**
	 * @throws InterruptedException
	 */
	public void correctCreate() throws InterruptedException {
		Thread.sleep(2000);
		driver.findElement(By.name("create")).click();
		Thread.sleep(2000);
		driver.findElement(By.id("exampleInputEmail1")).sendKeys("wolman@wisc.edu");
		driver.findElement(By.id("exampleInputFirst")).sendKeys("Owey");
		driver.findElement(By.id("exampleInputLast")).sendKeys("Wolf");
		driver.findElement(By.id("exampleInputPassword1")).sendKeys("caprisun");
		driver.findElement(By.id("exampleInputConfirmPassword1")).sendKeys("caprisun");

		driver.findElement(By.name("sub")).click();
		Thread.sleep(3000);
		
		String ActualURL = driver.getCurrentUrl();
		String ExpectedURL = "http://localhost:3000/confirm";
		Assert.assertEquals(ExpectedURL, ActualURL);
		
		String ActualName = driver.findElement(By.name("thanks")).getText();
		String ExpectedName = "Thank You!";
		Assert.assertEquals(ExpectedName, ActualName);
		
		driver.navigate().back();
		driver.navigate().back();
		
		System.out.println("Test 1 of 3: PASSED");
		
	}
	
	public void unconfirmedLogin() throws InterruptedException {
			Thread.sleep(2000);
			driver.findElement(By.name("loginbutton")).click();
			Thread.sleep(2000);
			driver.findElement(By.id("exampleInputEmail1")).sendKeys("wolman@wisc.edu");
			driver.findElement(By.id("exampleInputPassword1")).sendKeys("caprisun");
			driver.findElement(By.name("submit")).click();
			Thread.sleep(2000);
			
			String ActualURL = driver.getCurrentUrl();
			String ExpectedURL = "http://localhost:3000/login";
			Assert.assertEquals(ExpectedURL, ActualURL);
			
			String ActualErr = driver.findElement(By.name("invalid")).getText();
			String ExpectedErr = "This account doesn't exist or isn't confirmed!";
			Assert.assertEquals(ExpectedErr, ActualErr);
			
			driver.navigate().back();
			
			System.out.println("Test 2 of 3: PASSED");

			
	}
		
	

	public void passConfirmedIncorr() throws InterruptedException {
		Thread.sleep(2000);
		driver.findElement(By.name("create")).click();
		Thread.sleep(2000);
		driver.findElement(By.id("exampleInputEmail1")).sendKeys("owolman@wisc.edu");
		driver.findElement(By.id("exampleInputFirst")).sendKeys("Owey");
		driver.findElement(By.id("exampleInputLast")).sendKeys("Wolf");
		driver.findElement(By.id("exampleInputPassword1")).sendKeys("caprisun");
		driver.findElement(By.id("exampleInputConfirmPassword1")).sendKeys("caprisun7");

		driver.findElement(By.name("sub")).click();
		Thread.sleep(3000);
		
		String ActualURL = driver.getCurrentUrl();
		String ExpectedURL = "http://localhost:3000/create";
		Assert.assertEquals(ExpectedURL, ActualURL);
		
		String ActualName = driver.findElement(By.name("error")).getText();
		String ExpectedName = "Password Not Confirmed!";
		Assert.assertEquals(ExpectedName, ActualName);
		
		System.out.println("Test 3 of 3: PASSED");

		
	}
	
	// need a test to create account, confirm through email, log in 
	// need to add some kind of test about not being able to create multiple accounts with the same email address 

	
	public void logout() {
		driver.findElement(By.name("logout")).click();
	}
	

	public void closeBrowser() {
		driver.quit();
	}
	
	public static void main(String[] args) throws InterruptedException {
		Create obj = new Create();
		obj.launchBrowser();
		obj.correctCreate();
		obj.unconfirmedLogin();
		obj.passConfirmedIncorr();
		System.out.println("Tests have finished running.");
		obj.closeBrowser();
	}

}
