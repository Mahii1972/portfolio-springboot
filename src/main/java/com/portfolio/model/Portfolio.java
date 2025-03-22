package com.portfolio.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "portfolio")
@Data
public class Portfolio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    // Add your portfolio fields here
    // Example fields (modify as needed):
    private String name;
    private String description;
}