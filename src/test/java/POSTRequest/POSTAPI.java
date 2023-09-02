

package POSTRequest;

import java.io.File;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import static org.hamcrest.Matchers.*;

public class POSTAPI {

	@Test
	public void postAuthTest() {
		RestAssured.baseURI = "https://restful-booker.herokuapp.com/";

		String tokenId = RestAssured.given().contentType(ContentType.JSON)
				.body("{\r\n" + "    \"username\" : \"admin\",\r\n" + "    \"password\" : \"password123\"\r\n" + "}")
				.post("/auth").then().log().all().assertThat().statusCode(200).extract().path("token");

		System.out.println("Token id is: " + tokenId);
		Assert.assertNotNull(tokenId);

	}

	@Test
	public void passrequestBodyAsFileTest() {
		// File filepath= new File("./src/test/resource/data/basicAuth.json");

		RestAssured.baseURI = "https://restful-booker.herokuapp.com/";
		String tokenId = RestAssured.given().contentType(ContentType.JSON)
				.body(new File("./src/test/resource/data/basicAuth.json")).when().post("/auth").then().log().all()
				.assertThat().extract().path("token");

		System.out.println(tokenId);
		Assert.assertNotNull(tokenId);

	}

	@Test
	public void verifyPostCallTest() {

		RestAssured.baseURI = "https://gorest.co.in";

		int userID = RestAssured.given().contentType(ContentType.JSON)
				.header("Authorization", "Bearer e5bdeeaf245ab5554713f0a7d4c602d44feb6b20a37586cf40b141a8ae7e74ad")
				.body(new File("./src/test/resource/data/addUser.json")).when().post("/public/v2/users").then().log()
				.all().statusCode(201).and().body("name", equalTo("nanda")).extract().path("id");

		System.out.println("user id is: " + userID);

		RestAssured.given().log().all().contentType(ContentType.JSON)
				.header("Authorization", "Bearer e5bdeeaf245ab5554713f0a7d4c602d44feb6b20a37586cf40b141a8ae7e74ad")
				.when().get("/public/v2/users/" + userID).then().log().all().statusCode(200).and()
				.body("id", equalTo(userID));

	}

}
