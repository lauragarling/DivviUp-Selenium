package tests;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

// this class includes interaction between billroompayer and billroomnonpayer
public class BillRoom {
	WebDriver driver1;
	WebDriver driver2;
	
	public void launchBrowser() {
		System.setProperty("webdriver.chrome.driver", "/Users/lauragarling/Desktop/Selenium/chromedriver");
		driver1 = new ChromeDriver();
		driver1.get("http://localhost:3000/home");
		
		driver2 = new ChromeDriver();
		driver2.get("http://localhost:3000/home");
		
	}

	public void closeBrowser() {
		driver1.quit();
		driver2.quit();
	}
	
	public void testAdd() throws InterruptedException {
		
		Thread.sleep(3000);
		if (driver1.getCurrentUrl().equals("http://localhost:3000/start")) { // logs in if previous cookie DNE
			driver1.findElement(By.name("loginbutton")).click();
			Thread.sleep(2000);
			driver1.findElement(By.id("exampleInputEmail1")).sendKeys("garling@wisc.edu");
			driver1.findElement(By.id("exampleInputPassword1")).sendKeys("owen");
			driver1.findElement(By.name("submit")).click();
			Thread.sleep(2000);
		}
		
		Thread.sleep(3000);
		if (driver2.getCurrentUrl().equals("http://localhost:3000/start")) { // logs in if previous cookie DNE
			driver2.findElement(By.name("loginbutton")).click();
			Thread.sleep(2000);
			driver2.findElement(By.id("exampleInputEmail1")).sendKeys("owolman@wisc.edu");
			driver2.findElement(By.id("exampleInputPassword1")).sendKeys("lizard");
			driver2.findElement(By.name("submit")).click();
			Thread.sleep(2000);
		}
		
		Thread.sleep(3000);
		
		driver1.navigate().to("http://localhost:3000/billroompayer/H4000");
		driver2.navigate().to("http://localhost:3000/billroomnonpayer/H4000");
		
		Thread.sleep(3000);
		
		driver1.findElement(By.name("add")).click();
		driver1.findElement(By.name("itname")).sendKeys("pop");
		driver1.findElement(By.name("cost")).sendKeys("3.00");
		driver1.findElement(By.name("butt")).click();
		
		Thread.sleep(3000);
		
		driver2.navigate().refresh();

		Thread.sleep(3000);
		
		String name1 = driver1.findElement(By.name("name")).getText();
		String name2 = driver2.findElement(By.name("name1")).getText();
		Assert.assertEquals(name1, name2);
		
		System.out.println("Test 1 of 4: PASSED");
	}
	
	public void testDuplicate() throws InterruptedException {
		
		driver1.findElement(By.name("dup")).click();
		
		Thread.sleep(3000);
		
		driver2.navigate().refresh();

		Thread.sleep(3000);
		
		String name1 = driver1.findElement(By.name("name")).getText();
		String name2 = driver2.findElement(By.name("name1")).getText();
		Assert.assertEquals(name1, name2);
		
		System.out.println("Test 2 of 4: PASSED");		
	}
	
	public void testDelete() throws InterruptedException {
		
		driver1.findElement(By.name("del")).click();
		
		Thread.sleep(3000);
		
		driver2.navigate().refresh();

		Thread.sleep(3000);
		
		String name1 = driver1.findElement(By.name("name")).getText();
		String name2 = driver2.findElement(By.name("name1")).getText();
		Assert.assertEquals(name1, name2);
		
		System.out.println("Test 3 of 4: PASSED");		
	}
	
	public void testEdit() throws InterruptedException {
		
		driver1.findElement(By.name("e")).click();
		driver1.findElement(By.name("ename")).sendKeys(Keys.BACK_SPACE);
		driver1.findElement(By.name("ename")).sendKeys(Keys.BACK_SPACE);
		driver1.findElement(By.name("ename")).sendKeys(Keys.BACK_SPACE);
		driver1.findElement(By.name("ename")).sendKeys("burger");
		driver1.findElement(By.name("up")).click();

		Thread.sleep(3000);
		
		String name1 = driver1.findElement(By.name("name")).getText();
		String name2 = driver2.findElement(By.name("name1")).getText();
		Assert.assertEquals(name1, name2);
		
		String ActName = name1;
		String ExpName = driver2.findElement(By.name("name1")).getText();
		Assert.assertEquals(ExpName, ActName);
		
		System.out.println("Test 4 of 4: PASSED");		
	}
	
	
	public static void main(String[] args) throws InterruptedException {
		BillRoom obj = new BillRoom();
		obj.launchBrowser();
		obj.testAdd();
		obj.testDuplicate();
		obj.testDelete();
		obj.testEdit();
		System.out.println("Tests have finished running.");
		//obj.closeBrowser();
	}

}
