package PetStore;

import java.util.Arrays;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import PetStore.PetStoreLombok.Category;
import PetStore.PetStoreLombok.Tag;;

public class PetStorePostRequest {

	@Test
	public void post_and_get_request_Test_withoutBuilder() throws JsonMappingException, JsonProcessingException {

		RestAssured.baseURI = "https://petstore.swagger.io/";

		Category category = new Category(1, "animal");
		Tag tag1 = new Tag(1, "red");
		Tag tag2 = new Tag(2, "black");
		List<Tag> tag = Arrays.asList(tag1, tag2);
		List<String> photoUrls = Arrays.asList("https://dog.com", "https://cat.com");

		PetStoreLombok petStore = new PetStoreLombok(200, category, "petanimals", photoUrls, "available", tag);

		Response response = RestAssured.given().log().all().contentType(ContentType.JSON).body(petStore).when()
				.post("v2/pet");

		System.out.println(response.prettyPrint());

		ObjectMapper mapper = new ObjectMapper();
		PetStoreLombok petRes = mapper.readValue(response.getBody().asString(), PetStoreLombok.class);
		System.out.println(petRes.getPhotoUrls().get(1));
		System.out.println(petRes.getTags().get(0).getName());

	}

	@Test
	public void withBuilderTest() {
		RestAssured.baseURI = "https://petstore.swagger.io/";

		Category category = new Category.CategoryBuilder().id(500).name("cat").build();
		Tag tag1 = new Tag.TagBuilder().id(1).name("greeen").build();
		Tag tag2 = new Tag.TagBuilder().id(2).name("yelllo").build();
		List<Tag> tags = Arrays.asList(tag1, tag2);
		List<String> photoUrls = Arrays.asList("http:google.com", "http://magdalena.com");
		PetStoreLombok petStore = new PetStoreLombok(350, category, "cat", photoUrls, "not available", tags);

		Response response = RestAssured.given().contentType(ContentType.JSON).body(petStore).when().post("/v2/pet");

		ObjectMapper mapper = new ObjectMapper();
		try {
			PetStoreLombok petRes = mapper.readValue(response.getBody().asString(), PetStoreLombok.class);
			Assert.assertEquals(petStore.getId(), petRes.getId());
			Assert.assertEquals(petStore.getCategory().getId(), petRes.getCategory().getId());
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
