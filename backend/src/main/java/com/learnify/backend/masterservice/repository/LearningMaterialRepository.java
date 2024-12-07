package com.learnify.backend.masterservice.repository;

import com.learnify.backend.masterservice.dao.LearningMaterial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LearningMaterialRepository extends JpaRepository<LearningMaterial, Long> {

    Optional<LearningMaterial> findLearningMaterialByMaterialId(Long materialId);
}
