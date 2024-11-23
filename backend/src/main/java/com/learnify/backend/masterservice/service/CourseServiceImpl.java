package com.learnify.backend.masterservice.service;

import com.learnify.backend.common.BaseResponse;
import com.learnify.backend.common.constants.*;
import com.learnify.backend.common.exceptions.CourseAlreadyExistException;
import com.learnify.backend.common.exceptions.CourseNotFoundException;
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
import java.util.Optional;

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

    @Override
    public BaseResponse<Boolean> updateCourse(Long courseId, CourseRequestDTO course) {

        try {
            //check if course is null
            if(courseId == null) {
                throw new FieldsEmptyException("Course ID", "Please provide course ID");
            }
            //get existing course
            Optional<Course> existingCourse = courseRepository.findByCourseId(courseId);

            if (existingCourse.isEmpty()) {
                throw new CourseNotFoundException(courseId, course.getTitle());
            }
            //check if created teacher is the same as the provided teacher
            if (course.getTeacherId() != null && !course.getTeacherId().isEmpty()) {
                if (!existingCourse.get().getTeacher().getId().equals(Integer.valueOf(course.getTeacherId()))) {
                    throw new IllegalAccessException("User not allowed to update course");
                }
            }
            //update course by provided details except teacher and missing fields
            if (course.getTitle() != null && !course.getTitle().isEmpty()) {
                existingCourse.get().setTitle(course.getTitle());
            }
            if (course.getDescription() != null && !course.getDescription().isEmpty()) {
                existingCourse.get().setDescription(course.getDescription());
            }
            if (course.getPassword() != null && !course.getPassword().isEmpty()) {
                existingCourse.get().setPassword(passwordEncoder.encode(course.getPassword()));
            }
            if (course.getGrade() != null && !course.getGrade().isEmpty()) {
                existingCourse.get().setGrade(Grade.valueOf(course.getGrade()));
            }
            if (course.getStatus() != null && !course.getStatus().isEmpty()) {
                existingCourse.get().setStatus(CourseStatus.valueOf(course.getStatus()));
            }
            existingCourse.get().setUpdatedAt(LocalDateTime.now());

            courseRepository.save(existingCourse.get());
            log.info("Course updated successfully: {}", courseId);
            return new BaseResponse<>(SuccessCodes.COURSE_UPDATED);
        }catch (CourseNotFoundException e){
            log.error("Course not found: {}", courseId);
            return new BaseResponse<>(ErrorCodes.COURSE_NOT_FOUND);
        } catch (IllegalAccessException e) {
            log.error("User not allowed to update course: {}", courseId);
            return new BaseResponse<>(ErrorCodes.NOT_AUTHORIZED);
        } catch (FieldsEmptyException e) {
            log.error("Course details for updating are missing or incomplete: {}", e.getMessage());
            return new BaseResponse<>(ErrorCodes.COURSE_WITH_MISSING_FIELDS);
        }catch (Exception e) {
            log.error("Error while updating course: {}", e.getMessage());
            return new BaseResponse<>(ErrorCodes.UNKNOWN_ERROR);
        }
    }

    @Override
    public BaseResponse<Boolean> deleteCourse(Long courseId, Integer userId) {
        try {
            //check if course is null
            if(courseId == null) {
                throw new FieldsEmptyException("Course ID", "Please provide course ID");
            }
            //get existing course
            Optional<Course> existingCourse = courseRepository.findByCourseId(courseId);

            if (existingCourse.isEmpty()) {
                throw new CourseNotFoundException(courseId, null);
            }

            //check if created teacher is the same as the provided teacher
            if (userId != null) {
                if (!existingCourse.get().getTeacher().getId().equals(userId)) {
                    throw new IllegalAccessException("User not allowed to delete course");
                }
            }
            //delete course
            courseRepository.delete(existingCourse.get());
            log.info("Course deleted successfully: {}", courseId);
            return new BaseResponse<>(SuccessCodes.COURSE_DELETED);
        }catch (CourseNotFoundException e){
            log.error("Course with CourseID: {}", courseId + " not found");
            return new BaseResponse<>(ErrorCodes.COURSE_NOT_FOUND);
        } catch (FieldsEmptyException e) {
            log.error("Course ID is missing: {}", e.getMessage());
            return new BaseResponse<>(ErrorCodes.COURSE_WITH_MISSING_FIELDS);
        } catch (IllegalAccessException e) {
            log.error("User not allowed to delete course: {}", courseId);
            return new BaseResponse<>(ErrorCodes.NOT_AUTHORIZED);
        }
    }
}
