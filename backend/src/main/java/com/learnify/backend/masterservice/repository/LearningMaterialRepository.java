package com.learnify.backend.masterservice.repository;

import com.learnify.backend.masterservice.dao.LearningMaterial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LearningMaterialRepository extends JpaRepository<LearningMaterial, Long> {
}
