package com.portfolio.repository;

import com.portfolio.model.Experience;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ExperienceRepository extends JpaRepository<Experience, Long> {
    @Query("SELECT e FROM Experience e ORDER BY e.displayOrder")
    List<Experience> findAllOrderByDisplayOrder();
} 