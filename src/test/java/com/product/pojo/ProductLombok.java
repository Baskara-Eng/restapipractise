package com.product.pojo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class ProductLombok {
	private int id;
	private String title;
	private Object price;
	private String description;
	private String category;
	private String image;
	private Rating rating;
	
	@Data
	@NoArgsConstructor
	@AllArgsConstructor
	@Builder
	public static class Rating{
		private double rate;
		private int count;
	}
}
