package com.portfolio.service;

import com.portfolio.model.*;
import com.portfolio.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.sql.Array;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class PortfolioService {
    
    @Autowired
    private PortfolioRepository portfolioRepository;
    
    @Autowired
    private PersonalInfoRepository personalInfoRepository;
    
    @Autowired
    private EducationRepository educationRepository;
    
    @Autowired
    private ExperienceRepository experienceRepository;
    
    @Autowired
    private ProjectRepository projectRepository;
    
    @Autowired
    private SkillCategoryRepository skillCategoryRepository;
    
    @Autowired
    private InterestRepository interestRepository;
    
    @Autowired
    private LanguageRepository languageRepository;
    
    @Autowired
    private SkillRepository skillRepository;
    
    @Autowired
    private JdbcTemplate jdbcTemplate;
    
    public List<Portfolio> getAllPortfolios() {
        return portfolioRepository.findAll();
    }
    
    public Map<String, Object> getFullPortfolio() {
        Map<String, Object> response = new HashMap<>();
        
        // Get personal info
        List<PersonalInfo> personalInfo = personalInfoRepository.findAll();
        if (!personalInfo.isEmpty()) {
            response.put("personalInfo", personalInfo.get(0));
        }
        
        // Get education ordered by display_order
        response.put("education", educationRepository.findAllOrderByDisplayOrder());
        
        // Get experience ordered by display_order
        response.put("experience", experienceRepository.findAllOrderByDisplayOrder());
        
        // Get projects ordered by display_order
        response.put("projects", projectRepository.findAllOrderByDisplayOrder());
        
        // Get skills with categories - manually populate them
        List<SkillCategory> categories = skillCategoryRepository.findAllOrderByDisplayOrder();
        List<Skill> allSkills = skillRepository.findAll();
        
        for (SkillCategory category : categories) {
            // Filter skills that belong to this category
            List<Skill> categorySkills = allSkills.stream()
                .filter(skill -> skill.getCategoryId() != null && 
                         skill.getCategoryId().equals(category.getId()))
                .collect(Collectors.toList());
            
            category.setSkills(categorySkills);
        }
        
        response.put("skills", categories);
        
        // Get interests
        response.put("interests", interestRepository.findAll());
        
        // Get languages
        response.put("languages", languageRepository.findAll());
        
        return response;
    }
    
    public Map<String, Object> getPortfolioDataRaw() {
        Map<String, Object> result = new HashMap<>();
        
        // Personal info
        List<Map<String, Object>> personalInfo = jdbcTemplate.queryForList(
            "SELECT full_name, title, email, location, linkedin_url, phone, " +
            "birth_date, github_url, about_me, declaration, avatar_url " +
            "FROM personal_info LIMIT 1");
        
        if (!personalInfo.isEmpty()) {
            result.put("personalInfo", personalInfo.get(0));
        }
        
        // Education
        result.put("education", jdbcTemplate.queryForList(
            "SELECT institution, degree, field_of_study, start_date, end_date, " +
            "grade, location FROM education ORDER BY display_order"));
        
        // Experience
        result.put("experience", jdbcTemplate.queryForList(
            "SELECT company, title, location, start_date, end_date, is_current, " +
            "description FROM experience ORDER BY display_order"));
        
        // Projects - FIX: Handle PostgreSQL array properly
        List<Map<String, Object>> projectsRaw = jdbcTemplate.queryForList(
            "SELECT title, year, description, technologies, image_url, project_url, " +
            "github_url, is_featured FROM projects ORDER BY display_order");
        
        // Process PostgreSQL array objects to avoid serialization issues
        List<Map<String, Object>> projects = projectsRaw.stream().map(project -> {
            Map<String, Object> processedProject = new HashMap<>(project);
            
            // Convert PgArray to Java array/list
            if (project.get("technologies") instanceof Array) {
                try {
                    Array techArray = (Array) project.get("technologies");
                    String[] technologies = (String[]) techArray.getArray();
                    processedProject.put("technologies", technologies);
                } catch (SQLException e) {
                    // If there's an error, set an empty array
                    processedProject.put("technologies", new String[0]);
                }
            } else if (project.get("technologies") == null) {
                processedProject.put("technologies", new String[0]);
            }
            
            return processedProject;
        }).collect(Collectors.toList());
        
        result.put("projects", projects);
        
        // Skills with categories
        List<Map<String, Object>> categories = jdbcTemplate.queryForList(
            "SELECT id, name FROM skill_categories ORDER BY display_order");
        
        for (Map<String, Object> category : categories) {
            Long categoryId = ((Number) category.get("id")).longValue();
            List<Map<String, Object>> skills = jdbcTemplate.queryForList(
                "SELECT name, level FROM skills WHERE category_id = ? ORDER BY display_order", 
                categoryId);
            category.put("skills", skills);
        }
        result.put("skillCategories", categories);
        
        // Interests
        result.put("interests", jdbcTemplate.queryForList("SELECT name FROM interests"));
        
        // Languages
        result.put("languages", jdbcTemplate.queryForList(
            "SELECT name, proficiency_level, certification FROM languages"));
        
        return result;
    }
}