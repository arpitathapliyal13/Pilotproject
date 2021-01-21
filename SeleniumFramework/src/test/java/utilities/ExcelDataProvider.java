package utilities;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class ExcelDataProvider {
	
	
	WebDriver driver= null;
	@BeforeTest
	public void setUpTest()
	{
		String projectPath= System.getProperty("user.dir");

		System.setProperty("webdriver.chrome.driver",projectPath+ "/Driverrss/chromedriver/chromedriver.exe");

	    driver = new ChromeDriver();
	
}

	@Test(dataProvider="test1data")
	public void test1(String username, String password) throws Exception {
		System.out.println(username +"  |  "+ password);
		
		
		driver.get("https://www.facebook.com/");
		driver.findElement(By.id("email")).sendKeys(username);
		driver.findElement(By.id("pass")).sendKeys(password);
	    Thread.sleep(3000);
	}
	
	@DataProvider(name = "test1data")
	public static Object[][] getData()
	{
		String excelPath = "C:\\Users\\shiva\\eclipse-workspace\\SeleniumFramework\\excel\\New XLSX Worksheet.XLSX";
		Object data[][] = testData(excelPath ,"Sheet1");
		return data;
	}
	
	public static  Object[][] testData(String excelPath, String sheetName) 
	{
		
		ExcelUtils excel = new ExcelUtils(excelPath, sheetName);
	    
		int rowCount = excel.getRowCount();
		int colCount = excel.getColCount();
		
		Object data[][]= new Object[rowCount-1][colCount];
		
		for(int i=1; i<rowCount; i++)
		{
			for(int j=0; j<colCount; j++)
			{
				String cellData = excel.getCellDataString(i,j);
				//System.out.print(cellData+ " | ");
				data[i-1][j] = cellData;
			}
			System.out.println();
		}
		
		return data;
	}

}
