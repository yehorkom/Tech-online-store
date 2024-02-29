package com.techonlinestore.service.imp;

import com.techonlinestore.dao.BrandDao;
import com.techonlinestore.dto.BrandDto;
import com.techonlinestore.entity.Brand;
import com.techonlinestore.mapper.BrandMapper;
import com.techonlinestore.service.BrandService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BrandServiceImp implements BrandService {
	private final BrandDao brandDao;

	@Override
	public BrandDto getBrandById(int brandId) {
		Brand brand = brandDao.getBrandById(brandId);

		return BrandMapper.toBrandDto(brand);
	}

	@Override
	public List<BrandDto> getAllBrands() {
		return brandDao.getAllBrands()
			.stream()
			.map(BrandMapper::toBrandDto)
			.toList();
	}

	@Override
	@Transactional
	public BrandDto createBrand(BrandDto brandDto) {
		return brandDao.createBrand(brandDto);
	}

	@Override
	@Transactional
	public BrandDto updateBrand(int brandId, BrandDto brandDto) {
		Brand existingBrand = brandDao.getBrandById(brandId);

		if (existingBrand != null) {
			Brand updatedBrand = BrandMapper.toBrand(brandDto);
			updatedBrand.setBrandId(brandId);
			brandDao.updateBrand(updatedBrand);
			return brandDto;
		}

		return null;
	}

	@Override
	@Transactional
	public void deleteBrand(int brandId) {
		Brand existingBrand = brandDao.getBrandById(brandId);

		if (existingBrand != null){
			brandDao.deleteBrand(brandId);
		}
	}
}
