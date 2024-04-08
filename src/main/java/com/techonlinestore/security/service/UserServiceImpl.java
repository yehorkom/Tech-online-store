package com.techonlinestore.security.service;

import com.techonlinestore.security.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

	private final UserRepository userRepository;

	@Override
	public UserDetailsService userDetailsService() throws UsernameNotFoundException {
		return username -> userRepository.findUser(username)
			.orElseThrow(() -> new UsernameNotFoundException("User not found"));
	}
}
