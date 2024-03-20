package com.techonlinestore.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.server.ResponseStatusException;

@ControllerAdvice
@Slf4j
class GlobalExceptionHandler {



	@ExceptionHandler(ResponseStatusException.class)
	public ResponseEntity<Object> handleResponseStatusException(ResponseStatusException ex) {
		log.error("ResponseStatusException: {}", ex.getReason());
		return new ResponseEntity<>(ex.getMessage(), ex.getStatusCode());
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {
		log.error("MethodArgumentNotValidException: {}", ex.getMessage());

		String errorMessages = ex.getBindingResult().getFieldErrors().stream()
			.map(fieldError -> fieldError.getField() + " - " + fieldError.getDefaultMessage())
			.reduce("", (subtotal, element) -> subtotal + element + "\n");
		return new ResponseEntity<>(errorMessages + ex.getStatusCode(), HttpStatus.BAD_REQUEST);
	}
}
