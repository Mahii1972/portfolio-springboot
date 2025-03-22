package com.portfolio.repository;

import com.portfolio.model.Education;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EducationRepository extends JpaRepository<Education, Long> {
    @Query("SELECT e FROM Education e ORDER BY e.displayOrder")
    List<Education> findAllOrderByDisplayOrder();
} 