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

public class Multicity 
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
    	ReadExcelFile config = new ReadExcelFile("/home/ubuntu/Documents/multicity.xlsx");
    	int rows = config.getRowCount(0);
    	
    	Object[][] multicity = new Object[rows][5];
    	for(int i=0; i<rows;i++)
    	{
    		multicity[i][0]= config.getData(0, i, 0);
    		multicity[i][1]= config.getData(0, i, 1);
    		multicity[i][2]= config.getData(0,i,2);
    		multicity[i][3]= config.getData(0,i,3);
    		multicity[i][4]= config.getData(0,i,4);
    		
    	}
    	return multicity;
    }
    
    @Test(dataProvider="testData")
    public void verifyLoginTest01(String[] data) throws InterruptedException
    {
    	System.out.println("In verify test case method");
    	WebElement flightArrival1;
    	WebElement flightDestination1;
    	
    	WebElement flightArrival2;
    	WebElement flightDestination2;
    	
    	WebElement flightArrival3;
    	WebElement flightDestination3;
    	
    	WebElement flightArrival4;
    	WebElement flightDestination4;
    	//Radio-one way
    	wd.findElement(By.id("ctl00_mainContent_rbtnl_Trip_2")).click();;
    	wd.manage().timeouts().implicitlyWait(7000, TimeUnit.MILLISECONDS);

    	wd.findElement(By.id("MultiCityModelAlert")).click();;
    	wd.manage().timeouts().implicitlyWait(7000, TimeUnit.MILLISECONDS);


    	//Arrival
    	flightArrival1=wd.findElement(By.id("ctl00_mainContent_ddl_originStation1_CTXT"));
        flightArrival1.sendKeys(data[0]);
    	   
    	 //Destination
         wd.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
         wd.findElement(By.id("ctl00_mainContent_ddl_destinationStation1_CTXT")).clear();
         
    	flightDestination1=wd.findElement(By.id("ctl00_mainContent_ddl_destinationStation1_CTXT"));
    	flightDestination1.sendKeys(data[1]);
    	wd.findElement(By.linkText("Mumbai (BOM)")).click();
    	
    	//Date
		
		  WebElement DateWidget = wd.findElement(By.id("ui-datepicker-div"));
		  List<WebElement> columns = DateWidget.findElements(By.tagName("td"));
		  for(WebElement cell: columns) 
		  {
			  if (cell.getText().equals(data[2]))
			  {
				  cell.findElement(By.linkText(data[2])).click();
				  break;
			  }
		  }
		  
	    	wd.findElement(By.id("divpaxinfo")).click();
	    	
	    	
	    	//Passenger Drop down
	    	Select AdultDropdown = new Select(wd.findElement(By.id("ctl00_mainContent_ddl_Adult")));
	    	AdultDropdown.selectByValue(data[3].toString());
	    	
			//Currency Drop down
	        Select CurrencyDropdown = new Select(wd.findElement(By.id("ctl00_mainContent_DropDownListCurrency")));
			CurrencyDropdown.selectByValue(data[4]);
			
	    	wd.manage().timeouts().implicitlyWait(7000, TimeUnit.MILLISECONDS);
	    	Thread.sleep(7000);
	    	
	    	//Arrival
	    	flightArrival2=wd.findElement(By.id("ctl00_mainContent_ddl_originStation2_CTXT"));
	        flightArrival2.sendKeys(data[0]);
	    	   
	    	 //Destination
	         wd.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
	         wd.findElement(By.id("ctl00_mainContent_ddl_destinationStation2_CTXT")).clear();
	         
	    	flightDestination2=wd.findElement(By.id("ctl00_mainContent_ddl_destinationStation2_CTXT"));
	    	flightDestination2.sendKeys(data[1]);
	    	wd.findElement(By.linkText("Mumbai (BOM)")).click();
	    	
	    	Thread.sleep(7000);
	    	
	    	//Arrival
	    	flightArrival3=wd.findElement(By.id("ctl00_mainContent_ddl_originStation3_CTXT"));
	        flightArrival3.sendKeys(data[0]);
	    	   
	    	 //Destination
	         wd.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
	         wd.findElement(By.id("ctl00_mainContent_ddl_destinationStation3_CTXT")).clear();
	         
	    	flightDestination3=wd.findElement(By.id("ctl00_mainContent_ddl_destinationStation3_CTXT"));
	    	flightDestination3.sendKeys(data[1]);
	    	wd.findElement(By.linkText("Mumbai (BOM)")).click();
	    	
	    	//Date
			
			  WebElement DateWidget2 = wd.findElement(By.id("ui-datepicker-div"));
			  List<WebElement> columns2 = DateWidget2.findElements(By.tagName("td"));
			  for(WebElement cell2: columns2) 
			  {
				  if (cell2.getText().equals("20"))
				  {
					  cell2.findElement(By.linkText("20")).click(); 
					  break;
				  }
			  }
			  
		    	wd.findElement(By.id("divpaxinfo")).click();
		    	
	    	wd.manage().timeouts().implicitlyWait(7000, TimeUnit.MILLISECONDS);
	    	
	    	Thread.sleep(7000);     
	    	
	    	
			//Add Button
			wd.findElement(By.xpath("//*[@id=\"btnAddMore1\"]")).click();
			Thread.sleep(7000);     
	    	
	    	//Arrival
	    	flightArrival4=wd.findElement(By.id("ctl00_mainContent_ddl_originStation4_CTXT"));
	        flightArrival4.sendKeys(data[0]);
	    	   
	    	 //Destination
	         wd.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
	         wd.findElement(By.id("ctl00_mainContent_ddl_destinationStation4_CTXT")).clear();
	         
	    	flightDestination4=wd.findElement(By.id("ctl00_mainContent_ddl_destinationStation4_CTXT"));
	    	flightDestination4.sendKeys(data[1]);
	    	wd.findElement(By.linkText("Mumbai (BOM)")).click();

	    	//Date
			
			  WebElement DateWidget1 = wd.findElement(By.id("ui-datepicker-div"));
			  List<WebElement> columns1 = DateWidget1.findElements(By.tagName("td")); 
			  for(WebElement cell1: columns1) 
			  {
				  if (cell1.getText().equals("25"))
				  {
					  cell1.findElement(By.linkText("25")).click(); 
					  break;
				  }
			  }
			  
		    	wd.findElement(By.id("divpaxinfo")).click();
		    	
		    	wd.manage().timeouts().implicitlyWait(7000, TimeUnit.MILLISECONDS);
		    	Thread.sleep(7000);
		    
		    //Button Remove
	    	wd.findElement(By.xpath("//*[@id=\"btnRemove2\"]")).click();
	    	Thread.sleep(7000);

	    	wd.manage().timeouts().implicitlyWait(7000, TimeUnit.MILLISECONDS);

			//Search Button
			wd.findElement(By.id("ctl00_mainContent_btn_FindFlights")).click();
	    	wd.quit();
	    	Thread.sleep(7000);
    	
    }
}
