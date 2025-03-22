package com.yourpackage.util;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Component
public class PostgreSQLCertificateInitializer implements InitializingBean {

    @Value("${cert.file.path:src/main/resources/root.crt}")
    private String certFilePath;

    @Override
    public void afterPropertiesSet() throws Exception {
        // Try to read from env first
        String certContent = System.getenv("CERT");
        if (certContent != null && !certContent.isEmpty()) {
            writeCertToFile(certContent);
        } else {
            // Otherwise, copy from resources
            Path sourcePath = Paths.get(certFilePath);
            if (Files.exists(sourcePath)) {
                String userHome = System.getProperty("user.home");
                Path pgDir = Paths.get(userHome, "AppData", "Roaming", "postgresql");
                Files.createDirectories(pgDir);
                Path targetPath = pgDir.resolve("root.crt");
                Files.copy(sourcePath, targetPath, StandardCopyOption.REPLACE_EXISTING);
            }
        }
    }

    private void writeCertToFile(String cert) throws IOException {
        String userHome = System.getProperty("user.home");
        Path pgDir = Paths.get(userHome, "AppData", "Roaming", "postgresql");
        
        // Create directory if it doesn't exist
        Files.createDirectories(pgDir);
        
        // Write certificate to file
        Path certFile = pgDir.resolve("root.crt");
        try (FileWriter writer = new FileWriter(certFile.toFile())) {
            writer.write(cert);
        }
    }
} 