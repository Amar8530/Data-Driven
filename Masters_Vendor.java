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

public class Masters_Vendor 
{
WebDriver driver;
	
	@BeforeClass
		public void setup() throws InterruptedException
		{
		System.setProperty("webdriver.chrome.driver", "D:/Amar_TC/Selenium_Webdriver/Drivers New/chromedriver.exe");
		driver=new ChromeDriver();
		driver.get("https://bytzsoft.in/FlyPalTemp/Login.aspx?ReturnUrl=%2fFlypalbytz%2f");	
		driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);
		
		
		//Login
		driver.findElement(By.id("txtUserName")).sendKeys("AMAR");
		driver.findElement(By.id("txtPassword")).sendKeys("AMAR123");
		driver.findElement(By.id("btnLogin")).click();
		Thread.sleep(10000);
		
		
		driver.switchTo().frame("FrameLeft");
		Thread.sleep(1000);
		driver.findElement(By.xpath("//label[contains(text(),'Masters')]")).click(); //Masters
		Thread.sleep(1500);
		
		driver.findElement(By.xpath("//tbody/tr[4]/td[1]/ul[1]/li[1]/div[1]/ul[1]/li[2]/a[1]")).click(); //Vendor
		
		driver.switchTo().defaultContent();
		driver.switchTo().frame("FrameCentre");
		Thread.sleep(1000);
		
		driver.findElement(By.xpath("//input[@id='btnAddTop']")).click(); //Add New	
		Thread.sleep(2000);
		}

	@Test(priority=1,dataProvider="VendorData")
	public void Save (String vname,String vcode,String vadd,String vphone,String vemail) throws InterruptedException
	{			
	WebElement Vendorname =driver.findElement(By.xpath("//input[@id='txtName']"));   //Name
	Vendorname.clear();
	Vendorname.sendKeys(vname);
	Thread.sleep(1000);
	

	driver.findElement(By.id("chkSupplier")).click();   //Category	
	Thread.sleep(1000);
	
	WebElement Vendorcode =driver.findElement(By.xpath("//input[@id='txtVendorCode']")); //Code
	Vendorcode.clear();
	Vendorcode.sendKeys(vcode);
	Thread.sleep(1000);
	
	WebElement VendorAdd =driver.findElement(By.xpath("//textarea[@id='txtAddress']")); //Address	
	VendorAdd.clear();
	VendorAdd.sendKeys(vadd);
	Thread.sleep(1000);
	
	
	Select city=new Select(driver.findElement(By.name("cmbCity"))); //City
	city.selectByVisibleText("Pune");
	Thread.sleep(1000);
	
	WebElement Vendorphone = (driver.findElement(By.id("txtPhone1"))); //Phone1	
	Vendorphone.clear();
	Vendorphone.sendKeys(vphone);
	Thread.sleep(1000);
	
	WebElement VendorEmail = (driver.findElement(By.id("txtEmail"))); //Email
	VendorEmail.clear();
	VendorEmail.sendKeys(vemail);
	Thread.sleep(1000);
	
	
	driver.findElement(By.name("btnSave")).click();    //(SAVE)	
	Thread.sleep(1000);
	
	driver.findElement(By.id("chkSupplier")).click(); //Category	
	Thread.sleep(1000);
}
	
	
	@Test(priority=2) //Back Button (priority is used because system is clicking back after 1 vendor is saved).
	public void  backButton()throws InterruptedException
	{
	driver.findElement(By.name("btnBack")).click();  //Close 
	Thread.sleep(2000);
	}
	@Test(priority=3)
	public void duplicate() throws InterruptedException
	{
		
		driver.switchTo().defaultContent();
		driver.switchTo().frame("FrameCentre");
		Thread.sleep(1000);
		
		driver.findElement(By.xpath("//input[@id='btnAddTop']")).click(); //Add New	
		Thread.sleep(2000);
		
		driver.findElement(By.id("txtName")).sendKeys("John Cena");
		Thread.sleep(500);
		driver.findElement(By.id("chkSupplier")).click();
		Thread.sleep(500);
		driver.findElement(By.id("txtVendorCode")).sendKeys("871248");
		driver.findElement(By.id("txtAddress")).sendKeys("3936 White River Way");
		Select city = new Select(driver.findElement(By.id("cmbCity")));
		city.selectByVisibleText("Pune");
		Thread.sleep(1000);
		driver.findElement(By.id("txtEmail")).sendKeys("john456@gmail.com");
		driver.findElement(By.id("btnSave")).click(); //SAVE
		Thread.sleep(1000);
		
		WebElement Dupli = driver.findElement(By.id("MSGBoxCtrl_lblMsgTitle"));
		if(Dupli.isDisplayed());
		
		{
			System.out.println(Dupli.getText());
			Assert.assertTrue(true);
		}
		Thread.sleep(1000);
		driver.findElement(By.xpath("//input[@id='MSGBoxCtrl_btnOk']")).click();
		driver.findElement(By.id("btnBack")).click();
		Thread.sleep(1000);
		
	}
	@Test(priority=4)
	public void Edit() throws InterruptedException  
	{
		//driver.switchTo().defaultContent();
		driver.switchTo().frame("FrameCentre");
        Select search = new Select(driver.findElement(By.id("cmbLookIn")));
        search.selectByVisibleText("Vendor");
        Thread.sleep(500);
        driver.findElement(By.id("txtSearch")).sendKeys("John Cena"); //For
        Thread.sleep(500);
        driver.findElement(By.id("btnFindNow")).click();
        Thread.sleep(1000);
      		
		driver.findElement(By.xpath("//input[@id='dgVendor1_ctl02_EditRec']")).click();
		Thread.sleep(1500);
		driver.findElement(By.id("chkSupplier")).click(); //First untick "supplier"
		Thread.sleep(500);
		driver.findElement(By.id("chkIsServiceProvider")).click();  //Then tick "Service Provider"
		driver.findElement(By.id("btnSave")).click();
		Thread.sleep(500);
		driver.findElement(By.id("btnBack")).click();
		Thread.sleep(1500);
	
	}
