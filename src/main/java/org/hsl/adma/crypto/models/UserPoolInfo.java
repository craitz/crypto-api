package org.hsl.adma.crypto.models;

import java.math.BigInteger;

public class UserPoolInfo {
	private BigInteger prime;
	private BigInteger base;
	
	public UserPoolInfo(BigInteger prime, BigInteger base) {
		super();
		
		this.prime = prime;
		this.base = base;
	}

	public BigInteger getPrime() {
		return prime;
	}

	public void setPrime(BigInteger prime) {
		this.prime = prime;
	}

	public BigInteger getBase() {
		return base;
	}

	public void setBase(BigInteger base) {
		this.base = base;
	}
	
}
