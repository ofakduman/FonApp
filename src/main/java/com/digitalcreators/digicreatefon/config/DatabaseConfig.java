package com.digitalcreators.digicreatefon.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages = "com.digitalcreators.digicreatefon.repository")
@EntityScan(basePackages = "com.digitalcreators.digicreatefon.model")

public class DatabaseConfig {

}
