package com.cts.exceptions;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import jakarta.validation.ConstraintViolationException;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(RideSeekerNotFoundException.class)
	public ResponseEntity<String> handleRideSeekerNotFoundException(RideSeekerNotFoundException e) {
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
	}

	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(TripNotFoundException.class)
	public Map<String,String> handleTripNotFoundException(TripNotFoundException e) {
		Map<String,String> errorMap=new HashMap<>();
		errorMap.put("errorMessage", e.getMessage());
		return errorMap;
	}

	@Override
	public ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatusCode status, WebRequest request) {
		List<String> errors = ex.getBindingResult().getFieldErrors().stream().map((fe) -> fe.getDefaultMessage())
				.collect(Collectors.toList());
		return ResponseEntity.badRequest().body(errors);
	}

	@ExceptionHandler(ConstraintViolationException.class)
	public Object handleConstraintValidationException(ConstraintViolationException ex) {
		return ex.getConstraintViolations().stream().map((cv) -> cv.getMessage()).collect(Collectors.toList());
	}

}
