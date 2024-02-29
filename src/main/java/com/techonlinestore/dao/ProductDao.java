package com.techonlinestore.dao;

import com.techonlinestore.dto.ProductDto;
import com.techonlinestore.entity.Product;

import java.util.List;

public interface ProductDao {
	Product getProductById(int productId);
	List<Product> getProducts();
	ProductDto createProduct(ProductDto productDto);
//	List<Product> findProductsByBrand(BrandDto brandDto);
//	List<Product> findProductsByType(TypeDto typeDto);
	void updateProduct(Product product);
	void deleteProduct(int productId);
}
