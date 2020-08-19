package com.people.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
/**
 * 
 * @author sormenor
 * Custom HttpMediaTypeNotSupported execption
 * This will throw a HttpStatus 400 when HttpMediaTypeNotSupportedException exist
 */
public class HttpMediaTypeNotSupportedExceptionAdvice {

	@ResponseBody
	@ExceptionHandler(HttpMediaTypeNotSupportedException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	String httpMediaTypeNotSupportedHandler(HttpMediaTypeNotSupportedException ex) {
		return ex.getMessage();
	}
}
