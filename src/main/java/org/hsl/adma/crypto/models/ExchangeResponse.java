package org.hsl.adma.crypto.models;

public class ExchangeResponse {
	private String publicKey;
	private String expirationDate;
	
	public ExchangeResponse() {
		super();
	}
	
	public ExchangeResponse(String publicKey, String expirationDate) {
		this.publicKey = publicKey;
		this.expirationDate = expirationDate;
	}
	
	public String getPublicKey() {
		return publicKey;
	}
	
	public void setPublicKey(String publicKey) {
		this.publicKey = publicKey;
	}
	
	public String getExpirationDate() {
		return expirationDate;
	}
	
	public void setExpirationDate(String expirationDate) {
		this.expirationDate = expirationDate;
	}
}
