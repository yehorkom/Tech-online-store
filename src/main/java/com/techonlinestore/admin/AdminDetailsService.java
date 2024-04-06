package com.techonlinestore.admin;

import com.techonlinestore.entity.Admin;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdminDetailsService implements UserDetailsService {


	private final AdminRepository adminRepository;

	@Override
	public AdminDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Admin admin = adminRepository.findByUsername(username);
		if (admin == null) {
			throw new UsernameNotFoundException("Admin not found with username: " + username);
		}
		return new AdminDetails(admin);
	}
}
