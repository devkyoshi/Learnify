package com.learnify.backend.course.dto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CourseRequestDTO {
    private String title;
    private String description;
    private String password;
    private String grade;
    private String teacherId;
    private String status;
}
