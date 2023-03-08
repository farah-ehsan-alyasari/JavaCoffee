package com.javacoffee.JavaCoffee.adminPackage.repository;

import com.javacoffee.JavaCoffee.adminPackage.entity.Job;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobRepository extends JpaRepository<Job, Long> {
}
