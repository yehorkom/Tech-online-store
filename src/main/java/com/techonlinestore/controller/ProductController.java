package com.techonlinestore.controller;

import com.techonlinestore.dto.ProductDto;
import com.techonlinestore.mapper.ProductMapper;
import com.techonlinestore.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/product")
public class ProductController {
	private final ProductService productService;
	private final ProductMapper productMapper;


	@GetMapping("/{productId}")
	public ProductDto getProductById(@PathVariable("productId") int productId){
		ProductDto productDto = productService.getProductById(productId);

		if (productDto != null) {
			return productDto;
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found");
		}
	}

	@GetMapping("/all")
	public List<ProductDto> getAllProducts() {
		return productService.getAllProducts();
	}

	@PostMapping
	public ResponseEntity<Object> createProduct(@Valid @RequestBody ProductDto productDto) {
		var createdProduct = productService.createProduct(productDto);

		if (createdProduct == null){
			return ResponseEntity.badRequest().body("Unable to create product. Check your request.");
		}
		URI location = ServletUriComponentsBuilder
			.fromCurrentRequest()
			.path("/{productId}")
			.buildAndExpand(productMapper.toLastProduct(createdProduct).getProductId()).toUri();

		return ResponseEntity.created(location).build();
	}

	@PutMapping("/{productId}")
	public ProductDto updateProduct(@PathVariable("productId") int productId, @Valid @RequestBody ProductDto productDto) {
		ProductDto updatedProduct = productService.updateProduct(productId, productDto);

		if (updatedProduct != null) {
			return updatedProduct;
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found");
		}
	}

	@DeleteMapping("/{productId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteProduct(@PathVariable("productId") int productId) {
		productService.deleteProduct(productId);
	}
}
