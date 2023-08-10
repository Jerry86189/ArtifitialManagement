package com.jerry86189.artifitialmanagement.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * ClassName: FileStorageConfig
 * Description: TODO
 * date: 2023/06/10 21:10
 *
 * @author Jerry
 * @version 1.0
 * @since JDK 1.8
 */
@Configuration
@ConfigurationProperties(prefix = "app.file")
@Data
public class FileStorageConfig {

    private String uploadDir;
}
