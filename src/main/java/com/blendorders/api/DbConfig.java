package com.blendorders.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.util.StringUtils;

@Configuration
public class DbConfig {

	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Value("${aws.region}")
	public String region;
	
	@Value("${aws.secret_key}")
	public String secretKey;
	
	@Value("${aws.access_key_id}")
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
		
//		if( StringUtils.isNullOrEmpty(accessKeyId) || accessKeyId.equals("nokey")){
//			logger.info("Default credentials will be used");
//		} else {
//            builder = builder.withCredentials(credentialsProvider);
//			logger.info("Environment credentials will be used :{}", accessKeyId);
//		}
			
		AmazonDynamoDB client = builder.build();
		
		return new DynamoDB(client);
	}
	
}
