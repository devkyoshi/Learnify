package com.learnify.backend;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.learnify.backend.common.BaseResponse;
import com.learnify.backend.security.controller.AuthenticationController;
import com.learnify.backend.security.dto.AuthenticationRequest;
import com.learnify.backend.security.dto.AuthenticationResponse;
import com.learnify.backend.security.service.AuthenticationService;
import com.learnify.backend.student.dto.StudentRegistrationRequest;
import com.learnify.backend.teacher.dto.TeacherRegisterRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


public class AuthenticationTests {

    private MockMvc mockMvc;

    @Mock
    private AuthenticationService authenticationService;

    @InjectMocks
    private AuthenticationController authenticationController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(authenticationController).build();
    }

    @Test
    public void testRegisterStudent() throws Exception {
        // Prepare mock data
        StudentRegistrationRequest request = new StudentRegistrationRequest();
        request.setUsername("test");
        request.setPassword("test1234");

        // Mock the service method
        when(authenticationService.registerStudent(any(StudentRegistrationRequest.class)))
                .thenReturn(new BaseResponse<>(true,
                        new AuthenticationResponse("token", "student", "userId"), "Student registered successfully"));

        // Convert the request object to JSON
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonContent = objectMapper.writeValueAsString(request);

        // Perform the test
        mockMvc.perform(post("/user/register/student")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonContent))
                .andExpect(status().isCreated());
    }

    @Test
    public void testRegisterTeacher() throws Exception {
        // Prepare mock data
        TeacherRegisterRequest request = new TeacherRegisterRequest();
        request.setUsername("test");
        request.setPassword("test1234");

        // Mock the service method
        when(authenticationService.registerTeacher(any(TeacherRegisterRequest.class)))
                .thenReturn(new BaseResponse<>(true,
                        new AuthenticationResponse("token", "teacher", "userId"), "Teacher registered successfully"));

        // Convert the request object to JSON
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonContent = objectMapper.writeValueAsString(request);

        // Perform the test
        mockMvc.perform(post("/user/register/teacher")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonContent))
                .andExpect(status().isCreated());
    }

    @Test
    public void testLogin() throws Exception {
        // Prepare mock data
        AuthenticationRequest request = new AuthenticationRequest();
        request.setUsername("test");
        request.setPassword("test1234");

        // Mock the service method
        when(authenticationService.login(any(AuthenticationRequest.class)))
                .thenReturn(new BaseResponse<>(true,
                        new AuthenticationResponse("token", "student", "userId"), "Login successful"));

        // Convert the request object to JSON
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonContent = objectMapper.writeValueAsString(request);

        // Perform the test
        mockMvc.perform(post("/user/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonContent))
                .andExpect(status().isOk());
    }


    @Test
    public void testRegisterStudentFailure() throws Exception {
        // Prepare mock data
        StudentRegistrationRequest request = new StudentRegistrationRequest();
        request.setUsername("test");
        request.setPassword("test1234");

        // Mock the service method
        when(authenticationService.registerStudent(any(StudentRegistrationRequest.class)))
                .thenReturn(new BaseResponse<>(false,
                        null, "Username already exists"));

        // Convert the request object to JSON
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonContent = objectMapper.writeValueAsString(request);

        // Perform the test
        mockMvc.perform(post("/user/register/student")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonContent))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testRegisterTeacherFailure() throws Exception {
        // Prepare mock data
        TeacherRegisterRequest request = new TeacherRegisterRequest();
        request.setUsername("test");
        request.setPassword("test1234");

        // Mock the service method
        when(authenticationService.registerTeacher(any(TeacherRegisterRequest.class)))
                .thenReturn(new BaseResponse<>(false,
                        null, "Username already exists"));

        // Convert the request object to JSON
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonContent = objectMapper.writeValueAsString(request);

        // Perform the test
        mockMvc.perform(post("/user/register/teacher")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonContent))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testLoginFailure() throws Exception {
        // Prepare mock data
        AuthenticationRequest request = new AuthenticationRequest();
        request.setUsername("test");
        request.setPassword("test1234");

        // Mock the service method
        when(authenticationService.login(any(AuthenticationRequest.class)))
                .thenReturn(new BaseResponse<>(false,
                        null, "Invalid credentials"));

        // Convert the request object to JSON
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonContent = objectMapper.writeValueAsString(request);

        // Perform the test
        mockMvc.perform(post("/user/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonContent))
                .andExpect(status().isBadRequest());
    }
}
