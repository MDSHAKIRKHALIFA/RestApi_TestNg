package com.qa.client;
import java.io.IOException;
import java.util.HashMap;
import org.apache.http.Header;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

public class RestClient {
	
	//Shakir
	
	//1.Get Mathod
	
	public void get(String url) throws ClientProtocolException, IOException {
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpGet httpget = new HttpGet(url); //http get requerest
		CloseableHttpResponse closeablehttpresponse = httpClient.execute(httpget); //hit the get url
		
		//Get status code:
		int statusCode = closeablehttpresponse.getStatusLine().getStatusCode();
		System.out.println("The status code is ---->"+statusCode);
		
		String responseString = EntityUtils.toString(closeablehttpresponse.getEntity(), "UTF-8");
		
		//get json string:
		JSONObject responseJson = new JSONObject(responseString);
		System.out.println("responseJson from api --->"+responseJson);
		
		//get All headers Array
		Header[] headerArray =  closeablehttpresponse.getAllHeaders();
		HashMap<String, String> allHeaders = new HashMap<String, String>();
		
		for(Header header: headerArray) {
		allHeaders.put(header.getName(), header.getValue());
		
		System.out.println("Headers array---->"+allHeaders);
	}
	}
}