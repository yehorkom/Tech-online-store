package com.techonlinestore.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.AllArgsConstructor;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity(name = "products")
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int productId;

	@Column(nullable=false)
	private String model;

	@Column(nullable=false)
	private String description;

	@Column(nullable=false)
	private double price;

	@Column(nullable=false)
	private boolean availability;

	@Column(nullable=false)
	private String image;

	@Column(name = "brand_id", nullable=false)
	private int brandId;

	@Column(name = "type_id", nullable=false)
	private int typeId;
}
