package com.learnify.backend.masterservice.dao;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
public class LearningMaterials {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long materialId;

    @Column(nullable = false)
    private String fileName;

    @Column(nullable = false)
    private String fileUrl;

    private String fileType; // pdf, video, audio, image

    private Long fileSize; // in bytes

    private LocalDateTime uploadedAt = LocalDateTime.now();

    private LocalDateTime lastModifiedAt = LocalDateTime.now();

    @Column(nullable = false)
    private Long uploadedBy;

    @ManyToOne
    @JoinColumn(name = "course_id", nullable = false)
    private Course course;

    @PreUpdate
    public void preUpdate() {
        lastModifiedAt = LocalDateTime.now();
    }

}
