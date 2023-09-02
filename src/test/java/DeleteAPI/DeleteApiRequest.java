package DeleteAPI;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import static org.hamcrest.Matchers.*;

public class DeleteApiRequest {
	
	public String getRandomEmail() {
		return "automation"+System.currentTimeMillis()+"@mail.com";
	}
	
	@Test
	public void deleteAPITest() {
		RestAssured.baseURI="https://gorest.co.in";
		User user=new User("Narmada", getRandomEmail(),"female","active");
		
		Response response=RestAssured.given()
					.contentType(ContentType.JSON)
					.header("Authorization","Bearer e5bdeeaf245ab5554713f0a7d4c602d44feb6b20a37586cf40b141a8ae7e74ad")
					.body(user)
					.when()
					.post("/public/v2/users");
		Integer userId=response.jsonPath().get("id");
		System.out.println("user id:"+userId);
		
		RestAssured.given()
				.contentType(ContentType.JSON)
				.header("Authorization","Bearer e5bdeeaf245ab5554713f0a7d4c602d44feb6b20a37586cf40b141a8ae7e74ad")
				.when()
				.delete("/public/v2/users/"+userId)
				.then()
				.and()
				.assertThat()
				.statusCode(204);
		RestAssured.given()
		.contentType(ContentType.JSON)
		.header("Authorization","Bearer e5bdeeaf245ab5554713f0a7d4c602d44feb6b20a37586cf40b141a8ae7e74ad")
		.when()
		.get("/public/v2/users/"+userId)
		.then().and()
		.assertThat()
		.statusCode(404)
		.and()
		.assertThat()
		.body("message", equalTo("Resource not found"));
		
	}

}
