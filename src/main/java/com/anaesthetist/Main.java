package com.anaesthetist;

import java.io.IOException;
import org.apache.log4j.Logger;


public class Main {
    
	final static Logger LOGGER =  Logger.getLogger("Main");
	
	/*singleton class: The constructor Logger is protected, 
	so get logger method returns the object for the same.*/
	
	public static void main(String[] args) throws IOException {
		
		LOGGER.info("Start of Test");
		
		Utilities uti = new Utilities();
		uti.loadPropertyFile();
		uti.openBrowser();
		uti.openurl();
		uti.enterFirstNo();
		uti.operation();
		uti.enterSecondNo();
//		uti.waitForTime();

		uti.submit();
		uti.waitForTime();

		uti.readAnswer();
		
		
		
		
		
		

	}

}
