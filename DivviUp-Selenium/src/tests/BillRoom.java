package tests;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

// Need to test edge cases! 
// Need to test people button 

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
		
		double beforeTot = Double.parseDouble(driver1.findElement(By.name("totalCost")).getText().substring(1));
		
		driver1.findElement(By.name("add")).click();
		driver1.findElement(By.name("itname")).sendKeys("pop");
		driver1.findElement(By.name("cost")).sendKeys("3.00");
		driver1.findElement(By.name("button")).click();
		
		Thread.sleep(4000);
		
		double afterTot = Double.parseDouble(driver1.findElement(By.name("totalCost")).getText().substring(1));
		
		Thread.sleep(5000);
		
		driver2.navigate().refresh();

		Thread.sleep(4000);
		
		//System.out.println(beforeTot);
		//System.out.println(afterTot);
		
		if ((beforeTot + 3.15) != afterTot) {
			System.out.println("FAIL: Total Cost not updated correctly.");
			return;
		}
		
		try {
			String name1 = driver1.findElement(By.name("name1")).getText().substring(0,4);
			String name2 = driver2.findElement(By.name("name2")).getText().substring(0, 4);
			Assert.assertEquals(name1, name2);
			System.out.println("Test 1 of 5: PASSED");
		} catch (StringIndexOutOfBoundsException s) {
			System.out.println("FAIL: New Item not generated.");
		}
		
	}
	
	public void testDuplicate() throws InterruptedException {
		
		double beforeTot = Double.parseDouble(driver1.findElement(By.name("totalCost")).getText().substring(1));
		
		try {
		driver1.findElement(By.name("dup")).click();
		} catch (NoSuchElementException n) {
			System.out.println("FAIL: Duplicate Button could not be located.");
			return;
		}
		
		
		Thread.sleep(3000);
		
		driver2.navigate().refresh();

		Thread.sleep(3000);
		
		double afterTot = Double.parseDouble(driver1.findElement(By.name("totalCost")).getText().substring(1));

		if ((beforeTot + 3.15) != afterTot) {
			System.out.println("FAIL: Total Cost not updated correctly.");
			return;
		}
		
		try {
			String name1 = driver1.findElement(By.name("name1")).getText().substring(0,4);
			String name2 = driver2.findElement(By.name("name2")).getText().substring(0, 4);
			Assert.assertEquals(name1, name2);
			System.out.println("Test 2 of 5: PASSED");
		} catch (StringIndexOutOfBoundsException s) {
			System.out.println("FAIL: Item not duplicated.");
		}	
	}
	
	public void testDelete() throws InterruptedException {
		
		double beforeTot = Double.parseDouble(driver1.findElement(By.name("totalCost")).getText().substring(1));
		
		try {
			driver1.findElement(By.name("del")).click();
			} catch (NoSuchElementException n) {
				System.out.println("FAIL: Delete Button could not be located.");
				return;
			}
		
		Thread.sleep(3000);
		
		driver2.navigate().refresh();

		Thread.sleep(3000);
		
		double afterTot = Double.parseDouble(driver1.findElement(By.name("totalCost")).getText().substring(1));

		if ((beforeTot - 3.15) != afterTot) {
			System.out.println("FAIL: Total Cost not updated correctly.");
			return;
		}
		
		try {
			String name1 = driver1.findElement(By.name("name1")).getText().substring(0,4);
			String name2 = driver2.findElement(By.name("name2")).getText().substring(0, 4);
			Assert.assertEquals(name1, name2);
			System.out.println("Test 3 of 5: PASSED");
		} catch (StringIndexOutOfBoundsException s) {
			System.out.println("FAIL: Item not deleted.");
			return;
		}	
	}
	
	public void testEdit() throws InterruptedException {

		
		try {
		driver1.findElement(By.name("e")).click();
		driver1.findElement(By.name("ename")).click();
		driver1.findElement(By.name("ename")).sendKeys(Keys.BACK_SPACE);
		driver1.findElement(By.name("ename")).sendKeys(Keys.BACK_SPACE);
		driver1.findElement(By.name("ename")).sendKeys(Keys.BACK_SPACE);
		driver1.findElement(By.name("ename")).sendKeys("burger");
		driver1.findElement(By.name("up")).click();
		} catch (NoSuchElementException n) {
			System.out.println("FAIL: Edit Button could not be located.");
			return;
		}
		
		Thread.sleep(3000);
		
	
		try {
			String name1 = driver1.findElement(By.name("name1")).getText().substring(0, 4);
			String name2 = driver2.findElement(By.name("name2")).getText().substring(0, 4);
			Assert.assertEquals(name1, name2);
			System.out.println("Test 4 of 5: PASSED");
		} catch (StringIndexOutOfBoundsException s) {
			System.out.println("FAIL: Item not edited.");
			return;
		}			
	}
	
	public void checkUserTot() throws InterruptedException {
		
		String beforeTot = driver2.findElement(By.name("userCost")).getText();
		
		Thread.sleep(3000);
	
		
		try {
			driver2.findElement(By.name("claim")).click();
			} catch (NoSuchElementException n) {
				System.out.println("FAIL: Claim Button could not be located.");
				return;
			}
		
		Thread.sleep(5000);
		
		driver2.navigate().refresh();
		
		Thread.sleep(5000);

		String afterTot = driver2.findElement(By.name("userCost")).getText();
		
		if (beforeTot.equals(afterTot)) {
			System.out.print("FAIL: User Cost Incorrect.");
			return;
		}
		
		System.out.print(beforeTot);
		System.out.print(afterTot);

		int beforePrice = Integer.parseInt(beforeTot.substring(1, 5));
		int afterPrice = Integer.parseInt(afterTot.substring(1, 5));
		
		String dummy = (driver2.findElement(By.name("cost1")).getText());
		int price = Integer.parseInt(dummy.substring(6, dummy.length()-3));
		
		if (beforePrice + price != afterPrice) {
			System.out.print("FAIL: User Cost Updated Incorrectly.");
			return;
		}

		System.out.println("Test 5 of 5: PASSED");		

	}
	
	public static void main(String[] args) throws InterruptedException {
		BillRoom obj = new BillRoom();
		obj.launchBrowser();
		obj.testAdd();
		obj.testDuplicate();
		obj.testDelete();
		obj.testEdit();
		obj.checkUserTot();
		System.out.println("Tests have finished running.");
		obj.closeBrowser();
	}

}
