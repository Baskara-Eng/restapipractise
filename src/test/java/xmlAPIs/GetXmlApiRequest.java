package xmlAPIs;

import java.util.List;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;


public class GetXmlApiRequest {
		
	@Test
	public void getXmlResponseTest() {
		RestAssured.baseURI="http://ergast.com";
		
		Response response=RestAssured.given()
					.contentType(ContentType.XML)
					.when()
					.get("/api/f1/2010/circuits.xml");
		//creating xmlpath object
		XmlPath xmlpath = response.xmlPath();
		
//		List<String> circuitName=xmlpath.getList("MRData.CircuitTable.Circuit.CircuitName");
//		for(String e:circuitName) {
//			System.out.println(e);
//		}
//			
		String country= xmlpath.getList("**.findAll{it.@lat == '26.0325'}.Location.Country").toString();
		System.out.println(country);
		
	}
}
