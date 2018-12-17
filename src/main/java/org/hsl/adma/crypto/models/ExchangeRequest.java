package org.hsl.adma.crypto.models;

public class ExchangeRequest {
	private Long userId;
	private String dna;
	private String publicKey;
	
	public Long getUserId() {
		return userId;
	}
	
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	
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
