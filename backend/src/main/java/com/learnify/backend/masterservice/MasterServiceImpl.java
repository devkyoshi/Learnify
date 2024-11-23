package com.learnify.backend.masterservice;

import com.learnify.backend.common.BaseResponse;
import com.learnify.backend.course.dto.CourseRequestDTO;
import com.learnify.backend.masterservice.dao.Course;
import com.learnify.backend.masterservice.service.CourseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class MasterServiceImpl implements MasterService {

    CourseService courseService;

    @Autowired
    public MasterServiceImpl(CourseService courseService) {
        this.courseService = courseService;
    }

    @Override
    public BaseResponse<Boolean> saveCourse(CourseRequestDTO courseRequestDTO) {
        return courseService.saveCourse(courseRequestDTO);
    }
}
