package com.portfolio.repository;

import com.portfolio.model.SkillCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SkillCategoryRepository extends JpaRepository<SkillCategory, Long> {
    @Query("SELECT sc FROM SkillCategory sc ORDER BY sc.displayOrder")
    List<SkillCategory> findAllOrderByDisplayOrder();
} 