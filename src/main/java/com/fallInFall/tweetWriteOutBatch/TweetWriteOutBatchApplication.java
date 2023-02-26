package com.fallInFall.tweetWriteOutBatch;

import com.fallInFall.tweetWriteOutBatch.property.CredentialProperty;
import com.fallInFall.tweetWriteOutBatch.property.RequestToGetTweet;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableBatchProcessing
@ConfigurationPropertiesScan
@EnableConfigurationProperties({RequestToGetTweet.class, CredentialProperty.class})
public class TweetWriteOutBatchApplication {

	public static void main(String[] args) {
		System.exit(SpringApplication.exit(SpringApplication.run(TweetWriteOutBatchApplication.class, args)));
	}

}
