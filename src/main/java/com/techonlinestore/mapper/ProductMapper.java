package com.techonlinestore.mapper;

import com.techonlinestore.dto.ProductDto;
import com.techonlinestore.entity.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductMapper {
	private final JdbcTemplate jdbcTemplate;
	public static Product toProduct(ProductDto productDto){
		if (productDto == null){
			return null;
		}

		Product product = new Product();
		product.setModel(productDto.getModel());
		product.setDescription(productDto.getDescription());
		product.setAvailability(productDto.isAvailability());
		product.setPrice(productDto.getPrice());
		product.setImage(productDto.getImage());
		product.setBrandId(productDto.getBrandId());
		product.setTypeId(productDto.getTypeId());

		return product;
	}

	public Product toLastProduct(ProductDto productDto){
		if (productDto == null){
			return null;
		}
		List<Product> lastProduct = jdbcTemplate.query("SELECT * FROM products ORDER BY product_id DESC LIMIT 1",
			new BeanPropertyRowMapper<>(Product.class));

		Product product = new Product();
		product.setProductId(lastProduct.getLast().getProductId());
		product.setModel(productDto.getModel());
		product.setDescription(productDto.getDescription());
		product.setAvailability(productDto.isAvailability());
		product.setPrice(productDto.getPrice());
		product.setImage(productDto.getImage());
		product.setBrandId(productDto.getBrandId());
		product.setTypeId(productDto.getTypeId());

		return product;
	}

	public static ProductDto toProductDto(Product product){
		if (product == null){
			return null;
		}

		ProductDto productDto = new ProductDto();
		productDto.setModel(product.getModel());
		productDto.setDescription(product.getDescription());
		productDto.setAvailability(product.isAvailability());
		productDto.setPrice(product.getPrice());
		productDto.setImage(product.getImage());
		productDto.setBrandId(product.getBrandId());
		productDto.setTypeId(product.getTypeId());

		return productDto;
	}
}
