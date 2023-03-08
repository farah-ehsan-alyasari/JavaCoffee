package com.javacoffee.JavaCoffee.adminPackage.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name="JobApplication")
public class Job {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String FullName;

    @Column(name="uploadedFileName")
    private String uploadedFile;
}