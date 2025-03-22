package com.portfolio.repository;

import com.portfolio.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProjectRepository extends JpaRepository<Project, Long> {
    @Query("SELECT p FROM Project p ORDER BY p.displayOrder")
    List<Project> findAllOrderByDisplayOrder();
} 