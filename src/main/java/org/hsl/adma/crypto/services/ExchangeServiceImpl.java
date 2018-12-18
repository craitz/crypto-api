package org.hsl.adma.crypto.services;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.hsl.adma.crypto.exceptions.BucketDoesNotExistException;
import org.hsl.adma.crypto.models.ExchangeRequest;
import org.hsl.adma.crypto.models.ExchangeResponse;
import org.hsl.adma.crypto.models.UserPoolInfo;
import org.hsl.adma.crypto.repositories.ExchangeRepositoriy;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StreamUtils;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.S3Object;

@Service
public class ExchangeServiceImpl implements ExchangeService {

	@Autowired
	ExchangeRepositoriy exchangeRepository;
	
	private AmazonS3 amazonS3 = AmazonS3ClientBuilder.standard().withRegion("us-east-1").build();
	private UserPoolInfo info;
	
	public ExchangeServiceImpl() throws IOException {
		super();	
		info = getPoolInfo();
	}

	@Override
	public ExchangeResponse exchangeKeys(ExchangeRequest request) {
		BigInteger callerPublicKey = BigInteger.valueOf(Long.parseLong(request.getPublicKey()));
		BigInteger privateKey = generatePrivateKey();
		BigInteger secretKey = generateSecretKey(callerPublicKey, privateKey);
		BigInteger ownerPublicKey = generateExchangeKey(privateKey);
		String expirationDate = generateExpirationDate();
		
		saveSecretKey(request.getDna(), secretKey.toString(), expirationDate);
		
		return new ExchangeResponse(ownerPublicKey.toString(), expirationDate);
	}

	private UserPoolInfo getPoolInfo() throws IOException {
		if (!amazonS3.doesBucketExistV2("adma-config") || !amazonS3.doesObjectExist("adma-config", "cripto-public.json")) {
			throw new BucketDoesNotExistException();
		} else {
			S3Object s3Object = amazonS3.getObject("adma-config", "cripto-public.json");
			InputStream stream = s3Object.getObjectContent();
			String jsonString = StreamUtils.copyToString(stream, StandardCharsets.UTF_8);
			JSONObject json = new JSONObject(jsonString);
			
			BigInteger prime = BigInteger.valueOf(Long.valueOf(json.get("prime").toString()));
			BigInteger base = BigInteger.valueOf(Long.valueOf(json.get("base").toString()));
			
			return new UserPoolInfo(prime, base);
		}
	}
	
	private BigInteger generatePrivateKey() {
		Double innerExpression = ((Math.random() * 9999) + 1);
		Double outterExpression = Math.floor(innerExpression);
		Long longVal = Long.valueOf(Math.round(outterExpression));
		
		return new BigInteger(longVal.toString()); 
	}
	
	private BigInteger generateExchangeKey(BigInteger privateKey) {
		return info.getBase().modPow(privateKey, info.getPrime()); 
	}	
	
	private BigInteger generateSecretKey(BigInteger exchangeKey, BigInteger privateKey) {
		return exchangeKey.modPow(privateKey, info.getPrime()); 
	}
	
	private String generateExpirationDate() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
	    LocalDateTime today = LocalDateTime.now();
	    today = today.plusHours(1);

        return today.format(formatter);
	}
	
	private void saveSecretKey(String dna, String key, String expirationDate) {
		exchangeRepository.saveKey(dna, key, expirationDate);
	}	
}
