package com.techonlinestore.dao.imp;

import com.techonlinestore.dao.TypeDao;
import com.techonlinestore.dto.TypeDto;
import com.techonlinestore.entity.Type;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class TypeDaoImp implements TypeDao {
	private final EntityManager entityManager;

	@Override
	public Type getTypeById(int typeId) {
		return entityManager.find(Type.class, typeId);
	}

	@Override
	public List<Type> getAllTypes() {
		return entityManager.createQuery("SELECT t FROM types t", Type.class).getResultList();
	}

	@Override
	public TypeDto createType(TypeDto typeDto) {
		Type type = new Type();
		type.setTypeName(typeDto.getTypeName());
		entityManager.persist(type);

		return typeDto;
	}

	@Override
	public void updateType(Type type) {
		entityManager.merge(type);
	}

	@Override
	public void deleteType(int typeId) {
		Type type = entityManager.find(Type.class, typeId);

		if (type != null){
			entityManager.remove(type);
		}
	}
}
