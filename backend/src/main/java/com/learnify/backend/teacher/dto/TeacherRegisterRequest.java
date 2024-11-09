package com.learnify.backend.teacher.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TeacherRegisterRequest {
    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private String email;
    private String phone;
    private String address;
    private String city;
    private String district;
    private String zip;
    private String profilePic;
    private String qualification;
    private String experience;
    private String subject;
    private List<String> gradesTeaching;
    private double paymentFee;
}
