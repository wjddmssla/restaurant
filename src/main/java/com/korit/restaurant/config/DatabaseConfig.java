package com.korit.restaurant.config;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import javax.xml.crypto.Data;

@Configuration
public class DatabaseConfig {
    @Bean
    public DataSource dataSource() {
        DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
        dataSourceBuilder.driverClassName("com.mysql.cj.jdbc.Driver");
        dataSourceBuilder.username("admin");
        dataSourceBuilder.password("dkssud1234");
        dataSourceBuilder.url("jdbc:mysql:/localhost:3306/restaurant");
        return dataSourceBuilder.build();
    }
}
