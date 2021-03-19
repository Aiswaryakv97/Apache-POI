package com.SpiceJetTest;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class RoundTrip
{
    static WebDriver wd;
    @BeforeClass
	public static void init()
	{
    	WebDriverManager.chromedriver().setup();
		//System.setProperty("webdriver.chrome.driver","src/main/resources/chromedriver.exe");
		wd = new ChromeDriver();
		wd.manage().window().maximize();

		wd.get("https://www.spicejet.com/");
	}
    
    @DataProvider(name="testData")
    public Object[][] TestDataFeed()
    {
    	ReadExcelFile config = new ReadExcelFile("/home/ubuntu/Documents/roundtrip.xlsx");
    	int rows = config.getRowCount(0);
    	
    	Object[][] twoway = new Object[rows][5];
    	for(int i=0; i<rows;i++)
    	{
    		twoway[i][0]= config.getData(0, i, 0);
    		twoway[i][1]= config.getData(0, i, 1);
    		twoway[i][2]= config.getData(0,i,2);
    		twoway[i][3]= config.getData(0,i,3);
    		twoway[i][4]= config.getData(0,i,4);

    		
    	}
    	return twoway;
    }
    
    @Test(dataProvider="testData")
    public void verifyLoginTest01(String[] data) throws InterruptedException
    {
    	System.out.println("In verify test case method");
    	WebElement flightArrival;
    	WebElement flightDestination;
    	//Radio-two way
    	wd.findElement(By.id("ctl00_mainContent_rbtnl_Trip_1")).click();;
    	
    	//Arrival
    	flightArrival=wd.findElement(By.id("ctl00_mainContent_ddl_originStation1_CTXT"));
       flightArrival.sendKeys(data[0]);
    	   
    	 //Destination
         wd.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
         
         wd.findElement(By.id("ctl00_mainContent_ddl_destinationStation1_CTXT")).clear();
     	flightDestination=wd.findElement(By.id("ctl00_mainContent_ddl_destinationStation1_CTXT"));
     	flightDestination.sendKeys(data[1]);
     	wd.findElement(By.linkText("Mumbai (BOM)")).click();
     	
     	//Date
		
		  WebElement DateWidget = wd.findElement(By.id("ui-datepicker-div"));
		  List<WebElement> columns = DateWidget.findElements(By.tagName("td")); for
		  (WebElement cell: columns) { if (cell.getText().equals(data[2])) {
		  cell.findElement(By.linkText(data[2])).click(); break; } }
		  
	    	wd.findElement(By.id("divpaxinfo")).click();
	    	
	    	   
	    	   	
	    	  	    	
	    	Select AdultDropdown = new Select(wd.findElement(By.id("ctl00_mainContent_ddl_Adult")));
	    	
	    	AdultDropdown.selectByValue(data[3].toString());
			
	        Select CurrencyDropdown = new Select(wd.findElement(By.id("ctl00_mainContent_DropDownListCurrency")));
			
			CurrencyDropdown.selectByValue(data[4]);
			
			
			wd.findElement(By.id("ctl00_mainContent_btn_FindFlights")).click();
	    	wd.quit();
		
	    
     	
     	
    }
}