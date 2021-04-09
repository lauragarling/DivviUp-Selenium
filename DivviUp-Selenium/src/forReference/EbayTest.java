package forReference;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class EbayTest {
	WebDriver driver;
	
	public void launchBrowser() {
		System.setProperty("webdriver.chrome.driver", "/Users/lauragarling/Desktop/Selenium/chromedriver");
		driver = new ChromeDriver();
		driver.get("https://www.ebay.com/");
	}
	
	public void searchProduct() throws InterruptedException {
		Thread.sleep(200);
		driver.findElement(By.id("gh-ac")).sendKeys("JBL Speakers");
		driver.findElement(By.id("gh-btn")).click();
		Thread.sleep(200);
		driver.findElement(By.linkText("Daily Deals")).click();
	}
	
	public void naviagte() throws InterruptedException {
		Thread.sleep(200);
		driver.navigate().to("https://www.amazon.com/");
		Thread.sleep(200);
		driver.navigate().back();
		System.out.println("Title of Page : " + driver.getTitle());
	}
	
	public void closeBrowser() {
		driver.quit();
	}
	
	public static void main(String[] args) throws InterruptedException {
		EbayTest obj = new EbayTest();
		obj.launchBrowser();
		obj.searchProduct();
		obj.naviagte();
		obj.closeBrowser();
	}

}

