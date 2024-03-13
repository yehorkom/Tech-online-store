package com.techonlinestore.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {
	@NotEmpty(message = "Model name cannot be empty")
	private String model;

	private String description;

	private double price;

	private boolean availability;

	private String image;

	private int brandId;

	private int typeId;
}
