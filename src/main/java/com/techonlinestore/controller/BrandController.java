package com.techonlinestore.controller;

import com.techonlinestore.dto.BrandDto;
import com.techonlinestore.mapper.BrandMapper;
import com.techonlinestore.service.BrandService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/brand")
public class BrandController {
	private final BrandService brandService;
	private final BrandMapper brandMapper;

	@GetMapping("/{brandId}")
	public BrandDto getBrandById(@PathVariable("brandId") int brandId) {
		log.info("Request to get brand with ID: {}", brandId);
		BrandDto brandDto = brandService.getBrandById(brandId);

		if (brandDto != null) {
			log.info("Brand with ID: {} found", brandId);
			return brandDto;
		} else {
			log.error("Brand with ID: {} not found", brandId);
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Brand not found");
		}
	}

	@GetMapping("/all")
	public List<BrandDto> getAllBrands() {
		log.info("Request to get all brands");
		return brandService.getAllBrands();
	}

	@PostMapping
	public ResponseEntity<Object> createBrand(@Valid @RequestBody BrandDto brandDto) {
		log.info("Request to create a new brand");
		BrandDto createdBrand = brandService.createBrand(brandDto);

		if (createdBrand != null){
			log.info("Brand created successfully with ID: {}", brandMapper.toLastBrand(createdBrand).getBrandId());
			URI location = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{brandId}")
				.buildAndExpand(brandMapper.toLastBrand(createdBrand).getBrandId()).toUri();
			return ResponseEntity.created(location).build();
		} else {
			log.error("Failed to create brand");
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Unable to create brand");
		}



	}

	@PutMapping("/{brandId}")
	public BrandDto updateBrand(@PathVariable("brandId") int brandId,@Valid @RequestBody BrandDto brandDto) {
		log.info("Request to update brand with ID: {}", brandId);
		BrandDto updatedBrand = brandService.updateBrand(brandId,brandDto);

		if (updatedBrand != null) {
			log.info("Product with ID: {} updated successfully", brandId);
			return brandDto;
		} else {
			log.error("Failed to update brand with ID: {}", brandId);
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Brand not found");
		}
	}

	@DeleteMapping("/{brandId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteBrand(@PathVariable("brandId") int brandId) {
		log.info("Request to delete brand with ID: {}", brandId);
		brandService.deleteBrand(brandId);
		log.info("Brand with ID: {} deleted successfully", brandId);
	}
}
