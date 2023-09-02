package POSTRequest;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.restassured.RestAssured;

import static io.restassured.RestAssured.*;

import static org.hamcrest.Matchers.*;

public class Auth2TokenAPI {

	static String accessToken;

	@BeforeMethod
	public void getAccessToken() {

		RestAssured.baseURI = "https://test.api.amadeus.com/";
		accessToken = given().log().all().header("Content-Type", "application/x-www-form-urlencoded")
				.formParam("grant_type", "client_credentials")
				.formParam("client_id", "VUjQGnOnKoqbG7SilRM0gZDGvdJFd0IP")
				.formParam("client_secret", "iL81Re0VRTITAPQe").when().post("v1/security/oauth2/token").then()
				.statusCode(200).extract().path("access_token");
	}

	@Test
	public void auth2TokenTest() {
		RestAssured.baseURI = "https://test.api.amadeus.com/";
		String originValue = given().log().all().header("Authorization", "Bearer " + accessToken)
				.queryParam("origin", "PAR").queryParam("maxPrice", "200").when().get("v1/shopping/flight-destinations")
				.then().log().all().statusCode(200).and().extract().path("data[0].origin");

		Assert.assertEquals(originValue, "ORY");

	}

}
