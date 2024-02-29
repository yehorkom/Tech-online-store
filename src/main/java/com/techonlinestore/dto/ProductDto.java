package com.techonlinestore.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {
	private String model;

	private String description;

	private double price;

	private boolean availability;

	private String image;

	private int brandId;

	private int typeId;
}
