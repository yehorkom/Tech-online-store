package com.techonlinestore.dao;

import com.techonlinestore.dto.BrandDto;
import com.techonlinestore.entity.Brand;

import java.util.List;

public interface BrandDao {
	Brand getBrandById(int productId);
	List<Brand> getAllBrands();
	BrandDto createBrand(BrandDto brandDto);
	void updateBrand(Brand brand);
	void deleteBrand(int brandId);
}
