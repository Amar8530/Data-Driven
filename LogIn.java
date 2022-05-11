package FlyPal_login;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class LogIn 
{
WebDriver driver;
	
	@BeforeClass
	public void setup()
	{
		System.setProperty("webdriver.chrome.driver", "D:/Amar_TC/Selenium_Webdriver/Drivers New/chromedriver.exe");
		driver=new ChromeDriver();

		driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}
	
	@Test(dataProvider="LoginData")
	public void loginTest(String user,String pwd,String exp) throws InterruptedException
	{
		//System.setProperty("webdriver.chrome.driver", "D://Amar_TC//Selenium_Webdriver//Drivers//chromedriver.exe/");

		driver.get("https://bytzsoft.in/FlyPalBytz/Login.aspx?ReturnUrl=%2fFlyPalBytz%2fTopHeader.aspx");	
		 
		  WebElement txtUserName=driver.findElement(By.id("txtUserName"));                      
	      txtUserName.clear();
	      txtUserName.sendKeys(user);
	      
	      WebElement txtPassword=driver.findElement(By.id("txtPassword"));                       
	      txtPassword.clear();            
	      txtPassword.sendKeys(pwd);
	      
	      driver.findElement(By.name("btnLogin")).click();
		
		String exp_title="FlyPal System";
		String act_title=driver.getTitle();
		
		if(exp.equals("Valid"))
		{
			if(exp_title.equals(act_title))
			{
				Assert.assertTrue(true);

			}
				
			{
				driver.findElement(By.linkText("Logout")).click();
			}
		}
		
		else
			
		if(exp.equals("Invalid"))
		{
			if(exp_title.equals(act_title))
				
			{
				Assert.assertTrue(false);
			}
			
		}
		Thread.sleep(1000);
	}
	
	@DataProvider(name="LoginData")
	public String [][] getData() throws IOException
	{
		
		
							String path="D://Amar_TC//Master_TC//(AMAR)_Masters_TC.xlsx/";
							XLUtility xlutil=new XLUtility(path);
							
							int totalrows=xlutil.getRowCount("Sheet1");
							int totalcols=xlutil.getCellCount("Sheet1",1);	
									
							String loginData[][]=new String[totalrows][totalcols];
								
							
							for(int i=1;i<=totalrows;i++) //1
							{
								for(int j=0;j<totalcols;j++) //0
								{
									loginData[i-1][j]=xlutil.getCellData("Sheet1", i, j);
								}
									
							}
							return loginData;
	}
	
	@AfterClass
	void tearDown()
	{
		driver.close();
	}
	
	
	
	
}

