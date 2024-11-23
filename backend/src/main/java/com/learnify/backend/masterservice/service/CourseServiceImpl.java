package com.learnify.backend.masterservice.service;

import com.learnify.backend.common.BaseResponse;
import com.learnify.backend.common.constants.*;
import com.learnify.backend.common.exceptions.CourseAlreadyExistException;
import com.learnify.backend.common.exceptions.FieldsEmptyException;
import com.learnify.backend.common.exceptions.UserNotFoundException;
import com.learnify.backend.course.dto.CourseRequestDTO;
import com.learnify.backend.masterservice.dao.Course;
import com.learnify.backend.masterservice.dao.Teacher;
import com.learnify.backend.masterservice.repository.CourseRepository;
import com.learnify.backend.masterservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Slf4j
@Service
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService{
    private final CourseRepository courseRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public BaseResponse<Boolean> saveCourse(CourseRequestDTO course) {
        try {
            //check if course is null
            if (course.getTitle() == null || course.getTitle().isEmpty() ||
                    course.getTeacherId() == null || course.getTeacherId().isEmpty() ||
                    course.getDescription() == null || course.getDescription().isEmpty() ||
                    course.getPassword() == null || course.getPassword().isEmpty() ||
                    course.getGrade() == null || course.getGrade().isEmpty() )  {

                throw new FieldsEmptyException(ErrorCodes.COURSE_WITH_MISSING_FIELDS.getMessage());
            }

            Integer teacherId = Integer.valueOf(course.getTeacherId());

            //get teacher
            if (!userRepository.existsByIdAndRole(teacherId, Role.TEACHER)) {
                throw new UserNotFoundException("Teacher", course.getTeacherId());
            }

            //check if course exists
            if (courseRepository.existsByTeacherIdAndTitle(teacherId, course.getTitle())) {
                throw new CourseAlreadyExistException(course.getTitle());
            }

            //save course
            Course newCourse = Course.builder()
                    .title(course.getTitle())
                    .description(course.getDescription())
                    .password(passwordEncoder.encode(course.getPassword()))
                    .grade(Grade.valueOf(course.getGrade()))
                    .teacher(Teacher.builder().id(Integer.valueOf(course.getTeacherId())).build())
                    .status(course.getStatus() != null ? CourseStatus.valueOf(course.getStatus()) : CourseStatus.DRAFT)
                    .createdAt(LocalDateTime.now())
                    .updatedAt(LocalDateTime.now())
                    .build();

            courseRepository.save(newCourse);
            log.info("Course created successfully: {}", course.getTitle());
            return new BaseResponse<>(SuccessCodes.COURSE_CREATED);


        }catch (UserNotFoundException e) {
            log.error("Teacher not found: {}", course.getTeacherId());
            return new BaseResponse<>(ErrorCodes.TEACHER_NOT_FOUND);
        } catch (FieldsEmptyException e) {
            log.error("Course details are missing or incomplete: {}", course.getTitle());
            return new BaseResponse<>(ErrorCodes.COURSE_WITH_MISSING_FIELDS);
        } catch (CourseAlreadyExistException e) {
            log.error("Course already exists: {}", course.getTitle());
            return new BaseResponse<>(ErrorCodes.COURSE_ALREADY_EXISTS);
        }catch (Exception e) {
            log.error("Error while saving course: {}", e.getMessage());
           return new BaseResponse<>(ErrorCodes.UNKNOWN_ERROR);
        }
    }
}
