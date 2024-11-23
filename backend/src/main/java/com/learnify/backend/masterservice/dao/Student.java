package com.learnify.backend.masterservice.dao;

import com.learnify.backend.common.constants.Grade;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.Set;

@Entity
@DiscriminatorValue("STUDENT")
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Getter
@Setter
public class Student extends User{

    @Enumerated(EnumType.STRING)
    private Grade grade;

    @ManyToMany(mappedBy = "students")
    private Set<Course> enrolledCourses;
}
