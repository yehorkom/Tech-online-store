package com.techonlinestore.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Column;
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

	@Column(name ="description_ua",nullable=false)
	private String descriptionUA;

	@Column(name ="description_pl", nullable=false)
	private String descriptionPL;

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
