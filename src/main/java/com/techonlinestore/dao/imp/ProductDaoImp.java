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
	public List<Product> getAllProducts() {
		return entityManager.createQuery("SELECT p FROM products p", Product.class).getResultList();
	}

	@Override
	public ProductDto createProduct(ProductDto productDto) {
		Product product = new Product();
		product.setModel(productDto.getModel());
		product.setDescription(productDto.getDescription());
		product.setPrice(productDto.getPrice());
		product.setAvailability(productDto.isAvailability());
		product.setImage(productDto.getImage());
		product.setBrandId(productDto.getBrandId());
		product.setTypeId(productDto.getTypeId());
		entityManager.persist(product);

		return productDto;
	}

	@Override
	public void updateProduct(Product product) {
		entityManager.merge(product);
	}

	@Override
	public void deleteProduct(int productId) {
		Product product = entityManager.find(Product.class, productId);

		if (product != null){
			entityManager.remove(product);
		}
	}
}
