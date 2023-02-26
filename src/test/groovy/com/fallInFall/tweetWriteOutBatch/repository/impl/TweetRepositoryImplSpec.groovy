package com.fallInFall.tweetWriteOutBatch.repository.impl

import com.fallInFall.tweetWriteOutBatch.repository.TweetRepository
import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.http.MediaType
import org.springframework.test.web.client.MockRestServiceServer
import static org.springframework.http.HttpMethod.GET
import static org.springframework.test.web.client.match.MockRestRequestMatchers.method
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess
import org.springframework.web.client.RestTemplate
import spock.lang.Specification
import spock.lang.Unroll

import java.nio.file.Path
import java.nio.file.Paths

class TweetRepositoryImplTestSpec extends Specification {

    RestTemplate restTemplate = new RestTemplate()

    @Unroll
    def "getTweetのテスト"() {
        given:
        "リクエストエンティティの設定"
        TweetRepository tweetRepository = new TweetRepositoryImpl()
        def url = "https://api.twitter.com/1.1/statuses/user_timeline.json"
        Path jsonPath = Paths.get("./src/main/resources/credential.json")
        ObjectMapper mapper = new ObjectMapper()
        JsonNode node
        try {
            node = mapper.readTree(jsonPath.toFile())
        } catch (IOException e) {
            throw new RuntimeException(e)
        }
        def bearerToken = node.get("bearerToken").asText()
        def requestParam = new HashMap<String, String>()
        requestParam.put("userName", "udoku__")
        requestParam.put("count", "2")
        requestParam.put("isTrimmed", "true")
        requestParam.put("isExcluded_replies", "true")
        requestParam.put("isIncluded_rts", "false")

        "MockSeverの設定"
        def responce = "[{\"created_at\":\"Sat Feb 25 06:58:41 +0000 2023\",\"id\":1629375311656919040,\"id_str\":\"1629375311656919040\",\"text\":\"作曲、再会します。春ごろにEP出せる感じで\",\"truncated\":false,\"entities\":{\"hashtags\":[],\"symbols\":[],\"user_mentions\":[],\"urls\":[]},\"source\":\"<a href=\\\"http://twitter.com/download/iphone\\\" rel=\\\"nofollow\\\">Twitter for iPhone</a>\",\"in_reply_to_status_id\":null,\"in_reply_to_status_id_str\":null,\"in_reply_to_user_id\":null,\"in_reply_to_user_id_str\":null,\"in_reply_to_screen_name\":null,\"user\":{\"id\":1559165421819166720,\"id_str\":\"1559165421819166720\"},\"geo\":null,\"coordinates\":null,\"place\":null,\"contributors\":null,\"is_quote_status\":false,\"retweet_count\":0,\"favorite_count\":1,\"favorited\":false,\"retweeted\":false,\"lang\":\"ja\"}]"
        MockRestServiceServer mockServer = MockRestServiceServer.bindTo(restTemplate).build()
        mockServer.expect(requestTo("https://api.twitter.com/1.1/statuses/user_timeline.json?screen_name=udoku__&count=2&trim_user=true&exclude_replies=true&include_rts=false"))
                    .andExpect(method(GET))
                    .andRespond(withSuccess(responce, MediaType.APPLICATION_JSON))
        when:
        def result = tweetRepository.getTweet(url, bearerToken, requestParam, restTemplate)

        then:
        result == responce
        noExceptionThrown()
    }

}