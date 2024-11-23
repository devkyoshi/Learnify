package com.learnify.backend.masterservice.dao;

import com.learnify.backend.common.constants.Grade;
import jakarta.annotation.Nullable;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;
import java.util.Set;

@Entity
@DiscriminatorValue("TEACHER")
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class Teacher extends User{
    @Nullable
    private String qualification;
    @Nullable
    private String experience;
    @Nullable
    private String subject;
    @Nullable
    @ElementCollection
    private Set<Grade> gradesTeaching;
    @Nullable
    private Double paymentFee;


}
