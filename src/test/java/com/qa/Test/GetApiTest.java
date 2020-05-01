package com.qa.Test;
import java.io.IOException;
import org.apache.http.client.ClientProtocolException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.qa.Base.TestBase;
import com.qa.client.RestClient;

public class GetApiTest extends TestBase{
	
	//Shakir
	
	TestBase testBase;
	RestClient restClient;
	String serviceurl;
	String apiUrl;
	String url;
	
	@BeforeMethod
	public void setUP(){
		testBase = new TestBase();
		serviceurl = proper.getProperty("URL");
		apiUrl = proper.getProperty("serviceURL");
		url = serviceurl+apiUrl;
		
		
	}
	
	@Test
	public void getTest() throws ClientProtocolException, IOException {
		restClient = new RestClient();
		restClient.get(url);
	}
	
	@AfterMethod
	public void closeDown() {
		
	}

}
