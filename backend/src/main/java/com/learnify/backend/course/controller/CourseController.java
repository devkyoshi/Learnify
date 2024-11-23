package com.learnify.backend.course.controller;

import com.learnify.backend.common.BaseController;
import com.learnify.backend.common.BaseResponse;
import com.learnify.backend.course.dto.CourseRequestDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping("/course")
public class CourseController extends BaseController {

    @PostMapping("/create")
    public ResponseEntity<BaseResponse<Boolean>> createCourse(@RequestBody CourseRequestDTO courseRequestDTO) {
        log.info("Creating course: {}", courseRequestDTO.getTitle());
        var response = masterService.saveCourse(courseRequestDTO);
        return response.success()? ResponseEntity.ok(response): ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }

}
