package com.techonlinestore.security;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class SignUpRequest {
	private String firstName;
	private String lastName;
	private String email;
	private String password;
}
