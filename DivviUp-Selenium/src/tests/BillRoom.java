package tests;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

// this class includes interaction between billroompayer and billroomnonpayer
public class BillRoom {
	WebDriver driver;
	
	public void launchBrowser() {
		System.setProperty("webdriver.chrome.driver", "/Users/lauragarling/Desktop/Selenium/chromedriver");
		driver = new ChromeDriver();
		driver.get("http://localhost:3000/billroomnonpayer/H4000");
	}

	public void closeBrowser() {
		driver.quit();
	}
	
	
	
	
	public static void main(String[] args) throws InterruptedException {
		BillRoom obj = new BillRoom();
		obj.launchBrowser();
		System.out.println("Tests have finished running.");
		obj.closeBrowser();
	}

}
