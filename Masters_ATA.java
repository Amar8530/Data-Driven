package FlyPal_login;

import static org.testng.Assert.assertTrue;

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

public class Masters_ATA 
{
	WebDriver driver;

	@BeforeClass  //This method is executed only once at the beginning of the class.
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

	driver.findElement(By.xpath("//tbody/tr[4]/td[1]/ul[1]/li[1]/div[1]/ul[1]/li[11]/a[1]")).click();
	Thread.sleep(1000);

	driver.switchTo().defaultContent();
	driver.switchTo().frame("FrameCentre");
	Thread.sleep(1000);
	}


	@Test(priority=1,dataProvider = "AtaData")  //DataProvider passes data to test scripts.Using DataProvider we can easily inject multiple values into the same test case.
	public void Save (String Acode, String Achapter)throws InterruptedException
	{

	WebElement Atacode= driver.findElement(By.id("txtATACode"));//code
	Atacode.clear();
	Atacode.sendKeys(Acode);
	Thread.sleep(1000);

	WebElement Chapter =driver.findElement(By.id("txtATANomenclature"));//Chapter
	Chapter.clear();
	Chapter.sendKeys(Achapter);
	Thread.sleep(1000);

	driver.findElement(By.id("btnSave")).click();
	Thread.sleep(1000);
	
	}
@Test(priority=2) //(Duplicate Record Test Case)
	void Duplicate() throws InterruptedException
	{
	driver.findElement(By.id("txtATACode")).sendKeys("43");
	driver.findElement(By.id("txtATANomenclature")).sendKeys("Maintenance of Engine Training");
	driver.findElement(By.id("btnSave")).click();
	
	Thread.sleep(1000);
	
	WebElement Dupli = driver.findElement(By.id("MSGBoxCtrl_ucNotificationMsg1_lblNotificationTitle"));
	if(Dupli.isDisplayed());
	
	{
		System.out.println(Dupli.getText());
		Assert.assertTrue(true);
	}
	
	}

@Test(priority=3)//(Error Message Test Case)
	public void ErrorMessage()throws InterruptedException 
	{
		driver.findElement(By.id("txtATACode")).sendKeys("90");
	driver.findElement(By.id("btnSave")).click();
	
	  WebElement errorMsg=driver.findElement(By.xpath("//li[contains(text(),'Chapter Required.')]"));
	  if(errorMsg.isDisplayed()); 
	  { 
	   System.out.println(errorMsg.getText()); 
	   Assert.assertTrue(true);
	   //Assert.assertTrue(false);
	  }
	 
	  
	  driver.switchTo().defaultContent();
		driver.switchTo().frame("FrameCentre");
		Thread.sleep(1000);
		
	}

	@Test(priority=4,dataProvider="Delete") //(Delete Test Case)
	public void Delete(String Aflight)throws InterruptedException 
	{
	
		WebElement fli= driver.findElement(By.id("txtSearch"));//code
		fli.clear();
		fli.sendKeys(Aflight);
		driver.findElement(By.id("btnFindNow")).click();
		Thread.sleep(1500);
		 
		
		
		driver.findElement(By.linkText("Delete")).click();
		Thread.sleep(1000);
		driver.findElement(By.id("MSGBoxCtrl_btnYes")).click();
		Thread.sleep(1500);
	
	}
	@DataProvider(name="AtaData")   //DataProvider passes data to test scripts.Using DataProvider we can easily inject multiple values into the same test case.
	public String [][] getvendorData() throws IOException
	{
							String path="D:/Amar_TC/Master_TC/Data_Driven/DataDriven_Sheet.xlsx";
							XLUtility xlutil=new XLUtility(path); //To access methods from utility file.
							
							int totalrows=xlutil.getRowCount("ATA5");
							int totalcols=xlutil.getCellCount("ATA5",1);	
									
							String Test[][]=new String[totalrows][totalcols];
								
							
							for(int i=1;i<=totalrows;i++) //Represent the rows in excel sheet. In excel index in start from 0 and 0 is header part so we are starting from (i=1).
							{
								for(int j=0;j<totalcols;j++) //Represent the cells in each row.
								{
									Test[i-1][j]=xlutil.getCellData("ATA5", i, j); ///storing login data to 2 dia array.
								}
									
							}
							return Test;
	}

	
	
@DataProvider(name="Delete")   //DataProvider passes data to test scripts.Using DataProvider we can easily inject multiple values into the same test case.
public String [][] getdeleteData() throws IOException
{
						String path="D:/Amar_TC/Master_TC/Data_Driven/DataDriven_Sheet.xlsx";
						XLUtility xlutil=new XLUtility(path); //o access methods from utility file.
						
						int totalrows=xlutil.getRowCount("ATA6");
						int totalcols=xlutil.getCellCount("ATA6",1);	
								
						String del[][]=new String[totalrows][totalcols];
							
						
						for(int i=1;i<=totalrows;i++) //Represent the rows in excel sheet. In excel index in start from 0 and 0 is header part so we are starting from (i=1).
						{
							for(int j=0;j<totalcols;j++) //Represent the cells in each row.
							{
								del[i-1][j]=xlutil.getCellData("ATA6", i, j); ///storing login data to 2 dia array.
							}
								
						}
						return del;
}

}
