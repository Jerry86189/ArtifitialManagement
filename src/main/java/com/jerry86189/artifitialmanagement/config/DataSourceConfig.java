package com.jerry86189.artifitialmanagement.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.sql.SQLException;

/**
 * ClassName: DataSourceConfig
 * Description: TODO
 * date: 2023/06/09 23:34
 *
 * @author Jerry
 * @version 1.0
 * @since JDK 1.8
 */
@Configuration
public class DataSourceConfig {

    @Bean
    public DataSource dataSource(
            @Value("${spring.datasource.url}") String dbUrl,
            @Value("${spring.datasource.username}") String dbUsername,
            @Value("${spring.datasource.password}") String dbPassword
    ) throws SQLException {
        DruidDataSource dataSource = new DruidDataSource();

        dataSource.setUrl(dbUrl);
        dataSource.setUsername(dbUsername);
        dataSource.setPassword(dbPassword);

        dataSource.setInitialSize(5);
        dataSource.setMinIdle(5);
        dataSource.setMaxActive(20);

        dataSource.setFilters("stat,wall,log4j");

        return dataSource;
    }
}
