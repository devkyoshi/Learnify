package com.learnify.backend.masterservice.dao;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Entity
@DiscriminatorValue("TEACHER")
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class Teacher extends User{
    private String qualification;
    private String experience;
    private String subject;
    private List<String> gradesTeaching;
    private double paymentFee;


}
