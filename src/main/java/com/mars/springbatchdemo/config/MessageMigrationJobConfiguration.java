package com.mars.springbatchdemo.config;

import com.fasterxml.jackson.core.JsonParseException;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.database.JpaItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.FileSystemResource;

import javax.persistence.EntityManagerFactory;
import java.io.File;

public class MessageMigrationJobConfiguration {

    private final static Integer CHUNK_SIZE = 10;
    private final static Integer SKIP_LIMIT = 0;
    private final static String MESSAGE_FILE = "/Users/wuketao/Desktop/batch.txt";
    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;
    @Autowired
    private MessageItemReadListener messageItemReadListener;
    @Autowired
    private MessageItemWriteListener messageWriteListener;
    @Autowired
    private EntityManagerFactory entityManager;

    @Bean
    public JpaItemWriter messageItemWriter() {
        JpaItemWriter writer = new JpaItemWriter();
        writer.setEntityManagerFactory(entityManager);
        return writer;
    }

    @Bean
    public FlatFileItemReader messageItemReader() {
        FlatFileItemReader reader = new FlatFileItemReader();
        reader.setResource(new FileSystemResource(new File(MESSAGE_FILE)));
        reader.setLineMapper(new MessageLineMapper());
        return reader;
    }

    @Bean(name = "messageMigrationJob")
    public Job messageMigrationJob(@Qualifier("messageMigrationStep") Step messageMigrationStep) {
        return jobBuilderFactory.get("messageMigrationJob").start(messageMigrationStep).build();
    }

    @Bean
    public Step messageMigrationStep(@Qualifier("messageItemReader") FlatFileItemReader messageItemReader,
                                     @Qualifier("messageItemWriter") JpaItemWriter messageItemWriter) {
        return stepBuilderFactory.get("messageMigrationStep")
                .chunk(CHUNK_SIZE)
                .reader(messageItemReader).faultTolerant().skip(JsonParseException.class).skipLimit(SKIP_LIMIT)
                .listener(messageItemReadListener)
                .writer(messageItemWriter).faultTolerant().skip(Exception.class).skipLimit(SKIP_LIMIT)
                .listener(messageWriteListener)
                .build();
    }
}
