package com.fallInFall.tweetWriteOutBatch.repository;

import org.springframework.web.client.RestTemplate;

import java.util.Map;

public interface TweetRepository {
    String getTweet(String requestUrl, String bearerToken, Map<String, String> requestParam, RestTemplate restTemplate);
}
