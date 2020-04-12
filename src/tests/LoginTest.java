package tests;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import data.DataFile;
import pages.LoginPage;

import org.testng.annotations.BeforeMethod;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;

public class LoginTest {

	// to import this package add selenium-server-stand alone jar files and other
	// jar files in Referenced Libraries
	WebDriver driver; // global declare

	DataFile df = new DataFile(); // make object of data file for passing the value of username and password from excel sheet.
	LoginPage lp = new LoginPage(); // make this object for call all the methods from Login page file and call the
									// browser from property file using multibrowser code in openbrowser () method

	@BeforeMethod
	public void beforeMethod() throws IOException {

		lp.openBrowser();

		lp.openGmail();
		/*
		 * System.out.println(df.invalidEmail); System.out.println(df.emailError);
		 * System.out.println(df.passwordError); System.out.println(df.validEmail);
		 * System.out.println(df.wrongPassword);
		 */
	}

	@AfterMethod
	public void afterMethod() {
		// close FireFox
		lp.closeBrowser();
	}

	@Test
	public void loginWithWrongPasswordTest() throws InterruptedException {

		lp.enterEmail(df.validEmail); // df.validEmail pass the value from excel sheet
		lp.enterPassword(df.wrongPassword);
		Assert.assertEquals(lp.readPasswordErr(), df.passwordError);

	}

	@Test
	public void loginWithWrongEmailTest() throws InterruptedException {
		lp.enterEmail(df.invalidEmail);
		Assert.assertEquals(lp.readEmailErr(), df.emailError);

	}
}
