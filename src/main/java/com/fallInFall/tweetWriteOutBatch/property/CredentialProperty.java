package com.fallInFall.tweetWriteOutBatch.property;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "twitter-api.bearer-token")
public class CredentialProperty {
   private String path;
}
