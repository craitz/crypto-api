package org.hsl.adma.crypto.services;

import org.hsl.adma.crypto.models.ExchangeRequest;
import org.hsl.adma.crypto.models.ExchangeResponse;

public interface ExchangeService {
	public ExchangeResponse exchangeKeys(ExchangeRequest request);
}
