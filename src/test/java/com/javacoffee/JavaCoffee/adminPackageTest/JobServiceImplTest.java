package com.javacoffee.JavaCoffee.adminPackageTest;

import com.javacoffee.JavaCoffee.JavaCoffeeApplication;
import com.javacoffee.JavaCoffee.adminPackage.entity.Job;
import com.javacoffee.JavaCoffee.adminPackage.exception.ItemNotFoundException;
import com.javacoffee.JavaCoffee.adminPackage.repository.JobRepository;
import com.javacoffee.JavaCoffee.adminPackage.service.JobService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@SpringBootTest(classes = JavaCoffeeApplication.class)
public class JobServiceImplTest {


    @Autowired
    JobService jobService;

    @Autowired
    JobRepository jobRepository;


    private static List<Job> provideJobData(){

        List<Job> jobList = new ArrayList<>();

        Job job1 = new Job("FN LN 1", "TESTFILE1.pdf");
        Job job2 = new Job("FN LN 2", "TESTFILE2.pdf");
        Job job3 = new Job("FN LN 3", "TESTFILE3.pdf");

        jobList.add(job1);
        jobList.add(job2);
        jobList.add(job3);

        return jobList;
    }

    @ParameterizedTest
    @MethodSource("provideJobData")
    public void testSaveJob(Job job){
        Job savedJob = jobService.saveJob(job);

        Optional<Job> retrievedJob = jobRepository.findById(savedJob.getId());

        if (retrievedJob.isPresent()) {
            Job retrievedJobObject = retrievedJob.get();
            Assertions.assertEquals(retrievedJobObject.getId(), savedJob.getId());

            //then delete the item that has been saved because it is only for testing
            jobRepository.delete(savedJob);
        } else {
            throw new ItemNotFoundException();
        }
    }

}
