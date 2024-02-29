package com.techonlinestore.controller;

import com.techonlinestore.dto.ProductDto;
import com.techonlinestore.mapper.ProductMapper;
import com.techonlinestore.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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
	public ResponseEntity<ProductDto> getProductById(@PathVariable("productId") int productId){
		ProductDto productDto = productService.getProductById(productId);

		if (productDto != null) {
			return new ResponseEntity<>(productDto, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/all")
	public ResponseEntity<List<ProductDto>> getProducts() {
		List<ProductDto> products = productService.getAllProducts();
		return new ResponseEntity<>(products, HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<Object> createProduct(@RequestBody ProductDto productDto) {
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
}
