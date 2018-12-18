package org.hsl.adma.crypto.handlers;

import javax.servlet.http.HttpServletRequest;

import org.hsl.adma.crypto.exceptions.BucketDoesNotExistException;
import org.hsl.adma.crypto.models.ErrorResponse;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class SharedExceptionHandler {
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ErrorResponse handleMethodArgumentNotValidException(MethodArgumentNotValidException ex, HttpServletRequest request) {
		String message = String.format("Parâmetro inválido [%1$s]", ex.getBindingResult().getFieldError().getField());
		return new ErrorResponse("Bad Request", 400L, 1L, message);
	}
	
	@ExceptionHandler(HttpMessageNotReadableException.class)
	public ErrorResponse handleHttpMessageNotReadableException(HttpMessageNotReadableException ex, HttpServletRequest request) {
		String message = String.format("Erro no formato da requisição: [%1$s]", ex.getMostSpecificCause());
		return new ErrorResponse("Bad Request", 400L, 1L, message);
	}	
	
	@ExceptionHandler(BucketDoesNotExistException.class)
	public ErrorResponse handleBucketDoesNotExistException(BucketDoesNotExistException ex, HttpServletRequest request) {
		return new ErrorResponse("Bad Request", 400L, 1L, "O objeto especificado não existe no S3");
	}	
}
