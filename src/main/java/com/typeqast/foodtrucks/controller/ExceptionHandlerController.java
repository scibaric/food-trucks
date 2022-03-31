package com.typeqast.foodtrucks.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.typeqast.foodtrucks.exception.ApiExceptionResponse;
import com.typeqast.foodtrucks.exception.FoodTruckNotFoundException;

@RestControllerAdvice
public class ExceptionHandlerController {
	
	@ExceptionHandler(FoodTruckNotFoundException.class)
	public ResponseEntity<ApiExceptionResponse> handleException(FoodTruckNotFoundException e) {
		return ResponseEntity
				.status(HttpStatus.NOT_FOUND)
				.body(new ApiExceptionResponse(HttpStatus.NOT_FOUND, e.getMessage()));
	}

	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<ApiExceptionResponse> handleException(IllegalArgumentException e) {
		return ResponseEntity
				.status(HttpStatus.BAD_REQUEST)
				.body(new ApiExceptionResponse(HttpStatus.BAD_REQUEST, e.getMessage()));
	}
	
	@ExceptionHandler(MissingServletRequestParameterException.class)
	public ResponseEntity<ApiExceptionResponse> handleException(MissingServletRequestParameterException e) {
		return ResponseEntity
				.status(HttpStatus.BAD_REQUEST)
				.body(new ApiExceptionResponse(HttpStatus.BAD_REQUEST, e.getMessage()));
	}
}
