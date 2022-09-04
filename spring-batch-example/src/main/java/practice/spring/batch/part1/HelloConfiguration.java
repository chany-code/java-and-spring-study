package practice.spring.batch.part1;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
public class HelloConfiguration {

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;

    public HelloConfiguration(JobBuilderFactory jobBuilderFactory, StepBuilderFactory stepBuilderFactory) {
        this.jobBuilderFactory = jobBuilderFactory;
        this.stepBuilderFactory = stepBuilderFactory;
    }

//  Job은 Spring 배치의 실행단위
    @Bean
    public Job helloJob(){
        return jobBuilderFactory.get("helloJob")
                .incrementer(new RunIdIncrementer()) // Job이 시작할때마다 parameter ID를 생성해주는 i
                .start(this.helloStep())
                .build();
    }

//  Step은 Job의 실행단위. 하나의 잡은 N개의 Step을 가질 수 있음.
    @Bean
    public Step helloStep(){
        return stepBuilderFactory.get("helloStep")
                .tasklet((contribution, chunkContext) -> { // Step은 Tasklet이라는 객체로 처리할 수 있음
                    log.info("hello spring batch");
                    return RepeatStatus.FINISHED;
                }).build();
    }
}
