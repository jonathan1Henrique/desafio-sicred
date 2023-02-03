package com.desafio.pauta.controllers.exceptions;

import javassist.NotFoundException;
import org.hibernate.service.spi.ServiceException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ResourceExceptionHandler {

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
		Map<String, String> errors = new HashMap<>();
		ex.getBindingResult().getAllErrors().forEach((error) -> {
			String fieldName = ((FieldError) error).getField();
			String errorMessage = error.getDefaultMessage();
			errors.put(fieldName, errorMessage);
		});
		return errors;
	}
	
	
	@ExceptionHandler(ServiceException.class)
	public ResponseEntity<StandardError> serviceValidation(ServiceException e, HttpServletRequest request){
		return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY)
				.body( new StandardError(
						Instant.now(),
						HttpStatus.UNPROCESSABLE_ENTITY.value(),
						"Validation failure",
						e.getMessage(),
						request.getRequestURI()));
	}

	@ExceptionHandler(NotFoundException.class)
	public ResponseEntity<StandardError> notFound(NotFoundException e, HttpServletRequest request){
		return ResponseEntity.status(HttpStatus.NOT_FOUND)
				.body(new StandardError(Instant.now(),
						HttpStatus.NOT_FOUND.value(),
						"Resource not found",
						e.getMessage(),
						request.getRequestURI()));
	}

}