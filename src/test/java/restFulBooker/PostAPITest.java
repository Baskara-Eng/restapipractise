package restFulBooker;

import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import restFulBooker.RestFulPojo.BookingDate;



public class PostAPITest {
	
	@Test
	public void postRequestTest() {
		BookingDate bookingdates= new BookingDate("2018-01-01","2018-01-01");
		RestFulPojo restfulPojo=new RestFulPojo("nanda", "b", 98123, false, bookingdates, "lunch");
		
		Response response=RestAssured.given().log().all()
		.baseUri("https://restful-booker.herokuapp.com")
		.contentType(ContentType.JSON)
		.body(restfulPojo)
		.when()
		.post("/booking")
		.then()
		.statusCode(200)
		.extract()
		.response();
		
		response.prettyPrint();
		
		ObjectMapper mapper = new ObjectMapper();
		
		try {
			RestFulPojo responsePojo = mapper.readValue(response.getBody().asString(), RestFulPojo.class);
			System.out.println(responsePojo.getBookingid()+" "+responsePojo.getAdditionalneeds());
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	
}
}