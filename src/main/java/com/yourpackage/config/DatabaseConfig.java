package com.yourpackage.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import javax.sql.DataSource;

@Configuration
public class DatabaseConfig {

    @Value("${spring.datasource.url}")
    private String jdbcUrl;
    
    @Value("${spring.datasource.username:#{null}}")
    private String username;
    
    @Value("${spring.datasource.password:#{null}}")
    private String password;

    @Bean
    public DataSource dataSource() {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl(jdbcUrl);
        
        if (username != null) {
            config.setUsername(username);
        }
        
        if (password != null) {
            config.setPassword(password);
        }
        
        config.setMaximumPoolSize(5);
        config.setConnectionTimeout(30000);
        
        return new HikariDataSource(config);
    }
} 