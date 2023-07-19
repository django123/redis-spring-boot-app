package com.django.redis_spring_boot_app.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@Configuration
@EntityScan("com.django.redis_spring_boot_app.domain")
@EnableJpaRepositories("com.django.redis_spring_boot_app.repos")
@EnableTransactionManagement
public class DomainConfig {
}
