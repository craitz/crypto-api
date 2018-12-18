package org.hsl.adma.crypto.repositories;

import org.hsl.adma.crypto.exceptions.SavingCryptoKeyInDatabaseException;
import org.springframework.stereotype.Repository;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.Table;

@Repository
public class ExchangeRepositoriy {
    private AmazonDynamoDB client = AmazonDynamoDBClientBuilder.standard().withRegion("us-east-1").build();
    private DynamoDB dynamoDB = new DynamoDB(client);
    private Table table = dynamoDB.getTable("CryptoKeys");
    
    public void saveKey(String dna, String key, String expirationDate) {
		try {
			Item item = new Item()
					.withPrimaryKey("dna", dna)
					.withString("key", key)
	                .withString("expirationDate", expirationDate);
			
			table.putItem(item);			
		} catch (Exception err) {
			throw new SavingCryptoKeyInDatabaseException();
		}		
	}
}
