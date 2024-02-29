package com.techonlinestore.service;

import com.techonlinestore.dto.TypeDto;

import java.util.List;

public interface TypeService {
	TypeDto getTypeById(int typeId);

	List<TypeDto> getAllTypes();


	TypeDto createType(TypeDto typeDto);


	TypeDto updateType(int typeId, TypeDto typeDto);

	void deleteType(int typeId);
}
