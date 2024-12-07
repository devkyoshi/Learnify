package com.learnify.backend.security.controller;

import com.learnify.backend.common.BaseResponse;
import com.learnify.backend.security.dto.AuthenticationRequest;
import com.learnify.backend.security.dto.AuthenticationResponse;
import com.learnify.backend.security.service.AuthenticationService;
import com.learnify.backend.student.dto.StudentRegistrationRequest;
import com.learnify.backend.teacher.dto.TeacherRegisterRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
@Slf4j
public class AuthenticationController {

   private final AuthenticationService authenticationService;

    @PostMapping("/register/student")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<BaseResponse<AuthenticationResponse>>  registerStudent(@RequestBody StudentRegistrationRequest studentRegisterRequest) {
        log.info("Registering student: {}", studentRegisterRequest.getUsername());
        var response = authenticationService.registerStudent(studentRegisterRequest);

        return response.success() ? ResponseEntity.status(HttpStatus.CREATED).body(response) : ResponseEntity.badRequest().body(response);
    }
    @PostMapping("/register/teacher")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<BaseResponse<AuthenticationResponse>> registerTeacher(@RequestBody TeacherRegisterRequest teacherRegisterRequest) {
        log.info("Registering teacher: {}", teacherRegisterRequest.getUsername());
        var response = authenticationService.registerTeacher(teacherRegisterRequest);

        return response.success() ? ResponseEntity.status(HttpStatus.CREATED).body(response) : ResponseEntity.badRequest().body(response);
    }

    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<BaseResponse<AuthenticationResponse>>login (@RequestBody AuthenticationRequest authenticationRequest, HttpServletResponse response) {
        log.info("Logging in user: {}", authenticationRequest.getUsername());
        BaseResponse<AuthenticationResponse> serviceResponse =  authenticationService.login(authenticationRequest);

        if(serviceResponse.success()){
            //Set JWT token in an HTTP-only, secure cookie
            String token = serviceResponse.data().getToken();
            response.addHeader("Set-Cookie", String.format("jwt=%s; HttpOnly; Secure; SameSite=Strict; Path=/; Max-Age=%d", token, 3600)); //valid for 1hr
        }

        //Return the rest of the response (e.g., role and userId)
        return serviceResponse.success()?ResponseEntity.ok(serviceResponse): ResponseEntity.badRequest().body(serviceResponse);
    }
}
