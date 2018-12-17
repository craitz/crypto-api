package org.hsl.adma.crypto.services;

import java.math.BigInteger;
import java.time.LocalDateTime;

import org.hsl.adma.crypto.models.ExchangeRequest;
import org.hsl.adma.crypto.models.ExchangeResponse;
import org.springframework.stereotype.Service;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.Table;

@Service
public class ExchangeServiceImpl implements ExchangeService {

	private static final BigInteger prime = BigInteger.valueOf(14731);
	private static final BigInteger base = BigInteger.valueOf(5);
    private static AmazonDynamoDB client = AmazonDynamoDBClientBuilder.standard().withRegion("us-east-1").build();
    private static DynamoDB dynamoDB = new DynamoDB(client);
    
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
	
	private BigInteger generatePrivateKey() {
		Double innerExpression = ((Math.random() * 9999) + 1);
		Double outterExpression = Math.floor(innerExpression);
		Long longVal = Long.valueOf(Math.round(outterExpression));
		
		return new BigInteger(longVal.toString()); 
	}
	
	private BigInteger generateExchangeKey(BigInteger privateKey) {
		return base.modPow(privateKey, prime); 
	}	
	
	private BigInteger generateSecretKey(BigInteger exchangeKey, BigInteger privateKey) {
		return exchangeKey.modPow(privateKey, prime); 
	}
	
	private String generateExpirationDate() {
	    LocalDateTime today = LocalDateTime.now();
	    today = today.plusHours(1);

		return today.toString();
	}
	
	private void saveSecretKey(String dna, String key, String expirationDate) {
		Table table = dynamoDB.getTable("CryptoKeys");
		
		try {
			Item item = new Item()
					.withPrimaryKey("dna", dna)
					.withString("key", "key")
	                .withString("expirationDate", "expirationDate");
			
			table.putItem(item);			
		} catch (Exception err) {
			System.out.println(err);
		}
	}	
}
