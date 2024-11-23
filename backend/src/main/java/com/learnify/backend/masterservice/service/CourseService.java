package com.learnify.backend.masterservice.service;

import com.learnify.backend.common.BaseResponse;
import com.learnify.backend.course.dto.CourseRequestDTO;

public interface CourseService {
    BaseResponse<Boolean> saveCourse(CourseRequestDTO course);
}
