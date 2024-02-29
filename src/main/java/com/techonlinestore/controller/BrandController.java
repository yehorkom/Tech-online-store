package com.techonlinestore.controller;

import com.techonlinestore.dto.BrandDto;
import com.techonlinestore.mapper.BrandMapper;
import com.techonlinestore.service.BrandService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestParam;
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
	public ResponseEntity<BrandDto> getBrandById(@PathVariable("brandId") int brandId) {
		BrandDto brandDto = brandService.getBrandById(brandId);

		if (brandDto != null) {
			return new ResponseEntity<>(brandDto, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/all")
	public ResponseEntity<List<BrandDto>> getBrands() {
		List<BrandDto> brands = brandService.getAllBrands();
		return new ResponseEntity<>(brands, HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<Object> createBrand(@RequestBody BrandDto brandDto) {
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
	public ResponseEntity<BrandDto> updateBrand(@PathVariable("brandId") int brandId, @RequestBody BrandDto brandDto) {
		var updatedBrand = brandService.updateBrand(brandId,brandDto);

		if (updatedBrand != null) {
			return new ResponseEntity<>(brandDto, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/{brandId}")
	public ResponseEntity<Void> deleteBrand(@PathVariable("brandId") int brandId) {
		brandService.deleteBrand(brandId);

		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}
