package com.techonlinestore.controller;

import com.techonlinestore.dto.BrandDto;
import com.techonlinestore.mapper.BrandMapper;
import com.techonlinestore.service.BrandService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
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
@RequiredArgsConstructor
@RequestMapping("/api/brand")
public class BrandController {
	private final BrandService brandService;
	private final BrandMapper brandMapper;

	@GetMapping("/{brandId}")
	public BrandDto getBrandById(@PathVariable("brandId") int brandId) {
		BrandDto brandDto = brandService.getBrandById(brandId);

		if (brandDto != null) {
			return brandDto;
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Brand not found");
		}
	}

	@GetMapping("/all")
	public List<BrandDto> getAllBrands() {
		return brandService.getAllBrands();
	}

	@PostMapping
	public ResponseEntity<Object> createBrand(@Valid @RequestBody BrandDto brandDto) {
		var createdBrand = brandService.createBrand(brandDto);

		if (createdBrand == null) {
			return ResponseEntity.badRequest().body("Unable to create brand. Check your request.");
		}

		URI location = ServletUriComponentsBuilder
			.fromCurrentRequest()
			.path("/{brandId}")
			.buildAndExpand(brandMapper.toLastBrand(createdBrand).getBrandId()).toUri();

		return ResponseEntity.created(location).build();
	}

	@PutMapping("/{brandId}")
	public BrandDto updateBrand(@PathVariable("brandId") int brandId,@Valid @RequestBody BrandDto brandDto) {
		BrandDto updatedBrand = brandService.updateBrand(brandId,brandDto);

		if (updatedBrand != null) {
			return brandDto;
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Brand not found");
		}
	}

	@DeleteMapping("/{brandId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteBrand(@PathVariable("brandId") int brandId) {
		brandService.deleteBrand(brandId);
	}
}
