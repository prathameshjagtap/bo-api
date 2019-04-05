package com.blendorders.api;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;

@Configuration
public class DbConfig {

	@Value("${aws_region}")
	public String region;
	
	@Value("${secret_key}")
	public String secretKey;
	
	@Value("${access_key_id}")
	public String accessKeyId;
	
	@Bean
	public DynamoDB getDynamoDB(){
		
		AWSCredentialsProvider credentialsProvider = new AWSCredentialsProvider() {
			
			@Override
			public void refresh() {}
			
			@Override
			public AWSCredentials getCredentials() {
				return new AWSCredentials() {
					
					@Override
					public String getAWSSecretKey() {
						return secretKey;
					}
					
					@Override
					public String getAWSAccessKeyId() {
						return accessKeyId;
					}
				};
			}
		};
		
		AmazonDynamoDBClientBuilder	builder = AmazonDynamoDBClientBuilder.standard()
	            .withRegion(region);
		
		if(accessKeyId != null)
            builder = builder.withCredentials(credentialsProvider);
			
		AmazonDynamoDB client = builder.build();
		
		return new DynamoDB(client);
	}
	
}
