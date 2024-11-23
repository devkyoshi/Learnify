package com.learnify.backend.course.controller;

import com.learnify.backend.common.BaseController;
import com.learnify.backend.common.BaseResponse;
import com.learnify.backend.course.dto.CourseRequestDTO;
import com.learnify.backend.course.dto.LearningMaterialRequestDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/update/{courseId}")
    public ResponseEntity<BaseResponse<Boolean>> updateCourse(@PathVariable Long courseId, @RequestBody CourseRequestDTO courseRequestDTO) {
        log.info("Updating course: {}", courseRequestDTO.getTitle() + " with courseId: " + courseId);
        var response = masterService.updateCourse(courseId, courseRequestDTO);
        return response.success()? ResponseEntity.ok(response): ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @GetMapping("/delete/{userId}/{courseId}")
    public ResponseEntity<BaseResponse<Boolean>> deleteCourse(@PathVariable Long courseId , @PathVariable Integer userId) {
        log.info("Deleting course with courseId: {}", courseId);
        var response = masterService.deleteCourse(courseId , userId);
        return response.success()? ResponseEntity.ok(response): ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @PostMapping("/file/add")
    public ResponseEntity<BaseResponse<Boolean>> addLearningMaterial(@RequestBody LearningMaterialRequestDTO learningMaterialRequestDTO) {
        log.info("Adding a learning material to course: {}", learningMaterialRequestDTO.getCourseId());
        var response = masterService.addLearningMaterial(learningMaterialRequestDTO);
        return response.success()? ResponseEntity.ok(response): ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }

}
