package com.techonlinestore.security.controller;

import com.techonlinestore.security.AuthResponse;
import com.techonlinestore.security.SignInRequest;
import com.techonlinestore.security.SignUpRequest;
import com.techonlinestore.security.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

	private final AuthenticationService authenticationService;

	@PostMapping(value = "/signup")
	public ResponseEntity<AuthResponse> signUp(@RequestBody SignUpRequest request)  {
		return ResponseEntity.ok(authenticationService.signUp(request));
	}

	@PostMapping("/signin")
	public ResponseEntity<AuthResponse> signIn(@RequestBody SignInRequest request) {
		return ResponseEntity.ok(authenticationService.signIn(request));
	}
}
