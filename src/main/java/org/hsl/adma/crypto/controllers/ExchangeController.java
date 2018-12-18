package org.hsl.adma.crypto.controllers;

import javax.validation.Valid;

import org.hsl.adma.crypto.models.ExchangeRequest;
import org.hsl.adma.crypto.models.ExchangeResponse;
import org.hsl.adma.crypto.services.ExchangeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/crypto")
public class ExchangeController {
	
	@Autowired
	private ExchangeService exchangeService;
	
	@RequestMapping("/exchange-keys")
	public ResponseEntity<ExchangeResponse> exchangeKeys(@Valid @RequestBody ExchangeRequest request) {
		return ResponseEntity.ok().body(exchangeService.exchangeKeys(request));
	}
}
