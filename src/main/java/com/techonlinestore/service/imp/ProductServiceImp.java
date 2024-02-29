package com.techonlinestore.service.imp;

import com.techonlinestore.dao.ProductDao;
import com.techonlinestore.dto.ProductDto;
import com.techonlinestore.entity.Product;
import com.techonlinestore.mapper.ProductMapper;
import com.techonlinestore.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@RequiredArgsConstructor
public class ProductServiceImp implements ProductService {
	private final ProductDao productDao;
	@Override
	public ProductDto getProductById(int productId) {
		Product product = productDao.getProductById(productId);

		return ProductMapper.toProductDto(product);
	}

	@Override
	public List<ProductDto> getAllProducts() {
		return productDao.getProducts()
			.stream()
			.map(ProductMapper::toProductDto)
			.toList();
	}

	@Override
	@Transactional
	public ProductDto createProduct(ProductDto productDto) {
		return productDao.createProduct(productDto);
	}

	@Override
	@Transactional
	public ProductDto updateProduct(int productId, ProductDto productDto) {
		return null;
	}

	@Override
	@Transactional
	public void deleteProduct(int productId) {
		Product existingProduct = productDao.getProductById(productId);

		if (existingProduct != null){
			productDao.deleteProduct(productId);
		}
	}
}
