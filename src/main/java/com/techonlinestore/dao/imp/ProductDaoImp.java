package com.techonlinestore.dao.imp;

import com.techonlinestore.dao.ProductDao;
import com.techonlinestore.dto.ProductDto;
import com.techonlinestore.entity.Product;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
@RequiredArgsConstructor
public class ProductDaoImp implements ProductDao {
	private final EntityManager entityManager;

	@Override
	public Product getProductById(int productId) {
		return entityManager.find(Product.class, productId);
	}

	@Override
	public List<Product> getProducts() {
		return entityManager.createQuery("SELECT p FROM products p", Product.class).getResultList();
	}

	@Override
	public ProductDto createProduct(ProductDto productDto) {
		Product product = Product.builder()
			.model(productDto.getModel())
			.description(productDto.getDescription())
			.price(productDto.getPrice())
			.availability(productDto.isAvailability())
			.image(productDto.getImage())
			.brandId(productDto.getBrandId())
			.typeId(productDto.getTypeId())
			.build();
		entityManager.persist(product);

		return productDto;
	}

	@Override
	public void updateProduct(Product product) {

	}

	@Override
	public void deleteProduct(int productId) {
		Product product = entityManager.find(Product.class, productId);

		if (product != null){
			entityManager.remove(product);
		}
	}
}
