package com.fallInFall.tweetWriteOutBatch.service.impl

import com.fallInFall.tweetWriteOutBatch.service.TweetDataWriterService
import spock.lang.Specification
import spock.lang.Unroll

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class TweetDataWriterServiceImplSpec extends Specification {

    TweetDataWriterService tweetDataWriterService = new TweetDataWriterServiceImpl()

    @Unroll
    def "writeOut()のテスト"() {
        given:
        LocalDateTime localDateTime = LocalDateTime.now()
        def currentDate = localDateTime.format(DateTimeFormatter.ISO_LOCAL_DATE)
        def tweetData = "[{\"created_at\":\"Sat Feb 04 13:28:42 +0000 2023\",\"id\":1621863318704230406,\"id_str\":\"1621863318704230406\",\"text\":\"New JeansのDittoをPost Rockぽくしてみた\\n\\n#뉴진스 #NewJeans #Ditto https://t.co/xPHvszJySC\",\"truncated\":false,\"entities\":{\"hashtags\":[{\"text\":\"뉴진스\",\"indices\":[33,37]},{\"text\":\"NewJeans\",\"indices\":[38,47]},{\"text\":\"Ditto\",\"indices\":[48,54]}],\"symbols\":[],\"user_mentions\":[],\"urls\":[],\"media\":[{\"id\":1621143406339825664,\"id_str\":\"1621143406339825664\",\"indices\":[55,78],\"media_url\":\"http://pbs.twimg.com/media/FoIEDHsWIAMWH4F.jpg\",\"media_url_https\":\"https://pbs.twimg.com/media/FoIEDHsWIAMWH4F.jpg\",\"url\":\"https://t.co/xPHvszJySC\",\"display_url\":\"pic.twitter.com/xPHvszJySC\",\"expanded_url\":\"https://twitter.com/udoku__/status/1621863318704230406/video/1\",\"type\":\"photo\",\"sizes\":{\"thumb\":{\"w\":150,\"h\":150,\"resize\":\"crop\"},\"small\":{\"w\":680,\"h\":680,\"resize\":\"fit\"},\"medium\":{\"w\":1170,\"h\":1170,\"resize\":\"fit\"},\"large\":{\"w\":1170,\"h\":1170,\"resize\":\"fit\"}}}]},\"extended_entities\":{\"media\":[{\"id\":1621143406339825664,\"id_str\":\"1621143406339825664\",\"indices\":[55,78],\"media_url\":\"http://pbs.twimg.com/media/FoIEDHsWIAMWH4F.jpg\",\"media_url_https\":\"https://pbs.twimg.com/media/FoIEDHsWIAMWH4F.jpg\",\"url\":\"https://t.co/xPHvszJySC\",\"display_url\":\"pic.twitter.com/xPHvszJySC\",\"expanded_url\":\"https://twitter.com/udoku__/status/1621863318704230406/video/1\",\"type\":\"video\",\"sizes\":{\"thumb\":{\"w\":150,\"h\":150,\"resize\":\"crop\"},\"small\":{\"w\":680,\"h\":680,\"resize\":\"fit\"},\"medium\":{\"w\":1170,\"h\":1170,\"resize\":\"fit\"},\"large\":{\"w\":1170,\"h\":1170,\"resize\":\"fit\"}},\"video_info\":{\"aspect_ratio\":[16,9],\"duration_millis\":136234,\"variants\":[{\"bitrate\":832000,\"content_type\":\"video/mp4\",\"url\":\"https://video.twimg.com/amplify_video/1621143406339825664/vid/640x360/jYvOJpT5Bz9TpdWc.mp4?tag=14\"},{\"content_type\":\"application/x-mpegURL\",\"url\":\"https://video.twimg.com/amplify_video/1621143406339825664/pl/SquS-_BDWZdXSkfs.m3u8?tag=14&container=fmp4\"},{\"bitrate\":288000,\"content_type\":\"video/mp4\",\"url\":\"https://video.twimg.com/amplify_video/1621143406339825664/vid/480x270/HPSPPlj-cTW2cJMj.mp4?tag=14\"},{\"bitrate\":2176000,\"content_type\":\"video/mp4\",\"url\":\"https://video.twimg.com/amplify_video/1621143406339825664/vid/1280x720/-3nJI7eGhtm0BO_C.mp4?tag=14\"}]},\"additional_media_info\":{\"title\":\"\",\"description\":\"\",\"embeddable\":true,\"monetizable\":false}}]},\"source\":\"<a href=\\\"https://studio.twitter.com\\\" rel=\\\"nofollow\\\">Twitter Media Studio</a>\",\"in_reply_to_status_id\":null,\"in_reply_to_status_id_str\":null,\"in_reply_to_user_id\":null,\"in_reply_to_user_id_str\":null,\"in_reply_to_screen_name\":null,\"user\":{\"id\":1559165421819166720,\"id_str\":\"1559165421819166720\"},\"geo\":null,\"coordinates\":null,\"place\":null,\"contributors\":null,\"is_quote_status\":false,\"retweet_count\":4,\"favorite_count\":7,\"favorited\":false,\"retweeted\":false,\"possibly_sensitive\":false,\"lang\":\"ja\"}]"
        def fileName = "tweetData_" + currentDate + ".json"
        def filePath = "./tweetData/" + fileName
        File result = new File(filePath)
        when:
        tweetDataWriterService.writeOut(tweetData)

        then:
        result.exists() == true
        noExceptionThrown()

    }

}
