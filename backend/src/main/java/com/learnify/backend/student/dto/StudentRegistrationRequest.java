package com.learnify.backend.student.dto;


import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentRegistrationRequest {
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
    private String grade;

    @Override
    public String toString() {
        return "StudentRegisterRequest{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

}
