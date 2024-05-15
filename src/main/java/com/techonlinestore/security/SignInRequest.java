package com.techonlinestore.security;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class SignInRequest {
	private String username;
	private String password;
}
