package org.hsl.adma.crypto.models;

import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ExchangeRequest {
	
	@NotEmpty(message = "O campo dna é obrigatório")
	private String dna;

	@NotEmpty(message = "O campo publicKey é obrigatório")
	private String publicKey;
	
	public String getDna() {
		return dna;
	}
	
	public void setDna(String dna) {
		this.dna = dna;
	}
	
	public String getPublicKey() {
		return publicKey;
	}
	
	public void setPublicKey(String publicKey) {
		this.publicKey = publicKey;
	}
}
