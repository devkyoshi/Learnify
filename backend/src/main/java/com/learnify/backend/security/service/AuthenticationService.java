package com.learnify.backend.security.service;

import com.learnify.backend.common.constants.Role;
import com.learnify.backend.masterservice.dao.Student;
import com.learnify.backend.masterservice.dao.Teacher;
import com.learnify.backend.masterservice.repository.UserRepository;
import com.learnify.backend.masterservice.repository.StudentRepository;
import com.learnify.backend.masterservice.repository.TeacherRepository;
import com.learnify.backend.security.dto.AuthenticationRequest;
import com.learnify.backend.security.dto.AuthenticationResponse;
import com.learnify.backend.security.dto.StudentRegisterRequest;
import com.learnify.backend.teacher.dto.TeacherRegisterRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository userRepository;
    private final StudentRepository studentRepository;
    private final TeacherRepository teacherRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;
    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse registerStudent(StudentRegisterRequest studentRegisterRequest) {
        log.info("Registering student: {}", studentRegisterRequest.getUsername());


        Student student = Student.builder()
                .firstName(studentRegisterRequest.getFirstName())
                .lastName(studentRegisterRequest.getLastName())
                .username(studentRegisterRequest.getUsername())
                .password(passwordEncoder.encode(studentRegisterRequest.getPassword()))
                .email(studentRegisterRequest.getEmail())
                .role(Role.STUDENT)
                .phone(studentRegisterRequest.getPhone())
                .address(studentRegisterRequest.getAddress())
                .city(studentRegisterRequest.getCity())
                .district(studentRegisterRequest.getDistrict())
                .zip(studentRegisterRequest.getZip())
                .profilePic(studentRegisterRequest.getProfilePic())
                .grade(studentRegisterRequest.getGrade())
                .build();

        // Save the student
        studentRepository.save(student);

        var jwtToken = jwtTokenProvider.generateToken(student);
        log.info("Student registered successfully: {}", student.getUsername());

        // Return the token
        return AuthenticationResponse.builder().token(jwtToken).role(String.valueOf(student.getRole())).build();
    }

    public AuthenticationResponse registerTeacher(TeacherRegisterRequest teacherRegisterRequest) {
        log.info("Registering teacher: {}", teacherRegisterRequest.getUsername());

        Teacher teacher = Teacher.builder()
                .firstName(teacherRegisterRequest.getFirstName())
                .lastName(teacherRegisterRequest.getLastName())
                .username(teacherRegisterRequest.getUsername())
                .password(passwordEncoder.encode(teacherRegisterRequest.getPassword()))
                .email(teacherRegisterRequest.getEmail())
                .role(Role.TEACHER)
                .phone(teacherRegisterRequest.getPhone())
                .address(teacherRegisterRequest.getAddress())
                .city(teacherRegisterRequest.getCity())
                .district(teacherRegisterRequest.getDistrict())
                .zip(teacherRegisterRequest.getZip())
                .profilePic(teacherRegisterRequest.getProfilePic())
                .qualification(teacherRegisterRequest.getQualification())
                .experience(teacherRegisterRequest.getExperience())
                .subject(teacherRegisterRequest.getSubject())
                .gradesTeaching(teacherRegisterRequest.getGradesTeaching())
                .paymentFee(teacherRegisterRequest.getPaymentFee())
                .build();

        // Save the teacher
        teacherRepository.save(teacher);

        var jwtToken = jwtTokenProvider.generateToken(teacher);
        log.info("Teacher registered successfully: {}", teacher.getUsername());

        // Return the token
        return AuthenticationResponse.builder().token(jwtToken).role(String.valueOf(teacher.getRole())).build();
    }

    public AuthenticationResponse login(AuthenticationRequest authRequest) {
        try{
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
        } catch (Exception e) {
            log.error("Invalid username/password supplied: {}", authRequest.getUsername());
            throw new RuntimeException("Authentication failed");
        }

        var user = userRepository.findByUsername(authRequest.getUsername())
                .orElseThrow(() -> {
                    log.error("User not found: {}", authRequest.getUsername());
                    return new RuntimeException("User not found");
                });

        // Generate token
        var jwtToken = jwtTokenProvider.generateToken(user);

        log.info("User logged in successfully: {}", user.getUsername());

        // Return the token
        return AuthenticationResponse.builder().token(jwtToken).userId(String.valueOf(user.getId())).role(String.valueOf(user.getRole())).build();
    }


}
