package FlyPal_login;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class Masters_Currency 
{
	WebDriver driver;

	@BeforeClass
	public void setup() throws InterruptedException
	{
	System.setProperty("webdriver.chrome.driver", "D:/Amar_TC/Selenium_Webdriver/Drivers New/chromedriver.exe");
	driver=new ChromeDriver();
	driver.get("https://bytzsoft.in/FlyPalTemp/Login.aspx?ReturnUrl=%2fFlypalbytz%2f");	
	driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
	 
	//Login
	driver.findElement(By.id("txtUserName")).sendKeys("AMAR");
	driver.findElement(By.id("txtPassword")).sendKeys("AMAR123");
	driver.findElement(By.id("btnLogin")).click();
	Thread.sleep(5000);


	driver.switchTo().frame("FrameLeft");
	Thread.sleep(1000);

	driver.findElement(By.xpath("//label[contains(text(),'Masters')]")).click(); //Masters
	Thread.sleep(1000);
	driver.findElement(By.xpath("//a[contains(text(),'Currency')]")).click();
	Thread.sleep(1000);
	
	driver.switchTo().defaultContent();
	driver.switchTo().frame("FrameCentre");
	Thread.sleep(1000);
}
	@Test(priority=1,dataProvider = "Currency data")
	public void Save(String NameBefore, String NameAfter, String Symbol, String Cfactor) throws InterruptedException
	{
		WebElement first = driver.findElement(By.id("txtCurrencyName"));
		first.clear();
		first.sendKeys(NameBefore);
		Thread.sleep(500);
		
		WebElement second = driver.findElement(By.id("txtNameAfterDecimal"));
		second.clear();
		second.sendKeys(NameAfter);
		Thread.sleep(500);
		
		WebElement third = driver.findElement(By.id("txtSymbol"));
		third.clear();
		third.sendKeys(Symbol);
		Thread.sleep(500);
		
		WebElement fourth = driver.findElement(By.id("txtConvFactor"));
		fourth.clear();
		fourth.sendKeys(Cfactor);
		Thread.sleep(500);
		
		driver.findElement(By.name("btnSave")).click();
		Thread.sleep(1500);
	}
	@Test(priority=2)
	public void ErrorMessage() throws InterruptedException
	{
		driver.findElement(By.id("txtCurrencyName")).sendKeys("Belarus");
		
		driver.findElement(By.id("btnSave")).click();
		Thread.sleep(500);
		 
		  WebElement errMsg=driver.findElement(By.id("Validationsummary1")); 
		  if(errMsg.isDisplayed()); 
		  { 
		   System.out.println(errMsg.getText()); 
		   Assert.assertTrue(true);
		  }
		  Thread.sleep(1000);
	}
	@Test(priority=3)
	public void Duplicate() throws InterruptedException
	{
		driver.findElement(By.id("btnAdd")).click(); //New
		Thread.sleep(1000);
		driver.findElement(By.id("txtCurrencyName")).sendKeys("Argentina Peso");
		driver.findElement(By.id("txtNameAfterDecimal")).sendKeys("ARS");
		driver.findElement(By.id("txtSymbol")).sendKeys("$");
		driver.findElement(By.id("txtConvFactor")).clear();
		Thread.sleep(1000);
		driver.findElement(By.id("txtConvFactor")).sendKeys("0.65");
		Thread.sleep(1000);
		driver.findElement(By.id("btnSave")).click();
		
		WebElement dupli=driver.findElement(By.xpath("//span[@id='MSGBoxCtrl_lblMsgTitle']"));
		  if(dupli.isDisplayed()); 
		  { 
		   System.out.println(dupli.getText()); 
		   Assert.assertTrue(true);
		  }
		  driver.findElement(By.id("MSGBoxCtrl_btnOk")).click();
		  Thread.sleep(1000);
	}
	
	@Test(priority=4)
		public void Edit() throws InterruptedException
		{
		driver.findElement(By.xpath("//tbody/tr[2]/td[5]/a[1]")).click();
		Thread.sleep(1000);
		driver.findElement(By.id("txtSymbol")).clear();
		Thread.sleep(1000);
		driver.findElement(By.id("txtSymbol")).sendKeys("Ar");
		driver.findElement(By.id("btnSave")).click();
		Thread.sleep(1000);
		}
		
		@Test(priority=5)
		public void Delete()  throws InterruptedException
		{
			driver.findElement(By.xpath("//tbody/tr[3]/td[5]/a[1]")).click(); //Currency.
			Thread.sleep(1500);
			
			driver.switchTo().defaultContent();
			driver.switchTo().frame("FrameCentre");
			Thread.sleep(1000);
			
			driver.findElement(By.xpath("//tbody/tr[2]/td[6]/a[1]")).click();
			Thread.sleep(500);

			driver.findElement(By.id("MSGBoxCtrl_btnYes")).click();
			Thread.sleep(500);
			driver.findElement(By.xpath("//tbody/tr[2]/td[6]/a[1]")).click();
			Thread.sleep(500);

			driver.findElement(By.id("MSGBoxCtrl_btnYes")).click();
			Thread.sleep(500);
			driver.findElement(By.xpath("//tbody/tr[2]/td[6]/a[1]")).click();
			Thread.sleep(500);

			driver.findElement(By.id("MSGBoxCtrl_btnYes")).click();
			Thread.sleep(500);
			driver.findElement(By.xpath("//tbody/tr[2]/td[6]/a[1]")).click();
			Thread.sleep(500);

			driver.findElement(By.id("MSGBoxCtrl_btnYes")).click();
			Thread.sleep(500);
			
			
		
		}
		
	
		@DataProvider(name="Currency data")   //DataProvider passes data to test scripts.Using DataProvider we can easily inject multiple values into the same test case.
		public String [][] getcurrencyData() throws IOException
		{
								String path="D:/Amar_TC/Master_TC/Data_Driven/DataDriven_Sheet.xlsx";
								XLUtility xlutil=new XLUtility(path); //To access methods from utility file.
								
								int totalrows=xlutil.getRowCount("Currency3");
								int totalcols=xlutil.getCellCount("Currency3",1);	
										
								String Test[][]=new String[totalrows][totalcols];
									
								
								for(int i=1;i<=totalrows;i++) //Represent the rows in excel sheet. In excel index in start from 0 and 0 is header part so we are starting from (i=1).
								{
									for(int j=0;j<totalcols;j++) //Represent the cells in each row.
									{
										Test[i-1][j]=xlutil.getCellData("Currency3", i, j); ///storing login data to 2 dia array.
									}
										
								}
								return Test;
		}

	}

