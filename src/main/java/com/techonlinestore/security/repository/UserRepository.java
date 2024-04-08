package com.techonlinestore.security.repository;

import com.techonlinestore.security.entity.User;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class UserRepository {
	private static User user = null;

	public Optional<User> findUser(String email) {
		return Optional.of(user);
	}

	//todo: remove when JpaRepository will be using
	public void save(User user) {
		UserRepository.user = user;
	}
}
