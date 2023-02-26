package com.fallInFall.tweetWriteOutBatch.configTest;

import com.fallInFall.tweetWriteOutBatch.property.CredentialProperty;
import com.fallInFall.tweetWriteOutBatch.property.RequestToGetTweet;
import org.junit.jupiter.api.Test;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.ConfigDataApplicationContextInitializer;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

@SpringJUnitConfig(initializers = ConfigDataApplicationContextInitializer.class)
@EnableConfigurationProperties({RequestToGetTweet.class, CredentialProperty.class})
public class CorePropertiesTests {

    @Autowired
    RequestToGetTweet requestToGetTweet;

    @Autowired
    CredentialProperty credentialProperty;

    @Test
    void configTest() {
        requestToGetTweet.getRequestURL().equals("https://api.twitter.com/1.1/statuses/user_timeline.json");
        requestToGetTweet.getUserName().equals("udoku__");
        requestToGetTweet.getCount().equals("1");
        requestToGetTweet.getIsTrimmed().equals("true");
        requestToGetTweet.getIsIncluded_rts().equals("false");
        requestToGetTweet.getIsExcluded_replies().equals("true");
        String path = credentialProperty.getPath();
        Assert.assertTrue(path != null);
    }
}
