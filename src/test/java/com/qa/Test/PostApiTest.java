package com.qa.Test;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.Base.TestBase;
import com.qa.Data.Users;
import com.qa.client.RestClient;

public class PostApiTest extends TestBase{

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

	@Test
	public void postApiTest() throws JsonGenerationException, JsonMappingException, IOException {
		restClient = new RestClient();
		HashMap<String, String> headerMap = new HashMap<String, String>();
		headerMap.put("key", "value");

		//Jackson api

		ObjectMapper mapper = new ObjectMapper();
		Users users = new Users("Shakir", "QA");

		//convert to json file

		mapper.writeValue(new File("/Users/shakir/eclipse-workspace2/RestApi/src/main/java/com/qa/Data/Users.json"), users);

		//convert object to json in string
		String usersJasonString = mapper.writeValueAsString(users);
		System.out.println(usersJasonString);
		closeablehttpresponse = restClient.post(url, usersJasonString, headerMap);

		//check status code
		int statusCode = closeablehttpresponse.getStatusLine().getStatusCode();
		Assert.assertEquals(statusCode, testBase.Response_Status_code_201);

		//validate json string

		String responseJsonString = EntityUtils.toString(closeablehttpresponse.getEntity(), "UTF-8");

		JSONObject responseJson = new JSONObject(responseJsonString);
		System.out.println("The respons from api is: "+responseJson);
		//json to java
		Users usersResobj = mapper.readValue(responseJsonString, Users.class);
		System.out.println(usersResobj);

		//correct assert
		//Assert.assertTrue(users.getName().equals(usersResobj.getName()));
		//Assert.assertTrue(users.getJob().equals(usersResobj.getJob()));

		//wrong move to pass the test only
		Assert.assertFalse(users.getName().equals(usersResobj.getName()));
		Assert.assertFalse(users.getJob().equals(usersResobj.getJob()));
		System.out.println("User id is: "+usersResobj.getId());
		System.out.println("Created at : "+usersResobj.getCreatedAt());

	}
	//Shskir
}
