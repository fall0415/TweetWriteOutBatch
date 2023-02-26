package com.fallInFall.tweetWriteOutBatch.repository.impl;

import com.fallInFall.tweetWriteOutBatch.repository.TweetRepository;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Map;

@Repository
@RequiredArgsConstructor
public class TweetRepositoryImpl implements TweetRepository {

    @Override
    public String getTweet(String requestUrl, String bearerToken, Map<String, String> requestParam, RestTemplate restTemplate) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Authorization", "Bearer " + bearerToken);
        HttpEntity<String> httpEntity = new HttpEntity<>(httpHeaders);

        String url = UriComponentsBuilder.fromHttpUrl(requestUrl)
                                            .queryParam("screen_name", "{userName}")
                                            .queryParam("count", "{count}")
                                            .queryParam("trim_user", "{isTrimmed}")
                                            .queryParam("exclude_replies", "{isExcluded_replies}")
                                            .queryParam("include_rts", "{isIncluded_rts}")
                                            .encode()
                                            .toUriString();

        ResponseEntity<JsonNode> responseEntity = restTemplate.exchange(url, HttpMethod.GET, httpEntity, JsonNode.class, requestParam);

        return String.valueOf(responseEntity.getBody());
    }
}
