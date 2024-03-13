package com.techonlinestore.dao.imp;

import com.techonlinestore.dao.BrandDao;
import com.techonlinestore.dto.BrandDto;
import com.techonlinestore.entity.Brand;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
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
		Brand brand = new Brand();
		brand.setBrandName(brandDto.getBrandName());
		entityManager.persist(brand);

		return brandDto;
	}

	@Override
	public void updateBrand(Brand brand) {
		entityManager.merge(brand);
	}

	@Override
	public void deleteBrand(int brandId) {
		Brand existingBrand = entityManager.find(Brand.class, brandId);

		if (existingBrand != null){
			Query query = entityManager.createQuery("DELETE FROM products p WHERE p.brandId = :existingBrand");
			query.setParameter("existingBrand", existingBrand);
			query.executeUpdate();

			entityManager.remove(existingBrand);
		}
	}
}
