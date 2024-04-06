package com.techonlinestore.admin;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordEncoderGenerator {

	public static void main(String[] args) {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String plainPassword = "admin";
		String encodedPassword = passwordEncoder.encode(plainPassword);
		System.out.println(encodedPassword);
	}
}
