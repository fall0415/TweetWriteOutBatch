package com.fallInFall.tweetWriteOutBatch.service.impl;

import com.fallInFall.tweetWriteOutBatch.property.CredentialProperty;
import com.fallInFall.tweetWriteOutBatch.property.RequestToGetTweet;
import com.fallInFall.tweetWriteOutBatch.repository.TweetRepository;
import com.fallInFall.tweetWriteOutBatch.service.TweetItemReaderService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class TweetItemReaderServiceImpl implements TweetItemReaderService {

    private final TweetRepository tweetRepository;

    private final RestTemplateBuilder restTemplateBuilder;

    @Autowired
    RequestToGetTweet requestToGetTweet;

    @Autowired
    CredentialProperty credentialProperty;

    @Override
    public String callTwitterApi() {
        RestTemplate restTemplate = restTemplateBuilder.build();
        String url = requestToGetTweet.getRequestURL();
        String bearerToken = getBearerToken();
        Map<String, String> requestParam = mapValueToRequestEntity();

        return tweetRepository.getTweet(url, bearerToken, requestParam, restTemplate);
    }

    private String getBearerToken() {
        Path jsonPath = Paths.get(credentialProperty.getPath());
        ObjectMapper mapper = new ObjectMapper();
        JsonNode node;
        try {
            node = mapper.readTree(jsonPath.toFile());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return node.get("bearerToken").asText();
    }

    private Map<String, String> mapValueToRequestEntity() {
        Map<String, String> requestParam = new HashMap<>();
        requestParam.put("userName", requestToGetTweet.getUserName());
        requestParam.put("count", requestToGetTweet.getCount());
        requestParam.put("isTrimmed", requestToGetTweet.getIsTrimmed());
        requestParam.put("isExcluded_replies", requestToGetTweet.getIsExcluded_replies());
        requestParam.put("isIncluded_rts", requestToGetTweet.getIsIncluded_rts());
        return  requestParam;
    }
}
