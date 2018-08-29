package com.security.controller;

import java.io.IOException;
import java.nio.file.AccessDeniedException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.security.dto.AppConstants;
import com.security.dto.GenericResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(AccessDeniedException.class)
  public void handleAccessDeniedException(AccessDeniedException e, HttpServletResponse res) throws IOException {
	  e.printStackTrace();
    res.sendError(HttpStatus.FORBIDDEN.value(), "Access denied");
  }

  @ResponseStatus(HttpStatus.OK)
	@ExceptionHandler(value = IllegalArgumentException.class)
	public GenericResponse handleIllegalArgumentException(final IllegalArgumentException e) {
		e.printStackTrace();
		return new GenericResponse(AppConstants.GENERIC_RESPONSE_FAILURE,
				e.getMessage());
	}
  
  @ResponseStatus(HttpStatus.OK)
	@ExceptionHandler(value = MethodArgumentNotValidException.class)
	public GenericResponse handleMethodArgumentNotValidException(final MethodArgumentNotValidException e) {
		//e.printStackTrace();
		return new GenericResponse(AppConstants.GENERIC_RESPONSE_FAILURE,
				e.getBindingResult().getAllErrors().get(0).getDefaultMessage());
	}
  
  
  
  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(value = Exception.class)
	public GenericResponse handleException(final Exception e) {
		e.printStackTrace();
		return new GenericResponse(AppConstants.GENERIC_RESPONSE_FAILURE,
				"Oops! Something went wrong, please try again");
	}
}
