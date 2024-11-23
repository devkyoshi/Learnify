package com.learnify.backend.masterservice;

import com.learnify.backend.common.BaseResponse;
import com.learnify.backend.course.dto.CourseRequestDTO;

public interface MasterService {
    //Course Services
    public BaseResponse<Boolean> saveCourse(CourseRequestDTO course);
}
