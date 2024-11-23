package com.learnify.backend.masterservice.repository;


import com.learnify.backend.masterservice.dao.Course;
import com.learnify.backend.masterservice.dao.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
    boolean existsByTeacherIdAndTitle(Integer teacherId, String title);
    Optional<Course> findByCourseId(Long courseId);
}
