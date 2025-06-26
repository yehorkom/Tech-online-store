package com.techonlinestore.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
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
	@NotEmpty(message = "Description cannot be empty")
	private String descriptionUA;
	@NotEmpty(message = "Description cannot be empty")
	private String descriptionPL;
	@NotNull(message = "Price cannot be empty")
	private double price;
	@NotNull
	private boolean availability;

	private String image;
	@NotNull
	private int brandId;
	@NotNull
	private int typeId;
}
