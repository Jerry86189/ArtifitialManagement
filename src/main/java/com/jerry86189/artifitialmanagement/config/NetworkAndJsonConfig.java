package com.jerry86189.artifitialmanagement.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.OkHttpClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * ClassName: NetworkAndJsonConfig
 * Description: TODO
 * date: 2023/06/09 23:50
 *
 * @author Jerry
 * @version 1.0
 * @since JDK 1.8
 */
@Configuration
public class NetworkAndJsonConfig {
    @Bean
    public OkHttpClient okHttpClient() {
        return new OkHttpClient();
    }

    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper();
    }
}
