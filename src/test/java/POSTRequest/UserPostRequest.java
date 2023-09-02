package POSTRequest;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class UserPostRequest {
	
	public static String getRandomEmailId() {
		return "apiautomation"+System.currentTimeMillis()+"@mail.com";
		//return "apiautomation"+ UUID.randomUUID()+"@mail.com";
	}
	
	@Test
	public void verifyUserLombokTest() {
		RestAssured.baseURI="https://gorest.co.in";
		User user= new User("nanda",getRandomEmailId(),"male","active");
		Response response=RestAssured.given().log().all()
			.contentType(ContentType.JSON)
			.header("Authorization","Bearer e5bdeeaf245ab5554713f0a7d4c602d44feb6b20a37586cf40b141a8ae7e74ad")
			.body(user)
			.when()
			.post("/public/v2/users/");
		
		Integer userId=response.jsonPath().get("id");
		
		System.out.println(userId);
		
		Response getResponse=RestAssured.given()
			.contentType(ContentType.JSON)
			.header("Authorization","Bearer e5bdeeaf245ab5554713f0a7d4c602d44feb6b20a37586cf40b141a8ae7e74ad")
			.when()
			.get("/public/v2/users/"+ userId);
		ObjectMapper mapper= new ObjectMapper();
		try {
			User userRes=mapper.readValue(getResponse.getBody().asString(),User.class);
			System.out.println(userRes.getId()+":"+ userRes.getName()+":"+ userRes.getEmail()+":"+ userRes.getGender()+":"+ userRes.getStatus());
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void withBuildetTest() {
		RestAssured.baseURI="https://gorest.co.in";
		User user= new User.UserBuilder()
				.name("nanda")
				.email(getRandomEmailId())
				.gender("male")
				.status("active")
				.build();
		
		Response response=RestAssured.given().log().all()
		.header("Authorization","Bearer e5bdeeaf245ab5554713f0a7d4c602d44feb6b20a37586cf40b141a8ae7e74ad")
		.contentType(ContentType.JSON)
		.body(user)
		.when()
		.post("/public/v2/users");
		
		Integer userId=response.jsonPath().get("id");
		System.out.println("user id is:"+userId);
		
		Response getRes=RestAssured.given().log().all()
			.contentType(ContentType.JSON)
			.header("Authorization","Bearer e5bdeeaf245ab5554713f0a7d4c602d44feb6b20a37586cf40b141a8ae7e74ad")
			.when()
			.get("/public/v2/users/"+userId);
		ObjectMapper mapper= new ObjectMapper();
		try {
			User userGetRes=mapper.readValue(getRes.getBody().asString(), User.class);
			Assert.assertEquals(userGetRes.getEmail(), user.getEmail());
			Assert.assertEquals(userGetRes.getStatus(),user.getStatus());
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
