package com.learnify.backend.masterservice.dao;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LearningMaterial {

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
    private Integer uploadedBy;

    @ManyToOne
    @JoinColumn(name = "course_id", nullable = false)
    private Course course;

    @PreUpdate
    public void preUpdate() {
        lastModifiedAt = LocalDateTime.now();
    }

}
