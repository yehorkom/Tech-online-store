package com.techonlinestore.admin;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@Getter
@Setter
public class JwtResponse {

	private String token;
	private String type = "Bearer";
	private int id;
	private String username;
	private String password;
}
