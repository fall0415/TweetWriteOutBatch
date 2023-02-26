package com.fallInFall.tweetWriteOutBatch.config;

import com.fallInFall.tweetWriteOutBatch.step.TweetItemReaderTasklet;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class TweetWriteOutBatchConfigretion {
    private final JobBuilderFactory jobBUilderFactory;

    private final StepBuilderFactory stepBuilderFactory;

    private final TweetItemReaderTasklet tweetItemReaderTasklet;

    @Bean
    public Job tweetWriteOutBatchJob(Step tweetItemReaderStep) {
        return jobBUilderFactory.get("tweetWriteOutBatchJob").flow(tweetItemReaderStep).end().build();

    }
    @Bean
    public Step tweetItemReaderStep() {
        return stepBuilderFactory.get("tweetItemReaderStep").tasklet(tweetItemReaderTasklet).build();
    }

}
