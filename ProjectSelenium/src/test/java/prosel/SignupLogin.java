package prosel;

import static org.testng.Assert.assertEquals;

import java.io.FileInputStream;		
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;		
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class SignupLogin {
	
	 private WebDriver driver;
	 private Select dropdown;
	 private WebElement we;
	 String cuur,tit;
	 Properties p = new Properties();
	 private static final Logger logger = LogManager.getLogger(SignupLogin.class);

	 
    @BeforeClass
    public void init() throws Exception{
    	System.setProperty("webdriver.gecko.driver","src\\test\\resources\\geckodriver.exe");
		driver = (WebDriver) new FirefoxDriver();
        FileReader reader=new FileReader("src\\test\\resources\\webelement.properties");									
        
        p.load(reader);	
        FileReader reader1=new FileReader("src\\test\\resources\\information.properties");									
        p.load(reader1);			
		
    }
 
    @Test(testName = "testSignupLogin")
    public void testSignupLogin() throws InterruptedException {
    driver.get("https://demo.guru99.com/test/newtours/register.php/");
    driver.manage().window().maximize();
    driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
    we = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name(p.getProperty("Submitbtn"))));
	driver.findElement(By.name(p.getProperty("FirstName"))).click();
    driver.findElement(By.name(p.getProperty("FirstName"))).sendKeys((p.getProperty("Firstname")));
    driver.findElement(By.name(p.getProperty("LastName"))).click();
    driver.findElement(By.name(p.getProperty("LastName"))).sendKeys((p.getProperty("Lastname")));
    driver.findElement(By.name(p.getProperty("Phone"))).click();
    driver.findElement(By.name(p.getProperty("Phone"))).sendKeys((p.getProperty("PhoneNo")));
    driver.findElement(By.name(p.getProperty("Email"))).click();
    driver.findElement(By.name(p.getProperty("Email"))).sendKeys((p.getProperty("EmailID")));
    driver.findElement(By.name(p.getProperty("Address"))).click();
    driver.findElement(By.name(p.getProperty("Address"))).sendKeys((p.getProperty("AddressDet")));
    driver.findElement(By.name(p.getProperty("City"))).click();
    driver.findElement(By.name(p.getProperty("City"))).sendKeys((p.getProperty("CityDet")));
    driver.findElement(By.name(p.getProperty("State"))).click();
    driver.findElement(By.name(p.getProperty("State"))).sendKeys((p.getProperty("StateDet")));
    driver.findElement(By.name(p.getProperty("PostalCode"))).click();
    driver.findElement(By.name(p.getProperty("PostalCode"))).sendKeys((p.getProperty("PostBoxNo")));
    dropdown = new Select(driver.findElement(By.name(p.getProperty("Country"))));
    dropdown.selectByVisibleText(p.getProperty("CountryDet"));  
    driver.findElement(By.name(p.getProperty("UserID"))).click();
    driver.findElement(By.name(p.getProperty("UserID"))).sendKeys((p.getProperty("UserName")));
    driver.findElement(By.name(p.getProperty("Password"))).click();
    driver.findElement(By.name(p.getProperty("Password"))).sendKeys((p.getProperty("PassWord")));
    driver.findElement(By.name(p.getProperty("ConfirmPwd"))).click();
    driver.findElement(By.name(p.getProperty("ConfirmPwd"))).sendKeys((p.getProperty("Confirmpwd")));
    driver.findElement(By.name(p.getProperty("Submitbtn"))).click();
	driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
	// we = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name(p.getProperty("SignIn"))));
	cuur = driver.getCurrentUrl();
	System.out.println("Current Url Is:"+cuur);
	tit = driver.getTitle();
	System.out.println("Title is:"+tit);
	assertEquals(tit, "Register: Mercury Tours");
	System.out.println("Signup Title Validated.It Working Fine");
	logger.always();
	logger.info("Signup Title Validated");
	if (cuur.equals(p.getProperty("URLConfirmation")))
	{
    	System.out.println("Signup Completed");
    	logger.info("Signup Completed");
    }
    else
    {
    	System.out.println("Signup Not Completed");
    	logger.info("Signup Not Completed");
    }
    if(driver.getPageSource().contains(p.getProperty("RegisterConfirmation")))
    {
    	System.out.println("Signup SuccessPage Launched");
    	logger.info("Signup SuccessPage Launched");
    }
    else
    {
    	System.out.println("Signup SuccessPage Not Launched");
    	logger.error("Signup SuccessPage Not Launched");
    	driver.quit();
    }
    
    driver.findElement(By.linkText(p.getProperty("SignIn"))).click();
	driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
    if(driver.getPageSource().contains(p.getProperty("LoginPageConfirmation")))
    {
    	System.out.println("Login Page Launched");
    	logger.info("Login Page Launched");
    }
    else
    {
    	System.out.println("Login Page Not Launched");
    	logger.info("Login Page Not Launched");
    }
    driver.findElement(By.name(p.getProperty("UserID"))).click();
    driver.findElement(By.name(p.getProperty("UserID"))).sendKeys((p.getProperty("UserName")));
    driver.findElement(By.name(p.getProperty("Password"))).click();
    driver.findElement(By.name(p.getProperty("Password"))).sendKeys((p.getProperty("PassWord")));
    driver.findElement(By.name(p.getProperty("Submitbtn"))).click();
    driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
    if(driver.getPageSource().contains(p.getProperty("AfterLoginPageConfirmation")))
    {
    	System.out.println("Login Page Entered");
    	logger.info("Login Page Entered");
    }
    else
    {
    	System.out.println("Login Page Not Entered");
    	logger.info("Login Page Not Entered");
    }
    
    }
 
    @AfterClass
    public void cleanup(){
        if(driver !=null)
            driver.quit();
    }

}