package com.portfolio.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "skill_categories")
@Data
public class SkillCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long id;

    private String name;
    private Integer displayOrder;
    
    @Transient // This field won't be persisted
    private List<Skill> skills = new ArrayList<>();
    
    // We'll populate this list manually in the service
} 