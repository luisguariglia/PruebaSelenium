package com.selenium;

import static org.junit.Assert.*;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class MercuryTours_AutomatedTest {

	private WebDriver driver;
	private String userName;
	
	//para pagina de login
	By loginLinkLocator = By.id("menuUserLink");
	By loginPageLocator = By.xpath("/html/body/login-modal/div/div/div[3]/img");
	
	//para pagina de registro
	By registerLinkLocator = By.xpath("/html/body/login-modal/div/div/div[3]/a[2]");
	By registerPageOk = By.xpath("//*[@id=\"registerPage\"]/article/h3");
	
	//datos de usuario
	By usernameLocator = By.name("usernameRegisterPage");
	By emailnameLocator = By.name("emailRegisterPage");
	By passwordnameLocator = By.name("passwordRegisterPage");
	By confirmpasswordnameLocator = By.name("confirm_passwordRegisterPage");
	
	//confirmacion
	By agreeLocator = By.xpath("//*[@id=\"formCover\"]/sec-view/div/input");
	By registerBtnLocator = By.id("register_btnundefined");
	By LoginSuccessLocator = By.xpath("//*[@id=\"menuUserLink\"]/span");
	
	@Before
	public void setUp() throws Exception {
		System.setProperty("webdriver.chrome.driver", "./src/test/resources/chromedriver/chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("http://advantageonlineshopping.com/#/");
	}

	@After
	public void tearDown() throws Exception {
		driver.quit();
	}

	@Test
	public void registerUser() throws InterruptedException {
		Thread.sleep(5000);
		driver.findElement(loginLinkLocator).click();
		Thread.sleep(2000);
		
		if(driver.findElement(loginPageLocator).isEnabled()) {
			driver.findElement(registerLinkLocator).click();
			Thread.sleep(2000);
			if(driver.findElement(registerPageOk).isDisplayed()) {
				
				String generatedString=String.valueOf(ThreadLocalRandom.current().nextInt(1000,10000));   
			    
				userName="usuario-"+generatedString;
				driver.findElement(usernameLocator).sendKeys(userName);
				driver.findElement(emailnameLocator).sendKeys("usuario-"+generatedString+"@gmail.com");
				driver.findElement(passwordnameLocator).sendKeys("Usuario"+generatedString);
				driver.findElement(confirmpasswordnameLocator).sendKeys("Usuario"+generatedString);
				
				Thread.sleep(1000);
				driver.findElement(agreeLocator).click();
				driver.findElement(registerBtnLocator).click();
			}else {
				
				System.out.print("register page not found");
			}
		}else {
			
			System.out.print("login page not found");
		}
		Thread.sleep(2000);
		assertEquals(driver.findElement(LoginSuccessLocator).getText(),userName);	
		
	}


}
