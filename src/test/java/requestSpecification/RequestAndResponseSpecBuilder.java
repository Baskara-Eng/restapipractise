package requestSpecification;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import static org.hamcrest.Matchers.*;

public class RequestAndResponseSpecBuilder {
	
	public static RequestSpecification requestSpecBuilder() {
		RequestSpecification reqSpec = new RequestSpecBuilder()
		.setBaseUri("https://gorest.co.in")
		.addHeader("Authorization","e5bdeeaf245ab5554713f0a7d4c602d44feb6b20a37586cf40b141a8ae7e74ad")
		.build();
		return reqSpec;
	}
	
	
	
	public static ResponseSpecification responseSpecBuilder() {
		ResponseSpecification resSpec = new ResponseSpecBuilder()
		.expectContentType(ContentType.JSON)
		.expectStatusCode(200)
		.build();
		
		return resSpec;
	}
	
	public static ResponseSpecification responseBodyVerification() {
		ResponseSpecification responseBodyVerification = new ResponseSpecBuilder()
		.expectContentType(ContentType.JSON)
		.expectStatusCode(200)
		.expectBody("$.size()",equalTo(10))
		.expectBody("id",hasSize(10))
		.build();
		
		return responseBodyVerification;
	}
	
	@Test
	public void verifyReqResSpecTest() {
		ValidatableResponse response = RestAssured.given().spec(requestSpecBuilder())
		.queryParam("status","active")
		.get("/public/v2/users")
		.then().log().all()
		.assertThat()
		.spec(responseBodyVerification());
		
		
	}

}
