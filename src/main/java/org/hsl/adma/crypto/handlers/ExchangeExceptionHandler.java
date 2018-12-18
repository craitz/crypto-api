package org.hsl.adma.crypto.handlers;

import javax.servlet.http.HttpServletRequest;

import org.hsl.adma.crypto.exceptions.SavingCryptoKeyInDatabaseException;
import org.hsl.adma.crypto.models.ErrorResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExchangeExceptionHandler {
	
	@ExceptionHandler(SavingCryptoKeyInDatabaseException.class)
	public ErrorResponse handleSavingCryptoKeyInDatabaseException(SavingCryptoKeyInDatabaseException ex, HttpServletRequest request) {
		return new ErrorResponse("Server Error", 500L, 1L, "Erro salvando chave de criptografia na base de dados");
	}
}
