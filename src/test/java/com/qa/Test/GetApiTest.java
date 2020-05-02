package com.qa.Test;
import java.io.IOException;
import java.util.HashMap;
import org.apache.http.Header;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.json.Property;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.qa.Base.TestBase;
import com.qa.Util.TestUtil;
import com.qa.client.RestClient;

public class GetApiTest extends TestBase{
	
	//Shakir
	
	TestBase testBase;
	RestClient restClient;
	String serviceurl;
	String apiUrl;
	String url;
	CloseableHttpResponse closeablehttpresponse;
	
	@BeforeMethod
	public void setUP(){
		testBase = new TestBase();
		serviceurl = proper.getProperty("URL");
		apiUrl = proper.getProperty("serviceURL");
		url = serviceurl+apiUrl;
	}
	
	@Test(priority = 1)
	public void getApiTestWithOutHeader() throws ClientProtocolException, IOException {
		restClient = new RestClient();
		closeablehttpresponse = restClient.get(url);
		
		//Get status code:
		int statusCode = closeablehttpresponse.getStatusLine().getStatusCode();
		System.out.println("The status code is ---->"+statusCode);
		
		Assert.assertEquals(statusCode, Response_Status_code_200, "Status code doesn't match with actual.....");
		String responseString = EntityUtils.toString(closeablehttpresponse.getEntity(), "UTF-8");
		
		//get json string:
		JSONObject responseJson = new JSONObject(responseString);
		System.out.println("responseJson from api --->"+responseJson);
		
		//per page value
		String perPageValue = TestUtil.getValueByJPath(responseJson, "/per_page");
		System.out.println("Value of per page----> " + perPageValue);
		Assert.assertEquals(perPageValue,"6", "Page value doesnot match");
		
		//total int value after converting string to interger via wrapper class
		String totalValue = TestUtil.getValueByJPath(responseJson, "/total");
		System.out.println("Value of total---> " + totalValue);
		Assert.assertEquals(Integer.parseInt(totalValue),12, "total value doesnot match");
		
		//get the value for Json array
		
		String lastName = TestUtil.getValueByJPath(responseJson, "/data[0]/last_name");
		String id = TestUtil.getValueByJPath(responseJson, "/data[0]/id");
		String avatar = TestUtil.getValueByJPath(responseJson, "/data[0]/avatar");
		String first_name = TestUtil.getValueByJPath(responseJson, "/data[0]/first_name");
		String email = TestUtil.getValueByJPath(responseJson, "/data[0]/email");
		
		System.out.println(lastName);
		System.out.println(id);
		System.out.println(avatar);
		System.out.println(first_name);
		System.out.println(email);
		
		Assert.assertEquals(lastName, "Bluth");
		
		//get All headers Array
		Header[] headerArray =  closeablehttpresponse.getAllHeaders();
		HashMap<String, String> allHeaders = new HashMap<String, String>();
		
		for(Header header: headerArray) {
		allHeaders.put(header.getName(), header.getValue());
		System.out.println("Headers array---->"+allHeaders);
	}
		System.out.println("******************* End of the Test *******************\n");
	}
	
	@Test(priority = 2)
	public void getApiTesWithHeader() throws ClientProtocolException, IOException {
		restClient = new RestClient();
		
		HashMap<String, String> headerMap = new HashMap<String, String>();
		headerMap.put("key", "value");
		headerMap.put("username", "Test");
		headerMap.put("password", "Test123");
		headerMap.put("Auth token", "12345");
		closeablehttpresponse = restClient.get(url, headerMap);
		
		//Get status code:
		int statusCode = closeablehttpresponse.getStatusLine().getStatusCode();
		System.out.println("The status code is ---->"+statusCode);
		
		Assert.assertEquals(statusCode, Response_Status_code_200, "Status code doesn't match with actual.....");
		String responseString = EntityUtils.toString(closeablehttpresponse.getEntity(), "UTF-8");
		
		//get json string:
		JSONObject responseJson = new JSONObject(responseString);
		System.out.println("responseJson from api --->"+responseJson);
		
		//per page value
		String perPageValue = TestUtil.getValueByJPath(responseJson, "/per_page");
		System.out.println("Value of per page----> " + perPageValue);
		Assert.assertEquals(perPageValue,"6", "Page value doesnot match");
		
		//total int value after converting string to interger via wrapper class
		String totalValue = TestUtil.getValueByJPath(responseJson, "/total");
		System.out.println("Value of total---> " + totalValue);
		Assert.assertEquals(Integer.parseInt(totalValue),12, "total value doesnot match");
		
		//get the value for json array
		
		String lastName = TestUtil.getValueByJPath(responseJson, "/data[0]/last_name");
		String id = TestUtil.getValueByJPath(responseJson, "/data[0]/id");
		String avatar = TestUtil.getValueByJPath(responseJson, "/data[0]/avatar");
		String first_name = TestUtil.getValueByJPath(responseJson, "/data[0]/first_name");
		String email = TestUtil.getValueByJPath(responseJson, "/data[0]/email");
		
		System.out.println(lastName);
		System.out.println(id);
		System.out.println(avatar);
		System.out.println(first_name);
		System.out.println(email);
		
		Assert.assertEquals(lastName, "Bluth");
		
		//get All headers Array
		Header[] headerArray =  closeablehttpresponse.getAllHeaders();
		HashMap<String, String> allHeaders = new HashMap<String, String>();
		
		for(Header header: headerArray) {
		allHeaders.put(header.getName(), header.getValue());
		System.out.println("Headers array---->"+allHeaders);
	}
		System.out.println("******************* End of the Test *******************\n");
	}
	
	@AfterMethod
	public void tearDown() {
		
	}

}
