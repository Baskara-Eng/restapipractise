package com.product.pojo;

import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.restassured.RestAssured;

import io.restassured.mapper.ObjectMapperDeserializationContext;
import io.restassured.mapper.ObjectMapperSerializationContext;
import io.restassured.response.Response;

public class GetProduct {
	
	@Test 
	public void getProductTest() {
		Response response= RestAssured.given()
		 .baseUri("https://fakestoreapi.com")
		 .when()
		 .get("/products");
		
		ObjectMapper mapper = new ObjectMapper();
		
		try {
			Product product[]=mapper.readValue(response.getBody().asString(), Product[].class);
			for(Product p:product) {
				System.out.println("id: "+p.getId());
				System.out.println("title: "+p.getTitle());
				System.out.println("price: "+p.getPrice());
				System.out.println("Category: "+p.getCategory());
				System.out.println("description: "+p.getDescription());
				System.out.println("image: "+p.getImage());
				System.out.println("Rating: "+p.getRating().getRate());
				System.out.println("Count: "+p.getRating().getCount());
				System.out.println("----------------------");
			}
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@Test 
	public void getProductWithLombokTest() {
		
		Response response= RestAssured.given()
		 .baseUri("https://fakestoreapi.com")
		 .when()
		 .get("/products");
		
		ObjectMapper mapper = new ObjectMapper();
		
		try {
			ProductLombok product[]=mapper.readValue(response.getBody().asString(), ProductLombok[].class);
			
			for(ProductLombok p: product) {
				
				System.out.println("id: "+p.getId());
				System.out.println("title: "+p.getTitle());
				System.out.println("price: "+p.getPrice());
				System.out.println("Category: "+p.getCategory());
				System.out.println("description: "+p.getDescription());
				System.out.println("image: "+p.getImage());
				System.out.println("Rating: "+p.getRating().getRate());
				System.out.println("Count: "+p.getRating().getCount());
				System.out.println("------------with Lombok----------");
			}
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
