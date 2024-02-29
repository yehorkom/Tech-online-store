package com.techonlinestore.service.imp;

import com.techonlinestore.dao.TypeDao;
import com.techonlinestore.dto.TypeDto;
import com.techonlinestore.entity.Type;
import com.techonlinestore.mapper.TypeMapper;
import com.techonlinestore.service.TypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TypeServiceImp implements TypeService {
	private final TypeDao typeDao;

	@Override
	public TypeDto getTypeById(int typeId) {
		Type type = typeDao.getTypeById(typeId);

		return TypeMapper.toTypeDto(type);
	}

	@Override
	public List<TypeDto> getAllTypes() {
		return typeDao.getTypes()
			.stream()
			.map(TypeMapper::toTypeDto)
			.toList();
	}

	@Override
	@Transactional
	public TypeDto createType(TypeDto typeDto) {
		return typeDao.createType(typeDto);
	}

	@Override
	@Transactional
	public TypeDto updateType(int typeId, TypeDto typeDto) {
		Type existingType = typeDao.getTypeById(typeId);

		if (existingType != null) {
			Type updatedType = TypeMapper.toType(typeDto);
			updatedType.setTypeId(typeId);
			typeDao.updateType(updatedType);
			return typeDto;
		}

		return null;
	}

	@Override
	@Transactional
	public void deleteType(int typeId) {
		Type existingType = typeDao.getTypeById(typeId);

		if (existingType != null){
			typeDao.deleteType(typeId);
		}
	}
}
