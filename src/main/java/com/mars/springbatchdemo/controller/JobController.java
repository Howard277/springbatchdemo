package com.mars.springbatchdemo.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.configuration.JobRegistry;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@Slf4j
@RestController
@RequestMapping("job")
public class JobController {

    @Autowired
    private JobLauncher jobLauncher;
    @Autowired
    private JobRegistry jobRegistry;

    @GetMapping("launchJob")
    public String launchJob(@RequestParam(defaultValue = "messageMigrationJob") String jobName) {
        String result = "OK";
        try {
            Job job = jobRegistry.getJob(jobName);
            if (null != job) {
                jobLauncher.run(job, new JobParametersBuilder().addDate("date", new Date()).toJobParameters());
            } else {
                result = "job is null";
            }
        } catch (Exception ex) {
            log.error("{}", ex);
            result = "error";
        }
        return result;
    }
}
