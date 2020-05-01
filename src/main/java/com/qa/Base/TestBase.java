package com.qa.Base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

//Shakir

public class TestBase {
	
	public Properties proper;
	
	public TestBase() {
		try {
			proper = new Properties();
			FileInputStream fi = new FileInputStream("/Users/shakir/eclipse-workspace2/RestApi/src/main/java/com/qa/config/config.properties");
			proper.load(fi);	
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}	
	}

}
