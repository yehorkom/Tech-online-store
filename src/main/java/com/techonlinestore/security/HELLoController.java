package com.techonlinestore.security;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/home")
public class HELLoController {

	@GetMapping
	public ResponseEntity<String> sayHi() {
		return ResponseEntity.ok("HI");
	}
}
