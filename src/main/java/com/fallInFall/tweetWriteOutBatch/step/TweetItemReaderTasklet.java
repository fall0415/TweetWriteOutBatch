package com.fallInFall.tweetWriteOutBatch.step;

import com.fallInFall.tweetWriteOutBatch.service.TweetDataWriterService;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;

import com.fallInFall.tweetWriteOutBatch.service.TweetItemReaderService;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TweetItemReaderTasklet implements Tasklet{
    private final TweetItemReaderService tweetItemReaderService;

    private final TweetDataWriterService tweetDataWriterService;

    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) {
        String tweetData = tweetItemReaderService.callTwitterApi();
        tweetDataWriterService.writeOut(tweetData);

        return RepeatStatus.FINISHED;
    }
}
