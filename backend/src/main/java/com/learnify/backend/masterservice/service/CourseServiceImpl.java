package com.learnify.backend.masterservice.service;

import com.learnify.backend.common.BaseResponse;
import com.learnify.backend.common.constants.*;
import com.learnify.backend.common.exceptions.CourseAlreadyExistException;
import com.learnify.backend.common.exceptions.CourseNotFoundException;
import com.learnify.backend.common.exceptions.FieldsEmptyException;
import com.learnify.backend.common.exceptions.UserNotFoundException;
import com.learnify.backend.course.dto.CourseRequestDTO;
import com.learnify.backend.course.dto.LearningMaterialRequestDTO;
import com.learnify.backend.masterservice.dao.Course;
import com.learnify.backend.masterservice.dao.LearningMaterial;
import com.learnify.backend.masterservice.dao.Teacher;
import com.learnify.backend.masterservice.repository.CourseRepository;
import com.learnify.backend.masterservice.repository.UserRepository;
import jakarta.transaction.Transactional;
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
            validateCourseRequest(course);
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
            Course existingCourse = courseRepository.findByCourseId(courseId)
                    .orElseThrow(() -> new CourseNotFoundException(courseId, null));

            //check if created teacher is the same as the provided teacher
            if (course.getTeacherId() != null && !course.getTeacherId().isEmpty()) {
                if (!existingCourse.getTeacher().getId().equals(Integer.valueOf(course.getTeacherId()))) {
                    throw new IllegalAccessException("User not allowed to update course");
                }
            }
            //update course by provided details except teacher and missing fields
            if (course.getTitle() != null && !course.getTitle().isEmpty()) {
                existingCourse.setTitle(course.getTitle());
            }
            if (course.getDescription() != null && !course.getDescription().isEmpty()) {
                existingCourse.setDescription(course.getDescription());
            }
            if (course.getPassword() != null && !course.getPassword().isEmpty()) {
                existingCourse.setPassword(passwordEncoder.encode(course.getPassword()));
            }
            if (course.getGrade() != null && !course.getGrade().isEmpty()) {
                existingCourse.setGrade(Grade.valueOf(course.getGrade()));
            }
            if (course.getStatus() != null && !course.getStatus().isEmpty()) {
                existingCourse.setStatus(CourseStatus.valueOf(course.getStatus()));
            }
            existingCourse.setUpdatedAt(LocalDateTime.now());

            courseRepository.save(existingCourse);
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
            Course existingCourse = courseRepository.findByCourseId(courseId)
                    .orElseThrow(() -> new CourseNotFoundException(courseId, null));


            //check if created teacher is the same as the provided teacher
            if (userId != null) {
                if (!existingCourse.getTeacher().getId().equals(userId)) {
                    throw new IllegalAccessException("User not allowed to delete course");
                }
            }
            //delete course
            courseRepository.delete(existingCourse);
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

    @Override
    @Transactional
    public BaseResponse<Boolean> addLearningMaterial(LearningMaterialRequestDTO learningMaterialRequestDTO) {
        try {
            // Validate input fields
            validateLearningMaterialRequest(learningMaterialRequestDTO);

            // Get existing course
            Course course = courseRepository.findByCourseId(learningMaterialRequestDTO.getCourseId())
                    .orElseThrow(() -> new CourseNotFoundException(learningMaterialRequestDTO.getCourseId(), null));

            // Check user authorization
            if (!learningMaterialRequestDTO.getUploadedBy().equals(course.getTeacher().getId())) {
                throw new IllegalAccessException("User not allowed to add learning material for this course");
            }

            // Create new LearningMaterial
            LearningMaterial newLearningMaterial = LearningMaterial.builder()
                    .fileName(learningMaterialRequestDTO.getFileName())
                    .fileUrl(learningMaterialRequestDTO.getFileUrl())
                    .fileType(learningMaterialRequestDTO.getFileType())
                    .fileSize(learningMaterialRequestDTO.getFileSize())
                    .uploadedAt(LocalDateTime.now())
                    .lastModifiedAt(LocalDateTime.now())
                    .uploadedBy(learningMaterialRequestDTO.getUploadedBy())
                    .course(course)
                    .build();

            course.getLearningMaterials().add(newLearningMaterial);
            courseRepository.save(course);

            log.info("Learning material added successfully: {}", learningMaterialRequestDTO.getFileName());
            return new BaseResponse<>(SuccessCodes.LEARNING_MATERIAL_ADDED);

        } catch (FieldsEmptyException e) {
            log.error("Missing fields for learning material: {}", e.getMessage());
            return new BaseResponse<>(ErrorCodes.COURSE_WITH_MISSING_FIELDS);
        } catch (IllegalAccessException e) {
            log.error("Unauthorized access: {}", e.getMessage());
            return new BaseResponse<>(ErrorCodes.NOT_AUTHORIZED);
        } catch (CourseNotFoundException e) {
            log.error("Course is not found: {}", e.getMessage());
            return new BaseResponse<>(ErrorCodes.COURSE_NOT_FOUND);
        } catch (Exception e) {
            log.error("Unexpected error while adding learning material: {}", e.getMessage());
            return new BaseResponse<>(ErrorCodes.UNKNOWN_ERROR);
        }
    }

    private void validateLearningMaterialRequest(LearningMaterialRequestDTO request) throws FieldsEmptyException {
        if (request.getCourseId() == null) {
            throw new FieldsEmptyException("Course ID", "Please provide course ID");
        }
        if (request.getFileName() == null || request.getFileName().isEmpty()) {
            throw new FieldsEmptyException("File Name", "File name is missing or empty");
        }
        if (request.getFileUrl() == null || request.getFileUrl().isEmpty()) {
            throw new FieldsEmptyException("File URL", "File URL is missing or empty");
        }
        if (request.getFileType() == null || request.getFileType().isEmpty()) {
            throw new FieldsEmptyException("File Type", "File type is missing or empty");
        }
        if (request.getFileSize() == null) {
            throw new FieldsEmptyException("File Size", "File size is missing");
        }
        if (request.getUploadedBy() == null) {
            throw new FieldsEmptyException("Uploaded By", "Uploader ID is missing");
        }
    }

    private void validateCourseRequest(CourseRequestDTO course) throws FieldsEmptyException {
        if (course.getTitle() == null || course.getTitle().isEmpty()) {
            throw new FieldsEmptyException("Course Title", "Course title is missing or empty");
        }
        if (course.getDescription() == null || course.getDescription().isEmpty()) {
            throw new FieldsEmptyException("Course Description", "Course description is missing or empty");
        }
        if (course.getPassword() == null || course.getPassword().isEmpty()) {
            throw new FieldsEmptyException("Course Password", "Course password is missing or empty");
        }
        if (course.getGrade() == null || course.getGrade().isEmpty()) {
            throw new FieldsEmptyException("Course Grade", "Course grade is missing or empty");
        }
        if (course.getTeacherId() == null || course.getTeacherId().isEmpty()) {
            throw new FieldsEmptyException("Teacher ID", "Teacher ID is missing or empty");
        }
    }
}
