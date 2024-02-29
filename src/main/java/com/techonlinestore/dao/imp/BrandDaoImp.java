package com.techonlinestore.dao.imp;

import com.techonlinestore.dao.BrandDao;
import com.techonlinestore.dto.BrandDto;
import com.techonlinestore.entity.Brand;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
@RequiredArgsConstructor
public class BrandDaoImp implements BrandDao {
	private final EntityManager entityManager;
	@Override
	public Brand getBrandById(int brandId) {
		return entityManager.find(Brand.class, brandId);
	}

	@Override
	public List<Brand> getAllBrands() {
		return entityManager.createQuery("SELECT b FROM brands b", Brand.class).getResultList();
	}

	@Override
	public BrandDto createBrand(BrandDto brandDto) {
		Brand brand = Brand.builder()
			.brandName(brandDto.getBrandName())
			.build();
		entityManager.persist(brand);

		return brandDto;
	}

	@Override
	public void updateBrand(Brand brand) {

	}

	@Override
	public void deleteBrand(int brandId) {
		Brand brand = entityManager.find(Brand.class, brandId);

		if (brand != null){
			entityManager.remove(brand);
		}
	}
}
