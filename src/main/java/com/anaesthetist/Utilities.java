package com.anaesthetist;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.swing.text.Document;

import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import junit.framework.Assert;

public class Utilities{
	
	final static Logger LOGGER =  Logger.getLogger("Utilities");
        
	   static WebDriver wb;
	   JavascriptExecutor js;
	  
	   
	   String currentDirectory = System.getProperty("user.dir"); //gives current directory
	   
	 static Properties configurationProperties = new Properties();
	 String pfilepath = currentDirectory + "\\src\\main\\resources\\config.properties";
	   
	  
	   public void loadPropertyFile() {
	      
		   File file = new File(pfilepath);
		     try {
		    	 FileInputStream fi = new FileInputStream(file);
				configurationProperties.load(fi);
			
		     } catch (FileNotFoundException e) {
		    	 LOGGER.error("file not found", e);
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
	    	 
	    	 
	    	 
	     }
  
	   
       public void openBrowser() throws IOException {
    	
    	   	 
    	if(configurationProperties.getProperty("browser").matches("CHROME")) {
		String driverspath = currentDirectory +"\\src\\chromedriver.exe" ;
		System.setProperty("webdriver.chrome.driver",driverspath);
		wb = new ChromeDriver();
		
		
	  }
	
    	else if(configurationProperties.getProperty("browser").equalsIgnoreCase("Firefox")) {
        		
    			String currentDirectory = System.getProperty("user.dir"); //gives current directory
    			String driverspath = currentDirectory +"\\src\\geckodriver.exe" ;
    			System.setProperty("webdriver.gecko.driver",driverspath);
    			wb = new FirefoxDriver();
    		
    	}
 	
    		
  }

     public void openurl(){
       
       wb.get(configurationProperties.getProperty("Url"));
       
     }
       
     public void enterFirstNo(){
    	 
    	 
    	 wb.findElement(By.xpath(configurationProperties.getProperty("XpathNumber1"))).click();
    	 
    	 
     }
     
     public void enterSecondNo(){
    	 
    	 
    	 wb.findElement(By.xpath(configurationProperties.getProperty("XpathNumber2"))).click();
    	 
    	 
     } 
 
     
      public void submit() {
    	  
    	  js = (JavascriptExecutor)wb; 
    	  js.executeScript("document.querySelector(\"input[type='button']\").click()");
    	  
      }

	public void operation() {
		wb.findElement(By.xpath(configurationProperties.getProperty("Xpathoperation"))).click();
		
	}
     
     public void readAnswer()  {
    	 
       /*String value = wb.findElement(By.xpath(configurationProperties.getProperty("box"))).getText();
       //String  value = "jgjh";
        LOGGER.info("Output Value:-"+value);*/
    	 
    	 
		FileInputStream inputStream;
		String value = null;
		File file;
		WebElement outputBox = wb.findElement(By.xpath(configurationProperties.getProperty("box")));
		
		try {
			//Thread.sleep(5000);
			value = (String)js.executeScript("return arguments[0].value", outputBox);
			//value = js.executeScript("return document.getElementsByName('Display')[0].value").toString();
			LOGGER.info("Output Value:-"+value);
			//LOGGER.info("Output Value:-"+ob);
			
			file = new File("C:\\Users\\YELLOYOLK\\OneDrive\\SELENIUMEASY\\calculator\\WriteData.xlsx");
			inputStream = new FileInputStream(file);
			XSSFWorkbook wk = new XSSFWorkbook(inputStream);
			XSSFSheet sh = wk.getSheetAt(0);
			XSSFRow rw = sh.createRow(0);
			XSSFCell cl = rw.createCell(0);
			cl.setCellValue(value);
			FileOutputStream fileoput = new FileOutputStream(file);
			wk.write(fileoput);
			
			wk.close();
		} catch (Exception e) {
			LOGGER.error("Kuch to gadbad hai !", e);
			e.printStackTrace();
		}
		
		
        assert value.equalsIgnoreCase("12") : "Answer is different";

    	 
     }  
     
     
     public void waitForTime() {
    	 
    	 
    	 WebDriverWait wait = new WebDriverWait(wb, 10);
    	 wait.until(ExpectedConditions.textToBePresentInElementLocated(By.xpath(configurationProperties.getProperty("box")), ""));
    	 	 
    	 
     }
}