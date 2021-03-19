package com.SpiceJetTest;

import java.util.List;
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
import io.github.bonigarcia.wdm.WebDriverManager;
public class AppTest
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
    	ReadExcelFile config = new ReadExcelFile("/home/ubuntu/Documents/oneway.xlsx");
    	int rows = config.getRowCount(0);
    	
    	Object[][] oneway = new Object[rows][5];
    	for(int i=0; i<rows;i++)
    	{
    		oneway[i][0]= config.getData(0, i, 0);
    		oneway[i][1]= config.getData(0, i, 1);
    		oneway[i][2]= config.getData(0,i,2);
    		oneway[i][3]= config.getData(0,i,3);
    		oneway[i][4]= config.getData(0,i,4);
    		
    	}
    	return oneway;
    }
    @Test(dataProvider="testData")
    public void verifyLoginTest01(String[] data)
    {
    	System.out.println("In verify test case method");
    	WebElement flightArrival;
    	WebElement flightDestination;
    	//Radio-one way
    	wd.findElement(By.id("ctl00_mainContent_rbtnl_Trip_0")).click();;
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
		
		
		//WebElement btnSearch=wd.findElement(By.className("ctl00$mainContent$ButtonSubmit_MST"));
		//btnSearch.click();
    	wd.findElement(By.id("divpaxinfo")).click();
	//	wd.findElement(By.id("divAdult")).click();
    	Select AdultDropdown = new Select(wd.findElement(By.id("ctl00_mainContent_ddl_Adult")));
		//int no= Integer.parseInt(data[3]) ;
		AdultDropdown.selectByValue(data[3].toString());
		
        Select CurrencyDropdown = new Select(wd.findElement(By.id("ctl00_mainContent_DropDownListCurrency")));
		
		CurrencyDropdown.selectByValue(data[4]);
		
		
		wd.findElement(By.id("ctl00_mainContent_btn_FindFlights")).click();
    	wd.quit();
	
    }
}