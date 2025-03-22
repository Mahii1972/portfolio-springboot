package com.portfolio.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "projects")
@Data
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long id;

    private String title;
    private String year;
    private String description;
    
    @Column(columnDefinition = "text[]")
    private String[] technologies;
    
    private String imageUrl;
    private String projectUrl;
    private String githubUrl;
    private Boolean isFeatured;
    private Integer displayOrder;
    
    @JsonIgnore
    private LocalDateTime createdAt;
    
    @JsonIgnore
    private LocalDateTime updatedAt;
} 