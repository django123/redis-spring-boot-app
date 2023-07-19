package com.django.redis_spring_boot_app.repos;

import com.django.redis_spring_boot_app.domain.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserEntityRepository extends JpaRepository<UserEntity, Long> {
}
