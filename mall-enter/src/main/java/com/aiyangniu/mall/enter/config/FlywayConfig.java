package com.aiyangniu.mall.enter.config;

import org.flywaydb.core.Flyway;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.flyway.FlywayMigrationInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 * Flyway相关配置
 *
 * @author lzq
 * @date 2023/06/25
 */
@Configuration
@ConditionalOnProperty(prefix = "spring.flyway", name = "enabled")
public class FlywayConfig {

    @Value("${spring.flyway.locations}")
    String locations;

    @Bean
    public Flyway flyway(DataSource dataSource) {
        Flyway flyway = new Flyway();
        flyway.setBaselineOnMigrate(true);
        flyway.setDataSource(dataSource);
        flyway.setLocations(locations);
        return flyway;
    }

    @Bean
    public FlywayMigrationInitializer flywayInitializer(Flyway flyway) {
        return new FlywayMigrationInitializer(flyway, null);
    }
}