package FlyPal_login;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class Masters_Inspection_Kit 
{
	WebDriver driver;

	@BeforeClass  // This method will execute only one time before starting the class.
	public void setup() throws InterruptedException
	{
	System.setProperty("webdriver.chrome.driver", "D:/Amar_TC/Selenium_Webdriver/Drivers New/chromedriver.exe");
	driver=new ChromeDriver();
	driver.get("https://bytzsoft.in/FlyPalBytz/Index.aspx");	
	driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
	 
	//Login
	driver.findElement(By.id("txtUserName")).sendKeys("Amar");
	driver.findElement(By.id("txtPassword")).sendKeys("1234");
	driver.findElement(By.id("btnLogin")).click();
	Thread.sleep(5000);


	driver.switchTo().frame("FrameLeft");
	Thread.sleep(1000);
	 
	driver.findElement(By.xpath("//label[contains(text(),'Masters')]")).click();  //Master
	Thread.sleep(1000);
	driver.findElement(By.xpath("//tbody/tr[4]/td[1]/ul[1]/li[1]/div[1]/ul[1]/li[4]/a[1]")).click();
	Thread.sleep(1000);
	
	driver.switchTo().defaultContent();
	driver.switchTo().frame("FrameCentre");
	
	driver.findElement(By.id("btnAddNewTop")).click();   //Add New
	Thread.sleep(1000);
	driver.findElement(By.id("btnAdd")).click(); //Add New
	Thread.sleep(1000);
	
	Select part = new Select(driver.findElement(By.id("cmbPartNo")));
	part.selectByIndex(11);
	Thread.sleep(1000);
	
	driver.findElement(By.id("txtQuantity")).clear();
	Thread.sleep(500);
	driver.findElement(By.id("txtQuantity")).sendKeys("1");
	Thread.sleep(1000);
	
	driver.findElement(By.name("btnAddKitItem")).click();
	Thread.sleep(1000);
}
	@Test(dataProvider = "Inspection")  //@Test annotation is used to tell that the method under it is a test case.
	public void Save (String Ikit) throws InterruptedException
	{
		WebElement Kit= driver.findElement(By.id("txtInspectionKit"));//code
		Kit.clear();
		Kit.sendKeys(Ikit);
		Thread.sleep(1000);
		
		driver.findElement(By.id("btnSave")).click();
		Thread.sleep(1000);

		WebElement sav=driver.findElement(By.id("lblInspection"));
		  if(sav.isDisplayed()); 
		  { 
		   System.out.println(sav.getText()); 
		   Assert.assertTrue(true);
		}
	}

	@Test(priority=1)        
	public void ErrorMessage() throws InterruptedException
	{
		driver.findElement(By.id("btnBack")).click();
		Thread.sleep(1000);
		
		driver.switchTo().defaultContent();
		driver.switchTo().frame("FrameCentre");
		driver.findElement(By.id("btnAddNewTop")).click();
		Thread.sleep(1000);
		driver.findElement(By.id("btnAdd")).click();
		Thread.sleep(1000);
		Select part = new Select(driver.findElement(By.id("cmbPartNo"))); //Part Selection.
		part.selectByIndex(11);
		Thread.sleep(1000);
		
		driver.findElement(By.id("txtQuantity")).clear();
		Thread.sleep(1000);
		driver.findElement(By.id("txtQuantity")).sendKeys("5");
		Thread.sleep(500);
		driver.findElement(By.id("btnAddKitItem")).click();
		Thread.sleep(500);
		driver.findElement(By.id("btnSave")).click(); //Save.
		Thread.sleep(500);
		
		
		
		WebElement Error=driver.findElement(By.xpath("//div[@id='Validationsummary1']"));
		  if(Error.isDisplayed()); 
		  { 
		   System.out.println(Error.getText()); 
		   Assert.assertTrue(true);
		  }
			
		
		Thread.sleep(1000);
		driver.findElement(By.id("btnBack")).click();
		Thread.sleep(1000);
		driver.findElement(By.id("MSGBoxCtrl_btnNo")).click();
	}
	
	@Test(priority=2)
	public void Duplicate()throws InterruptedException
	{
		//driver.findElement(By.id("btnBack")).click();   //close/back
		//Thread.sleep(1000);

		driver.switchTo().defaultContent();
		driver.switchTo().frame("FrameCentre");
		
		driver.findElement(By.xpath("//input[@id='btnAddNewTop']")).click();  //Add New
		Thread.sleep(1000);
		
		driver.findElement(By.id("btnAdd")).click(); //Add New
		Thread.sleep(1000);
		
		Select part = new Select(driver.findElement(By.id("cmbPartNo")));
		part.selectByIndex(11);
		Thread.sleep(1000);
		
		driver.findElement(By.id("txtQuantity")).clear();
		Thread.sleep(1000);
		driver.findElement(By.id("txtQuantity")).sendKeys("1");
		Thread.sleep(500);
		driver.findElement(By.id("btnAddKitItem")).click();
		driver.findElement(By.id("txtInspectionKit")).sendKeys("Annual Engine Inspection");
		Thread.sleep(1000);

		
		driver.findElement(By.id("btnSave")).click(); //Save
		Thread.sleep(1000);
		
		WebElement Dup = driver.findElement(By.xpath("//span[@id='MSGBoxCtrl_lblMsgTitle']"));
		if(Dup.isDisplayed());
		
		{
			System.out.println(Dup.getText());
			Assert.assertTrue(true);
		}
		
		Thread.sleep(1000);
		driver.findElement(By.id("MSGBoxCtrl_btnOk")).click();
		
		driver.findElement(By.id("btnBack")).click();  //Close
		Thread.sleep(1000);
		driver.findElement(By.id("MSGBoxCtrl_btnNo")).click();
		Thread.sleep(1000);
		
}

	
	@Test(priority=3)
	void Edit() throws InterruptedException 
	{
		driver.switchTo().defaultContent();
		driver.switchTo().frame("FrameCentre");
		
		Select Search = new Select(driver.findElement(By.xpath("//select[@id='cmbLookIn']"))); //Search Menu
		Search.selectByVisibleText("Name");
		System.out.println("Search Menu Clicked");
		Thread.sleep(1000);
		
		driver.findElement(By.id("txtFor")).sendKeys("Annual Engine Inspection"); //For field.
		Thread.sleep(1000);
		driver.findElement(By.id("btnFindNow")).click();
		Thread.sleep(2000);
		
		
		//Edit-1
		driver.findElement(By.xpath("/html[1]/body[1]/form[1]/table[1]/tbody[1]/tr[1]/td[1]/div[1]/table[1]/tbody[1]/tr[5]/td[1]/div[1]/div[1]/table[1]/tbody[1]/tr[2]/td[2]/a[1]")).click(); //Edit
		Thread.sleep(1000);
		driver.findElement(By.id("txtInspectionKit")).clear();
		Thread.sleep(1000);
		driver.findElement(By.id("txtInspectionKit")).sendKeys("Annual AMAR Inspection");  //Kit Name changed.
		Thread.sleep(500);
		
		
		//Edit-2	
		driver.findElement(By.xpath("//a[contains(text(),'Edit/View')]")).click();
		Thread.sleep(1000);
		
		driver.findElement(By.id("txtQuantity")).clear();//Quantity
		Thread.sleep(500);
		driver.findElement(By.id("txtQuantity")).sendKeys("4");
		
		driver.findElement(By.id("btnAddKitItem")).click();  //OK
		Thread.sleep(1000);
		driver.findElement(By.id("btnSave")).click();
		Thread.sleep(1000);
		//USE SOME CONDITION.
		
		WebElement yedi = driver.findElement(By.id("lblInspection"));
		  if(yedi.isDisplayed()); 
		  { 
		   System.out.println(yedi.getText()); 
		   Assert.assertTrue(true);
		driver.findElement(By.id("btnBack")).click();
		Thread.sleep(1000);
		  }
	}
@Test(priority=4)
	public void Delete() throws InterruptedException
	{
	driver.switchTo().defaultContent();
	driver.switchTo().frame("FrameCentre");
	driver.findElement(By.id("txtFor")).clear();
	Thread.sleep(500);
	driver.findElement(By.id("txtFor")).sendKeys("Annual AMAR Inspection");
	driver.findElement(By.id("btnFindNow")).click();
	Thread.sleep(1000);
	
	
	driver.findElement(By.xpath("//a[contains(text(),'Delete')]")).click();
	Thread.sleep(500);
	driver.findElement(By.id("MSGBoxCtrl_btnYes")).click();
	}

	@DataProvider(name="Inspection")   //DataProvider passes data to test scripts.Using DataProvider we can easily inject multiple values into the same test case.
	public String [][] getvendorData() throws IOException
	{
							String path="D:/Amar_TC/Master_TC/Data_Driven/DataDriven_Sheet.xlsx";
							XLUtility xlutil=new XLUtility(path); //To access methods from utility file.
							
							int totalrows=xlutil.getRowCount("InspectionKit3");
							int totalcols=xlutil.getCellCount("InspectionKit3",1);	
									
							String Test[][]=new String[totalrows][totalcols];
								
							
							for(int i=1;i<=totalrows;i++) //Represent the rows in excel sheet. In excel index in start from 0 and 0 is header part so we are starting from (i=1).
							{
								for(int j=0;j<totalcols;j++) //Represent the cells in each row.
								{
									Test[i-1][j]=xlutil.getCellData("InspectionKit3", i, j); ///storing login data to 2 dia array.
								}
									
							}
							return Test;
}
	
}
	
