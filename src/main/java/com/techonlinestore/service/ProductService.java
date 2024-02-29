package com.techonlinestore.service;

import com.techonlinestore.dto.ProductDto;

import java.util.List;

public interface ProductService {
	ProductDto getProductById(int productId);
	List<ProductDto> getAllProducts();
	ProductDto createProduct(ProductDto productDto);
	ProductDto updateProduct(int productId, ProductDto productDto);
	void deleteProduct(int productId);
}
