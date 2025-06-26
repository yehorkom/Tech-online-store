package com.techonlinestore.security.service;

import com.techonlinestore.security.AuthResponse;
import com.techonlinestore.security.SignInRequest;
import com.techonlinestore.security.SignUpRequest;
import com.techonlinestore.security.entity.User;
import com.techonlinestore.security.enums.Role;
import com.techonlinestore.security.repository.UserRepository;
import com.techonlinestore.security.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
	private final AuthenticationManager authenticationManager;
	private final PasswordEncoder passwordEncoder;
	private final UserRepository userRepository;
	private final JwtUtil jwtUtil;

	public AuthResponse signUp(SignUpRequest request) {
		var user = new User(request.getUsername(),
			passwordEncoder.encode(request.getPassword()));
		userRepository.save(user);
		var jwt = jwtUtil.generateToken(user);

		return new AuthResponse(jwt);
	}

	public AuthResponse signIn(SignInRequest request) {
		authenticationManager.authenticate(
			new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
		var user = userRepository.findByUsername(request.getUsername())
			.orElseThrow(() -> new IllegalArgumentException("Invalid email or password."));
		var jwt = jwtUtil.generateToken(user);

		return new AuthResponse(jwt);
	}
}
