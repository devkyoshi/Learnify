package com.learnify.backend.security.service;

import com.learnify.backend.common.BaseResponse;
import com.learnify.backend.common.constants.Role;
import com.learnify.backend.common.exceptions.UserAlreadyExistException;
import com.learnify.backend.common.exceptions.UserNotFoundException;
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

import static com.learnify.backend.common.constants.ErrorCodes.USER_ALREADY_EXISTS;
import static com.learnify.backend.common.constants.ErrorCodes.USER_REGISTRATION_FAILED;

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

    public BaseResponse<AuthenticationResponse> registerStudent(StudentRegisterRequest studentRegisterRequest) {
        log.info("Registering student: {}", studentRegisterRequest.getUsername());

        try{
            if (studentRepository.existsByUsername(studentRegisterRequest.getUsername())) {
                log.error("UserName is already taken: {}", studentRegisterRequest.getUsername());
                throw new UserAlreadyExistException(studentRegisterRequest.getUsername());
            }

            if (studentRepository.existsByEmail(studentRegisterRequest.getEmail())) {
                log.error("Email exists.: {}", studentRegisterRequest.getEmail());
                throw new UserAlreadyExistException(studentRegisterRequest.getUsername());
            }
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

            // Generate token
            var jwtToken = jwtTokenProvider.generateToken(student);
            log.info("Student registered successfully: {}", student.getUsername());
            AuthenticationResponse authResponse = AuthenticationResponse.builder().token(jwtToken).role(String.valueOf(student.getRole())).build();
            // Return the token
            return new BaseResponse<>(true, authResponse, "Student registered successfully");

        } catch (UserAlreadyExistException e) {
            log.error("Student: {} already exists", studentRegisterRequest.getUsername() );
            return new BaseResponse<>(USER_ALREADY_EXISTS);
        } catch (Exception e) {
            log.error("Student registration failed: {}", studentRegisterRequest.getUsername() + ": " + e.getMessage());
            return new BaseResponse<>(USER_REGISTRATION_FAILED);
        }
    }

    public BaseResponse<AuthenticationResponse> registerTeacher(TeacherRegisterRequest teacherRegisterRequest) {
        log.info("Registering teacher: {}", teacherRegisterRequest.getUsername());

       try {
           if (teacherRepository.existsByUsername(teacherRegisterRequest.getUsername())) {
               log.error("Username is already taken: {}", teacherRegisterRequest.getUsername());
               throw new UserAlreadyExistException(teacherRegisterRequest.getUsername());
           }

           if (teacherRepository.existsByEmail(teacherRegisterRequest.getEmail())) {
               log.error("Email is already taken: {}", teacherRegisterRequest.getEmail());
               throw new UserAlreadyExistException(teacherRegisterRequest.getUsername());
           }

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
           AuthenticationResponse authResponse = AuthenticationResponse.builder().token(jwtToken).role(String.valueOf(teacher.getRole())).build();
           // Return the token
           return new BaseResponse<>(true, authResponse, "Teacher registered successfully");
       }catch (UserAlreadyExistException e) {
           log.error("Teacher already exists: {}", teacherRegisterRequest.getUsername() );
           return new BaseResponse<>(USER_ALREADY_EXISTS);
       } catch (Exception e) {
           log.error("Teacher registration failed: {}", teacherRegisterRequest.getUsername() + ": " + e.getMessage());
           return new BaseResponse<>(USER_REGISTRATION_FAILED);
       }
    }

    public BaseResponse<AuthenticationResponse> login(AuthenticationRequest authRequest) {
        try{
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
        } catch (Exception e) {
            log.error("Invalid username/password supplied: {}", authRequest.getUsername());
            return new BaseResponse<>(false, null, "Invalid username/password supplied");
        }

        var user = userRepository.findByUsername(authRequest.getUsername())
                .orElseThrow(() -> {
                    log.error("User not found: {}", authRequest.getUsername());
                    return new UserNotFoundException(authRequest.getUsername());
                });

        // Generate token
        var jwtToken = jwtTokenProvider.generateToken(user);

        log.info("User logged in successfully: {}", user.getUsername());

        // Return the token
        AuthenticationResponse authResponse =  AuthenticationResponse.builder().token(jwtToken).userId(String.valueOf(user.getId())).role(String.valueOf(user.getRole())).build();
        return new BaseResponse<>(true, authResponse, "User logged in successfully");
    }


}
