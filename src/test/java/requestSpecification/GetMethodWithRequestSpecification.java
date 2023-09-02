package requestSpecification;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

public class GetMethodWithRequestSpecification {

	public static RequestSpecification user_request_spec() {
		RequestSpecification requestBuilder = (RequestSpecification) new RequestSpecBuilder()
				.setBaseUri("https://gorest.co.in")
				.addHeader("Authorizaration", "Bearer e5bdeeaf245ab5554713f0a7d4c602d44feb6b20a37586cf40b141a8ae7e74ad")
				.setContentType(ContentType.JSON).build();
		return requestBuilder;
	}

	@Test()
	public void getAPIwithRequestSpec() {
		RestAssured.given().spec(user_request_spec()).get("/public/v2/users").then().statusCode(200);
	}

	@Test
	public void getTest() {

		RestAssured.given().spec(user_request_spec()).queryParam("gender", "male").queryParam("status", "active")
				.get("/public/v2/users").then().statusCode(200);
	}

}
