package com.learnify.backend.security.controller;

import com.learnify.backend.security.dto.AuthenticationRequest;
import com.learnify.backend.security.dto.AuthenticationResponse;
import com.learnify.backend.security.dto.StudentRegisterRequest;
import com.learnify.backend.security.service.AuthenticationService;
import com.learnify.backend.teacher.dto.TeacherRegisterRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
@Slf4j
public class AuthenticationController {

   private final AuthenticationService authenticationService;

    @PostMapping("/register/student")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<AuthenticationResponse> registerStudent(@RequestBody StudentRegisterRequest studentRegisterRequest) {
        log.info("Registering student: {}", studentRegisterRequest.getUsername());
        return ResponseEntity.ok(authenticationService.registerStudent(studentRegisterRequest));
    }
    @PostMapping("/register/teacher")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<AuthenticationResponse> registerTeacher(@RequestBody TeacherRegisterRequest teacherRegisterRequest) {
        log.info("Registering teacher: {}", teacherRegisterRequest.getUsername());
        return ResponseEntity.ok(authenticationService.registerTeacher(teacherRegisterRequest));
    }

    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<AuthenticationResponse> login (@RequestBody AuthenticationRequest authenticationRequest) {
        log.info("Logging in user: {}", authenticationRequest.getUsername());
        return ResponseEntity.ok(authenticationService.login(authenticationRequest));
    }
}
