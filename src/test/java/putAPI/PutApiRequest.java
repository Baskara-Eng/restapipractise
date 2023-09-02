package putAPI;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import static org.hamcrest.Matchers.*;
import io.restassured.response.Response;
import io.restassured.builder.RequestSpecBuilder;

public class PutApiRequest {

	public String getRandomEmail() {
		return "automatonTesting" + System.currentTimeMillis() + "@mail.com";
	}

	@Test
	public void putAPITest() {
		RestAssured.baseURI = "https://gorest.co.in";
		UserPojo userPojo = new UserPojo("nanda", getRandomEmail(), "male", "active");

		Integer userId = RestAssured.given()
				.header("Authorization", "Bearer e5bdeeaf245ab5554713f0a7d4c602d44feb6b20a37586cf40b141a8ae7e74ad")
				.contentType(ContentType.JSON).body(userPojo).when().post("/public/v2/users").then().extract()
				.path("id");

		System.out.println(userId);
		userPojo.setName("NandaB");
		userPojo.setStatus("inactive");

		RestAssured.given().log().all().contentType(ContentType.JSON)
				.header("Authorization", "Bearer e5bdeeaf245ab5554713f0a7d4c602d44feb6b20a37586cf40b141a8ae7e74ad")
				.body(userPojo).when().log().all().put("/public/v2/users/" + userId).then().and().assertThat()
				.statusCode(200).and().assertThat().body("id", equalTo(userId)).and().assertThat()
				.body("name", equalTo(userPojo.getName())).and().assertThat()
				.body("status", equalTo(userPojo.getStatus()));

	}

}
