package com.fallInFall.tweetWriteOutBatch.service.impl;

import com.fallInFall.tweetWriteOutBatch.service.TweetDataWriterService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;

@Service
@RequiredArgsConstructor
public class TweetDataWriterServiceImpl implements TweetDataWriterService {

    private final String dirPathToWriteOut = "./tweetData";

    final static Logger logger = LoggerFactory.getLogger(TweetDataWriterServiceImpl.class);
    @Override
    public void writeOut(String tweetData) {
        LocalDateTime localDateTime = LocalDateTime.now();
        String currentDate = localDateTime.format(DateTimeFormatter.ISO_LOCAL_DATE);
        String fileName = "tweetData_" + currentDate + ".json";
        Path filePath = Path.of(dirPathToWriteOut, fileName);
        try {
            if(!Files.exists(Path.of(dirPathToWriteOut))) {
                Files.createDirectory(Path.of(dirPathToWriteOut));
            }
            if(Files.exists(filePath)) {
                logger.info("今日分のデータファイルはすでに存在するためファイルを作成せずに処理を終了します");
            } else {
                Files.createFile(filePath);
                Files.write(filePath, Collections.singleton(tweetData), Charset.forName("UTF-8"));
            }
        } catch (IOException e) {
            logger.error("ファイルの書き出しに失敗しました");
        }
    }
}
