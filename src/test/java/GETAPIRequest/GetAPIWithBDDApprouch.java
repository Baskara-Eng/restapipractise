package GETAPIRequest;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GetAPIWithBDDApprouch {

	@Test
	public void fackStoreTest() {
		given().log().all().when().log().all().get("https://fakestoreapi.com/products").then().log().all().assertThat()
				.statusCode(200).and().body("$.size()", equalTo(20)).and().body("$.[0].price", equalTo("109.95"));

	}

	@Test
	public void getAPIwithAuthorizationTest() {
		RestAssured.baseURI = "https://gorest.co.in";
		HashMap<String, String> queryParamsMap = new HashMap<String, String>();
		queryParamsMap.put("gender", "female");
		queryParamsMap.put("status", "active");
		given().log().all().queryParams(queryParamsMap)
				.header("Authorization", "Bearer e5bdeeaf245ab5554713f0a7d4c602d44feb6b20a37586cf40b141a8ae7e74ad")
				.when().log().all().get("/public/v2/users").then().log().all().assertThat().statusCode(200).and()
				.contentType(ContentType.JSON).and().body("$.size()", equalTo(10));

	}

	@Test
	public void fetchDatafromResponseBodyTest() {

		RestAssured.baseURI = "https://gorest.co.in";
		HashMap<String, String> queryParamsMap = new HashMap<String, String>();
		queryParamsMap.put("gender", "female");
		queryParamsMap.put("status", "active");
		Response response = given().log().all().queryParams(queryParamsMap)
				.header("Authorization", "Bearer e5bdeeaf245ab5554713f0a7d4c602d44feb6b20a37586cf40b141a8ae7e74ad")
				.when().log().all().get("/public/v2/users");
		System.out.println("=========================");
		System.out.println(response.prettyPrint());
		JsonPath js = response.jsonPath();
		System.out.println("========================");
		System.out.println("Email value is:" + js.getString("[2].email"));
		System.out.println("Status values are:" + js.getString("[0].status"));
		System.out.println(js.getString("[0].gender"));
		// printing some value

	}

	@Test
	public void fakeStoreAPITest() {
		RestAssured.baseURI = "https://fakestoreapi.com/";
		Response response = given().when().get("/products");

		JsonPath js = response.jsonPath();
		System.out.println("==========total response==========");
		System.out.println(response.prettyPrint());
		System.out.println("===============================");
		System.out.println("last item rate:" + js.getDouble("[19].rating.rate"));
		System.out.println("Rating Count is:" + js.getInt("[4].rating.count"));

	}

	@Test
	public void verifyResponseArrayTest() {
		RestAssured.baseURI = "https://fakestoreapi.com";
		Response response = given().queryParam("limit", 5).when().get("/products");

		JsonPath js = response.jsonPath();
		List<Integer> idList = js.getList("id");
		List<Float> rateList = js.getList("rating.rate");
		List<Integer> countList = js.getList("rating.count");
		List<String> categoryList = js.getList("category");

		for (int i = 0; i < 5; i++) {
			int id = idList.get(i);
			float rate = rateList.get(i);
			int count = countList.get(i);
			String category = categoryList.get(i);

			System.out.println("id" + id + "rate" + rate + "count" + count + "category" + category);

		}

	}
	
	@Test
	public void goRestFetchResponsefromAraayTest() {
		RestAssured.baseURI="https://gorest.co.in";
		Response response=given()
		.header("Authorization","\r\n"
				+ "Bearer e5bdeeaf245ab5554713f0a7d4c602d44feb6b20a37586cf40b141a8ae7e74ad")
		.when()
		.get("/public/v2/users");
		
		JsonPath js = response.jsonPath();
		System.out.println(response.prettyPrint());
		List<Integer>idList=js.getList("id");
		List<String>nameList=js.getList("name");
		List<String>emailList=js.getList("email");
		List<String>genderList=js.getList("gender");
		List<String>statusList=js.getList("status");
		
		for(int i=0;i<idList.size();i++) {
			int id=idList.get(i);
			String names=nameList.get(i);
			String email=emailList.get(i);
			String gender=genderList.get(i);
			String status=statusList.get(i);
			
			System.out.println("id :" + id + " name: "+names+ " email :" +email +" gender:" +gender +" status:"+status);
		}
	}

}
