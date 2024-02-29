package com.techonlinestore.mapper;

import com.techonlinestore.dto.TypeDto;
import com.techonlinestore.entity.Type;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TypeMapper {
	private final JdbcTemplate jdbcTemplate;

	public static Type toType(TypeDto typeDto){
		if (typeDto == null){
			return null;
		}

		Type type = new Type();
		type.setTypeName(typeDto.getTypeName());

		return type;
	}

	public Type toLastType(TypeDto typeDto){
		if (typeDto == null){
			return null;
		}
		List<Type> lastType = jdbcTemplate.query("SELECT * FROM types ORDER BY type_id DESC LIMIT 1",
			new BeanPropertyRowMapper<>(Type.class));

		Type type = new Type();
		type.setTypeId(lastType.get(0).getTypeId());
		type.setTypeName(typeDto.getTypeName());

		return type;
	}

	public static TypeDto toTypeDto(Type type){
		if (type == null){
			return null;
		}

		TypeDto typeDto = new TypeDto();
		typeDto.setTypeName(type.getTypeName());

		return typeDto;
	}
}
