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
	public List<Type> getTypes() {
		return entityManager.createQuery("SELECT t FROM types t", Type.class).getResultList();
	}

	@Override
	public TypeDto createType(TypeDto typeDto) {
		Type type = Type.builder()
			.typeName(typeDto.getTypeName())
			.build();
		entityManager.persist(type);

		return typeDto;
	}

	@Override
	public void updateType(Type type) {
		// Реализуйте логику обновления типа
	}

	@Override
	public void deleteType(int typeId) {
		Type type = entityManager.find(Type.class, typeId);

		if (type != null){
			entityManager.remove(type);
		}
	}
}
