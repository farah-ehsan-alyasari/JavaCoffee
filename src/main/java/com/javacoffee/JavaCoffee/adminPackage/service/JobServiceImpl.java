package com.javacoffee.JavaCoffee.adminPackage.service;

import com.javacoffee.JavaCoffee.adminPackage.entity.Job;
import com.javacoffee.JavaCoffee.adminPackage.repository.JobRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class JobServiceImpl implements  JobService{

    @Autowired
    private JobRepository jobRepository;

    @Override
    public Job saveJob(Job job) {
        log.info("a new job application was saved");
        return jobRepository.save(job);
    }

}
