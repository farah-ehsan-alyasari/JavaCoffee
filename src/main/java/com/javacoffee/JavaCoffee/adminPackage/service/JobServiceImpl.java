package com.javacoffee.JavaCoffee.adminPackage.service;

import com.javacoffee.JavaCoffee.adminPackage.entity.Job;
import com.javacoffee.JavaCoffee.adminPackage.repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JobServiceImpl implements  JobService{

    @Autowired
    private JobRepository jobRepository;

    @Override
    public Job saveJob(Job job) {
        return jobRepository.save(job);
    }

}
