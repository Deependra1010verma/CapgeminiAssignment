package com.cg.exception;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatusCode status, WebRequest request) {
		Map<String, String> fieldErrors = new LinkedHashMap<>();

		for (FieldError fieldError : ex.getBindingResult().getFieldErrors()) {
			String fieldName = fieldError.getField();
			String currentMessage = fieldErrors.get(fieldName);
			String nextMessage = fieldError.getDefaultMessage();

			if (currentMessage == null || shouldReplaceMessage(currentMessage, nextMessage)) {
				fieldErrors.put(fieldName, nextMessage);
			}
		}

		ApiErrorResponse errorResponse = new ApiErrorResponse(
				HttpStatus.BAD_REQUEST.value(),
				HttpStatus.BAD_REQUEST.getReasonPhrase(),
				"Validation failed for the request body",
				getRequestPath(request),
				fieldErrors);

		return ResponseEntity.badRequest().body(errorResponse);
	}

	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
			HttpHeaders headers, HttpStatusCode status, WebRequest request) {
		String message = "Request body is missing or contains invalid data";
		Throwable cause = ex.getMostSpecificCause();
		String exceptionMessage = ex.getMessage();

		if (cause instanceof InvalidFormatException invalidFormatException) {
			String fieldName = invalidFormatException.getPath().isEmpty() ? "request field"
					: invalidFormatException.getPath().get(0).getFieldName();

			if ("dobDate".equals(fieldName)) {
				message = "Invalid date format for dobDate. Expected format is dd-MM-yyyy";
			} else if ("salary".equals(fieldName)) {
				message = "Invalid salary format. Please enter a numeric value";
			} else {
				message = "Invalid value provided for " + fieldName;
			}
		} else if (exceptionMessage != null && (exceptionMessage.contains("LocalDate") || exceptionMessage.contains("dobDate"))) {
			message = "Invalid date format for dobDate. Expected format is dd-MM-yyyy";
		} else if (exceptionMessage != null && exceptionMessage.contains("salary")) {
			message = "Invalid salary format. Please enter a numeric value";
		}

		ApiErrorResponse errorResponse = new ApiErrorResponse(
				HttpStatus.BAD_REQUEST.value(),
				HttpStatus.BAD_REQUEST.getReasonPhrase(),
				message,
				getRequestPath(request));

		return ResponseEntity.badRequest().body(errorResponse);
	}

	@ExceptionHandler(EmployeeNotFoundException.class)
	public ResponseEntity<ApiErrorResponse> handleEmployeeNotFound(EmployeeNotFoundException ex, WebRequest request) {
		ApiErrorResponse errorResponse = new ApiErrorResponse(
				HttpStatus.NOT_FOUND.value(),
				HttpStatus.NOT_FOUND.getReasonPhrase(),
				ex.getMessage(),
				getRequestPath(request));

		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
	}

	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	public ResponseEntity<ApiErrorResponse> handleTypeMismatch(MethodArgumentTypeMismatchException ex,
			WebRequest request) {
		String message = "Invalid value for " + ex.getName() + ". Please provide the correct format";
		ApiErrorResponse errorResponse = new ApiErrorResponse(
				HttpStatus.BAD_REQUEST.value(),
				HttpStatus.BAD_REQUEST.getReasonPhrase(),
				message,
				getRequestPath(request));

		return ResponseEntity.badRequest().body(errorResponse);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ApiErrorResponse> handleGenericException(Exception ex, WebRequest request) {
		ApiErrorResponse errorResponse = new ApiErrorResponse(
				HttpStatus.INTERNAL_SERVER_ERROR.value(),
				HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(),
				"Something went wrong while processing the request",
				getRequestPath(request));

		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
	}

	private String getRequestPath(WebRequest request) {
		if (request instanceof ServletWebRequest servletWebRequest) {
			return servletWebRequest.getRequest().getRequestURI();
		}

		return "";
	}

	private boolean shouldReplaceMessage(String currentMessage, String nextMessage) {
		return isRequiredMessage(nextMessage) && !isRequiredMessage(currentMessage);
	}

	private boolean isRequiredMessage(String message) {
		return message != null && message.toLowerCase().contains("required");
	}
}
