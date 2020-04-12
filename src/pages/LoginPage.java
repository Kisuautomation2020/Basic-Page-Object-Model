package pages;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {

	WebDriver driver;
	WebDriverWait wait;

	// Note: We can't use initialize the following web element otherwise it will
	// throw null pointer exception error
	// because web driver will be empty at that time and did get any value for

	/*
	 * WebElement email=driver.findElement(By.name("identifier")); WebElement
	 * emailNext=driver.findElement(By.className("CwaK9")); WebElement
	 * password=driver.findElement(By.name("password")); WebElement
	 * passwordNext=driver.findElement(By.className("CwaK9")); WebElement
	 * passwordError=driver.findElement(By.xpath("//div[@jsname='B34EJ']/span"));
	 * WebElement emailError=driver.findElement(By.xpath("//div[@class='GQ8Pzc']"));
	 */

	// use the following page factory method for not using driver for this null
	// pointer exception error solution

	@FindBy(name = "identifier")
	public static WebElement email;

	@FindBy(className = "CwaK9")
	public static WebElement emailNext;

	@FindBy(name = "password")
	public static WebElement password;

	@FindBy(className = "CwaK9")
	public static WebElement passwordNext;

	@FindBy(xpath = "//div[@jsname='B34EJ']/span")
	public static WebElement passwordError;

	@FindBy(xpath = "//div[@class='GQ8Pzc']")
	public static WebElement emailError;

	public void openBrowser() throws IOException {
		FileInputStream fs = new FileInputStream("C:\\testing\\prop.properties");
		Properties prop = new Properties();
		prop.load(fs);

		String browser = prop.getProperty("browser");
		if (browser.equals("Firefox")) {

			System.setProperty("webdriver.gecko.driver", "C:\\SeleniumJars\\geckodriver.exe");
			driver = new FirefoxDriver();
		} else if (browser.equals("Chrome")) {
			System.setProperty("webdriver.chrome.driver", "C:\\SeleniumJars\\chromedriver.exe");
			driver = new ChromeDriver();
		} else {
			System.setProperty("webdriver.chrome.driver", "C:\\SeleniumJars\\chromedriver.exe");
			driver = new ChromeDriver();
		}

		PageFactory.initElements(driver, this);
	}

	public void openGmail() {
		driver.get(
				"https://accounts.google.com/signin/v2/identifier?continue=https%3A%2F%2Fmail.google.com%2Fmail%2F&service=mail&flowName=GlifWebSignIn&flowEntry=ServiceLogin");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

	}

	public void closeBrowser() {
		driver.quit();
	}

	public void enterEmail(String a) throws InterruptedException // here pass the string for accept different string
																	// value for enter email id...not need to make
																	// different methods
	{
		email.sendKeys(a); // pass this value to send keys (String a)
		emailNext.click();
		Thread.sleep(3000);
	}

	public void enterPassword(String b) throws InterruptedException {
		password.sendKeys(b);
		passwordNext.click();
		// Thread.sleep(3000); //-- used explicit wait instead of it
	}

	public String readPasswordErr() {
		wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.textToBePresentInElementLocated(By.xpath("//div[@jsname='B34EJ']/span"),
				"Wrong password. Try again or click Forgot password to reset it."));
		String actualErrMsg = passwordError.getText();
		System.out.println(actualErrMsg);
		return actualErrMsg;
	}

	public String readEmailErr() {
		String actualErrMsg = emailError.getText();
		System.out.println(actualErrMsg);
		return actualErrMsg;
	}

}
