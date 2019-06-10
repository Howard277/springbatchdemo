package com.mars.springbatchdemo;

import org.springframework.batch.core.*;
import org.springframework.batch.core.configuration.JobRegistry;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.Date;

import static java.lang.String.format;

/**
 * 批处理主程序
 */
@SpringBootApplication
@EnableTransactionManagement
public class Application {
    public static void main(String[] args) {
//        SpringApplication.run(Application.class);
        String jobName = "messageMigrationJob";
        try {
            ConfigurableApplicationContext context = SpringApplication.run(Application.class, args);
            JobRegistry jobRegistry = context.getBean(JobRegistry.class);
            Job job = jobRegistry.getJob(jobName);
            JobLauncher jobLauncher = context.getBean(JobLauncher.class);
            JobExecution jobExecution = jobLauncher.run(job, createJobParams());
            if (!jobExecution.getExitStatus().equals(ExitStatus.COMPLETED)) {
                throw new RuntimeException(format("%s Job execution failed.", jobName));
            }
        } catch (Exception e) {
            throw new RuntimeException(format("%s Job execution failed.", jobName));
        }
    }

    private static JobParameters createJobParams() {
        return new JobParametersBuilder().addDate("date", new Date()).toJobParameters();
    }
}
