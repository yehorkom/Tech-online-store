package com.techonlinestore.dao;

import com.techonlinestore.dto.TypeDto;
import com.techonlinestore.entity.Type;

import java.util.List;

public interface TypeDao {
	Type getTypeById(int typeId);

	List<Type> getTypes();

	TypeDto createType(TypeDto typeDto);

	void updateType(Type type);

	void deleteType(int typeId);
}
