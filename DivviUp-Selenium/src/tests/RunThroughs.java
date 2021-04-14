package tests;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class RunThroughs {
	WebDriver driver1;
	
	public void launchBrowser() {
		System.setProperty("webdriver.chrome.driver", "/Users/lauragarling/Desktop/Selenium/chromedriver");
		driver1 = new ChromeDriver();
		driver1.get("http://localhost:3000/start");
	}

	public void closeBrowser() {
		driver1.quit();
	}
	
	public void login() throws InterruptedException {
		Thread.sleep(2000);
		driver1.findElement(By.name("loginbutton")).click();
		Thread.sleep(2000);
		driver1.findElement(By.id("exampleInputEmail1")).sendKeys("garling@wisc.edu");
		driver1.findElement(By.id("exampleInputPassword1")).sendKeys("owen");
		driver1.findElement(By.name("submit")).click();
		Thread.sleep(2000);
	}
	
	public void createRoom() throws InterruptedException {
		Thread.sleep(3000);
		
		driver1.findElement(By.name("create")).click();
		Thread.sleep(2000);		
		driver1.findElement(By.name("box1")).click();
		driver1.findElement(By.name("box1")).sendKeys("Laura's Christmas Party");
		driver1.findElement(By.name("create2")).click();
			
		Thread.sleep(10000);
		
		driver1.findElement(By.name("add")).click();
		driver1.findElement(By.name("itname")).sendKeys("sub");
		driver1.findElement(By.name("cost")).sendKeys("6.00");
		driver1.findElement(By.name("button")).click();

		System.out.println("Step 1 Complete.");
	}
	
	public void finalizeRoom() throws InterruptedException {
		
		try {
			driver1.findElement(By.name("claim")).click();
			} catch (NoSuchElementException n) {
				System.out.println("FAIL: Claim Button could not be located.");
				return;
			}
		
		Thread.sleep(5000);
		
		driver1.findElement(By.name("fin")).click();
		
		Thread.sleep(10000);
		
		String expected = "$6.00";
		String actual1 = driver1.findElement(By.name("tot")).getText();
		String actual2 = driver1.findElement(By.name("userTot")).getText();
		
		Assert.assertEquals(expected, actual1);
		Assert.assertEquals(expected, actual2);
		
		System.out.println("Step 2 Complete.");
	}
	
	public void checkPastBills() throws InterruptedException {
		driver1.navigate().to("http://localhost:3000/home");
		
		Thread.sleep(3000);
		
		String expected = "Laura's Christmas Party";
		String dummy = driver1.findElement(By.name("pname")).getText();
		String actual = dummy.substring(11, dummy.length());
		Assert.assertEquals(expected, actual);
		
		driver1.findElement(By.name("see")).click();
		
		Thread.sleep(3000);
		
		String expected2 = "laura garling";
		String actual2 = driver1.findElement(By.name("userName")).getText();
		Assert.assertEquals(expected2, actual2);
		
		System.out.println("Step 3 Complete.");
	}

	public static void main(String[] args) throws InterruptedException {
		RunThroughs obj = new RunThroughs();
		obj.launchBrowser();
		obj.login();
		obj.createRoom();
		obj.finalizeRoom();
		obj.checkPastBills();
		System.out.println("Run-Through Passed.");
		obj.closeBrowser();
	}

}
