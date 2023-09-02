package GETAPIRequest;

import java.util.HashMap;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class GETApi {
	RequestSpecification request;
	
	@BeforeTest
	public void setUP() {
		RestAssured.baseURI = "https://gorest.co.in";
		request = RestAssured.given();
		request.header("Authorization", "Bearer e5bdeeaf245ab5554713f0a7d4c602d44feb6b20a37586cf40b141a8ae7e74ad");
	}

	@Test
	public void getAPITest() {

		RestAssured.baseURI = "https://gorest.co.in";
		RequestSpecification request = RestAssured.given();
		request.header("Authorization", "Bearer e5bdeeaf245ab5554713f0a7d4c602d44feb6b20a37586cf40b141a8ae7e74ad");
		Response response = request.get("/public/v2/users");
		int statusCode = response.statusCode();
		// printing body
//		response.prettyPrint();
//
//		// verifying status code
//		Assert.assertEquals(statusCode, 200);
//		;
//
//		// getting single header
//		String contentType = response.header("Content-Type");
//		System.out.println("Content Type header is: " + contentType);
//
//		// getting all headers data
//		List<Header> allHeadersData = response.headers().asList();
//		for (Header h : allHeadersData) {
//			System.out.println(h.getName() + " " + h.getValue());
//		}

	}
	
	@Test
	public void getAPITESTWithQueryParameters() {
		RestAssured.baseURI = "https://gorest.co.in";
		RequestSpecification request = RestAssured.given();
		request.queryParam("gender", "male");
		request.queryParam("status", "active");
		request.header("Authorization", "Bearer e5bdeeaf245ab5554713f0a7d4c602d44feb6b20a37586cf40b141a8ae7e74ad");
		Response response = request.get("/public/v2/users");
		response.prettyPrint();
	}
	
	@Test
	public void getAPIWithQueryParameterMap() {
		RestAssured.baseURI = "https://gorest.co.in";
		RequestSpecification request = RestAssured.given();
		HashMap<String, String> queryParamsMap = new HashMap<String,String>();
		queryParamsMap.put("gender","male");
		queryParamsMap.put("status","inactive");
		request.queryParams(queryParamsMap);
		request.header("Authorization", "Bearer e5bdeeaf245ab5554713f0a7d4c602d44feb6b20a37586cf40b141a8ae7e74ad");
		Response response = request.get("/public/v2/users");
		response.prettyPrint();
	}

}
