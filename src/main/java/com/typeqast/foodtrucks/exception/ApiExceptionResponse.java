package com.typeqast.foodtrucks.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;

public class ApiExceptionResponse {
	
	private HttpStatus status;
	
	private String message;
	
	private LocalDateTime time;

	public ApiExceptionResponse(HttpStatus status, String message) {
		this.status = status;
		this.message = message;
		this.time = LocalDateTime.now();
	}

	public HttpStatus getStatus() {
		return status;
	}

	public String getMessage() {
		return message;
	}

	public LocalDateTime getTime() {
		return time;
	}
}
