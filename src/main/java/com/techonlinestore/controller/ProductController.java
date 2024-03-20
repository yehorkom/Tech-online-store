package com.techonlinestore.controller;

import com.techonlinestore.dto.ProductDto;
import com.techonlinestore.mapper.ProductMapper;
import com.techonlinestore.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/product")
public class ProductController {
	private final ProductService productService;
	private final ProductMapper productMapper;


	@GetMapping("/{productId}")
	public ProductDto getProductById(@PathVariable("productId") int productId){
		log.info("Request to get product with ID: {}", productId);
		ProductDto productDto = productService.getProductById(productId);

		if (productDto != null) {
			log.info("Product with ID: {} found", productId);
			return productDto;
		} else {
			log.error("Product with ID: {} not found", productId);
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found");
		}
	}

	@GetMapping("/all")
	public List<ProductDto> getAllProducts() {
		log.info("Request to get all products");
		return productService.getAllProducts();
	}

	@PostMapping
	public ResponseEntity<Object> createProduct(@Valid @RequestBody ProductDto productDto) {
		log.info("Request to create a new product");
		ProductDto createdProduct = productService.createProduct(productDto);
		if (createdProduct != null){
			log.info("Product created successfully with ID: {}", productMapper.toLastProduct(createdProduct).getProductId());
			URI location = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{productId}")
				.buildAndExpand(productMapper.toLastProduct(createdProduct).getProductId()).toUri();
			return ResponseEntity.created(location).build();
		} else {
			log.error("Failed to create product");
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Unable to create product");
		}
	}

	@PutMapping("/{productId}")
	public ProductDto updateProduct(@PathVariable("productId") int productId, @Valid @RequestBody ProductDto productDto) {
		log.info("Request to update product with ID: {}", productId);
		ProductDto updatedProduct = productService.updateProduct(productId, productDto);

		if (updatedProduct != null) {
			log.info("Product with ID: {} updated successfully", productId);
			return updatedProduct;
		} else {
			log.error("Failed to update product with ID: {}", productId);
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found");
		}
	}

	@DeleteMapping("/{productId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteProduct(@PathVariable("productId") int productId) {
		log.info("Request to delete product with ID: {}", productId);
		productService.deleteProduct(productId);
		log.info("Product with ID: {} deleted successfully", productId);
	}
}
