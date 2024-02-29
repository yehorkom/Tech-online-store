package com.techonlinestore.service;

import com.techonlinestore.dto.BrandDto;

import java.util.List;

public interface BrandService {
	BrandDto getBrandById(int brandId);

	List<BrandDto> getAllBrands();

	BrandDto createBrand(BrandDto brandDto);

	BrandDto updateBrand(int brandId, BrandDto brandDto);

	void deleteBrand(int brandId);
}
