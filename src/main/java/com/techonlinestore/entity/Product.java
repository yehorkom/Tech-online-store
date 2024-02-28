package com.techonlinestore.entity;

import jakarta.persistence.Entity;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class Product {
	private int id;
	private String name;
	private String description;
	private double price;
	private String condition;
	private String image;
	private int brandId;
	private int typeId;
}
