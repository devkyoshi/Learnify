package com.learnify.backend.masterservice.service;

import com.learnify.backend.common.BaseResponse;
import com.learnify.backend.course.dto.CourseRequestDTO;
import com.learnify.backend.course.dto.LearningMaterialRequestDTO;

public interface CourseService {
    BaseResponse<Boolean> saveCourse(CourseRequestDTO course);
    BaseResponse<Boolean> updateCourse(Long courseId, CourseRequestDTO course);
    BaseResponse<Boolean> deleteCourse(Long courseId , Integer userId);
    BaseResponse<Boolean> addLearningMaterial(LearningMaterialRequestDTO learningMaterialRequestDTO);
    BaseResponse<Boolean> deleteLearningMaterial(Long learningMaterialId, Integer userID);
}
