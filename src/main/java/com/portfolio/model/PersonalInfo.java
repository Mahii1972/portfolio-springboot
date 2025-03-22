package com.portfolio.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "personal_info")
@Data
public class PersonalInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long id;
    
    private String fullName;
    private String title;
    private String email;
    private String location;
    private String linkedinUrl;
    private String phone;
    private LocalDate birthDate;
    private String githubUrl;
    private String aboutMe;
    private String declaration;
    private String avatarUrl;
    
    @JsonIgnore
    private LocalDateTime createdAt;
    
    @JsonIgnore
    private LocalDateTime updatedAt;
} 