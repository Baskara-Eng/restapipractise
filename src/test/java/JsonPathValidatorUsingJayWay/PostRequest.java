package JsonPathValidatorUsingJayWay;

import java.util.List;

import org.testng.annotations.Test;

import com.jayway.jsonpath.JsonPath;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;


public class PostRequest {
	
	public static RequestSpecification requestSpec() {
		RequestSpecification reqSpec = new RequestSpecBuilder()
				.setBaseUri("https://test.api.amadeus.com/")
				.addHeader("Content-Type", "application/x-www-form-urlencoded")
				.addFormParam("grant_type", "client_credentials")
				.addFormParam("client_id", "VUjQGnOnKoqbG7SilRM0gZDGvdJFd0IP")
				.addFormParam("client_secret", "iL81Re0VRTITAPQe").build();
		return reqSpec;
		
				
	}
	
	@Test
	public void verifyJayWay_Response_Test() {
		String accessToken=RestAssured.given()
		.spec(requestSpec())
		.when()
		.post("v1/security/oauth2/token")
		.then()
		.extract()
		.path("access_token");
		
		System.out.println(accessToken);
	
		Response response=RestAssured.given()
		.baseUri("https://test.api.amadeus.com/")
		.contentType(ContentType.JSON)
		.header("Authorization","Bearer "+accessToken)
		.when()
		.get("v1/shopping/flight-destinations?origin=PAR&maxPrice=200");
		
		String jsonRes = response.asString();
		List<String> flightOffersRes=JsonPath.read(jsonRes,"$..links.flightOffers");
		System.out.println(flightOffersRes.size());
		System.out.println(flightOffersRes);
		
		List<Object> locations = JsonPath.read(jsonRes,"$..locations");
		System.out.println(locations);
		
		
	}

}
