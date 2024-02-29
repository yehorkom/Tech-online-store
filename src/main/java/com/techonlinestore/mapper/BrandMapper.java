package com.techonlinestore.mapper;

import com.techonlinestore.dto.BrandDto;
import com.techonlinestore.entity.Brand;
import lombok.RequiredArgsConstructor;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BrandMapper {
	private final JdbcTemplate jdbcTemplate;

	public static Brand toBrand(BrandDto brandDto){
		if (brandDto == null){
			return null;
		}

		Brand brand = new Brand();
		brand.setBrandName(brandDto.getBrandName());

		return brand;
	}

	public Brand toLastBrand(BrandDto brandDto){
		if (brandDto == null){
			return null;
		}
		List<Brand> lastBrand = jdbcTemplate.query("SELECT * FROM brands ORDER BY brand_id DESC LIMIT 1",
			new BeanPropertyRowMapper<>(Brand.class));

		Brand brand = new Brand();
		brand.setBrandId(lastBrand.get(0).getBrandId());
		brand.setBrandName(brandDto.getBrandName());

		return brand;
	}

	public static BrandDto toBrandDto(Brand brand){
		if (brand == null){
			return null;
		}

		BrandDto brandDto = new BrandDto();
		brandDto.setBrandName(brand.getBrandName());

		return brandDto;
	}
}
