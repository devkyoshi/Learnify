package com.learnify.backend.course.dto;

import com.learnify.backend.masterservice.dao.Course;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class LearningMaterialRequestDTO {
    private String fileName;
    private String fileUrl;
    private String fileType; // pdf, video, audio, image
    private Long fileSize; // in bytes
    private Integer uploadedBy;
    private Long courseId;

}