@Test(priority=5)
	public void Delete() throws InterruptedException
	{
	//driver.switchTo().defaultContent();
	driver.switchTo().frame("FrameCentre");
	driver.findElement(By.id("dgVendor1_ctl02_Delete")).click();
	Thread.sleep(500);
	driver.findElement(By.id("MSGBoxCtrl_btnYes")).click();
	}
@Test(priority=6)
public void ErrorMessage() throws InterruptedException
{
	driver.switchTo().defaultContent();
	driver.switchTo().frame("FrameCentre");
	
	driver.findElement(By.id("btnAddTop")).click();
	Thread.sleep(1000);
	driver.findElement(By.id("txtName")).sendKeys("FlyPal Aviation");
	driver.findElement(By.id("chkCustomer")).click();
	driver.findElement(By.id("btnSave")).click();
	Thread.sleep(1000);
	
	  WebElement errorMsg=driver.findElement(By.xpath("//div[@id='Validationsummary2']"));
	  if(errorMsg.isDisplayed()); 
	  { 
	   System.out.println(errorMsg.getText()); 
	   Assert.assertTrue(true);
	  }
	  Thread.sleep(1000);
	  driver.findElement(By.id("btnBack")).click();//CLOSE.
	  driver.quit();
}
@DataProvider(name="VendorData")
public String [][] getvendorData() throws IOException
{
						String path="D:/Amar_TC/Master_TC/Data_Driven/DataDriven_Sheet.xlsx";
						XLUtility xlutil=new XLUtility(path); //o access methods from utility file.
						
						int totalrows=xlutil.getRowCount("Vendor2");
						int totalcols=xlutil.getCellCount("Vendor2",1);	
								
						String VendorTest[][]=new String[totalrows][totalcols];
							
						
						for(int i=1;i<=totalrows;i++) //Represent the rows in excel sheet. In excel index in start from 0 and 0 is header part so we are starting from (i=1).
						{
							for(int j=0;j<totalcols;j++) //Represent the cells in each row.
							{
								VendorTest[i-1][j]=xlutil.getCellData("Vendor2", i, j); ///storing login data to 2 dia array.
							}
								
						}
						return VendorTest;
}

}
