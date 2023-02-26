package com.fallInFall.tweetWriteOutBatch.property;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "twitter-api.statuses.param")
public class RequestToGetTweet {
    private String requestURL;

    private String userName;

    private String count;

    private String isTrimmed;

    private String isExcluded_replies;

    private String isIncluded_rts;
}
