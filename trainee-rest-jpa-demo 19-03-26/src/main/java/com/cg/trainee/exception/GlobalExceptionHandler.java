package com.cg.trainee.exception;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(TraineeNotFoundException.class)
	public ResponseEntity<Map<String, String>> handleTraineeNotFound(TraineeNotFoundException ex) {
		Map<String, String> response = new LinkedHashMap<>();
		response.put("message", ex.getMessage());
		return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(TraineeAlreadyExistsException.class)
	public ResponseEntity<Map<String, String>> handleTraineeAlreadyExists(TraineeAlreadyExistsException ex) {
		Map<String, String> response = new LinkedHashMap<>();
		response.put("message", ex.getMessage());
		return new ResponseEntity<>(response, HttpStatus.CONFLICT);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String, String>> handleValidationErrors(MethodArgumentNotValidException ex) {
		Map<String, String> response = new LinkedHashMap<>();
		response.put("message", ex.getBindingResult().getFieldError().getDefaultMessage());
		return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	}
}
