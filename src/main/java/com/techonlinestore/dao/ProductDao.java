package com.techonlinestore.dao;

import com.techonlinestore.dto.ProductDto;
import com.techonlinestore.entity.Product;

import java.util.List;

public interface ProductDao {
	Product getProductById(int productId);
	List<Product> getAllProducts();
	ProductDto createProduct(ProductDto productDto);
	void updateProduct(Product product);
	void deleteProduct(int productId);
}
